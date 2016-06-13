package eu.fiskur.simpleviewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import static eu.fiskur.simpleviewpager.R.drawable.circle;

public class SimpleViewPager extends RelativeLayout {
    private SimpleViewPagerAdapter adapter;

    private Context context;
    private ViewPager viewPager;

    //Circle indicators
    private LinearLayout circleLayout;
    private Drawable selectedCircle = null;
    private Drawable unselectedCircle = null;

    private boolean forceSquare = false;

    public SimpleViewPager(Context context) {
        super(context);
        this.context = context;
        setupViewPager();
    }

    public SimpleViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SimpleViewPager, 0, 0);

        try {
            forceSquare = a.getBoolean(R.styleable.SimpleViewPager_forceSquare, false);
        } finally {
            a.recycle();
        }

        setupViewPager();
    }

    private void setupViewPager() {
        viewPager = new ViewPager(context);
        viewPager.setId(R.id.programmatic_viewpager);
        addView(viewPager);
    }

    public void setImageUrls(String[] imageUrls, ImageURLLoader imageURLLoader){
        adapter = new SimpleViewPagerAdapter(context, imageUrls, imageURLLoader);
        viewPager.setAdapter(adapter);
    }

    public void setImageIds(int[] resourceIds, ImageResourceLoader imageResourceLoader){
        adapter = new SimpleViewPagerAdapter(context, resourceIds, imageResourceLoader);
        viewPager.setAdapter(adapter);
    }

    public void showIndicator(int indicatorColor, int selectedIndicatorColor){
        setupIndicator(indicatorColor, selectedIndicatorColor);
    }

    public void setScaleType(ImageView.ScaleType scaleType){
        if(adapter != null){
            adapter.setScaleType(scaleType);
        }
    }

    public void setOnPageChangeListener( ViewPager.OnPageChangeListener onPageChangeListener){
        viewPager.addOnPageChangeListener(onPageChangeListener);
    }

    public void clearListeners(){
        viewPager.clearOnPageChangeListeners();
    }

    public void setupIndicator(int unselectedColor, int selectedColor) {
        selectedCircle = ContextCompat.getDrawable(context, circle);
        selectedCircle.setColorFilter(new PorterDuffColorFilter(selectedColor, PorterDuff.Mode.MULTIPLY));

        unselectedCircle = ContextCompat.getDrawable(context, circle);
        unselectedCircle.setColorFilter(new PorterDuffColorFilter(unselectedColor, PorterDuff.Mode.MULTIPLY));

        float scale = getResources().getDisplayMetrics().density;
        int padding = (int) (5 * scale + 0.5f);

        circleLayout = new LinearLayout(context);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.bottomMargin = 75;
        circleLayout.setLayoutParams(params);

        addView(circleLayout);

        for (int i = 0; i < adapter.getCount(); i++) {
            ImageView circle = new ImageView(context);
            circle.setImageDrawable(unselectedCircle);
            circle.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            circle.setAdjustViewBounds(true);
            circle.setPadding(padding, 0, padding, 0);
            circleLayout.addView(circle);
        }

        setIndicator(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //unused
            }

            @Override public void onPageSelected(int position) {
                setIndicator(position);
            }

            @Override public void onPageScrollStateChanged(int state) {
                //unused
            }
        });
    }

    private void setIndicator(int index) {
        int imageCount = adapter.getCount();

        if (index >= imageCount) {
            return;
        }

        ((ImageView) circleLayout.getChildAt(index)).setImageDrawable(selectedCircle);

        if (index > 0) {
            ((ImageView) circleLayout.getChildAt(index - 1)).setImageDrawable(unselectedCircle);
        }

        if (index < imageCount - 1) {
            ((ImageView) circleLayout.getChildAt(index + 1)).setImageDrawable(unselectedCircle);
        }
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (forceSquare) {
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        } else {
            /*
                int height = 0;
                for (int i = 0; i < getChildCount(); i++) {
                    View child = getChildAt(i);
                    child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                    int h = child.getMeasuredHeight();
                    if (h > height) height = h;
                }

                heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
            */

            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
