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

Then add your images:
```java
SimpleViewPager simpleViewPager = (SimpleViewPager) findViewById(R.id.simple_view_pager);

String[] demoArray = new String[]{
        "http://fiskur.eu/apps/simpleviewpagerdemo/001.jpg",
        "http://fiskur.eu/apps/simpleviewpagerdemo/002.jpg",
        "http://fiskur.eu/apps/simpleviewpagerdemo/003.jpg",
        "http://fiskur.eu/apps/simpleviewpagerdemo/004.jpg",
        "http://fiskur.eu/apps/simpleviewpagerdemo/005.jpg",
};

int indicatorColor = Color.parseColor("#ffffff");
int selectedIndicatorColor = Color.parseColor("#fff000");

simpleViewPager.setup(demoArray, indicatorColor, selectedIndicatorColor, new ImageLoader() {
    @Override
    public void loadImage(ImageView view, String url) {
        Picasso.with(MainActivity.this).load(url).into(view);
    }
});
```

You can also use an array of Drawables (you need to handle memory/downsizing yourself). You can also set the ScaleType for the images:

```java
SimpleViewPager simpleViewPager = (SimpleViewPager) findViewById(R.id.simple_view_pager);

int[] resourceIds = new int[]{
        R.mipmap.image_a,
        R.mipmap.image_b,
        R.mipmap.image_c,
        R.mipmap.image_d,
        R.mipmap.image_e,
};

int indicatorColor = Color.parseColor("#ffffff");
int selectedIndicatorColor = Color.parseColor("#fff000");

ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER_INSIDE;

simpleViewPager.setup(resourceIds, scaleType, indicatorColor, selectedIndicatorColor);
```

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
    compile 'com.github.fiskurgit:SimpleViewPager:1.0.1'
}
```
You can find the latest version in the releases tab above: https://github.com/fiskurgit/SimpleViewPager/releases

More options at jitpack.io: https://jitpack.io/#fiskurgit/SimpleViewPager

##Screenshots
![SimpleViewPager](images/example_image1.png)
