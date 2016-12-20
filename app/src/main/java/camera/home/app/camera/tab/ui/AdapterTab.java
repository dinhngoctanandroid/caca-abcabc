package camera.home.app.camera.tab.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import camera.home.app.camera.about.ui.AboutFragment;
import camera.home.app.camera.home.ui.HomeFragment;
import camera.home.app.camera.settting.ui.SettingFragment;

/**
 * Created by Hai on 11/2/2016.
 */
public class AdapterTab extends FragmentStatePagerAdapter
{
    private static final int LOOP = 1000;

    private List<Fragment> mListFragments = new ArrayList<>();

    public AdapterTab(FragmentManager fm) {
        super(fm);

        // TODO why cached fragments cannot be used with java.lang.IllegalStateException: Fragment already active
        mListFragments.add(new HomeFragment());
        mListFragments.add(new SettingFragment());
        mListFragments.add(new AboutFragment());
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("Adapter", "View pager get item position = " + position);
        position = position % mListFragments.size();
        Log.d("Adapter", "View pager get item (calculated) position = " + position);
        // return mListFragments.get(position);
        switch (position)
        {
            case 0: return new HomeFragment();
            case 1: return new SettingFragment();
            case 2: return new AboutFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mListFragments.size() * LOOP;
    }

    public int getMiddlePosition() {
        return mListFragments.size() * LOOP / 2;
    }
}
