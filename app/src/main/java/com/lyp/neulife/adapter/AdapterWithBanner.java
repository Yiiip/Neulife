package com.lyp.neulife.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.lyp.neulife.R;
import com.lyp.neulife.activity.MainActivity;
import com.lyp.neulife.bean.BeanBanner;
import com.lyp.neulife.bean.BeanNews;
import com.lyp.neulife.view.LocalImageHolderView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyp on 2016/3/21.
 */
public class AdapterWithBanner extends BaseAdapter implements OnItemClickListener, View.OnClickListener{

    private static final int TYPE_BANNER = 0;
    private static final int TYPE_LIST = 1;

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private List<BeanBanner> mBannerDatas;
    private List<BeanNews> mListDatas;

    //Banner图片
    int[] localImgIDs = {R.drawable.img_bg_02, R.drawable.img_bg_01};
    ArrayList<Integer> localImages;

    public AdapterWithBanner(Context context, List<BeanNews> datas) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mListDatas = datas;

    }

    @Override
    public int getItemViewType(int position) {
        int type;
        if(position == 0){
            type = TYPE_BANNER;
        } else {
            type = TYPE_LIST;
        }
        return type;

    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return mListDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mListDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        switch (getItemViewType(position)) {
            case TYPE_BANNER:
                ViewHolderBanner viewHolderBanner = null;
                if (convertView == null) {
                    convertView = mLayoutInflater.inflate(R.layout.home_header, parent, false);

                    viewHolderBanner = new ViewHolderBanner();
                    viewHolderBanner.btFun1 = (LinearLayout) convertView.findViewById(R.id.btFun1);
                    viewHolderBanner.btFun2 = (LinearLayout) convertView.findViewById(R.id.btFun2);
                    viewHolderBanner.btFun3 = (LinearLayout) convertView.findViewById(R.id.btFun3);
                    viewHolderBanner.btFun4 = (LinearLayout) convertView.findViewById(R.id.btFun4);
                    viewHolderBanner.convenientBanner = (ConvenientBanner) convertView.findViewById(R.id.homeBanner);

                    convertView.setTag(viewHolderBanner);
                } else {
                    viewHolderBanner = (ViewHolderBanner) convertView.getTag();
                }

                localImages = new ArrayList<Integer>();
                for (int i = 0; i < localImgIDs.length; i++) {
                    localImages.add(localImgIDs[i]);
                }

                viewHolderBanner.btFun1.setOnClickListener(this);
                viewHolderBanner.btFun2.setOnClickListener(this);
                viewHolderBanner.btFun3.setOnClickListener(this);
                viewHolderBanner.btFun4.setOnClickListener(this);
                viewHolderBanner.convenientBanner
                        .setPages(new CBViewHolderCreator<LocalImageHolderView>() {
                            @Override
                            public LocalImageHolderView createHolder() {
                                return new LocalImageHolderView();
                            }
                        }, localImages)
                        .startTurning(6000)
                        .setOnItemClickListener(this);
                break;

            case TYPE_LIST:
                ViewHolderList viewHolderList = null;
                if (convertView == null) {
                    convertView = mLayoutInflater.inflate(R.layout.item_home_news, parent, false);

                    viewHolderList = new ViewHolderList();
                    viewHolderList.title = (TextView) convertView.findViewById(R.id.news_title);

                    convertView.setTag(viewHolderList);
                } else {
                    viewHolderList = (ViewHolderList) convertView.getTag();
                }

                BeanNews beanNews = (BeanNews) getItem(position);
                viewHolderList.title.setText(beanNews.getTitle());
                break;
        }

        return convertView;
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(mContext, "点击了第"+position+"个", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btFun1:
                Toast.makeText(mContext, "点击1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btFun2:
                Toast.makeText(mContext, "点击2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btFun3:
                Toast.makeText(mContext, "点击3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btFun4:
                MainActivity.getMainViewPager().setCurrentItem(1);
                break;
        }
    }


    private class ViewHolderBanner {
        LinearLayout btFun1, btFun2, btFun3, btFun4;
        ConvenientBanner convenientBanner;
    }

    private class ViewHolderList {
        TextView title;
    }


    /**
     * 通过文件名获取资源id 例子：getResId("icon", R.drawable.class);
     *
     * @param variableName
     * @param c
     * @return
     */
    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
