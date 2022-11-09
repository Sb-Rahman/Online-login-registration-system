package com.retrofit.retrofitpractise;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController {
        static final String url="http://192.168.209.1/androidapi/";
        //http://me-tube.ml/api/

        private static ApiController clientObject;
        private  static Retrofit retrofit;


        public ApiController() {

                retrofit=new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
        }
        public static synchronized ApiController getInstance(){
                if (clientObject==null)
                        clientObject=new ApiController();
                return clientObject;
        }
        ApiInterface getApi(){
              return retrofit.create(ApiInterface.class);

        }
}
