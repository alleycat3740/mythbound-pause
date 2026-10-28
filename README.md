# Mythbound illustrated pause menu (Fabric 1.21.1)

**Source project — not a compiled JAR.** This project has NOT been compiled or tested in Minecraft. The GitHub workflow compiles it in GitHub Actions, where Fabric dependencies can be downloaded.

Artwork: the approved full-resolution menu image, not a tiny atlas. Discord: https://discord.gg/E4JVh5YUYc.

## Build online
1. Create a GitHub repository and upload the *contents* of this folder (including `.github/workflows/build.yml`). GitHub's web upload may hide dotfolders; check that `.github/workflows/build.yml` is included.
2. Open **Actions → Build Mythbound Fabric mod → Run workflow**.
3. If green, download **mythbound-pause-jar** artifact; unzip it and use the non-`sources` `.jar` in a *copy* of your CurseForge profile.
4. Keep Fabric API and Berk installed. Keep your working main-menu resource pack. Disable earlier broken pause-menu resource packs.

## Known caveats
- The approved artwork contains baked-in text "Player Reporting", but in singleplayer the right-hand third-row click opens LAN (to support e4mc). The graphic label will not dynamically change.
- ModMenu and Berk settings are accessed by reflection; these actions require in-game validation.
- Menu placement scales the entire art to screen. Smaller GUI scales may make text hard to read.
- The JAR is **not yet verified**; any failed workflow or in-game issue needs debugging, not blind installation.
