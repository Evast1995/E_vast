package com.ychen.tourism.util;

import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.ychen.tourism.MyApplication;
import com.ychen.tourism.R;


/**
 * Created by 72963 on 2015/10/17.
 */
public class ImageLoadUtil {

    private ImageLoader imageLoader = null;
    private static ImageLoadUtil imageLoadUtil = new ImageLoadUtil();

    private ImageLoadUtil(){
       imageLoader = new ImageLoader(
                MyApplication.getInstance().getRequestQueue(), new BitmapCache());
    };

    public static ImageLoadUtil getInstance(){
        return imageLoadUtil;
    }

    public void setImageLoader(ImageView imageHead,String imageUrl){
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(
                imageHead, R.mipmap.mini_avatar, R.mipmap.mini_avatar);
        imageLoader.get(imageUrl,imageListener);
    }
}
