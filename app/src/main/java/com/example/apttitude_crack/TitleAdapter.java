package com.example.apttitude_crack;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apttitude_crack.GetterSetter.TitleData;
import com.squareup.picasso.Picasso;

import java.util.List;

class TitleAdapter extends BaseAdapter {
    private Context mContext;
    List<TitleData> titleData;


    public TitleAdapter(Context c, List<TitleData> titleData) {
        mContext = c;
        this.titleData = titleData;

    }

    @Override
    public int getCount() {
        return titleData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("My :: ", "Title Adpater :: " +position);
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View inflate = layoutInflater.inflate(R.layout.custom_grid, null);
        TextView textView = inflate.findViewById(R.id.grid_text);
        ImageView imageView = inflate.findViewById(R.id.grid_img);
        textView.setText(titleData.get(position).getTitlename());
//        textView.setText(demo[position]);
        textView.setSelected(true);
        Typeface custom_font = Typeface.createFromAsset(mContext.getAssets(),"GothamMedium.ttf");
        textView.setTypeface(custom_font);


        Log.d("My :: ", "Title Adpater :: " +titleData.get(position).getTitleimg());
        Picasso.with(mContext).load(titleData.get(position).getTitleimg()).placeholder(R.drawable.biochemistry).error(R.drawable.download).into(imageView);


        return inflate;


    }
}
