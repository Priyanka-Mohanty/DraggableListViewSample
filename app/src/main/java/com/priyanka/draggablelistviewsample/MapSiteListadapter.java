package com.priyanka.draggablelistviewsample;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.takaiwa.draggablelistviewsample.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MapSiteListadapter extends BaseAdapter {

    public static List<String> SiteIdNameList =new ArrayList<>();
    List<String> distanceList =new ArrayList<>();
    Activity context;
    public static boolean[] itemChecked;

    public MapSiteListadapter(Activity context, ArrayList <String> SiteIdNameList,ArrayList<String> distanceList) {
        super();
        this.context = context;
        this.SiteIdNameList = SiteIdNameList;
        this.distanceList=distanceList;
        itemChecked = new boolean[SiteIdNameList.size()];
    }

    private class ViewHolder {
        TextView textSiteIDName;
        CheckBox checkbox;
        TextView textViewDistance;
    }

    public int getCount() {
        return SiteIdNameList.size();
    }

    public Object getItem(int position) {
        return SiteIdNameList.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        LayoutInflater inflater = context.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.map_site_list_item, null);
            holder = new ViewHolder();

            holder.textSiteIDName = (TextView) convertView
                    .findViewById(R.id.text_name);
            holder.checkbox = (CheckBox) convertView
                    .findViewById(R.id.checkBox1);
            holder.textViewDistance=(TextView)convertView.findViewById(R.id.text_distance) ;
            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        holder.textSiteIDName.setText(SiteIdNameList.get(position));
        holder.checkbox.setChecked(false);
        holder.textViewDistance.setText(distanceList.get(position));


        if (itemChecked[position])
            holder.checkbox.setChecked(true);
        else
            holder.checkbox.setChecked(false);

        holder.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (holder.checkbox.isChecked())
                    itemChecked[position] = true;
                else
                    itemChecked[position] = false;
            }
        });

        return convertView;

    }



}