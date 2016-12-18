package stats.aleperf.example.missstats;

/**
 * A HomeArgument is a key element that appears in the homepage of the app.
 * It has :
 * -a title (designed to captive attention)
 * - a subtitle to introduce a more complex description that will be launched by another class
 * - an image reference as an int drawable that is different for every HomeArgument and
 * must have some reference to the title, the subtitle or the further explanations.
 * -a  color. The color should match the image shown in the drawable:
 * This app is design to be colorful, engaging and to help memory: different colors help
 * to remember different things
 */
public class HomeArgument {

    private final String mTitle;
    private final String mSubtitle;
    private final int mDrawable;
    private final int mColor;

    public HomeArgument(String title, String subtitle, int drawable, int color) {
        mTitle = title;
        mSubtitle = subtitle;
        mDrawable = drawable;
        mColor = color;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSubtitle() {
        return mSubtitle;
    }

    public int getDrawable() {
        return mDrawable;
    }

    public int getColor() {
        return mColor;
    }
}
