package com.example.android.utils;

import com.example.android.data.ResultData;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    String RUBBISH_CATEGORY = "rubbish/category";

    @POST(RUBBISH_CATEGORY)
    Observable<ResultData> getRubbishCategory(@Query("key") String key);

}
