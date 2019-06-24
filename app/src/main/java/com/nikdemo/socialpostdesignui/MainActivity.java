package com.nikdemo.socialpostdesignui;

import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nikdemo.socialpostdesignui.adapter.FeedAdapter;
import com.nikdemo.socialpostdesignui.menu.DrawerAdapter;
import com.nikdemo.socialpostdesignui.menu.DrawerItem;
import com.nikdemo.socialpostdesignui.menu.SimpleItem;
import com.nikdemo.socialpostdesignui.model.FeedModel;
import com.nikdemo.socialpostdesignui.utils.BaseActivity;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;
import com.yarolegovich.slidingrootnav.callback.DragStateListener;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends BaseActivity implements DrawerAdapter.OnItemSelectedListener, FeedAdapter.setOnFeedClickListener {

    public static SlidingRootNav slidingRootNav;
    private Toolbar toolBar;
    private String[] screenTitles;
    private Drawable[] screenIcons;
    private com.yarolegovich.slidingrootnav.SlidingRootNavBuilder SlidingRootNavBuilder;
    private RecyclerView rl_feed;

    private ArrayList<FeedModel> feedList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        SlidingRootNavBuilder = new SlidingRootNavBuilder(this);
        slidingRootNav = SlidingRootNavBuilder.withToolbarMenuToggle(toolBar)
                .withMenuOpened(false)
                .withDragDistance(150)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .withContentClickableWhenMenuOpened(false)
                .addDragStateListener(new DragStateListener() {
                    @Override
                    public void onDragStart() {
                    }

                    @Override
                    public void onDragEnd(boolean isMenuOpened) {
                        if (isMenuOpened) {
                            toolBar.setNavigationIcon(R.drawable.menu_back);
                        } else {
                            toolBar.setNavigationIcon(R.drawable.menu_open);
                        }
                    }
                })
                .inject();
        toolBar.setNavigationIcon(R.drawable.menu_open);
        setMenu();
    }

    private ArrayList<FeedModel> getFeedList() {
        ArrayList<FeedModel> list = new ArrayList<>();
        list.add(new FeedModel(R.drawable.profile1,
                R.drawable.a, "Ravi Patel", "2 hour ago", "Articles are words that define a noun as specific or unspecific. Consider the following examples",
                40, 500, 64));
        list.add(new FeedModel(R.drawable.profile2,
                R.drawable.b, "Payal Mavani", "8 hour ago", "After the long day, the cup of tea tasted particularly good.",
                32, 120, 12));
        list.add(new FeedModel(R.drawable.profile3,
                R.drawable.c, "Kajal Bela", "1 week ago", "By using the article",
                400, 842, 216));
        list.add(new FeedModel(R.drawable.profile1,
                R.drawable.d, "Nikunj Patel", "2 day ago", "Please give me the hammer.",
                85, 725, 22));

        return list;
    }

    private void initUI() {
        toolBar = findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);

        rl_feed = findViewById(R.id.rl_feed);
        rl_feed.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        rl_feed.setAdapter(new FeedAdapter(this, getFeedList(), this));

    }

    public void setMenu() {
        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();
        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(0),
                createItemFor(1),
                createItemFor(2),
                createItemFor(3),
                createItemFor(4),
                createItemFor(5)));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

      /*  ((TextView) findViewById(R.id.tv_name)).setText(tinyDB.getString(Constants.USER_NAME));
        ((TextView) findViewById(R.id.tv_email)).setText(tinyDB.getString(Constants.USER_EMAIL));
*/
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(android.R.color.white))
                .withTextTint(color(android.R.color.white))
                .withSelectedIconTint(color(R.color.purple1))
                .withSelectedTextTint(color(R.color.purple1));
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }

    @Override
    public void onItemSelected(int position) {
        switch (position) {
            case 1: {
                /*fr = new DashboardFragment();
                FragmentTransaction fragmentTransaction =
                        getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, fr);
                fragmentTransaction.commit();*/
                break;
            }
        }
        slidingRootNav.closeMenu();
    }

    @Override
    public void onFeedClick(int index, FeedModel model) {

    }

}
