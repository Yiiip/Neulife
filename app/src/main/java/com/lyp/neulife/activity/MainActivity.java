package com.lyp.neulife.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.lyp.neulife.R;
import com.lyp.neulife.adapter.TabViewPagerAdapter;
import com.lyp.neulife.util.ViewUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private final String IMAGE_CACHE_PATH = "Neusoft/Neulife/imageCache";

    private Toolbar mainToolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private TabLayout mainTab;
    private static ViewPager mainViewPager;
    private TabViewPagerAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.setTranslucentStatusBar(this);
        setContentView(R.layout.activity_main);

        initImageLoader();
        initView();
        initEvent();

    }

    private void initImageLoader() {
        File cacheDir = com.nostra13.universalimageloader.utils.StorageUtils
                .getOwnCacheDirectory(this, IMAGE_CACHE_PATH);

        DisplayImageOptions options = new DisplayImageOptions
                .Builder()
                .showImageOnLoading(R.drawable.img_stub)
                .showImageForEmptyUri(R.drawable.img_stub)
                .showImageOnFail(R.drawable.img_stub)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(this)
                .defaultDisplayImageOptions(options)
                .memoryCache(new LruMemoryCache(12 * 1024 * 1024))
                .memoryCacheSize(12 * 1024 * 1024)
                .diskCacheSize(32 * 1024 * 1024)
                .diskCacheFileCount(100)
                .diskCache(new UnlimitedDiskCache(cacheDir))
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoader.getInstance().init(config);
    }

    private void initView() {
        mainToolbar = (Toolbar) findViewById(R.id.mainToolbar);
        setSupportActionBar(mainToolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mainToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        mainViewPager = (ViewPager) findViewById(R.id.mainViewPager);

        mAdapter = new TabViewPagerAdapter(getSupportFragmentManager()) {
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
            }
        };
        mainViewPager.setAdapter(mAdapter);

        mainTab = (TabLayout) findViewById(R.id.mainTab);
        mainTab.setupWithViewPager(mainViewPager);
    }

    private void initEvent() {
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_search) {
            startActivity(new Intent(this, SearchActivity.class));
            return true;
        } else if (id == R.id.menu_message) {
            Toast.makeText(this, "消息", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_collection) {
            Toast.makeText(this, "item clicked", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_message) {

        } else if (id == R.id.nav_setting) {
            startActivity(new Intent(this, SettingActivity.class));

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_logout) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            final NormalDialog dialog = new NormalDialog(this);
            dialog.content("确定退出程序？")
                    .style(NormalDialog.STYLE_TWO)
                    .titleTextSize(23)
                    .show();
            dialog.setOnBtnClickL(
                    new OnBtnClickL() {
                        @Override
                        public void onBtnClick() {
                            dialog.dismiss();
                        }
                    },
                    new OnBtnClickL() {
                        @Override
                        public void onBtnClick() {
                            dialog.dismiss();
                            killAllActivity();
                        }
                    });
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


    public static ViewPager getMainViewPager() {
        return mainViewPager;
    }
}
