# OnePlus Ace 3 (astonc)

Device tree for the **OnePlus Ace 3** — used to build Infinity-X 17.

| | |
|---|---|
| SoC | Qualcomm Snapdragon 8 Gen 2 (SM8550) |
| Model | PJE110 (**China only**) |
| Codename | `astonc` |
| Firmware base | PJE110_16.0.5.1002 (CN01) |
| Branch | `17` |

Branch **`17`** = the Android 17 build tree (this is what the ROM builds against).
The plain LineageOS-24 counterpart of each repo is available upstream.

> Same hardware as the 12R (`aston`), different firmware/partition layout. Never cross-flash.

Blobs: [gitlab](https://gitlab.com/EXlipsecore/proprietary_vendor_oneplus_astonc)

```bash
lunch infinity_astonc-user && m bacon -j$(nproc --all)
```
