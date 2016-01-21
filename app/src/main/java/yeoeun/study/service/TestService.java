package yeoeun.study.service;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by elite on 16. 1. 21..
 */
public class TestService {
    static final String IP_ADDR = "http://10.10.0.164:5555";
    private static TestService instance;

    private TestService() {
    }

    public static TestService getInstance() {
        if (instance == null) {//있는지 체크 없으면
            instance = new TestService(); //생성한뒤
        }
        return instance;//성성자를 넘긴다.
    }

    public SalesStocksService getSalesStocksService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IP_ADDR)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(SalesStocksService.class);
    }
}
