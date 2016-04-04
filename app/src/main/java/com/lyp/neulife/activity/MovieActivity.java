package com.lyp.neulife.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lyp.neulife.R;
import com.lyp.neulife.adapter.MovieRcAdapter;
import com.lyp.neulife.bean.BeanMovie;
import com.lyp.neulife.util.JsonURL;
import com.lyp.neulife.util.NetworkUtil;
import com.lyp.neulife.util.ViewUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MovieActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView movieRcView;
    private Toolbar movieToolbar;
    private SwipeRefreshLayout movieSwipeRefreshLayout;

    private MovieRcAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.setTranslucentStatusBar(this);
        setContentView(R.layout.activity_movie);


        initView();
        initData();
        initEvent();
    }

    private void initView() {

        movieToolbar = (Toolbar) findViewById(R.id.movieToolbar);
        setSupportActionBar(movieToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        movieRcView = (RecyclerView) findViewById(R.id.movieRcView);
        movieRcView.setLayoutManager(new GridLayoutManager(this,2));

        movieSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.movieSwipeRefreshLayout);
        movieSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#03a9f4"), Color.parseColor("#2196f3"));
    }

    private void initData() {
//        movieDatas = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            BeanMovie bean = new BeanMovie("影视"+i, "", (int)(Math.random()*50)+"", "类型"+i);
//            if (i % 2 == 0) {
//                bean.setMovieImgLocal(R.drawable.img_bg_01);
//            } else {
//                bean.setMovieImgLocal(R.drawable.img_bg_02);
//            }
//
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), bean.getMovieImgLocal());
//            Palette.Builder builder = Palette.from(bitmap);
//            int color = builder.generate().getVibrantColor(getResources().getColor(R.color.colorAccent));
//            bean.setPaletteColor(color);
//
//            movieDatas.add(bean);
//        }
//        adapter = new MovieRcAdapter();
//        movieRcView.setAdapter(adapter);

        Toast.makeText(MovieActivity.this, "加载中...", Toast.LENGTH_SHORT).show();
        new MovieAsyncTask().execute(JsonURL.MOVIE_JSON_URL);
    }

    private void initEvent() {
        movieToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        movieSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        movieSwipeRefreshLayout.setRefreshing(true);

        new MovieAsyncTask().execute(JsonURL.MOVIE_JSON_URL);
    }



    /**
     * JSON的解析与数据封装
     * @param url
     * @return
     */
    private List<BeanMovie> getJsonDatas(String url) {
        List<BeanMovie> datas = new ArrayList<>();
        try {
            String jsonString = NetworkUtil.readStream(new URL(url).openStream());
            Log.d("LYP", jsonString);

            BeanMovie bean;
            String movieName = null;
            String movieType = null;
            String movieDuration = null;
            String movieImgUrl = null;

            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                movieName = jsonObject.getString("name");
                movieType = jsonObject.getString("description");
                movieImgUrl = jsonObject.getString("picSmall");
                movieDuration = jsonObject.getString("learner");

                Bitmap bitmap = BitmapFactory.decodeStream(new URL(movieImgUrl).openStream());
                Palette.Builder builder = Palette.from(bitmap);
                int color = builder.generate().getVibrantColor(getResources().getColor(R.color.colorAccent));

                bean = new BeanMovie(movieName, movieImgUrl, movieDuration, movieType);
                bean.setPaletteColor(color);

                datas.add(bean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datas;
    }


    /**
     * 网络数据异步访问
     */
    class MovieAsyncTask extends AsyncTask<String, Void, List<BeanMovie>> {

        @Override
        protected List<BeanMovie> doInBackground(String... params) {
            return getJsonDatas(params[0]);
        }

        @Override
        protected void onPostExecute(List<BeanMovie> movieDatas) {
            super.onPostExecute(movieDatas);
            Log.d("LYP", "MovieDataNum = "+movieDatas.size());

            adapter = new MovieRcAdapter(MovieActivity.this, movieDatas);
            movieRcView.setAdapter(adapter);

//            if (movieRcView == null) {
//                adapter = new MovieRcAdapter();
//                movieRcView.setAdapter(adapter);
//            } else {
//                adapter.notifyDataSetChanged();
//            }

            movieSwipeRefreshLayout.setRefreshing(false);
            Toast.makeText(MovieActivity.this, "刷新成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
