package eu.fiskur.simpleviewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SimpleViewPagerAdapter extends PagerAdapter {

    private Context context;
    private String[] imageUrls;
    private ImageLoader imageLoader;

    public SimpleViewPagerAdapter(Context context, String[] imageUrls, ImageLoader imageLoader) {
        this.context = context;
        this.imageUrls = imageUrls;
        this.imageLoader = imageLoader;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);

        imageView.setImageResource(R.drawable.dummy_placeholder);

        imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT));
        //imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setPadding(0, 0, 0, 0);

        String url = imageUrls[position];
        container.addView(imageView);
        imageLoader.loadImage(imageView, url);
        return imageView;
    }

    @Override
    public int getCount() {
        return imageUrls.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        if (view == ((ImageView) object)) return true;
        else return false;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container, position, object);
    }
}
