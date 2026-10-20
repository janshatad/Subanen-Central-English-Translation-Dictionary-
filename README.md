# Subanen Central English Dictionary

An Android dictionary app for looking up Subanen words and their English meanings. The dictionary data is bundled with the app, so lookups work offline.

## Features

- Search dictionary entries by Subanen word or English synonym.
- Open an entry to read its meaning and the longer notes/examples included in the dictionary data.
- Save entries to bookmarks and revisit recently opened entries in search history.
- Listen to entry text with Android text-to-speech, copy it to the clipboard, or share it with another app.
- View a word of the day and configure its reminder frequency through the app.

For English-to-Subanen searches, the app's Search Help recommends adding a semicolon after the English keyword (for example, `house;`).

## Project details

- **Platform:** Android
- **Language:** Java
- **Application ID:** `com.bon.subanen.dictionary`
- **Minimum Android version:** Android 6.0 (API 23)
- **Target Android version:** API 33
- **Dictionary data:** `app/src/main/assets/directory1.txt` (JSON array of entries containing `word` and `synonym` fields)
- **Main source:** `app/src/main/java/com/bon/subanen/dictionary/`

Bookmarks, search history, and reminder preferences are stored locally on the device using Android shared preferences.

## Build and run

1. Open this project directory in Android Studio and allow Gradle to sync.
2. Select the `app` run configuration and launch it on an Android device or emulator running Android 6.0 or later.

You can also build a debug APK from the project root with the Gradle wrapper:

```bash
./gradlew assembleDebug
```

On Windows PowerShell:

```powershell
.\gradlew.bat assembleDebug
```

The debug APK is written to `app/build/outputs/apk/debug/`.

## Repository layout

```text
app/
  src/main/
    assets/directory1.txt       Bundled dictionary data
    java/.../dictionary/        Android activities and supporting classes
    res/                        Layouts, icons, and app resources
gradle/                         Gradle wrapper files
```
