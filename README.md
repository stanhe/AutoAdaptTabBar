## AutoAdaptTabBar

### AutoAdaptTabBar

For auto adapt tabBar

### use

used as tabBar,you just need provide a title list.
#### xml
```
<com.stan.adefault.adaptertitlebar.AutoAdaptTabBar
        android:id="@+id/titleBar"
        android:background="@android:color/holo_green_dark"
        android:layout_width="match_parent"
        android:layout_height="60dp"/>
```	
#### code
```
   	AutoAdaptTabBar titleBar = (AutoAdaptTabBar) findViewById(R.id.titleBar);
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
### Add dependency

1. Add it in your root build.gradle at the end of repositories:
```
allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```
2. Add the dependency

```
dependencies {
	         compile 'com.github.stanhe:AutoAdaptTabBar:1.0.0'
	}
 ```
### Effect
![stan](http://oanvj2lsv.bkt.clouddn.com/image/git/sharesharegit.png)
