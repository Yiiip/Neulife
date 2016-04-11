package com.lyp.neulife.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.lyp.neulife.R;
import com.lyp.neulife.activity.MainActivity;
import com.lyp.neulife.activity.MovieActivity;
import com.lyp.neulife.bean.BeanNews;
import com.lyp.neulife.view.LocalImageHolderView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyp on 2016/3/23.
 */
public class HomeRcAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_BANNER_ITEM = 0;
    private static final int TYPE_NEWS_ITEM = 1;
    private static final int TYPE_FOOTER_ITEM = 2;

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<BeanNews> mDatas;

    //Banner图片
    private int[] localImgIDs = {R.drawable.img_banner_02, R.drawable.img_banner_03, R.drawable.img_banner_01};
    private ArrayList<Integer> localImages;

    public HomeRcAdapter(Context context, List<BeanNews> datas) {
        this.mContext = context;
        this.mDatas = datas;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_BANNER_ITEM;
        } else if (position + 1 == getItemCount()) {
            return TYPE_FOOTER_ITEM;
        } else {
            return TYPE_NEWS_ITEM;
        }
    }

    /**
     * 渲染具体的ViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_BANNER_ITEM:
                return new ViewHolderBanner(mLayoutInflater.inflate(R.layout.home_header, parent, false));
            case TYPE_NEWS_ITEM:
                return new ViewHolderNews(mLayoutInflater.inflate(R.layout.item_home_news, parent, false));
            case TYPE_FOOTER_ITEM:
                return new ViewHolderFooter(mLayoutInflater.inflate(R.layout.list_footer, parent, false));
        }
        return null;
    }

    /**
     * 绑定ViewHolder的数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        BeanNews beanNews = null;
        if (position > 0 && position < getItemCount()-1) {
            beanNews = mDatas.get(position - 1);
            if (beanNews == null) {
                return;
            }
        }


        if (holder instanceof ViewHolderNews) {

            ((ViewHolderNews) holder).title.setText(beanNews.getTitle());

        } else if (holder instanceof ViewHolderFooter) {

            ((ViewHolderFooter) holder).footerInfo.setText("加载更多…");

        } else {

            localImages = new ArrayList<Integer>();
            for (int i = 0; i < localImgIDs.length; i++) {
                localImages.add(localImgIDs[i]);
            }

            ((ViewHolderBanner) holder).convenientBanner
                    .setPages(new CBViewHolderCreator<LocalImageHolderView>() {
                        @Override
                        public LocalImageHolderView createHolder() {
                            return new LocalImageHolderView();
                        }
                    }, localImages);

            int[] btImgs = {R.drawable.icon_email_64, R.drawable.icon_schedule_64, R.drawable.icon_leave_64, R.drawable.icon_more_64};
            ((ViewHolderBanner) holder).btImg1.setImageResource(btImgs[0]);
            ((ViewHolderBanner) holder).btImg2.setImageResource(btImgs[1]);
            ((ViewHolderBanner) holder).btImg3.setImageResource(btImgs[2]);
            ((ViewHolderBanner) holder).btImg4.setImageResource(btImgs[3]);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size() + 2;
    }


    /**
     * 轮播项的ViewHolder
     */
    class ViewHolderBanner extends RecyclerView.ViewHolder implements OnItemClickListener, View.OnClickListener {
        LinearLayout btFun1, btFun2, btFun3, btFun4;
        ImageView btImg1, btImg2, btImg3, btImg4;
        ConvenientBanner convenientBanner;
        TextView more;

        public ViewHolderBanner(View itemView) {
            super(itemView);
            btFun1 = (LinearLayout) itemView.findViewById(R.id.btFun1);
            btFun2 = (LinearLayout) itemView.findViewById(R.id.btFun2);
            btFun3 = (LinearLayout) itemView.findViewById(R.id.btFun3);
            btFun4 = (LinearLayout) itemView.findViewById(R.id.btFun4);
            btImg1 = (ImageView) itemView.findViewById(R.id.btImg1);
            btImg2 = (ImageView) itemView.findViewById(R.id.btImg2);
            btImg3 = (ImageView) itemView.findViewById(R.id.btImg3);
            btImg4 = (ImageView) itemView.findViewById(R.id.btImg4);
            convenientBanner = (ConvenientBanner) itemView.findViewById(R.id.homeBanner);
            more = (TextView) itemView.findViewById(R.id.homeMore);

            btFun1.setOnClickListener(this);
            btFun2.setOnClickListener(this);
            btFun3.setOnClickListener(this);
            btFun4.setOnClickListener(this);
            more.setOnClickListener(this);

            convenientBanner
                    .startTurning(6000)
                    .setOnItemClickListener(this);
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
//                    mContext.startActivity(new Intent(mContext, MovieActivity.class));
                    break;
                case R.id.btFun4:
                    MainActivity.getMainViewPager().setCurrentItem(1);
                    break;
                case R.id.homeMore:
                    MainActivity.getMainViewPager().setCurrentItem(1);
                    break;
            }
        }
    }



    /**
     * News项的ViewHolder
     */
    class ViewHolderNews extends RecyclerView.ViewHolder {
        TextView title;
        public ViewHolderNews(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.news_title);
            itemView.findViewById(R.id.news_container).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "News "+getPosition(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    /**
     * Footer的ViewHolder
     */
    class ViewHolderFooter extends RecyclerView.ViewHolder {
        TextView footerInfo;
        public ViewHolderFooter(View itemView) {
            super(itemView);
            footerInfo = (TextView) itemView.findViewById(R.id.footerInfo);
        }
    }

}
