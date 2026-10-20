package com.bon.subanen.dictionary;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;

import static android.content.Context.ALARM_SERVICE;

/** Maintains one notification alarm at a time. */
public class NotificationHelper {
    public static final int ALARM_TYPE_RTC = 100;
    public static final int ALARM_TYPE_ELAPSED = 101;
    private static final String PREFS_NAME = "files";
    private static final String PREF_FREQUENCY = "daywordsettings";

    private static PendingIntent getPendingIntent(Context context, int requestCode) {
        Intent intent = new Intent(context, AlarmReceiver.class);
        int flags = PendingIntent.FLAG_UPDATE_CURRENT;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            flags |= PendingIntent.FLAG_IMMUTABLE;
        }
        return PendingIntent.getBroadcast(context, requestCode, intent, flags);
    }

    // Compatibility method: cancel legacy wall-clock alarms to avoid duplicates.
    public static void scheduleRepeatingRTCNotification(Context context, String hour, String min) {
        AlarmManager manager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        PendingIntent pi = getPendingIntent(context, ALARM_TYPE_RTC);
        if (manager != null) manager.cancel(pi);
        pi.cancel();
    }

    private static void scheduleInterval(Context context, long intervalMillis) {
        AlarmManager manager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        if (manager == null) return;
        PendingIntent pi = getPendingIntent(context, ALARM_TYPE_ELAPSED);
        manager.cancel(pi);
        long firstTrigger = android.os.SystemClock.elapsedRealtime() + intervalMillis;
        manager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                firstTrigger, intervalMillis, pi);
    }

    public static void scheduleRepeatingElapsedNotification1day(Context c) {
        scheduleInterval(c, AlarmManager.INTERVAL_DAY);
    }
    public static void scheduleRepeatingElapsedNotification12(Context c) {
        scheduleInterval(c, AlarmManager.INTERVAL_HALF_DAY);
    }
    public static void scheduleRepeatingElapsedNotificationhour(Context c) {
        scheduleInterval(c, AlarmManager.INTERVAL_HOUR);
    }
    public static void scheduleRepeatingElapsedNotification30minutes(Context c) {
        scheduleInterval(c, AlarmManager.INTERVAL_HALF_HOUR);
    }
    public static void scheduleRepeatingElapsedNotification15minutes(Context c) {
        scheduleInterval(c, 15L * 60L * 1000L);
    }

    public static void scheduleSavedFrequency(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String setting = prefs.getString(PREF_FREQUENCY, "");
        AlarmManager manager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        if (manager != null) {
            manager.cancel(getPendingIntent(context, ALARM_TYPE_RTC));
            manager.cancel(getPendingIntent(context, ALARM_TYPE_ELAPSED));
        }
        if ("twice".equals(setting)) {
            scheduleRepeatingElapsedNotification12(context);
        } else if ("1hour".equals(setting)) {
            scheduleRepeatingElapsedNotificationhour(context);
        } else if ("30mins".equals(setting)) {
            scheduleRepeatingElapsedNotification30minutes(context);
        } else if ("15mins".equals(setting)) {
            scheduleRepeatingElapsedNotification15minutes(context);
        } else {
            scheduleRepeatingElapsedNotification1day(context);
        }
    }

    public static void cancelAlarmRTC(Context context) {
        AlarmManager manager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        PendingIntent pi = getPendingIntent(context, ALARM_TYPE_RTC);
        if (manager != null) manager.cancel(pi);
        pi.cancel();
    }
    public static void cancelAlarmElapsed(Context context) {
        AlarmManager manager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        PendingIntent pi = getPendingIntent(context, ALARM_TYPE_ELAPSED);
        if (manager != null) manager.cancel(pi);
        pi.cancel();
    }
    // Compatibility overloads for older call sites.
    public static void cancelAlarmRTC() {}
    public static void cancelAlarmElapsed() {}

    public static NotificationManager getNotificationManager(Context context) {
        return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }
    public static void enableBootReceiver(Context context) {
        ComponentName receiver = new ComponentName(context, AlarmBootReceiver.class);
        context.getPackageManager().setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }
    public static void disableBootReceiver(Context context) {
        ComponentName receiver = new ComponentName(context, AlarmBootReceiver.class);
        context.getPackageManager().setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }
}