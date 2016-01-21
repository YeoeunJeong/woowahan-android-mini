package yeoeun.study.miniandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import yeoeun.study.adapter.ViewPagerAdapter;

public class MiniDetailActivity extends FragmentActivity {
    @Bind(R.id.viewpager)
    ViewPager viewPager;

    @Bind(R.id.detail_cosmetic_nm_textview)
    TextView textCosmeticNmTv;

    private String salesStockId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        textCosmeticNmTv.setText(intent.getStringExtra("cosmeticNm"));
        salesStockId = intent.getStringExtra("salesStockId");
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
    }

    public String getSalesStockId() {
        return salesStockId;
    }

}
