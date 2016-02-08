# SimpleViewPager
[![Build Status](https://travis-ci.org/fiskurgit/SimpleViewPager.svg?branch=master)](https://travis-ci.org/fiskurgit/SimpleViewPager)

That image ViewPager you have to code for every Android project - use this instead.

Basic demo available on the Play Store:  
<a href="https://play.google.com/store/apps/details?id=eu.fiskur.simpleviewpagerdemo&utm_source=global_co&utm_medium=prtnr&utm_content=Mar2515&utm_campaign=PartBadge&pcampaignid=MKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1"><img alt="Get it on Google Play" src="https://play.google.com/intl/en_us/badges/images/generic/en-play-badge.png" width=175 /></a>

## Usage

Add to your Android layout xml:
```xml
<eu.fiskur.simpleviewpager.SimpleViewPager
    android:id="@+id/simple_view_pager"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    />
```

Then add your images, and setup [Picasso](http://square.github.io/picasso/) ( or [Glide](https://github.com/bumptech/glide), [Fresco](https://github.com/facebook/fresco), or whatever):
```java
SimpleViewPager simpleViewPager = (SimpleViewPager) findViewById(R.id.simple_view_pager);

String[] demoUrlArray = new String[]{
        "http://fiskur.eu/apps/simpleviewpagerdemo/001.jpg",
        "http://fiskur.eu/apps/simpleviewpagerdemo/002.jpg",
        "http://fiskur.eu/apps/simpleviewpagerdemo/003.jpg",
        "http://fiskur.eu/apps/simpleviewpagerdemo/004.jpg",
        "http://fiskur.eu/apps/simpleviewpagerdemo/005.jpg",
};

int indicatorColor = Color.parseColor("#ffffff");
int selectedIndicatorColor = Color.parseColor("#fff000");

simpleViewPager.setup(demoUrlArray, indicatorColor, selectedIndicatorColor, new ImageLoader() {
    @Override
    public void loadImage(ImageView view, String url) {
        Picasso.with(MainActivity.this)
            .load(url)
            .into(view);
    }
});
```

If you're using resources that ship with your app make life easy and let Picasso handle the memory management/downsampling and use an array of resources IDs. You can also set the ScaleType for the images (or again; leave that to [Picasso in the callback](http://square.github.io/picasso/#features)).  
  
Using resources:
```java
int[] resourceIds = new int[]{
    R.drawable.a,
    R.drawable.b,
    R.drawable.c,
    R.drawable.d,
    R.drawable.e
}

int indicatorColor = Color.parseColor("#ffffff");
int selectedIndicatorColor = Color.parseColor("#fff000");

simpleViewPager.setup(resourceIds, indicatorColor, selectedIndicatorColor, new ImageResourceLoader() {
    @Override
    public void loadImageResource(ImageView view, int id) {
        Picasso.with(MainActivity.this)
            .load(id)
            .resize(screenWidth, screenWidth)
            .centerCrop()
            .into(view);
    }
});
```

Using drawables (not advised, you'll have memory issues):
```java
Drawable[] drawables = new Drawable[]{
        ContextCompat.getDrawable(this, R.drawable.a),
        ContextCompat.getDrawable(this, R.drawable.b),
        ContextCompat.getDrawable(this, R.drawable.c),
        ContextCompat.getDrawable(this, R.drawable.d),
        ContextCompat.getDrawable(this, R.drawable.e)
};
ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER_CROP;

int indicatorColor = Color.parseColor("#ffffff");
int selectedIndicatorColor = Color.parseColor("#fff000");

simpleViewPager.setup(drawables, scaleType, indicatorColor, selectedIndicatorColor);
```

Add a ViewPager.OnPageChangeListener if needed: ```simpleViewPager(someOnPageChangeListener)```

Always call ```simpleViewPager.clearListeners()``` when the activity/fragment is destroyed to avoid leaks.

##Dependency

Add jitpack.io to your root build.gradle, eg:

```groovy
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```

then add the dependency to your project build.gradle:

```groovy
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile 'com.github.fiskurgit:SimpleViewPager:1.0.2'
}
```
You can find the latest version in the releases tab above: https://github.com/fiskurgit/SimpleViewPager/releases

More options at jitpack.io: https://jitpack.io/#fiskurgit/SimpleViewPager

##Screenshots
![SimpleViewPager](images/example_image1.png)
