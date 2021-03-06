package com.stan.adefault.adaptertitlebar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.view.MotionEvent.ACTION_UP;

/**
 * Created by stan on 16/10/10.
 */

public class AutoAdaptTabBar extends FrameLayout {

    /**
     *  tabContainer
     */
    private HorizontalScrollView tabContainer;
    /**
     *  the tab No. to show
     */
    private int showItems=DEFAULT_ITEMS;
    /**
     *  tabData
     */
    private List<String> mChildList;

    private Context mContext;
    /**
     * preTab
     */
    private TextView preTab;
    /**
     * click tab color
     */
    private int clickColor;
    /**
     * tab text color
     */
    private int textColor;
    /**
     * tab text color
     */
    private int textBg;
    /**
     * gap view color
     */
    private int gapColor;
    /**
     * gap width
     */
    private int gapViewWidth;
    /**
     * tab textSize
     */
    private int textSize=DEFAULT;
    /**
     * showGapView
     */
    private boolean showGapView=true;
    /**
     * scroll direction
     */
    private int scrollDirection = SCROLL_DIRECTION_L;
    /**
     * clickListener
     */
    private View.OnClickListener clickListener;
    /**
     * style
     */
    private Style style;
    /**
     * screenWidth
     */
    private int screenWidth;
    /**
     * default value
     */
    public static final int DEFAULT = 0;
    public static final int OK = 1;
    public static final int DEFAULT_ITEMS = 2;
    /**
     * scroll direction
     */
    public static final int SCROLL_DIRECTION_R = 1;
    public static final int SCROLL_DIRECTION_L = 0;
    public static final int SCROLL_DIRECTION_N = -1;


    public AutoAdaptTabBar(Context context) {
        super(context);
        mContext = context;
        initView();
    }
    public AutoAdaptTabBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public AutoAdaptTabBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    public AutoAdaptTabBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        initView();
    }

    /**
     * add HorizontalScrollView
     */
    private void initView() {
        HorizontalScrollView hsv = new HorizontalScrollView(mContext);
        hsv.setScrollBarSize(0);
        hsv.setOverScrollMode(OVER_SCROLL_NEVER);
        hsv.setBackgroundColor(mContext.getResources().getColor(android.R.color.white));
        hsv.setOnTouchListener(scrollTouchListener);
        hsv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tabContainer = hsv;
        addView(hsv);

        textColor = mContext.getResources().getColor(android.R.color.darker_gray);
        textBg = mContext.getResources().getColor(android.R.color.transparent);
        gapColor = textColor;
        clickColor = mContext.getResources().getColor(R.color.click_default);
        screenWidth = DisplayUtils.getScreenWidth(mContext);
    }

    /**
     * define the tab style
     */
    public static class Style{
        private int containerBgColor;
        private Drawable containerBg;
        private int textColor;
        private int clickColor;
        private int textSize;
        private boolean showGapView;
        private int gapColor;
        private int scrollDirection;
        private int showItems;

        public Style() {
        }

        public int getContainerBg() {
            return containerBgColor;
        }

        public void setContainerBg(int containerBg) {
            this.containerBgColor = containerBg;
        }

        public int getTextColor() {
            return textColor;
        }

        public void setTextColor(int textColor) {
            this.textColor = textColor;
        }

        public int getTextSize() {
            return textSize;
        }

        public void setTextSize(int textSize) {
            this.textSize = textSize;
        }

        public void setClickColor(int clickColor) {
            this.clickColor = clickColor;
        }
        public int getClickColor() {
            return clickColor;
        }

        public void setGapColor(int gapColor) {
            this.gapColor = gapColor;
        }

        public int getGapColor() {
            return gapColor;
        }

        public void setScrollDirection(int scrollDirection) {
            this.scrollDirection = scrollDirection;
        }

        public int getScrollDirection() {
            return scrollDirection;
        }

        public void setShowGapView(boolean showGapView) {
            this.showGapView = showGapView;
        }

        public boolean isShowGapView() {
            return showGapView;
        }

        public void setShowItems(int showItems) {
            this.showItems = showItems;
        }

        public int getShowItems() {
            return showItems;
        }

        public void setContainerBg(Drawable containerBg) {
            this.containerBg = containerBg;
        }

        public int getContainerBgColor() {
            return containerBgColor;
        }
    }

    /**
     * set style
     */
    private void handleStyle() {
        if (this.style==null)return;
        if (style.containerBgColor!=0)
            setContainerBgColor(style.containerBgColor);
        if (style.containerBg!=null)
            setContainerBg(style.containerBg);
        if (style.textColor!=0)
            setTextColor(style.textColor);
        if (style.clickColor!=0)
            setClickColor(style.clickColor);
        if (style.textSize!=0)
            setTextSize(style.textSize);
        if (style.gapColor!=0)
            setGapColor(style.gapColor);
        if (style.showItems!=0)
            setShowItems(style.showItems);
        setShowGapView(style.showGapView);
        setScrollDirection(style.scrollDirection);
    }

    /**
     *
     * @param childList the tab list
     */
    public void initTabs(List<String> childList,String defaultS) {
        if ((childList==null || childList.size()<1) && TextUtils.isEmpty(defaultS))return;
        if ((childList==null || childList.size()<1) && !TextUtils.isEmpty(defaultS)){
            childList = new ArrayList<>();
            childList.add(defaultS);
        }
        tabContainer.removeAllViews();
        mChildList = childList;
        if (showItems>childList.size())
            showItems = childList.size();
        if (showItems<1)
            showItems = 1;
        this.setVisibility(View.VISIBLE);
        int childSize = childList.size();
        LinearLayout container = new LinearLayout(mContext);
        container.setOrientation(LinearLayout.HORIZONTAL);
        container.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        for (int i=0;i<childSize;i++){
            TextView textView = new TextView(mContext);
            textView.setTextColor(textColor);
            textView.setText(childList.get(i));
            textView.setBackgroundColor(textBg);
            if (textSize!=DEFAULT)
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);
            LinearLayout.LayoutParams params;
            if (childSize==1){
                params = new LinearLayout.LayoutParams(screenWidth, ViewGroup.LayoutParams.MATCH_PARENT);
            }else {
                params = new LinearLayout.LayoutParams(screenWidth/showItems, ViewGroup.LayoutParams.MATCH_PARENT);
            }
            textView.setLayoutParams(params);
            textView.setSingleLine(true);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setGravity(Gravity.CENTER);
            if (i==0) {
                textView.setTextColor(this.clickColor);
                preTab = textView;
            }
            textView.setTag(i);
            textView.setOnClickListener(tabClickListener);
            container.addView(textView);
            if (i<childSize-1 && showGapView){
                final TextView line = new TextView(mContext);
                line.setText("|");
                line.setTextColor(gapColor);
                line.setBackgroundColor(textBg);
                container.addView(line);
                if (i==0 && childSize>1) {
                    line.post(new Runnable() {
                        @Override
                        public void run() {
                            gapViewWidth = line.getWidth();
                        }
                    });
                }
            }else if (!showGapView){
                gapViewWidth = 0;
            }
        }
        tabContainer.addView(container);
    }

    /**
     * tab listener
     */
    View.OnClickListener tabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setCurrentTab((int) v.getTag(),true);
            if (clickListener!=null){
                clickListener.onClick(v);
            }
        }
    };
    /**
     * tab touch listener
     */
    private int scrollX;
    OnTouchListener scrollTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                case ACTION_UP:
                    scrollX = tabContainer.getScrollX();
                    checkStopped();
                    break;
            }
            return false;
        }
    };
    /**
     *  listen scrollView stop event
     */
    private void checkStopped() {
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                    switch (msg.what){
                            case OK:
                                int halfSize = (screenWidth / showItems + gapViewWidth);
                                int scrolledX = Math.abs(scrollX);
                                int n = scrollX / halfSize;
                                if (Math.abs(scrolledX - n * halfSize) > (halfSize / 2))
                                    n += 1;
                                setCurrentTab(n,false);
                                break;
                        }
            }
        };
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int currentX = tabContainer.getScrollX();
                if (scrollX!=currentX)
                    scrollX = currentX;
                else {
                    timer.cancel();
                    handler.sendEmptyMessage(OK);
                }

            }
        },50,100);

    }
    /**
     *
     * @param position tab position
     * @param chooseItem is selected
     */
    public void setCurrentTab(final int position, final boolean chooseItem, int delay){
        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                setCurrentTab(position,chooseItem);
            }
        },delay);
    }
    /**
     *
     * @param position tab position
     * @param chooseItem is selected
     */
    private void setCurrentTab(int position,boolean chooseItem){
        if (position<0 || position>mChildList.size())
            return;
        if (chooseItem) {
            TextView currentTab = (TextView) tabContainer.findViewWithTag(position);
            preTab.setTextColor(textColor);
            preTab = currentTab;
            preTab.setTextColor(clickColor);
        }
        if (mChildList!=null && mChildList.size()>2 && scrollDirection!=SCROLL_DIRECTION_N) {
            if (position < mChildList.size()-1){
                tabContainer.smoothScrollTo((screenWidth/showItems+gapViewWidth)*(position-scrollDirection), 0);
            }else if (position == mChildList.size()-1){
                if (showItems==1)
                    position+=1;
                tabContainer.smoothScrollTo((screenWidth/showItems+gapViewWidth)*(position-1), 0);
            }
        }
    }

    public void setTabClickListener(OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setContainerBgColor(int bgColor){
        tabContainer.setBackgroundColor(bgColor);
    }
    public void setContainerBg(Drawable d){
        tabContainer.setBackground(d);
    }
    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void setShowGapView(boolean showGapView) {
        this.showGapView = showGapView;
    }

    public void setShowItems(int showItems) {
        this.showItems = showItems;
    }

    public void setClickColor(int clickColor) {
        this.clickColor = clickColor;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setGapColor(int gapColor) {
        this.gapColor = gapColor;
    }

    public void setScrollDirection(int scrollDirection) {
        if (scrollDirection>0)
            this.scrollDirection = SCROLL_DIRECTION_R;
        else if (scrollDirection<0)
            this.scrollDirection = SCROLL_DIRECTION_N;
        else
            this.scrollDirection = SCROLL_DIRECTION_L;
    }

    public void setStyle(Style style) {
        this.style = style;
        handleStyle();
    }
}
