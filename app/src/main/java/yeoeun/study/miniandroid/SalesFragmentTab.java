package yeoeun.study.miniandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yeoeun.study.model.SalesStock;
import yeoeun.study.service.SalesStocksService;
import yeoeun.study.service.RetrofitService;

/**
 * Created by elite on 16. 1. 19..
 */
public class SalesFragmentTab extends Fragment {
    //    private static FragmentActivity activity;

    static final String IP_ADDR = "http://10.10.0.164:5555";
    private SalesStock salesStock;
    private Activity activity;
    private String id;

    @Bind(R.id.sales_fragment_edittext)
    EditText mSalesEdit;


    @Bind(R.id.sales_fragment_textview)
    TextView mSalesTv;

    @Bind(R.id.sales_fragment_button)
    Button salesBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sales_fragment_tab, container, false);
        ButterKnife.bind(this, view);
        activity = getActivity();

        Intent intent = activity.getIntent();
        id = intent.getStringExtra("salesStockId");

        if(intent.getBooleanExtra("soldOut", false) == true) {
            salesBtn.setEnabled(false);
            mSalesTv.setText("재고가 없습니다. 재고 주문을 해주세요.");
        } else {
            salesBtn.setEnabled(true);
        }
        return view;
    }

    @OnClick(R.id.sales_fragment_button)
    void sellOnClick() {
        SalesStocksService service = RetrofitService.getInstance().getSalesStocksService();
        String salesVolume = mSalesEdit.getText().toString();
        Call<SalesStock> salesStockCall = service.updateSales("4", id, salesVolume);

        salesStockCall.enqueue(new Callback<SalesStock>() {
            @Override
            public void onResponse(Response<SalesStock> response) {
                SalesFragmentTab.this.salesStock = response.body();
                getActivity().finish();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.i("test", t.getMessage());
            }
        });
    }


}
