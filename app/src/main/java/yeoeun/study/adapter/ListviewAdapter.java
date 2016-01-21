package yeoeun.study.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import yeoeun.study.miniandroid.R;
import yeoeun.study.model.SalesStock;


/**
 * Created by elite on 16. 1. 20..
 */
public class ListviewAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private ArrayList<SalesStock> arrayList;
    private int layout;

    public ListviewAdapter(Context context, ArrayList<SalesStock> arrayList, int layout) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arrayList = arrayList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = layoutInflater.inflate(layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        SalesStock salesStock = arrayList.get(position);
        Log.i("***", salesStock.getCosmetic().getName() + ", " + salesStock.getSales_volume() + ", " + salesStock.getStock_volume());

        holder.textCosmeticNm.setText("" + salesStock.getCosmetic().getName());
        holder.textSalesVolume.setText(salesStock.getSales_volume() + "개");
        holder.textStockVolume.setText(salesStock.getStock_volume() + "개");
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.list_item_cosmetic_nm)
        TextView textCosmeticNm;
        @Bind(R.id.list_item_stock_volume)
        TextView textStockVolume;
        @Bind(R.id.list_item_sales_volume)
        TextView textSalesVolume;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}