package cn.connie.tinker.utils;

/**
 * Created by hinge on 18/6/11.
 */

public interface GlideCatchConfig {

    // 图片缓存最大容量，150M，根据自己的需求进行修改
    int GLIDE_CATCH_SIZE = 150 * 1024 * 1024;

    // 图片缓存子目录
    String GLIDE_CARCH_DIR = "image_catch";

    // 图片缓存获取失败的大小
    String DEFAULT_FAILED_CACHE_SIZE = "0B";
}
