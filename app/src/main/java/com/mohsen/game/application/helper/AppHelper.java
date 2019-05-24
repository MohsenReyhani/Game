package com.mohsen.game.application.helper;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;

import com.mohsen.game.R;
import com.mohsen.game.application.AppInitializeHelper;
import com.mohsen.game.application.GlobalParams;
import com.mohsen.game.application.GlobalSettings;
import com.mohsen.game.application.MainApplication;
import com.mohsen.game.database.AppDatabaseManager;
import com.mohsen.game.ui.activities.MainActivity;
import com.mohsen.game.ui.helper.SnackBarHelper;

import java.io.File;
import java.io.OutputStream;
import java.util.Random;

public class AppHelper {
	
	public static LayoutInflater getLayoutInflater() {
		LayoutInflater LayoutInflater =
				(LayoutInflater) MainApplication.getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (LayoutInflater == null) {
			throw new AssertionError("LayoutInflater not found.");
		}
		return LayoutInflater;
	}
	
	public static void shareApp(Activity context) {
		try {
			Intent i = new Intent(Intent.ACTION_SEND);
			i.setType("text/plain");
			//i.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name_full));
			//String description = context.getString(R.string.app_share_description);
			//description += "\n\n" + context.getString(R.string.app_share_link);
			//i.putExtra(Intent.EXTRA_TEXT, description);
			context.startActivityForResult(Intent.createChooser(i, context.getString(R.string.choose)), KeyHelper.REQUEST_SHARE_APP);
		} catch (Exception e) { //e.toString();
			L.e(e.getMessage());
		}
	}
	
	
	public static void share(Activity context, String msg, Bitmap image) {
		try {
			Intent share = new Intent(Intent.ACTION_SEND);
			
			ContentValues values = new ContentValues();
			values.put(MediaStore.Images.Media.TITLE, "title");
			values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
			
			final Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
			
			OutputStream outstream = context.getContentResolver().openOutputStream(uri);
			image.compress(Bitmap.CompressFormat.JPEG, 100, outstream);
			outstream.close();
			
			share.setAction(Intent.ACTION_SEND);
			share.putExtra(Intent.EXTRA_TEXT, msg);
			share.putExtra(Intent.EXTRA_STREAM, uri);
			share.setType("image/*");
			share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
			
			context.startActivity(share);
		} catch (Exception e) {
			SnackBarHelper.showSnackOnUiThread(context.getWindow().getDecorView().findViewById(android.R.id.content),
					SnackBarHelper.SnackState.Error,
					e.getMessage());
		}
	}
	
	public static Bitmap getBitmapFromView(View view) {
		//Define a bitmap with the same size as the view
		Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
		//Bind a canvas to it
		Canvas canvas = new Canvas(returnedBitmap);
		//Get the view's background
		Drawable bgDrawable = view.getBackground();
		if (bgDrawable != null)
			//has background drawable, then draw it on the canvas
			bgDrawable.draw(canvas);
		else
			//does not have background drawable, then draw white background on the canvas
			canvas.drawColor(Color.WHITE);
		// draw the view on the canvas
		view.draw(canvas);
		//return the bitmap
		return returnedBitmap;
	}
	
	
	public static void updateWidget(Context context) {
		
		//TODO Write widget
		/*AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
		ComponentName thisWidget = new ComponentName(context, WidgetReceiver.class);
		int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
		
		Intent intent = new Intent(context, WidgetReceiver.class);
		intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
		context.sendBroadcast(intent);*/
		
	}
	
	public static Object isNull(Object value, Object defaultReturnValue) {
		if (value == null)
			return defaultReturnValue;
		else
			return value;
		
	}
	
	public static int randInt(int min, int max) {
		
		// Usually this can be a field rather than a method variable
		Random rand = new Random();
		
		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;
		
		return randomNum;
	}
	
	public static void RecreateDatabaseAndRestartApplication(Activity context) {
		AppDatabaseManager.getInstance().dropAllTables(context);
		AppDatabaseManager.getInstance().createAllTables(context);
		//notifyAppWidgetViewDataChanged(context);
		
		restartApplication(context);
	}
	
	public static void restartApplication(Activity context) {
		
		Class First_Activity = MainActivity.class;
		
		AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		mgr.set(
				AlarmManager.RTC,
				System.currentTimeMillis() + 1000,
				PendingIntent.getActivity(context, 0, new Intent(context, First_Activity), 0)
		);
		
		
		
		MainActivity activity_main = (MainActivity) MainApplication.getAppContext();
		if (activity_main.getActivity_MainContext() != null)
			activity_main.getActivity_MainContext().finish();
		
		context.finish();
		
		quitApplication();
	}
	
	public static void quitApplication() {
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(0);
	}
	
	public static void recreateDatabaseAndInitialData() {
		AppDatabaseManager.getInstance().dropAllTables(MainApplication.getAppContext());
		AppDatabaseManager.getInstance().createAllTables(MainApplication.getAppContext());
		GlobalParams.setIsInitialDataInserted(false);
		AppInitializeHelper.initialApp();
	}
	
	
	public static boolean deleteDir(File dir) {
		if (dir != null && dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		
		return dir.delete();
	}
	
	public static File getImageDirectoryPath() {
		return ContextCompat.getExternalFilesDirs(MainApplication.getAppContext(), Environment.DIRECTORY_PICTURES)[0];
	}
	
	public static void notifyAppWidgetViewDataChanged(Activity context) {
		Intent initialUpdateIntent = new Intent(
				AppWidgetManager.ACTION_APPWIDGET_UPDATE);
		initialUpdateIntent
				.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
		context.sendBroadcast(initialUpdateIntent);
	}
	
	public static String getArbaeenFolderPath() {
		return Environment.getExternalStorageDirectory() + "//" + GlobalSettings.getMainFolderName() + "//";
	}
	
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivityManager
				= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	
	public static boolean isPackageExist(Context context, String targetPackage) {
		if (context == null) return false;
		PackageManager pm = context.getPackageManager();
		try {
			PackageInfo info = pm.getPackageInfo(targetPackage, PackageManager.GET_META_DATA);
		} catch (PackageManager.NameNotFoundException e) {
			return false;
		}
		return true;
	}
	
}
