package com.lyp.neulife.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lyp.neulife.R;
import com.lyp.neulife.bean.BeanCampusGrid;

import java.util.List;

/**
 * Created by lyp on 2016/4/7.
 */
public class GridWithImgAdapter extends ArrayAdapter<BeanCampusGrid>{

    private int layoutId;

    public GridWithImgAdapter(Context context, int resource, List<BeanCampusGrid> objects) {
        super(context, resource, objects);
        this.layoutId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BeanCampusGrid bean = getItem(position);
        GridViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(layoutId, null, false);
            holder = new GridViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.campus_grid_img);
            holder.name = (TextView) convertView.findViewById(R.id.campus_grid_name);
            convertView.setTag(holder);
        } else {
            holder = (GridViewHolder) convertView.getTag();
        }

        holder.image.setImageResource(bean.getCampusGridImgID());
        holder.name.setText(bean.getCampusGridName());

        return convertView;
    }

    class GridViewHolder {
        ImageView image;
        TextView name;
    }
}
