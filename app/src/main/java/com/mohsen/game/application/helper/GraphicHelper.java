package com.mohsen.game.application.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.text.TextPaint;
import android.util.TypedValue;


public class GraphicHelper {
	
	public static Bitmap changeBitmapColor(Bitmap sourceBitmap, int color) {
		
		Bitmap resultBitmap = Bitmap.createBitmap(sourceBitmap, 0, 0,
				sourceBitmap.getWidth() - 1, sourceBitmap.getHeight() - 1);
		Paint p = new Paint();
		ColorFilter filter = new LightingColorFilter(color, 1);
		p.setColorFilter(filter);
		
		Canvas canvas = new Canvas(resultBitmap);
		canvas.drawBitmap(resultBitmap, 0, 0, p);
		
		return resultBitmap;
	}
	
	public static Bitmap getFontBitmap(Context context, String text, final Typeface typeface, float fontSizeSP, int color) {
		int fontSizePX = convertDiptoPix(context, fontSizeSP);
		int pad = (fontSizePX / 9);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setTypeface(typeface);
		paint.setColor(color);
		paint.setTextSize(fontSizePX);
		
		int textWidth = (int) (paint.measureText(text) + pad * 2);
		int height = (int) (fontSizePX / 0.75);
		Bitmap bitmap = Bitmap.createBitmap(textWidth, height, Bitmap.Config.ARGB_4444);
		Canvas canvas = new Canvas(bitmap);
		float xOriginal = pad;
		canvas.drawText(text, xOriginal, fontSizePX, paint);
		return bitmap;
	}
	
	public static int convertDiptoPix(Context context, float dip) {
		int value = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
		return value;
	}
	
	public static Bitmap buildBitmapString(final String text, final Typeface typeface, final float textSizePixels, final int textColour) {
		final TextPaint textPaint = new TextPaint();
		textPaint.setTypeface(typeface);
		textPaint.setTextSize(textSizePixels);
		textPaint.setAntiAlias(true);
		textPaint.setSubpixelText(true);
		textPaint.setColor(textColour);
		textPaint.setTextAlign(Paint.Align.LEFT);
		Bitmap myBitmap = Bitmap.createBitmap((int) textPaint.measureText(text), (int) textSizePixels, Bitmap.Config.ARGB_8888);
		Canvas myCanvas = new Canvas(myBitmap);
		myCanvas.drawText(text, 0, myBitmap.getHeight(), textPaint);
		return myBitmap;
	}
	
	public static Drawable changeDrawableColor(Drawable drawable, int color) {
		
		getDrawableCopy(drawable).setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.MULTIPLY));
		return drawable;
	}
	
	public static BitmapDrawable writeOnDrawable(Context context, int drawableResourceId
			, int colorOverDrawableResourceId, int textColorResourceId
			, String text, int textSize, Typeface typeFace) {
		
		Drawable dr = context.getResources().getDrawable(drawableResourceId);
		
		if (colorOverDrawableResourceId != 0) {
			changeDrawableColor(dr, context.getResources().getColor(colorOverDrawableResourceId));
		}
		
		Bitmap bm = drawableToBitmap(dr).copy(Bitmap.Config.ARGB_8888, true);
		
		Paint paint = new Paint();
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(context.getResources().getColor(textColorResourceId));
		paint.setTextSize(textSize);
		paint.setUnderlineText(false);
		paint.setTypeface(typeFace);
		
		Canvas canvas = new Canvas(bm);
		canvas.drawText(text, bm.getWidth() / 2, (bm.getHeight() / 4) * 2.5F, paint);
		
		BitmapDrawable bd = new BitmapDrawable(context.getResources(), bm);
		
		
		return bd;
	}
	
	
	public static Bitmap drawableToBitmap(Drawable drawable) {
		
		
		Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
		drawable.draw(canvas);
		
		return bitmap;
	}
	
	public static Drawable getDrawableCopy(Drawable drawable) {
		return drawable.mutate();
	}
	
	public static Bitmap flipImage(Bitmap src, int type) {
		// create new matrix for transformation
		Matrix matrix = new Matrix();
		// if vertical
		if (type == ExifInterface.ORIENTATION_FLIP_VERTICAL) {
			// y = y * -1
			matrix.preScale(1.0f, -1.0f);
		}
		// if horizonal
		else if (type == ExifInterface.ORIENTATION_FLIP_HORIZONTAL) {
			// x = x * -1
			matrix.preScale(-1.0f, 1.0f);
			// unknown type
		} else {
			return null;
		}
		
		// return transformed image
		return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
	}
	
}
