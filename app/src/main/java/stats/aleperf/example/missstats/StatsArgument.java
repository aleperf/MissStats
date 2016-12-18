package stats.aleperf.example.missstats;

/**
 * A StatsArgument represents a topic about Statistics
 *
 */

public class StatsArgument {
    private final String mTitle;
    private final String mText;

    public StatsArgument(String title, String text){
        mTitle = title;
        mText = text;


    }

    public String getTitle() {
        return mTitle;
    }

    public String getText() {
        return mText;
    }
}
