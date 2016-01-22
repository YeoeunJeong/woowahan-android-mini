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
public class StockFragmentTab extends Fragment {

    private SalesStock salesStock;
    private Activity activity;
    private String id;

    @Bind(R.id.stock_fragment_button)
    Button stockBtn;

    @Bind(R.id.stock_fragment_edittext)
    EditText stockVolumeEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stock_fragment_tab, container, false);
        ButterKnife.bind(this, view);
        activity = getActivity();

        Intent intent = activity.getIntent();
        id = intent.getStringExtra("salesStockId");

        return view;
    }

    @OnClick(R.id.stock_fragment_button)
    void stockOnClick() {
        SalesStocksService service = RetrofitService.getInstance().getSalesStocksService();

        String orderVolume = stockVolumeEdit.getText().toString();

        Call<SalesStock> salesStockCall = service.updateOrder("4", id, orderVolume);

        salesStockCall.enqueue(new Callback<SalesStock>() {
            @Override
            public void onResponse(Response<SalesStock> response) {
                StockFragmentTab.this.salesStock = response.body();
                getActivity().finish();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.i("test", t.getMessage());
            }
        });
    }
}
