package com.example.finalproject.network;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofitInstance;
    private static final String BASE_URL = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/";
    public static final String USER_NAME = "ck_120a89c914da239359b2683859fb36ce3c94fc0a";
    public static final String PASSWORD = "cs_0dabb4ea47c464969eaad199a30370b9e7cb7e7b";

    public static Retrofit getRetrofit() {
        if (retrofitInstance == null) {
           OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new BasicAuthInterceptor(USER_NAME, PASSWORD))
                    .build();



            retrofitInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }


        return retrofitInstance;
    }



    private static class BasicAuthInterceptor implements Interceptor {

        private String credentials;

        public BasicAuthInterceptor(String user, String password) {
            this.credentials = Credentials.basic(user, password);
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request authenticatedRequest = request.newBuilder()
                    .header("Authorization", credentials).build();
            return chain.proceed(authenticatedRequest);
        }

    }





}
