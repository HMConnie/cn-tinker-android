package cn.connie.tinker.utils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.GlideModule;

/**
 * 加入缓存清理目录夹
 * 需要在AndroidManifest.xml中声明
 * <meta-data
 * android:name="com.sgcai.benben.utils.GlideConfiguration"
 * android:value="GlideModule" />
 */

public class GlideConfiguration implements GlideModule {


    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //自定义缓存目录
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, GlideCatchConfig.GLIDE_CARCH_DIR, GlideCatchConfig.GLIDE_CATCH_SIZE));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
    }
}