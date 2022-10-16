package com.kasimkartal866.demoapp.common;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kasimkartal866.demoapp.R;

import java.util.regex.Pattern;

public class Utilities {
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    public static boolean isValidPassword(String s) {
        Pattern PASSWORD_PATTERN
                = Pattern.compile("^" +
                "(?=.*[0-9])" +
                "(?=.*[a-z])" +
                "(?=.*[A-Z])" +
                "(?=.*[@#$%^.&+=])" +
                ".{6,}" +
                "$");

        return !TextUtils.isEmpty(s) && PASSWORD_PATTERN.matcher(s).matches();
    }
    public static void loadImage(Context context, String imageAddress, ImageView imageView) {
        Glide
                .with(context)
                .load(imageAddress)
                .centerCrop()
                .placeholder(R.drawable.car_app)
                .into(imageView);

    }

}
