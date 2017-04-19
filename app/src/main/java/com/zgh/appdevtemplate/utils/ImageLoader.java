package com.zgh.appdevtemplate.utils;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zgh.appdevtemplate.R;

import java.io.File;

/**
 * 图片加载内，将开源的图片加载库封装在里面，
 * 统一使用入口，以后要换库也方便
 */
public class ImageLoader {
    public static void withDefault(Context context, @NonNull String imageUrl, @NonNull ImageView imageView) {
        Glide.with(context)
                .load(imageUrl)
                .dontAnimate()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .centerCrop()
                .into(imageView);
    }

    public static void withDefault(Context context, @NonNull File file, @NonNull ImageView imageView) {
        Glide.with(context)
             .load(file)
             .dontAnimate()
             .placeholder(R.mipmap.ic_launcher)
             .crossFade()
             .centerCrop()
             .into(imageView);
    }

    public static void withNoDefault(Context context, @NonNull String imageUrl, @NonNull ImageView imageView) {
        Glide.with(context)
             .load(imageUrl)
             .dontAnimate()
             .crossFade()
             .centerCrop()
             .into(imageView);
    }

    public static void withNoDefault(Context context, @NonNull File file, @NonNull ImageView imageView) {
        Glide.with(context)
             .load(file)
             .dontAnimate()
             .crossFade()
             .centerCrop()
             .into(imageView);
    }

    public static void withImage(Context context, @NonNull String imageUrl, @DrawableRes int resId, @NonNull ImageView imageView) {
        Glide.with(context)
             .load(imageUrl)
             .dontAnimate()
             .placeholder(resId)
             .crossFade()
             .centerCrop()
             .into(imageView);
    }

    public static void withImage(Context context, @NonNull File file, @DrawableRes int resId, @NonNull ImageView imageView) {
        Glide.with(context)
             .load(file)
             .dontAnimate()
             .placeholder(resId)
             .crossFade()
             .centerCrop()
             .into(imageView);
    }
}
