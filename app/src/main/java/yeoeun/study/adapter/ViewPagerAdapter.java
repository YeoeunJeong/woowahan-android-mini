package yeoeun.study.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import yeoeun.study.miniandroid.SalesFragmentTab;
import yeoeun.study.miniandroid.StockFragmentTab;

/**
 * Created by elite on 16. 1. 19..
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    // tab titles

    private String tabTitles[] = new String[]{"판매", "재고 주문 "};

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new SalesFragmentTab();
                break;
            case 1:
                fragment = new StockFragmentTab();
                break;
            default:
                break;
        }
        return fragment;
    }
}
