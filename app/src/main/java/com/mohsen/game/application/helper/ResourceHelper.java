package com.mohsen.game.application.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;

public class ResourceHelper {
/*
    public static float getTextSizeInSp(Context context, int dimenResId) {
        int sp = (int)context.getResources().getDimension(dimenResId);
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, sp , context.getResources().getDisplayMetrics());
    }*/
	
	public static Drawable getDrawableByResName(Context context, String resourceName) {
		int nameResourceID = context.getResources().getIdentifier(resourceName, "drawable", context.getApplicationInfo().packageName);
		if (nameResourceID == 0) {
			throw new IllegalArgumentException("No resource string found with name " + resourceName);
		} else {
			return ContextCompat.getDrawable(context, nameResourceID);
		}
	}
	
	public static int getStringResourceIdByName(Context context, String resourceName) {
		
		return getResourceIdByResName(context, "string", resourceName);
		
	}
	
	public static int getDrawableResourceIdByResName(Context context, String resourceName) {
		
		int nameResourceID = getResourceIdByResName(context, "drawable", resourceName);
		if (nameResourceID == 0) {
			return android.R.color.transparent;
		} else {
			return nameResourceID;
		}
	}
	
	public static int getColorResourceIdByResName(Context context, String resourceName) {
		
		int nameResourceID = getResourceIdByResName(context, "color", resourceName);
		if (nameResourceID == 0) {
			return android.R.color.transparent;
		} else {
			return nameResourceID;
		}
	}
	
	public static int getColorByResName(Context context, String resourceName) {
		
		int nameResourceID = getColorResourceIdByResName(context, resourceName);
		if (nameResourceID == 0) {
			return ContextCompat.getColor(context, android.R.color.transparent);
		} else {
			return ContextCompat.getColor(context, nameResourceID);
		}
	}
	
	public static int getResourceIdByResName(Context context, String resourceType, String resourceName) {
		if (resourceName.contains(""))
			
			if (resourceName == null || resourceName.equals(""))
				return android.R.color.transparent;
		
		int nameResourceID = context.getResources().getIdentifier(resourceName, resourceType, context.getApplicationInfo().packageName);
		
		return nameResourceID;
		
	}
	
	public static String getResNameByResId(Context context, int resId) {
		String result = context.getResources().getResourceEntryName(resId);
		return result;
		
	}
	
	public static Drawable getContactImage(Context context, String image_uri) {
		if (image_uri == null || image_uri.trim().equals("")) {
			return ResourceHelper.getDrawableByResName(context, "ic_sign_contact");
		} else {
			try {
				Bitmap bmp = MediaStore.Images.Media.getBitmap(context.getContentResolver(), Uri.parse(image_uri));
				return new BitmapDrawable(context.getResources(), bmp);
			} catch (Exception e) {
				return ResourceHelper.getDrawableByResName(context, "ic_sign_contact");
			}
			
			
		}
	}
	
	
}
