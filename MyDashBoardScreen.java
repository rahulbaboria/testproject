package com.loyality.testproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.loyality.testproject.navigation.NavigationDrawerFragment;

/**
 * Created by Rohit Gupta on 27-01-2017.
 */
public class MyDashBoardScreen extends AppCompatActivity implements NavigationDrawerFragment.FragmentDrawerListener {
    private Fragment lastFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dashboard);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        displayView(0);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    public void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        String Tag = "";
        switch (position) {
            case 0:
                fragment = new MyDashBoardFragment();
                title = getString(R.string.nav_item_my_dashboard);
                Tag = "MyDashboardFragment";
                break;
            case 1:
                fragment = new ProfileFragment();
                title = getString(R.string.nav_item_my_profile);
                Tag = "ProfileFragment";
                break;
            case 2:
                fragment = new ProfileFragment();
                title = getString(R.string.nav_item_add_item);
                Tag = "AddItemFragment";
                break;
            case 3:
//              Code for SignOut
                clearUserDetails();
//                intent for signout;
                break;
            default:
                break;
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (lastFragment != null) {
                fragmentTransaction.remove(lastFragment);
                fragmentManager.popBackStack();
            }
            fragmentTransaction.add(R.id.container_body, fragment, Tag);
            fragmentTransaction.commit();
            lastFragment = fragment;
            TextView toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
            toolbarTitle.setText(title);
            // set the toolbar title
            // getSupportActionBar().setTitle(title);
        }
    }

    private void clearUserDetails() {


    }

    @Override
    public void onBackPressed() {


        Fragment fragment = new MyDashBoardFragment();
        String title = getString(R.string.nav_item_my_dashboard);
        String Tag = "MyDashboardFragment";
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (lastFragment instanceof MyDashBoardFragment) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            if (fragment != null) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if (lastFragment != null) {
                    fragmentTransaction.remove(lastFragment);
                    fragmentManager.popBackStack();
                }
                fragmentTransaction.add(R.id.container_body, fragment, Tag);
                fragmentTransaction.commit();
                lastFragment = fragment;
                TextView toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
                toolbarTitle.setText(title);
                // set the toolbar title
                //getSupportActionBar().setTitle(title);
            }
        }
    }
}
