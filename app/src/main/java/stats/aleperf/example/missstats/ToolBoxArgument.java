package stats.aleperf.example.missstats;

/**
 * A ToolboxArgument is an element of descriptive Statistics
 * It has a title mTitle and a description mText
 */

public class ToolboxArgument {
    private final String mTitle;
    private final String mText;

    public ToolboxArgument(String title, String text){
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
