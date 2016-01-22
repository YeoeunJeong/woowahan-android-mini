package yeoeun.study.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import yeoeun.study.model.SalesStock;

/**
 * Created by elite on 16. 1. 20..
 */
public interface SalesStocksService {
    @GET("/shops/{shop_id}/sales_stocks.json")
    Call<List<SalesStock>> getSalesStocksList(@Path("shop_id") String shopId,
                                              @Query("use_id") String useId);

    @PUT("/shops/{shop_id}/sales_stocks/{id}.json")
    Call<SalesStock> updateSales(@Path("shop_id") String shopId, @Path("id") String id,
                                 @Query("sales_volume") String salesVolume);

    @PUT("/shops/{shop_id}/sales_stocks/{id}.json")
    Call<SalesStock> updateOrder(@Path("shop_id") String shopId, @Path("id") String id,
                                 @Query("order_volume") String orderVolume);

    @PUT("/shops/{shop_id}/sales_stocks/{id}.json")
    Call<SalesStock> updateStock(@Path("shop_id") String shopId, @Path("id") String id,
                                 @Query("stock_volume") String stockVolume);

}