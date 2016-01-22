package yeoeun.study.miniandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yeoeun.study.adapter.ViewPagerAdapter;
import yeoeun.study.model.SalesStock;
import yeoeun.study.service.RetrofitService;
import yeoeun.study.service.SalesStocksService;

public class MiniDetailActivity extends FragmentActivity {
    private String salesStockId;
    private SalesStock salesStock;

    @Bind(R.id.viewpager)
    ViewPager mViewPager;

    @Bind(R.id.detail_cosmetic_nm_textview)
    TextView mCosmeticNmTv;

    @Bind(R.id.detail_stock_volume_textview)
    TextView mStockVolumeTv;

    @Bind(R.id.detail_sales_volume_textview)
    TextView mSalesVolumeTv;

    @Bind(R.id.detail_order_volume_button)
    Button mOrderVolumeBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        mCosmeticNmTv.setText(intent.getStringExtra("cosmeticNm"));
        salesStockId = intent.getStringExtra("salesStockId");
        mStockVolumeTv.setText("재고 " + intent.getStringExtra("stockVolume") +"개");
        mSalesVolumeTv.setText("판매 " + intent.getStringExtra("salesVolume") +"개");

        String orderVolume = intent.getStringExtra("orderVolume");

        if (Integer.parseInt(orderVolume) > 0) {
            mOrderVolumeBtn.setVisibility(View.VISIBLE);
            mOrderVolumeBtn.setText(orderVolume + "개 재고 받기");
        }
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
    }

    @OnClick(R.id.detail_order_volume_button)
    void orderVolumeOnClick() {
        SalesStocksService service = RetrofitService.getInstance().getSalesStocksService();

        // update stock 은 parameter value 값 (여기에서는 "1") 에 상관없이 stock_volume 이라는 키를 보내 주기만 하면 된다
        Call<SalesStock> salesStockCall = service.updateStock("4", salesStockId, "1");

        salesStockCall.enqueue(new Callback<SalesStock>() {
            @Override
            public void onResponse(Response<SalesStock> response) {
                salesStock = response.body();
                finish();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.i("test", t.getMessage());
            }
        });
    }

//    public String getSalesStockId() {
//        return salesStockId;
//    }

}
