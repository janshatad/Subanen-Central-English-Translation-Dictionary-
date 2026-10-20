#!/data/data/com.termux/files/usr/bin/bash
set -e

PROJECT="/storage/emulated/0/Download/SubanenDictionary lite released (1)/SubanenDictionary lite released"
SDK="/data/data/com.itsaky.androidide/files/home/android-sdk"
TOOLS="$SDK/build-tools/36.0.0"
KEYSTORE="$HOME/.subanen-dictionary/release-key.jks"
ALIAS="subanenrelease"

cd "$PROJECT"

UNSIGNED="app/build/outputs/apk/release/app-release-unsigned.apk"
ALIGNED="app/build/outputs/apk/release/app-release-aligned.apk"
SIGNED="app/build/outputs/apk/release/SubanenDictionary-release.apk"

if [ ! -f "$KEYSTORE" ]; then
    echo "ERROR: Keystore not found: $KEYSTORE"
    exit 1
fi

for TOOL in "$TOOLS/zipalign" "$TOOLS/apksigner"; do
    if [ ! -x "$TOOL" ]; then
        echo "ERROR: Required tool not found: $TOOL"
        exit 1
    fi
done

echo "Building release APK..."
bash ./gradlew --no-daemon assembleRelease

if [ ! -f "$UNSIGNED" ]; then
    echo "ERROR: Unsigned APK was not generated."
    exit 1
fi

echo "Aligning APK..."
"$TOOLS/zipalign" -f -v 4 "$UNSIGNED" "$ALIGNED"

read -s -p "Enter keystore password: " KS_PASS
echo
read -s -p "Enter key password (press Enter if same as keystore): " KEY_PASS
echo
[ -n "$KEY_PASS" ] || KEY_PASS="$KS_PASS"

export KS_PASS KEY_PASS
echo "Signing release APK..."
"$TOOLS/apksigner" sign \
    --ks "$KEYSTORE" \
    --ks-key-alias "$ALIAS" \
    --ks-pass env:KS_PASS \
    --key-pass env:KEY_PASS \
    --out "$SIGNED" \
    "$ALIGNED"

unset KS_PASS KEY_PASS

echo "Verifying APK signature..."
"$TOOLS/apksigner" verify --verbose "$SIGNED"

echo
echo "Release APK created:"
ls -lh "$SIGNED"
echo
echo "Opening Android package installer..."
termux-open "$SIGNED"
