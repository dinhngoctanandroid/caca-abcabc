package camera.home.app.camera.tab.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import camera.home.app.camera.about.ui.TabAboutActivity;
import camera.home.app.camera.home.ui.TabHomeActivity;
import camera.home.app.camera.settting.ui.TabSettingActivity;

/**
 * Created by Hai on 11/2/2016.
 */
public class AdapterTab extends FragmentStatePagerAdapter
{
    int mNumOfTabs;
    private FragmentManager fm;

    public AdapterTab(FragmentManager fm, int mNumOfTabs) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0: return new TabHomeActivity();
            case 1: return new TabSettingActivity();
            case 2: return new TabAboutActivity();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(View container, int position) {
        return super.instantiateItem(container, position);
    }
}
