package yeoeun.study.model;

/**
 * Created by elite on 16. 1. 19..
 */
public class SalesStock {

    private int id;
    private Cosmetic cosmetic;
    private int sales_volume;
    private int stock_volume;
    private int order_volume;
    private boolean sold_out;

    public SalesStock(Cosmetic cosmetic, int sales_volume, int stock_volume) {
        this.cosmetic = cosmetic;
        this.sales_volume = sales_volume;
        this.stock_volume = stock_volume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cosmetic getCosmetic() {
        return cosmetic;
    }

    public void setCosmetic(Cosmetic cosmetic) {
        this.cosmetic = cosmetic;
    }

    public int getStock_volume() {
        return stock_volume;
    }

    public void setStock_volume(int stock_volume) {
        this.stock_volume = stock_volume;
    }

    public int getSales_volume() {
        return sales_volume;
    }

    public void setSales_volume(int sales_volume) {
        this.sales_volume = sales_volume;
    }

    public int getOrder_volume() {
        return order_volume;
    }

    public void setOrder_volume(int order_volume) {
        this.order_volume = order_volume;
    }

    public boolean isSold_out() {
        return sold_out;
    }

    public void setSold_out(boolean sold_out) {
        this.sold_out = sold_out;
    }
}
