package com.love.leeutils.network;

import com.love.leeutils.modle.MeizhiData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lixinxin on 2016/11/26.
 * 邮箱 895330766@qq.com
 */

public interface MeizhiApi  {

    @GET("data/福利/10/{day}")//()里面的是相对路径，当然绝对路径也是可以的
    Call<MeizhiData> getMeizhi(@Path("day") int day);//{}里面的是要替换的内容 用注解@Path映射
}
