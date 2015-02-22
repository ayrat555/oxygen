package com.badykov.ayrat.swkazan;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class SportActivity extends ActionBarActivity {

    private static final String[] sportNames={"Воркаут","Футбол","Баскетбол","Беговые дорожки"};
    private static final int[] sportImages={R.drawable.workout, R.drawable.soccer, R.drawable.basketball, R.drawable.running};
    private ListView toPopulateWithDisticts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);
        setDistrictName();
        populateListviewWithDistricts();
        setOnListViewItemClickAction();
    }

    private void populateListviewWithDistricts(){

        toPopulateWithDisticts = (ListView) findViewById(R.id.list_view);
        ArrayList<NamedImage> sports=getSportsNamesWithImages();

        TextWithImageAdapter adapter= new TextWithImageAdapter(this,R.layout.simpletextitem, sports);

        toPopulateWithDisticts.setAdapter(adapter);

    }



    private ArrayList<NamedImage> getSportsNamesWithImages(){
        ArrayList<NamedImage> sports=new ArrayList<NamedImage>();
        for (int i=0;i<sportNames.length;i++){
            NamedImage ni=new NamedImage(sportNames[i],sportImages[i]);
            sports.add(ni);
        }
        return sports;
    }

    private void setOnListViewItemClickAction() {

        toPopulateWithDisticts.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent startedIntent=getIntent();
                String districtName=startedIntent.getStringExtra("DistrictName");
                NamedImage clickedItem=(NamedImage)parent.getItemAtPosition(position);
                String sportName=clickedItem.getName();
                Intent intent = new Intent(SportActivity.this, GroundActivity.class);
                intent.putExtra("DistrictName",districtName);
                intent.putExtra("SportName",sportName);
                SportActivity.this.startActivity(intent);


            }

        });
    }

    private void setDistrictName(){
        Intent startedIntent=getIntent();
        String districtName=startedIntent.getStringExtra("DistrictName");
        TextView toShowDistrictName=(TextView)findViewById(R.id.text);
        toShowDistrictName.setText(districtName);
    }

    public void onDistrictClick(View v){

        Intent intent = new Intent(SportActivity.this, DistrictActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        SportActivity.this.startActivity(intent);

    }





}