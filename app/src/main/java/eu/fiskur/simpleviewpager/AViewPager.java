package eu.fiskur.simpleviewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

/*
    This forces the viewpager to draw correctly based on the height of the largest child.
 */
public class AViewPager extends ViewPager {

    private int layout_height;

    public AViewPager(Context context, int layout_height) {
        super(context);
        this.layout_height = layout_height;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(layout_height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            int height = 0;
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                int h = child.getMeasuredHeight();
                if (h > height) height = h;
            }

            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
