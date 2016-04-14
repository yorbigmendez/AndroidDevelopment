package cr.ac.itcr.ict_exam;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mendez Soto on 4/7/2016.
 */
public class ScreenSlideActivity extends ActionBarActivity {
    //Variables used for the class
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);
        //Toggle all the widgets used
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewPager.setAdapter(new CustomAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(2);
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        //Add tab with viewpager
        tabLayout.setupWithViewPager(viewPager);
        //Override the on tab selected actions
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_screen_slide, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //The selected item is logOUt
        //You must log out to see the listView with the updated items
        if (id == R.id.logOut) {
            //Back up to the last activity
            onBackPressed();
            //End Activity
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //The adapter for the pager
    //You must log out to see the listView with the updated items
    public class CustomAdapter extends FragmentPagerAdapter{
        String titles[] = getResources().getStringArray(R.array.titles);
        private Map<Integer,String> mFragmentTags;//Used as intent to try to fix the updateLisView problem on ViewPagers

        public CustomAdapter(FragmentManager supportFragmentManager){
            super(supportFragmentManager);
            mFragmentTags = new HashMap<Integer,String>();
        }

        //Selec te action on each tab selection
        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    return new AboutFragment();
                    //FragmentAbout
                case 1:
                    return new ListFragment();
                    //Fragment List
                case 2:
                    return new ManageFragment();
                    //Fragment Manage
                default:
                    return null;
            }
        }

        @Override
        public int getCount(){
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position){
            return titles[position];
        }

        @Override
        public int getItemPosition(Object object) {
            // TODO Auto-generated method stub
            return POSITION_NONE;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position){
            Object obj = super.instantiateItem(container,position);
            if(obj instanceof Fragment){
                Fragment f = (Fragment) obj;
                String tag = f.getTag();
                mFragmentTags.put(position,tag);
            }
            return obj;
        }



    }
}
