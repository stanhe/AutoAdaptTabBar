# AndroidLibrary_AutoAdaptTabBar

### AutoAdaptTabBar
For auto adapt tabBar

### code
used as tabBar,you just need provide a title list.
```
 final AutoAdaptTabBar titleBar = (AutoAdaptTabBar) findViewById(R.id.titleBar);
        List<String> title = new ArrayList<String >(){{add("tab0");add("news1");add("hello2");add("kety");add("god");add("killing");}};
        AutoAdaptTabBar.Style style = new AutoAdaptTabBar.Style();

//        style.setContainerBg(getResources().getColor(android.R.color.holo_orange_light));
//        style.setTextSize(20);
//        style.setTextColor(getResources().getColor(android.R.color.white));
//        style.setClickColor(getResources().getColor(android.R.color.holo_green_light));
//        style.setGapColor(getResources().getColor(android.R.color.holo_blue_bright));

        style.setShowItems(4);
        titleBar.setClickColor(getResources().getColor(android.R.color.holo_orange_light));
        titleBar.setStyle(style);
        titleBar.setShowGapView(false);
        titleBar.initTabs(title,"no data");
        titleBar.setScrollDirection(AutoAdaptTabBar.SCROLL_DIRECTION_L);
```
