package com.stan.adefault.d1010;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.stan.adefault.adaptertitlebar.AutoAdaptTabBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final AutoAdaptTabBar titleBar = (AutoAdaptTabBar) findViewById(R.id.titleBar);
        List<String> title = new ArrayList<String >(){{add("tab0");add("news1");add("hello2");add("kety");add("god");add("killing");}};
        AutoAdaptTabBar.Style style = new AutoAdaptTabBar.Style();

//        style.setContainerBg(getResources().getColor(android.R.color.holo_orange_light));
//        style.setTextSize(20);
//        style.setTextColor(getResources().getColor(android.R.color.white));
//        style.setClickColor(getResources().getColor(android.R.color.holo_green_light));
//        style.setGapColor(getResources().getColor(android.R.color.holo_blue_bright));

//        style.setShowItems(4);
//        titleBar.setClickColor(getResources().getColor(android.R.color.holo_orange_light));
//        titleBar.setStyle(style);
        titleBar.setShowGapView(false);
        titleBar.initTabs(title,"no data");
        titleBar.setScrollDirection(AutoAdaptTabBar.SCROLL_DIRECTION_L);

        findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleBar.setCurrentTab(3,true);
            }
        });
        titleBar.setTabClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "current id: "+(int)v.getTag(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
