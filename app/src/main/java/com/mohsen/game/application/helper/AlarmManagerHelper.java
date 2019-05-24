package com.mohsen.game.application.helper;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.mohsen.game.application.persian.calendar.PersianCalendar;
import com.mohsen.game.application.receivers.DailyReminderAlarmReceiver;

import java.util.Calendar;

public class AlarmManagerHelper {

    private static int CHEQUE_AUTO_NOTIFIER_ALARM = Integer.MAX_VALUE - 2;
    private static int TRANSACTION_AUTO_NOTIFIER_ALARM = Integer.MAX_VALUE - 1;

    public static void createDailySubmitTransactionReminderAlarm(Context context) {

        Long interval = (long) (long) DateTimeHelper.DAY_IN_MILLIS;

        Long alarmHour = 12L;//GlobalParams.getSettingSubmitTransactionReminder();

        if (alarmHour < 12L) {
            cancelDailySubmitTransactionReminderAlarm(context);
            return;
        }

        Intent alarmIntent = new Intent(context, DailyReminderAlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, TRANSACTION_AUTO_NOTIFIER_ALARM, alarmIntent, 0);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if (manager != null) {

            PersianCalendar currentCalendar = new PersianCalendar();
            currentCalendar.setTimeInMillis(System.currentTimeMillis());
            currentCalendar.setTime(alarmHour.intValue(), 0, 0, 0, Calendar.PM);


            if (System.currentTimeMillis() <= currentCalendar.getTimeInMillis()) {
                manager.setRepeating(AlarmManager.RTC_WAKEUP, currentCalendar.getTimeInMillis() + 1000, interval, pendingIntent);

            } else {

                currentCalendar.addPersianDate(Calendar.DAY_OF_MONTH, 1);
                currentCalendar.setTime(alarmHour.intValue(), 0, 0, 0, Calendar.PM);
                manager.setRepeating(AlarmManager.RTC_WAKEUP, currentCalendar.getTimeInMillis() + 1000, interval, pendingIntent);
            }


        }
    }

    public static void cancelDailySubmitTransactionReminderAlarm(Context context) {

        Intent alarmIntent = new Intent(context, DailyReminderAlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, TRANSACTION_AUTO_NOTIFIER_ALARM, alarmIntent, 0);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if (manager != null) {
            manager.cancel(pendingIntent);

        }
    }

}
