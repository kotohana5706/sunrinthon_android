package kr.edcan.sunrinton;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.Toast;

import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.Collections;

import kr.edcan.sunrinton.databinding.ActivityMainBinding;
import kr.edcan.sunrinton.fragment.SettingsFragment;
import kr.edcan.sunrinton.views.ControllableViewPager;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;
    MainPagerAdapter pager;
    ArrayList<String> pageTitle = new ArrayList<>();
    ControllableViewPager mainPager;

    @Override
    protected void setDefault() {
        binding = (ActivityMainBinding) baseBinding;
        Collections.addAll(pageTitle,
                "asdf", "asdf", "asdf", "Settings");
        pager = new MainPagerAdapter(getSupportFragmentManager());
        mainPager = binding.mainPager;
        mainPager.setAdapter(pager);
        binding.mainBottombar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.main_newsfeed:
                        mainPager.setCurrentItem(0, true);
                        break;
                    case R.id.main_studio:
                        mainPager.setCurrentItem(1, true);
                        break;
                    case R.id.main_myeditorpage:
                        mainPager.setCurrentItem(2, true);
                        break;
                    case R.id.main_settings:
                        mainPager.setCurrentItem(3, true);
                        Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return R.id.toolbar;
    }

    class MainPagerAdapter extends FragmentStatePagerAdapter {

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new SettingsFragment();
        }

        @Override
        public int getCount() {
            return pageTitle.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return super.getPageTitle(position);
        }
    }
}
