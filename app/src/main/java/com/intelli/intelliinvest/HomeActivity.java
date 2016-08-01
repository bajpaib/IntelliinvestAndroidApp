package com.intelli.intelliinvest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.intelli.Fragments.AnalysisFragment;
import com.intelli.Fragments.FeedbackFragment;
import com.intelli.Fragments.PersonalizeFragment;
import com.intelli.Fragments.PortfolioFragment;
import com.intelli.Fragments.RiskReturnFragment;
import com.intelli.Fragments.SuggestFragment;
import com.intelli.Fragments.WatchListFragment;
import com.intelli.utils.ApiConstants;
import com.intelli.utils.Constants;
import com.intelli.utils.Logger;

import android.support.v4.app.Fragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment currentFragment = null;
    String currentFragmentClass = "";
    int currentNavigationID = -1;
    int tabList[] = {R.id.nav_analysis, R.id.nav_addon, R.id.nav_personalize};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(tabList.length > 1) {
            SharedPreferences sP = getSharedPreferences(Constants.INTELLIINVEST_SHARED_PREF, MODE_PRIVATE);
            if(sP.contains(ApiConstants.IS_NEW_USER) && sP.getBoolean(ApiConstants.IS_NEW_USER, false) == true) {
                setFragment(tabList[2]);
            } else {
                setFragment(tabList[0]);
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;

//        if (!mNavigationDrawerFragment.isDrawerOpen()) {
//            // Only show items in the action bar relevant to this screen
//            // if the drawer is not showing. Otherwise, let the drawer
//            // decide what to show in the action bar.
//            getMenuInflater().inflate(R.menu.home, menu);
//            restoreActionBar();
//            return true;
//        }
//        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        setFragment(id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public void setFragment(int id)
    {
        int position = 0;
        if(id != currentNavigationID) {

            currentNavigationID = id;
            currentFragment = null;

                switch (id)
                {
                    case R.id.nav_analysis:
                        currentFragment = new AnalysisFragment();
                        currentFragmentClass = Constants.ACTIVITY_ANALYSIS;
                        position = 0;
                        break;
                    case R.id.nav_addon:
                        currentFragment = new PortfolioFragment();
                        currentFragmentClass = Constants.ACTIVITY_PORTFOLIO;
                        position = 1;
                        break;
                    case R.id.nav_personalize:
                        currentFragment = new PersonalizeFragment();
                        currentFragmentClass = Constants.ACTIVITY_PERSONALIZE;
                        position = 2;
                        break;
                    case R.id.nav_logout:
                        logout();
                        break;
                    default:
                        Logger.logI(Constants.ACTIVITY_ANALYSIS, "Class not found: " + id);
                }

            if (currentFragment != null) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                if(fm.findFragmentById(R.id.fragmentContainer) == null) {
                    ft.add(R.id.fragmentContainer, new AnalysisFragment(), currentFragmentClass);
                }else {
                    ft.replace(R.id.fragmentContainer, currentFragment, currentFragmentClass);
                }

                ft.commit();
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                navigationView.getMenu().getItem(position).setChecked(true);
                setTitle(currentFragmentClass);
            }
        }
    }

    public void logout()
    {
        SharedPreferences.Editor editPref = getSharedPreferences(Constants.INTELLIINVEST_SHARED_PREF, MODE_PRIVATE).edit();
        editPref.putString(ApiConstants.LOGIN_STATUS, "false");
        editPref.commit();

        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }






//    @Override
//    public void onNavigationDrawerItemSelected(int position) {
//        // update the main content by replacing fragments
//        FragmentManager fragmentManager = getSupportFragmentManager();
//
//        switch (position) {
//            case 0:
//                fragmentManager.beginTransaction()
//                        .replace(R.id.container, PortfolioFragment.newInstance("1", "2"))
//                        .commit();
//                break;
//            case 1:
//                fragmentManager.beginTransaction()
//                        .replace(R.id.container, WatchListFragment.newInstance("1", "2"))
//                        .commit();
//                break;
//            case 2:
//                fragmentManager.beginTransaction()
//                        .replace(R.id.container, SuggestFragment.newInstance("1", "2"))
//                        .commit();
//                break;
//            case 3:
//                fragmentManager.beginTransaction()
//                        .replace(R.id.container, RiskReturnFragment.newInstance("1", "2"))
//                        .commit();
//                break;
//            case 4:
//                fragmentManager.beginTransaction()
//                        .replace(R.id.container, FeedbackFragment.newInstance("1", "2"))
//                        .commit();
//                break;
//            case 5:
//                fragmentManager.beginTransaction()
//                        .replace(R.id.container, SettingsFragment.newInstance("1", "2"))
//                        .commit();
//                break;
//        }
//    }
//
//    public void onSectionAttached(int number) {
//        switch (number) {
//            case 1:
//                mTitle = getString(R.string.title_section1);
//                break;
//            case 2:
//                mTitle = getString(R.string.title_section2);
//                break;
//            case 3:
//                mTitle = getString(R.string.title_section3);
//                break;
//            case 4:
//                mTitle = getString(R.string.title_section4);
//                break;
//            case 5:
//                mTitle = getString(R.string.title_section5);
//                break;
//            case 6:
//                mTitle = getString(R.string.title_section6);
//                break;
//        }
//    }
//
//    public void restoreActionBar() {
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
//        actionBar.setDisplayShowTitleEnabled(true);
//        actionBar.setTitle(mTitle);
//    }
//
//    /**
//     * A placeholder fragment containing a simple view.
//     */
//    public static class PlaceholderFragment extends Fragment {
//        /**
//         * The fragment argument representing the section number for this
//         * fragment.
//         */
//        private static final String ARG_SECTION_NUMBER = "section_number";
//
//        /**
//         * Returns a new instance of this fragment for the given section
//         * number.
//         */
//        public static PlaceholderFragment newInstance(int sectionNumber) {
//            PlaceholderFragment fragment = new PlaceholderFragment();
//            Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//            fragment.setArguments(args);
//            return fragment;
//        }
//
//        public PlaceholderFragment() {
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_home, container, false);
//            return rootView;
//        }
//
//        @Override
//        public void onAttach(Activity activity) {
//            super.onAttach(activity);
//            ((HomeActivity) activity).onSectionAttached(
//                    getArguments().getInt(ARG_SECTION_NUMBER));
//        }
//    }
//
//    @Override
//    public void onFragmentInteraction(Uri uri)
//    {
//
//
//    }
}
