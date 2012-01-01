package com.alexgames;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class BabyScroll extends  ListActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CircularArrayAdapter<String> adapter = new CircularArrayAdapter<String>(this, R.layout.list_item, ABCs);
        setListAdapter(adapter);

        ListView lv = getListView();
        lv.setTextFilterEnabled(true);

        lv.setSelectionFromTop(adapter.MIDDLE, 0);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    static final String[] ABCs = new String[] {
            "A","Z","Y","X","W","V","U","T","S","R","Q",
            "P","O","N","M","L","K","J","I","H","G","F",
            "E","D","C","B"
    };
}
