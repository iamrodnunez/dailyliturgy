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

Requires **JDK 17** (the wrapper's Gradle 8.9 does not support newer JDKs) and the Android
SDK.

```bash
JAVA_HOME=/path/to/jdk-17 ./gradlew :app:assembleDebug
# → app/build/outputs/apk/debug/app-debug.apk
```

A prebuilt debug APK is included in [`dist/`](dist/) and attached to the latest
[release](../../releases).

## Stack

Gradle 8.9 · AGP 8.5.2 · Kotlin 2.0.21 · Compose · minSdk 26 · compileSdk 34.
