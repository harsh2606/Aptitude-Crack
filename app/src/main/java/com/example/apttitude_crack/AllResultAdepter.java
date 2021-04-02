package com.example.apttitude_crack;



import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

class AllResultAdepter extends BaseAdapter {
    private Context mContext;
    List<Integer> exam_ids;


    public AllResultAdepter(Context c, List<Integer> exam_ids) {
        mContext = c;
        this.exam_ids = exam_ids;


    }

    @Override
    public int getCount() {
        return exam_ids.size();
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
        View itemView = layoutInflater.inflate(R.layout.allresultadepter, null);

        TextView txttotal = itemView.findViewById(R.id.txttotal);
        TextView aq = itemView.findViewById(R.id.aq);
        TextView ri = itemView.findViewById(R.id.ri);
        TextView wr = itemView.findViewById(R.id.wr);
        TextView time = itemView.findViewById(R.id.time);
        TextView marks = itemView.findViewById(R.id.marks);
        TextView txttime = itemView.findViewById(R.id.txttime);
        ImageView test = itemView.findViewById(R.id.test);


        TestAdapter1 mDbHelper = new TestAdapter1(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.GetResult(exam_ids.get(position));
        if (cursor.moveToFirst()) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            String dateString = formatter.format(new Date(cursor.getLong(3)));

            long time_given = cursor.getInt(5);
            if(time_given == 900000)
            {
                test.setImageResource(R.drawable.easy);
//                test.setText("Difficulty:"+"Easy");
            }
            else if(time_given == 720000)
            {
                test.setImageResource(R.drawable.medium);
//                test.setText("Difficulty:"+"Medium");
            }
           else if(time_given == 600000)
            {
                test.setImageResource(R.drawable.hard);
//                test.setText("Difficulty:"+"Hard");
            }

            int not_attp = cursor.getInt(10);
            int wrong_marks = (cursor.getInt(11) - not_attp) * 1;


            int tmark = ((cursor.getInt(12) * 2) - wrong_marks );
            txttotal.setText("" + cursor.getInt(8));
            aq.setText("" +cursor.getInt(9));
            wr.setText("" + cursor.getInt(11));
            ri.setText("" + cursor.getInt(12));
            marks.setText("" + tmark);
            time.setText("" +  dateString);
            txttime.setText("" +  ResultActivity.GetTimeTaken(cursor.getInt(4)));

        }


        mDbHelper.close();

        return itemView;
    }
}
