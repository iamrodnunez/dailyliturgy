"""Merge freshly scraped days into app/src/main/assets/daily_readings.json.

Usage:  python3 merge_daily.py new_days.json
(new_days.json comes from:  python3 fetch_daily.py START END new_days.json)
"""
import datetime, json, os, sys

ASSET = os.path.join(os.path.dirname(__file__), "../../app/src/main/assets/daily_readings.json")

def tag_canticles(rs):
    """Canticles (Magnificat, Benedictus, Isaiah 12, ...) sometimes stand in the psalm slot."""
    for r in rs:
        if r["citation"].startswith("Canticle:") or r["citation"] in ("Luke 1:68-79", "Isaiah 12:2-6"):
            r["role"] = "Canticle"
    if not any(r["role"] in ("Psalm", "Canticle") for r in rs):
        if rs and (len(rs) == 3 or rs[0]["citation"].startswith(("Luke 1:", "Isaiah 12:2"))):
            rs[0]["role"] = "Canticle"

f = json.load(open(ASSET))
new = json.load(open(sys.argv[1]))
empty = [k for k, v in new.items() if not v["readings"]]
if empty:
    sys.exit(f"refusing to merge: {len(empty)} empty days, e.g. {empty[:5]}")
for v in new.values():
    tag_canticles(v["readings"])
f["days"].update(new)
days = sorted(f["days"])
first, last = (datetime.date.fromisoformat(days[i]) for i in (0, -1))
if len(days) != (last - first).days + 1:
    sys.exit("refusing to merge: coverage has gaps")
f["days"] = {k: f["days"][k] for k in days}
f["meta"]["coverageEnd"] = days[-1]
f["meta"]["generated"] = datetime.date.today().isoformat()
json.dump(f, open(ASSET, "w"), ensure_ascii=False, separators=(",", ":"))
print("coverage", days[0], "→", days[-1], f"({len(days)} days)")
