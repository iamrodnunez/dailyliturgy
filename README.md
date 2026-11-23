# Comfort Cross

A native Android (Kotlin + Jetpack Compose) daily‑liturgy app. It takes its name and
motif from a small handheld olive‑wood **comfort cross** — meant to be held quietly in
prayer.

## Features

- **Today** — the day's own reading from the Revised Common Lectionary *Daily* Readings
  (complementary track in the Season after Pentecost; the single daily track otherwise),
  with the liturgical season and cycle year.
- **Liturgies** — pick one to pray on its own page:
  - **Morning Prayer** and **Evening Prayer** — the historic Daily Office (opening,
    confession, invitatory, psalm, reading, creed, the Lord's Prayer, suffrages, a collect,
    a benediction), with the psalm and reading drawn from today's daily lectionary.
  - **A Liturgy for Morning Prayer** — after the Rev. Brian Zahnd, offered freely for use.
- **Readings** — today's appointed readings, linking out to the NRSVue on Bible Gateway.
- **Prayers** — historical prayers of the saints (Augustine, Patrick, Benedict, Francis,
  Aquinas, Ignatius, Wesley, and others).

## Sources & licensing of content

- Scripture **citations** come from the Revised Common Lectionary (Sunday readings via the
  Vanderbilt Divinity Library; daily readings via dailyLectio.net). Only the citation
  references are bundled — the scripture text itself opens on Bible Gateway.
- All fixed liturgical texts (creeds, the Lord's Prayer, BCP confession / suffrages /
  collects, the canticle Phos Hilaron, KJV sentences) and the historical prayers are in
  their traditional **public‑domain** wording.

## Build

Requires **JDK 17** (Gradle 8.14 / AGP 8.13 do not run on JDK 25) and the Android SDK
(platform 36).

```bash
JAVA_HOME=/path/to/jdk-17 ./gradlew :app:assembleDebug
# → app/build/outputs/apk/debug/app-debug.apk
```

A prebuilt debug APK is included in [`dist/`](dist/) and attached to the latest
[release](../../releases).

## Stack

Gradle 8.14.3 · AGP 8.13.2 · Kotlin 2.2.21 · Compose BOM 2025.12.01 · minSdk 26 ·
compileSdk/targetSdk 36.

## Data coverage & upkeep

The app works fully offline; readings are bundled assets with a fixed date range.

| Asset | Covers | Source |
|---|---|---|
| `daily_readings.json` | 2026‑07‑23 → **2030‑11‑30** | dailyLectio.net |
| `lectionary.json` (Sunday context) | 2023‑01‑01 → **2028‑11‑26** | Vanderbilt PDFs |

Past those dates the app still runs: Today shows a note and links to the readings online,
and the "this week's Sunday" card is hidden. To extend the daily readings:

```bash
cd tools/lectionary
python3 fetch_daily.py 2030-12-01 2031-11-29 new_days.json   # resumable, ~0.4 s/day
python3 merge_daily.py new_days.json
```

Vanderbilt had not yet published Year A 2028‑2029 as of 2026‑11; add it when it appears.

**Before a Google Play submission** (planned ~2028): Play then requires a recent
targetSdk (expect API 37–38), which means moving to AGP 9 / Gradle 9 (and with it a newer
JDK). Keep `release.jks` + `keystore.properties` backed up somewhere safe — they are
gitignored, and losing them means installed copies can never be updated.
