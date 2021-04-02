package com.example.apttitude_crack;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.apttitude_crack.GetterSetter.SubTitleData;
import com.example.apttitude_crack.GetterSetter.TitleData;
import com.squareup.picasso.Picasso;

import java.util.List;

class Arithmetic_aptitude_Adepter extends BaseAdapter {

    private Context mContext;
    List<SubTitleData> subTitleData;

   // private Context mContext;
//   private final String[] web;
//    private final int[] imageid;



    public Arithmetic_aptitude_Adepter(Activity arithmetic_aptitude_activity, List<SubTitleData> subTitleData) {
        mContext = arithmetic_aptitude_activity;
        this.subTitleData = subTitleData;
//        Log.d("My :: ", "************************>>> "+ subTitleData.size());
    }

    @Override
    public int getCount() {
        return subTitleData.size();
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

//       View view;


        // if (convertView!=null)
        // {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//           view= new View(mContext);
        View inflate = layoutInflater.inflate(R.layout.arithmetic_aptitude_adepter_layout, null);

        TextView text = inflate.findViewById(R.id.text);
        TextView textquestion = inflate.findViewById(R.id.textquestion);

        ProgressBar progress_bar_item_image = (ProgressBar) inflate.findViewById(R.id.progress_bar_item_image);
        TextView progress_percentage = (TextView) inflate.findViewById(R.id.text_view_progress_image_item);
        Typeface custom_font = Typeface.createFromAsset(mContext.getAssets(),"GothamMedium.ttf");
        text.setTypeface(custom_font);
        textquestion.setTypeface(custom_font);

//        ImageView ariimg = inflate.findViewById(R.id.ariimg);


        text.setText(subTitleData.get(position).getSubTitleName());
        textquestion.setText("Total Question"+ subTitleData.get(position).getTquestion()+" | "+"Visited:"+subTitleData.get(position).getVisited());
        text.setSelected(true);

        int percentage = (Integer) (Integer.parseInt(subTitleData.get(position).getVisited()) * 100 ) / Integer.parseInt(subTitleData.get(position).getTquestion());
        progress_percentage.setText(percentage+"%");
        progress_bar_item_image.setProgress(percentage);
        //ariimg.setImageResource(imageid[position]);

        //Picasso.with(mContext).load(subTitleData.get(position).getSubtitleimg()).placeholder(R.drawable.biochemistry).error(R.drawable.download).into(ariimg);


//}
//       else
//       {
//           view =(View) convertView;
//       }

        return inflate;
    }
}
