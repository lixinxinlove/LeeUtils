package com.love.leeutils.network;

import com.love.leeutils.Config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lixinxin on 2016/11/26.
 * 邮箱 895330766@qq.com
 */

public class ApiServiceManager {
    private static ApiServiceManager ourInstance = new ApiServiceManager();

    public static ApiServiceManager getInstance() {
        return ourInstance;
    }

    private MeizhiApi meizhiApi;

    private ApiServiceManager() {
        Retrofit.Builder builderWithGson = new Retrofit.Builder()
                .baseUrl(Config.baseMeizhiUrl)//设置基础url
                .addConverterFactory(GsonConverterFactory.create());//Gson转换器直接返回对象
        Retrofit retrofit = builderWithGson.build();
        meizhiApi = retrofit.create(MeizhiApi.class);
    }

    public MeizhiApi getMeizhiApi() {
        return meizhiApi;
    }

}
