package stats.aleperf.example.missstats;

import android.content.Context;
import android.content.res.Resources;


public class HomeArgumentsLab {

    private static HomeArgumentsLab argumentsLab;
    private static String[] mTitles;
    private static String[] mSubtitles;

    private HomeArgumentsLab(Context context) {
        Resources res = context.getResources();
        mTitles = res.getStringArray(R.array.scrap_item_title);
        mSubtitles = res.getStringArray(R.array.scrap_item_subtitle);
    }

    public static HomeArgumentsLab getHomeArgumentsLab(Context context) {
        if (argumentsLab == null) {
            argumentsLab = new HomeArgumentsLab(context);
        }
        return argumentsLab;
    }

    public String[] getTitles(){
        return mTitles;
    }

    public String[] getSubtitles(){
        return mSubtitles;
    }
}
