package com.bon.subanen.dictionary;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import androidx.core.app.NotificationCompat;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ptyagi on 4/17/17.
 */

/**
 * AlarmReceiver handles the broadcast message and generates Notification
 */
public class AlarmReceiver extends BroadcastReceiver {
    private SharedPreferences database;
    private String allText = "";
    private double random = 0;
    private String    word_of_day ="";
    private ArrayList<HashMap<String, Object>> searchmap = new ArrayList<>();
    private Timer _timer = new Timer();
    private TimerTask timer;
 
    @Override
    public void onReceive(Context context, Intent intent) {
        database =context. getSharedPreferences("files", Activity.MODE_PRIVATE);
        
        try {

            java.io.InputStream stream = context. getAssets().open("directory1.txt");


            java.io.BufferedReader bfr = new java.io.BufferedReader(new java.io.InputStreamReader(stream));

            String nextline = "";
            //String allText ="";

            while ( (nextline = bfr.readLine()) != null) {
                allText = allText + nextline + "\n";
            }

            //textview1.setText(allText);

            searchmap = new Gson().fromJson(allText, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());

        } catch (java.io.IOException e){

            //showMessage(e.toString());

        }
        
        //Get notification manager to manage/send notifications


        //Intent to invoke app when click on notification.
        //In this sample, we want to start/launch this sample app when user clicks on notification
        Intent intentToRepeat = new Intent(context, DaywordActivity.class);
        //set flag to restart/relaunch the app
        intentToRepeat.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //Pending intent to handle launch of Activity in intent above
        
int flags = PendingIntent.FLAG_UPDATE_CURRENT;

if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    flags |= PendingIntent.FLAG_IMMUTABLE;
}

PendingIntent pendingIntent = PendingIntent.getActivity(
    context,
    NotificationHelper.ALARM_TYPE_RTC,
    intentToRepeat,
    flags
);

        //Build notification
        Notification repeatedNotification = buildLocalNotification(context, pendingIntent).build();

        //Send local notification
        NotificationHelper.getNotificationManager(context).notify(NotificationHelper.ALARM_TYPE_RTC, repeatedNotification);
    }

    public NotificationCompat.Builder buildLocalNotification(Context context, PendingIntent pendingIntent) {
      
        /*NotificationCompat.Builder builder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(android.R.drawable.arrow_up_float)
                .setContentTitle("Morning Notification")
                .setAutoCancel(true);
                 builder.build();
                 
       */

        int notifyId = 0;               
                 
        searchmap = new Gson().fromJson(allText, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
        if (searchmap == null || searchmap.isEmpty()) {
    return new NotificationCompat.Builder(context, "0")
        .setSmallIcon(android.R.drawable.ic_popup_reminder)
        .setContentTitle("Word Notification")
        .setContentText("Open the app to view the word of the day.");
}

random = SketchwareUtil.getRandom(
    0, searchmap.size() - 1);

        word_of_day = searchmap.get((int)random).get("word").toString().concat("\n".concat(searchmap.get((int)random).get("synonym").toString()));

        database.edit().putString("daywordview", searchmap.get((int)random).get("word").toString()).commit();
        database.edit().putString("daysynonymview", searchmap.get((int)random).get("synonym").toString()).commit();
        database.edit().putString("notifyword", word_of_day).commit();




        
        
        
        
        androidx.core.app.NotificationCompat.BigTextStyle bpStyle = new androidx.core.app.NotificationCompat.BigTextStyle(); 


      //  Intent notificationIntents = new Intent(context, DaywordActivity.class); 
      //  PendingIntent pendingIntentnotification = PendingIntent.getActivity(context, 0, notificationIntents, 0); 
        word_of_day = database.getString("notifyword", "");

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
          long removem = 1500;

        androidx.core.app.NotificationCompat.Builder mBuilder = new androidx.core.app.NotificationCompat.Builder(context) 


            .setSmallIcon(android.R.drawable.ic_popup_reminder) 
            .setContentTitle("Word Notification") 
            .setContentText(word_of_day)
            //.setOngoing(true)
            .setStyle(bpStyle)

            .setDefaults(Notification.DEFAULT_ALL)
           .setPriority(NotificationCompat.PRIORITY_HIGH)   // heads-up
            .setSound(alarmSound)
             
            .setAutoCancel(true);
            
             
        mBuilder.setContentIntent(pendingIntent); 
        
        
        NotificationManager notificationManager = (NotificationManager)context. getSystemService(context. NOTIFICATION_SERVICE); 
        
        
            

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { 
            String channelId2 = "0"; 
            String channelName2 = "Notification Service"; 

            NotificationChannel channel = new NotificationChannel(channelId2, channelName2, NotificationManager.IMPORTANCE_HIGH); 

            channel.enableLights(true); 
            channel.setLightColor(Color.RED); 
            channel.setShowBadge(true); 
            channel.enableVibration(true); 

            mBuilder.setChannelId(channelId2); 

       
            
            if (notificationManager != null) { 
               notificationManager.createNotificationChannel(channel); 
            } 
        }  else { 
            mBuilder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE); 
            
            } 

        

        
        
        
        if (Build.VERSION.SDK_INT <=    Build.VERSION_CODES.O)
        {
            notificationManager.cancel(notifyId);
        }
        else {
            
            
        }
        
        
        
        
        Handler h = new Handler();
        long delayInMilliseconds = 5000;
        h.postDelayed(new Runnable() {
                public void run() {
            //  notificationManager.cancel(notifyId);
                }
            }, delayInMilliseconds);
        
        
       return mBuilder ;
        
    }
    
    
}
