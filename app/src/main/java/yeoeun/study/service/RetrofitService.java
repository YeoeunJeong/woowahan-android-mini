package yeoeun.study.service;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by elite on 16. 1. 21..
 */
public class RetrofitService {
    static final String IP_ADDR = "http://10.10.0.164:5555";
    private static RetrofitService instance;

    private RetrofitService() {
    }

    public static RetrofitService getInstance() {
        if (instance == null) {
            instance = new RetrofitService();
        }
        return instance;
    }

    public SalesStocksService getSalesStocksService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IP_ADDR)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(SalesStocksService.class);
    }
}
