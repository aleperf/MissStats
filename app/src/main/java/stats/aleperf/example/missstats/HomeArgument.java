package stats.aleperf.example.missstats;

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
