package com.mohsen.game.application.helper;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.mohsen.game.ui.helper.SnackBarHelper;

import java.io.FileNotFoundException;
import java.io.OutputStream;

public class GlideHelper {

    public static void setPicture(Context context, final String url, ImageView imageView) {


        if (imageView == null || url.length() == 0 || context == null)
            return;

        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);


    }

    public static void setPictureWithLoading(final Context context, final View rootView, final String url, ImageView imageView, final View loadingView) {


        if (imageView == null || url.length() == 0 || context == null)
            return;

        loadingView.setVisibility(View.VISIBLE);
        /*loadingView.stop();
    
        loadingView.start();*/

        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        if (e != null) {

                            SnackBarHelper.showSnack(rootView, SnackBarHelper.SnackState.Error, e.getMessage());
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        loadingView.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(imageView);

    }

    public static void startShareActivity(final Activity activity, final String data, final String mainText) {

        try {


            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "title");
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");

            final Uri uri = activity.getApplicationContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            Glide.with(activity.getApplicationContext())
                    .asBitmap()
                    .load(data)
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            try {
                                OutputStream outstream = activity.getApplicationContext().getContentResolver().openOutputStream(uri);
                                resource.compress(Bitmap.CompressFormat.JPEG, 100, outstream);
                                outstream.close();

                                Intent share = new Intent(Intent.ACTION_SEND);
                                share.setAction(Intent.ACTION_SEND);
                                share.putExtra(Intent.EXTRA_TEXT, mainText);
                                share.putExtra(Intent.EXTRA_STREAM, uri);
                                share.setType("image/*");
                                share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                //share.putExtra(Intent.EXTRA_SUBJECT, title);
                                //share.putExtra(Intent.EXTRA_TEXT, mainText);

                                if (share != null) {
                                    activity.getApplicationContext().startActivity(share);
                                }

                            } catch (FileNotFoundException e) {
                                SnackBarHelper.showSnack(activity.getWindow().getDecorView().findViewById(android.R.id.content), SnackBarHelper.SnackState.Error, e.getMessage());
                                e.printStackTrace();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {

                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
