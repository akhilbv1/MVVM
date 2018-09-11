package com.architecture.mvvm.DI;

import com.architecture.mvvm.Storage.DataManager;
import com.architecture.mvvm.Storage.RestService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by akhil on 10/9/18.
 */

@Module(includes = {ViewModelModule.class})
public class AppModule {

    @Provides
    @Singleton
    DataManager provideDatamanager(RestService restService) {
        return new DataManager(restService);
    }

    @Provides
    @Singleton
    RestService getRestApi(Retrofit retrofit) {
        return retrofit.create(RestService.class);
    }


    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    // build OkHttp client.
    @Provides
    @Singleton
    OkHttpClient buildOkHttpClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .header("Accept-Language", "en-gb")
                        // .header("Authorization", getCustomAuth())
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        httpClient.addInterceptor(logging);
        httpClient.connectTimeout(1, TimeUnit.MINUTES);
        httpClient.readTimeout(1, TimeUnit.MINUTES);

        return httpClient.build();
    }

}
