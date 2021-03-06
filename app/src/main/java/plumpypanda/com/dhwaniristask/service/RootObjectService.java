package plumpypanda.com.dhwaniristask.service;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import plumpypanda.com.dhwaniristask.R;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenovo on 9/20/2018.
 */

public class RootObjectService {

 

      TokenInterceptor tokenInterceptor = new TokenInterceptor(new TokenManager() {
        @Override
        public String getToken() {
            return TOKEN;

        @Override
        public boolean hasToken() {
            return true;
        }

        @Override
        public void clearToken() {

        }

        @Override
        public String refreshToken() {
            return null;
        }
    });
    public RootObjectApi getApi() {
        //OkHttpClient.Builder client = new OkHttpClient().newBuilder();

        //client.interceptors().add(tokenInterceptor);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(RootObjectApi.class);

    }
    private OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
        okhttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                // Set authorization token here
                Request.Builder requestBuilder = original.newBuilder()
                        .header("token", "TOKEN")
                        .method(original.method(), original.body());

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        // okhttpClientBuilder.addInterceptor(tokenInterceptor);
        return okhttpClientBuilder.build();
    }

    private static class TokenInterceptor implements Interceptor {
        private final TokenManager mTokenManager;

        private TokenInterceptor(TokenManager tokenManager) {
            mTokenManager = tokenManager;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request initialRequest = chain.request();
            Request modifiedRequest = initialRequest;
            if (mTokenManager.hasToken()) {
                modifiedRequest = initialRequest.newBuilder()
                        .addHeader("token", mTokenManager.getToken())
                        .build();
            }

            Response response = chain.proceed(modifiedRequest);
            boolean unauthorized = response.code() == 401;
            if (unauthorized) {
                mTokenManager.clearToken();
                String newToken = mTokenManager.refreshToken();
                modifiedRequest = initialRequest.newBuilder()
                        .addHeader("token", mTokenManager.getToken())
                        .build();
                return chain.proceed(modifiedRequest);
            }
            return response;
        }
    }

    interface TokenManager {
        String getToken();
        boolean hasToken();
        void clearToken();
        String refreshToken();
    }

}
