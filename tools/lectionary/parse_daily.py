import re, html, sys
GOSPELS = ("Matthew", "Mark", "Luke", "John")
def role(c):
    if c.startswith("Psalm"): return "Psalm"
    if c.split(" ")[0] in GOSPELS: return "Gospel"
    return "Reading"
def pane(h, pid):
    i = h.find("id='%s'" % pid)
    if i < 0: return None
    j = h.find("class='tab-pane", i + 10)
    seg = h[i: j if j > 0 else len(h)]
    cits = [re.sub(r"\s+", " ", html.unescape(re.sub(r"<[^>]+>", "", x))).replace("\xa0", " ").strip()
            for x in re.findall(r"<h2>(.*?)</h2>", seg, re.S)]
    return [c for c in cits if c]
def parse(h):
    comp = pane(h, "complementary")
    if comp:
        return {"track": "complementary", "readings": [{"role": role(c), "citation": c} for c in comp]}
    semi = pane(h, "semicontinuous") or []
    return {"track": "single", "readings": [{"role": role(c), "citation": c} for c in semi]}
