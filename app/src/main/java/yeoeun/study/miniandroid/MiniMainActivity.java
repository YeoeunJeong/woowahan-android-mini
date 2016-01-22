package yeoeun.study.miniandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yeoeun.study.adapter.ListviewAdapter;
import yeoeun.study.model.SalesStock;
import yeoeun.study.service.SalesStocksService;
import yeoeun.study.service.RetrofitService;

import static android.widget.Toast.LENGTH_SHORT;

//import java.util.List;

public class MiniMainActivity extends AppCompatActivity {
    static String IP_ADDR = "http://10.10.0.164:5555";
    private ArrayList<SalesStock> arrayList;
    private List<SalesStock> salesStockList;
    private ListviewAdapter listAdapter;

    @Bind(R.id.spinner)
    Spinner spinner;

    @Bind(R.id.main_listview)
    ListView listView;

    @Bind(R.id.main_sales_tv)
    List<View> sales;

    @Bind(R.id.main_stock_tv)
    List<View> stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_main);
        ButterKnife.bind(this);

        SharedPreferences pref = getSharedPreferences("pref",
                MODE_PRIVATE);
        String adminId = pref.getString("admin_id", "admin111");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cosmetic_use, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(onItemSelectedListener);

        arrayList = new ArrayList<>();

        listAdapter = new ListviewAdapter(this, arrayList, R.layout.list_item);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(onClickListItem);

//        refreshData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshData("0");
    }

    public void refreshData(String useId) {
        SalesStocksService service = RetrofitService.getInstance().getSalesStocksService();
        Call<List<SalesStock>> salesStocks;
//        if (!useId.equals("0")) {
            salesStocks = service.getSalesStocksList("4", useId);
//
//        } else {
//            salesStocks = service.getSalesStocksList("4", null);
//        }

        salesStocks.enqueue(new Callback<List<SalesStock>>() {
            @Override
            public void onResponse(Response<List<SalesStock>> response) {
                arrayList.clear();
                salesStockList = response.body();

                for (SalesStock salesStock : salesStockList) {
                    Log.i("test", salesStock.getCosmetic().getName());
                    arrayList.add(salesStock);
                }
                listAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Throwable t) {
                Log.i("test", t.getMessage());
            }
        });
    }

//    @OnItemSelected(R.id.spinner)
//    void onUseSelected() {
//
//    }

    private AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            refreshData(String.valueOf(position));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
//            refreshData("");
        }
    };

    private AdapterView.OnItemClickListener onClickListItem = new AdapterView.OnItemClickListener() {

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(MiniMainActivity.this, arrayList.get(position).getCosmetic().getName(), Toast.LENGTH_SHORT).show();
            // 이벤트 발생 시 해당 아이템 위치의 텍스트를 출력
            Intent intent = new Intent(MiniMainActivity.this, MiniDetailActivity.class);
            intent.putExtra("cosmeticNm", arrayList.get(position).getCosmetic().getName());
            intent.putExtra("salesStockId", arrayList.get(position).getId() + "");
            intent.putExtra("soldOut", arrayList.get(position).isSold_out());

            Log.i("**", arrayList.get(position).getCosmetic().getId() + ", " + arrayList.get(position).getCosmetic().getName());
            startActivity(intent);
        }
    };


    /* 터치 효과 */
    private static final ButterKnife.Action<View> ALPHA_FADE = new ButterKnife.Action<View>() {
        @Override
        public void apply(@NonNull View view, int index) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
            alphaAnimation.setFillBefore(true);
            alphaAnimation.setDuration(500);
            alphaAnimation.setStartOffset(index * 100);
            view.startAnimation(alphaAnimation);
        }
    };

    @OnClick(R.id.main_sales_tv)
    void blinkSalesTv() {
        Toast.makeText(this, "판매순으로 정렬합니다", LENGTH_SHORT).show();
        ButterKnife.apply(sales, ALPHA_FADE);
    }

    @OnClick(R.id.main_stock_tv)
    void blinkStockTv() {
        Toast.makeText(this, "재고순으로 정렬합니다", LENGTH_SHORT).show();
        ButterKnife.apply(stock, ALPHA_FADE);
    }
}
