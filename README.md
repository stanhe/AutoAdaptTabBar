## AutoAdaptTabBar

For auto adapt tabBar with deffrent tab size.

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
        style.set......
        titleBar.setStyle(style);
        titleBar.setShowGapView(false);
        titleBar.setScrollDirection(AutoAdaptTabBar.SCROLL_DIRECTION_L);
        titleBar.initTabs(title,"no data");  // do after settings done.
        titleBar.setTabClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "current id: "+(int)v.getTag(), Toast.LENGTH_SHORT).show();
            }
        });
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
	         compile 'com.github.stanhe:AutoAdaptTabBar:1.1.0'
	}
 ```
### Effect
![stan](http://oanvj2lsv.bkt.clouddn.com/image/gif/share/autoAdaptBar.gif)

### Update log

##### v 1.1.0:
> fix setItemClick no scroll when init view.

##### v 1.0.8:
> set Over Scroll Mode never,to reduce detection time when click with fling .
