package com.priyanka.draggablelistviewsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.takaiwa.draggablelistviewsample.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    List<Data> list = new ArrayList<Data>();
    ArrayList<String> checkedValue = new ArrayList<String>();
    ArrayList<String> Value2 = new ArrayList<String>();
    ListView mapSiteListView;
    TextView textViewDone;
    CustomListView listView;
    MapSiteListadapter mapSiteListadapter;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (CustomListView) findViewById(R.id.listView1);
        mapSiteListView = (ListView) findViewById(R.id.listView2);
        textViewDone = (TextView) findViewById(R.id.text_done);

        /* list = Arrays.asList(
        new Data("Walking", "06:30 - 07:00", -8860075),
        new Data("Meeting", "09:00 - 10:00", -10725938),
        new Data("Business trip", "10:00 - 17:00", -3254946),
        new Data("Vist dentist", "18:00 - 19:00", -11159858)
                new Data("Walking"),
                new Data("Meeting"),
                new Data("Business trip"),
                new Data("Vist dentist")
        );*/

        list.add(new Data("Walking"));
        list.add(new Data("Meeting"));
        list.add(new Data("Business trip"));
        list.add(new Data("Vist dentist"));

        checkedValue.add("abc");
        checkedValue.add("def");
        checkedValue.add("ghi");
        checkedValue.add("jkl");

        Value2.add("122 km");
        Value2.add("2435 km");
        Value2.add("5454 km");
        Value2.add("877 km");

        mapSiteListadapter = new MapSiteListadapter(this, checkedValue, Value2);
        mapSiteListView.setAdapter(mapSiteListadapter);

        adapter = new Adapter(this, list, new Adapter.Listener() {
            @Override
            public void onGrab(int position, RelativeLayout row) {
                listView.onGrab(position, row);
            }
        });

        listView.setAdapter(adapter);
        listView.setListener(new CustomListView.Listener() {
            @Override
            public void swapElements(int indexOne, int indexTwo) {
                Data temp = list.get(indexOne);
                list.set(indexOne, list.get(indexTwo));
                list.set(indexTwo, temp);
            }
        });

        textViewDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<Data> checkedSiteList = new ArrayList<>();

                ArrayList<Data> checkedDistanceList = new ArrayList<>();

                String str = "";
                for (int i = 0; i < mapSiteListadapter.itemChecked.length; i++) {
                    if (mapSiteListadapter.itemChecked[i] == true) {

                        checkedSiteList.add(new Data(mapSiteListadapter.SiteIdNameList.get(i)));
                        checkedDistanceList.add(new Data(mapSiteListadapter.distanceList.get(i)));
                    }
                }

                list.addAll(checkedSiteList);

                // Now element  is moved one position back
                // So element 30 is removed this time
               /* for(int i=0;i<checkedSiteList.size();i++){
                    Log.d("checkedSiteList","checkedSiteList" +checkedSiteList.get(i));
                    if(checkedValue.contains(checkedSiteList.get(i).title)){
                        checkedValue.remove(i);
                    }

                }*/
               /* for(int i=0;i<checkedDistanceList.size();i++){
                    Value2.remove(i);
                }
*/


                mapSiteListadapter.notifyDataSetChanged();
                adapter.notifyDataSetChanged();

                //  Toast.makeText(RoutePlanningActivity.this,checkedSiteList,Toast.LENGTH_SHORT).show();
            }
        });

    }

}
