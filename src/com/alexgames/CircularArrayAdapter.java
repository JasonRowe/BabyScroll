package com.alexgames;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CircularArrayAdapter<T> extends ArrayAdapter
{

    public static final int HALF_MAX_VALUE = Integer.MAX_VALUE/2;
    public final int MIDDLE;
    private ArrayList<T> objects;

    private Context adapterContext;

    public CircularArrayAdapter(Context context, int textViewResourceId, ArrayList<T> objects)
    {
        super(context, textViewResourceId, objects);
        adapterContext = context;
        this.objects = objects;
        MIDDLE = HALF_MAX_VALUE - HALF_MAX_VALUE % objects.size();
    }

    @Override
    public int getCount()
    {
        return Integer.MAX_VALUE;
    }

    @Override
    public T getItem(int position)
    {
        return objects.get(position % objects.size());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LinearLayout babyScrollView;
        BabyScrollItem scrollItem =(BabyScrollItem) getItem(position);

        //Inflate the view
        if(convertView==null)
        {
            convertView = LayoutInflater.from(adapterContext).inflate(R.layout.list_item, parent, false);
        }

        babyScrollView = (LinearLayout) convertView;

        //Set Text
        TextView babyScrollTextView =(TextView)babyScrollView.findViewById(R.id.txtItem);
        babyScrollTextView.setText(scrollItem.getItemText());

        ImageView babyScrollImageView =(ImageView)babyScrollView.findViewById(R.id.imgItem);
        babyScrollImageView.setImageResource(scrollItem.getImageResource());

        //Todo Set Image
        return babyScrollView;
    }
}