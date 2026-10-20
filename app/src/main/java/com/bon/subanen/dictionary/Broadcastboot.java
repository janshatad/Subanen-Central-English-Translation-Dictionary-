package com.bon.subanen.dictionary;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.app.AlarmManager ;
import android.app.DatePickerDialog ;
import android.app.Notification ;
import android.app.PendingIntent ;
import android.content.Context ;
import android.content.Intent ;
import android.os.Bundle ;
//import android.support.v4.app.NotificationCompat ;
//import android.support.v7.app.AppCompatActivity ;
import android.view.View ;
import android.widget.Button ;
import android.widget.DatePicker ;
import java.text.SimpleDateFormat ;
import java.util.Calendar ;
import java.util.Date ;
import java.util.Locale ;




public class Broadcastboot extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        
     //  NotificationPublisher notificationHelper = new NotificationPublisher();
    
     // notificationHelper.notifyAll();
       
        
    //    NotificationHelper notificationHelper = new NotificationHelper(context);
     //   notificationHelper.createNotification();
       if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
           Intent serviceIntent = new Intent(context, BackgroundProvider.class);
          context.startService(serviceIntent);
     //   }

 //   public class NotificationReceiver extends BroadcastReceiver {

     //   @Override
     //   public void onReceive(Context context, Intent intent) {

            
}
        
    }

    }    
        



    





