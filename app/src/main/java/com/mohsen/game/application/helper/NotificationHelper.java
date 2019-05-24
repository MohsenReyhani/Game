package com.mohsen.game.application.helper;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.widget.RemoteViews;

import com.mohsen.game.R;
import com.mohsen.game.application.AppInitializeHelper;
import com.mohsen.game.application.MainApplication;
import com.mohsen.game.application.persian.calendar.PersianCalendar;
import com.mohsen.game.database.DAO.AppVariableDAO;
import com.mohsen.game.database.model.AppVariable;
import com.mohsen.game.ui.activities.Activity_SplashScreen;
import com.mohsen.game.ui.activities.MainActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class NotificationHelper {
	
	private static final long[] vibratePattern = new long[]{0L, 200L, 500L};
	private static final String ledColor = "#00ff00";
	
	private static final String strNotificationMetaSequencePattern = "UniqueNotificationId";
	
	public static int getUniqueNotificationIdInCreate(String notificationId) {
		List<Integer> ids = new ArrayList<>();
		List<AppVariable> variables;
		
		variables = AppVariableDAO.selectAllByDomainId(AppVariablesHelper.KEY_DOMAIN_NOTIFICATION);
		if (variables != null)
			for (AppVariable appVariable : variables) {
				String id = appVariable.getAppVariableId().replace(strNotificationMetaSequencePattern, "");
				ids.add(ConversionHelper.StringToInteger(id));
			}
		
		int Id = ids.get(ids.size() - 1) + 1;
		
		AppVariablesHelper.putValue(
				strNotificationMetaSequencePattern + Id,
				notificationId, AppVariablesHelper.KEY_DOMAIN_NOTIFICATION);
		
		return Id;
	}
	
	public static void cancelNotification(Context context, int Id) {
		NotificationManager nMgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		nMgr.cancel(Id);
	}
	
	public static void createNotification(Context context, Object data, boolean isLaunching, String notificationId) {
		
		if (data == null) return;
		
		String localLanguage = Locale.getDefault().getDisplayLanguage();
		
		int layout = 0;//R.layout.notification_custom_with_buttons;
		
		RemoteViews customNotificationView =
				new RemoteViews(MainApplication.getAppContext().getPackageName(), layout);
		
		int bitmapSize = (int) MainApplication.getAppContext().getResources().getDimension(R.dimen.medium_size);

        /*customNotificationView.setTextViewText(R.id.txtTitleMessage, wallet.getWalletName());
        customNotificationView.setTextViewText(R.id.txtDescription, context.getString(R.string.tap_to_view_wallet));
        customNotificationView.setImageViewBitmap(R.id.imgIcon, UiHelper.getBitmapFromIcon(context, wallet.getImageId(), 50));


        Intent walletMainViewIntent = new Intent(context, Activity_SplashScreen.class);
        PendingIntent walletMainViewPendingIntent = PendingIntent.getActivity(context, KeyHelper.KEY_NOTIFICATION_ACTIVITY_WALLET_MAIN, walletMainViewIntent, 0);
        customNotificationView.setOnClickPendingIntent(R.id.txtTitleMessage, walletMainViewPendingIntent);
        customNotificationView.setOnClickPendingIntent(R.id.txtDescription, walletMainViewPendingIntent);
        customNotificationView.setOnClickPendingIntent(R.id.imgIcon, walletMainViewPendingIntent);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(Activity_SplashScreen.class);
        Intent settingViewIntent = new Intent(context, MainActivity.class);
        settingViewIntent.putExtra(MainActivity.KEY_DEFAULT_MENU_ITEM_ON_OPEN, "vBox_nav_li_Setting");
        stackBuilder.addNextIntent(settingViewIntent);
        PendingIntent settingViewPendingIntent = stackBuilder.getPendingIntent(getUniqueNotificationId(String.valueOf(wallet.getWalletId())), PendingIntent.FLAG_CANCEL_CURRENT);
        customNotificationView.setOnClickPendingIntent(R.id.imgSetting, settingViewPendingIntent);

        stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(Activity_SplashScreen.class);
        stackBuilder.addNextIntent(new Intent(context, MainActivity.class));
        Intent newTransactionIntent = new Intent(context, ActivityCU_Transaction.class);
        newTransactionIntent.putExtra(KeyHelper.KEY_OPEN_FROM_NOTIFICATION, true);
        newTransactionIntent.putExtra(KeyHelper.KEY_ACTIVITY_CU_STATE, ActivityCU_Base.ACTIVITY_STATE_NEW_MODE);
        stackBuilder.addNextIntent(newTransactionIntent);
        PendingIntent newTransactionPendingIntent = stackBuilder.getPendingIntent(getUniqueNotificationId(String.valueOf(wallet.getWalletId())), PendingIntent.FLAG_CANCEL_CURRENT);
        customNotificationView.setOnClickPendingIntent(R.id.imgAddTransaction, newTransactionPendingIntent);


        int smallIconId = R.drawable.ic_nt_logo;*/
		
		
		NotificationCompat.Builder mBuilder =
				new NotificationCompat.Builder(context)
						.setContent(customNotificationView)
						.setOngoing(true)
						//.setSmallIcon(smallIconId)
						.setContentTitle(context.getString(R.string.app_name))
						.setContentText(context.getString(R.string.app_name))
						.setAutoCancel(false);
		
		if (isLaunching)
			mBuilder.setTicker(context.getString(R.string.app_name));
		
		//mBuilder.setContentIntent(walletMainViewPendingIntent);
		NotificationManager mNotificationManager =
				(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = mBuilder.build();
		mNotificationManager.notify(getUniqueNotificationIdInCreate(notificationId), notification);
		
		
	}
	
	public static void checkAndCreateDailySubmitTransaction(Context context) {
		
		AppInitializeHelper.initialApp();
		
		//if (accTransaction != null) {
		PersianCalendar fromDate, toDate;
		fromDate = new PersianCalendar();
		toDate = new PersianCalendar();
		//fromDate.setTimeInMillis(accTransaction.get(0).getRegGeDate());
		toDate.setTime(11, 59, 0, 0, Calendar.PM);
		
		int diff = DateTimeHelper.getDiffDaysBetweenTwoDate(fromDate.getTimeInMillis(), toDate.getTimeInMillis());
		
		if (diff > 4) {
			//createDailyNotification(context, R.string.notification_touch_to_submit_transaction_week);
		} else if (diff > 2) {
			//createDailyNotification(context, R.string.notification_touch_to_submit_transaction_for_days);
		} else if (diff > 1) {
			//createDailyNotification(context, R.string.notification_touch_to_submit_transaction);
		} else {
			return;
		}
		
		//createDailyNotification(context, R.string.notification_touch_to_submit_transaction);
	
	
}
	
	private static void createDailyNotification(Context context, int stringResourceId) {
		NotificationCompat.Builder mBuilder =
				new NotificationCompat.Builder(context)
						//.setSmallIcon(R.drawable.ic_logo)
						.setContentTitle(context.getString(R.string.app_name))
						.setContentText(context.getString(stringResourceId))
						.setLights(Color.parseColor(ledColor), 700, 2000)
						.setVibrate(vibratePattern)
						//.setSound(Uri.parse((new StringBuilder()).append("android.resource://").append(context.getPackageName()).append("/").append(R.raw.notification).toString()))
						.setAutoCancel(true);
		
		
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
		stackBuilder.addParentStack(Activity_SplashScreen.class);
		stackBuilder.addNextIntent(new Intent(context, MainActivity.class));
		//Intent newTransactionIntent = new Intent(context, ActivityCU_Transaction.class);
		//newTransactionIntent.putExtra(KeyHelper.KEY_ACTIVITY_CU_STATE, ActivityCU_Base.ACTIVITY_STATE_NEW_MODE);
		//stackBuilder.addNextIntent(newTransactionIntent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(KeyHelper.KEY_NOTIFICATION_ACTIVITY_NEW_TRANSACTION, PendingIntent.FLAG_CANCEL_CURRENT);
		
		mBuilder.setContentIntent(resultPendingIntent);
		
		NotificationManager mNotificationManager =
				(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(KeyHelper.KEY_NOTIFICATION_ACTIVITY_NEW_TRANSACTION, mBuilder.build());
	}
	
	public static void createReminderNotification(Context context, String notificationId) {
		
		AppInitializeHelper.initialApp();
		
		Object data = null;
		
		if (data == null) return;
		
		NotificationCompat.Builder mBuilder =
				new NotificationCompat.Builder(context)
						//.setSmallIcon(R.drawable.ic_logo)
						//.setContentTitle(context.getString(R.string.notice))
						//.setContentText(context.getString(R.string.notification_transaction_reminder) + " " + data)
						.setLights(Color.parseColor(ledColor), 700, 2000)
						.setVibrate(vibratePattern)
						//.setSound(Uri.parse((new StringBuilder()).append("android.resource://").append(context.getPackageName()).append("/").append(R.raw.notification).toString()))
						.setAutoCancel(true);
		
		
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
		stackBuilder.addParentStack(Activity_SplashScreen.class);
		stackBuilder.addNextIntent(new Intent(context, MainActivity.class));
		//Intent editTransactionIntent = new Intent(context, ActivityCU_Transaction.class);
		//editTransactionIntent.putExtra(KeyHelper.KEY_ENTITY, accTransaction);
		//editTransactionIntent.putExtra(KeyHelper.KEY_OPEN_FROM_NOTIFICATION, true);
		//editTransactionIntent.putExtra(KeyHelper.KEY_ACTIVITY_CU_STATE, ActivityCU_Base.ACTIVITY_STATE_EDIT);
		//stackBuilder.addNextIntent(editTransactionIntent);
		
		
		//PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(notificationId, PendingIntent.FLAG_CANCEL_CURRENT);
		
		/*mBuilder.setContentIntent(resultPendingIntent);
		
		
		NotificationManager mNotificationManager =
				(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(notificationId, mBuilder.build());
		*/
		
	}
	
	
	public static void createNotification(String title, String body) {
		Context context = MainApplication.getAppContext();
		NotificationCompat.Builder mBuilder =
				new NotificationCompat.Builder(context)
						//.setSmallIcon(R.drawable.ic_stat_onesignal_default)
						.setColor(ContextCompat.getColor(context, R.color.green))
						.setContentTitle(title)
						.setContentText(body)
						.setAutoCancel(true);
		
		
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
		stackBuilder.addParentStack(Activity_SplashScreen.class);
		stackBuilder.addNextIntent(new Intent(context, MainActivity.class));
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT);
		mBuilder.setContentIntent(resultPendingIntent);
		
		Notification notification = mBuilder.build();
		
		NotificationManager mNotificationManager =
				(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(0, notification);
		
	}
}
