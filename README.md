# SimpleViewPager

That view pager to display images you have to code for every Android project - use this instead.

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
    compile 'com.github.fiskurgit:SimpleViewPager:1.0.0'
}
```
You can find the latest version in the releases tab above: https://github.com/fiskurgit/SimpleViewPager/releases

More options at jitpack.io: https://jitpack.io/#fiskurgit/SimpleViewPager

##Screenshots
![SimpleViewPager](images/example_image1.png)
