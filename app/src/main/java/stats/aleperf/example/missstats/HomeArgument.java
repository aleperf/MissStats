package stats.aleperf.example.missstats;

/**
 * Created by Tundra on 07/12/2016.
 */

public class HomeArgument {

    private final String mTitle;
    private final String mSubtitle;
    private final int mDrawable;
    private final int mColor;

    public HomeArgument(String title, String subtitle, int drawable, int color){
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
