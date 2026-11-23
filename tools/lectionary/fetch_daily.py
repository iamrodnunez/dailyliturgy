import json, os, sys, time, datetime, subprocess
sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
from parse_daily import parse
UA = "Mozilla/5.0 (Macintosh; Intel Mac OS X 14_0) AppleWebKit/537.36 Chrome/130 Safari/537.36"
start, end, out = sys.argv[1], sys.argv[2], sys.argv[3]
data = json.load(open(out)) if os.path.exists(out) else {}
d = datetime.date.fromisoformat(start); e = datetime.date.fromisoformat(end)
while d <= e:
    k = d.isoformat()
    if k not in data or not data[k]["readings"]:
        for attempt in range(3):
            h = subprocess.run(["curl", "-s", "-A", UA, f"https://www.dailylectio.net/{k}/x"], capture_output=True, text=True).stdout
            r = parse(h)
            if r["readings"]: break
            time.sleep(2)
        data[k] = r
        if len(data) % 25 == 0:
            json.dump(data, open(out, "w")); print(k, len(data), flush=True)
        time.sleep(0.4)
    d += datetime.timedelta(days=1)
json.dump(data, open(out, "w")); print("done", len(data))
