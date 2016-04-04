package com.lyp.neulife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lyp.neulife.R;
import com.lyp.neulife.bean.BeanMovie;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by lyp on 2016/3/28.
 */
public class MovieRcAdapter extends RecyclerView.Adapter<MovieRcAdapter.MovieViewHolder> {


    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<BeanMovie> mDatas;

    private ImageLoader mImageLoader = ImageLoader.getInstance();

    public MovieRcAdapter(Context context, List<BeanMovie> datas) {
        this.mContext = context;
        this.mDatas = datas;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MovieRcAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieViewHolder(mLayoutInflater.inflate(R.layout.item_grid_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(MovieRcAdapter.MovieViewHolder holder, int position) {
        BeanMovie bean = mDatas.get(position);
        holder.name.setText(bean.getMovieName());
        holder.type.setText(bean.getMovieType());
        holder.duration.setText(bean.getMovieDuration());
        holder.img.setScaleType(ImageView.ScaleType.FIT_XY);
//        holder.img.setImageResource(bean.getMovieImgLocal());
        mImageLoader.displayImage(bean.getMovieImgUrl(), holder.img);
        holder.textContainer.setBackgroundColor(bean.getPaletteColor());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }



    class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView type;
        TextView duration;
        ImageView img;
        LinearLayout textContainer;
        LinearLayout movieContainer;

        public MovieViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.movieName);
            type = (TextView) itemView.findViewById(R.id.movieType);
            duration = (TextView) itemView.findViewById(R.id.movieDuration);
            img = (ImageView) itemView.findViewById(R.id.movieImg);
            textContainer = (LinearLayout) itemView.findViewById(R.id.movieTextContainer);
            movieContainer = (LinearLayout) itemView.findViewById(R.id.movieContainer);
        }
    }
}
