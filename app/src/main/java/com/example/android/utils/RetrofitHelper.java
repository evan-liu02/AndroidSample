package com.example.android.utils;

import com.example.android.BuildConfig;
import com.example.android.data.ResultData;

import java.io.File;
import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private static final String HOST = "http://apis.juhe.cn/";

    private static RetrofitHelper self;
    private ApiService apiService;

    public static RetrofitHelper getInstance() {
        if (self == null) {
            synchronized (RetrofitHelper.class) {
                if (self == null) {
                    self = new RetrofitHelper();
                }
            }
        }
        return self;
    }

    private RetrofitHelper() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            //Log信息拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);//这里可以选择拦截级别
            builder.addInterceptor(loggingInterceptor);
        }

        // 设置缓存
        /*File cacheFile = new File("/data/data/com.example.android/cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetworkUtil.isConnected()) {
                    request = request
                            .newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                } else {
                    request = request
                            .newBuilder()
                            .cacheControl(CacheControl.FORCE_NETWORK)
                            .build();
                }
                Response response = chain.proceed(request);
                if (NetworkUtil.isConnected()) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    request
                            .newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .build();
                } else {
                    // 无网络时，设置超时为半天
                    int maxStale = 60 * 5;
                    request
                            .newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .build();
                }
                return response;
            }
        };
        builder.addInterceptor(cacheInterceptor);
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.cache(cache);*/

        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public void getRubbishCategory(Observer<ResultData> observer, String key) {
        apiService.getRubbishCategory(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
