package com.example.apttitude_crack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class General_knowledge_Adepter extends BaseAdapter {
    private Context mContext;
    private final String[] web;
    private final int[] imageid;

    public General_knowledge_Adepter(Context c, String[] web, int[] Imageid) {
        mContext = c;
        this.imageid = Imageid;
        this.web = web;
    }

    @Override
    public int getCount() {
        return web.length;
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
        View inflate = layoutInflater.inflate(R.layout.general_knowledge_adepter_layout, null);
        TextView textView = inflate.findViewById(R.id.genraltext);
        ImageView imageView = inflate.findViewById(R.id.genralimg);
        textView.setText(web[position]);
        textView.setSelected(true);
        imageView.setImageResource(imageid[position]);
//}
//       else
//       {
//           view =(View) convertView;
//       }

        return inflate;
    }
}
