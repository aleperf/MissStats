package stats.aleperf.example.missstats;
/***
 * This class create a singleton with all the HomeArguments that should appear in the
 * Home Page of the app
 */

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;


public class HomeArgumentsLab {

    private static HomeArgumentsLab argumentsLab;
    private static List<HomeArgument> arguments;


    private HomeArgumentsLab(Context context) {
        Resources res = context.getResources();
        String[] title = res.getStringArray(R.array.scrap_item_title);
        String[] subtitle = res.getStringArray(R.array.scrap_item_subtitle);
        int[] images = {R.drawable.missstats1, R.drawable.what_is_stat, R.drawable.toolbox, R.drawable.rolling_dice,
                R.drawable.bell_curve_simple, R.drawable.monty_hall, R.drawable.trivia_quiz};
        int[] colors = {R.color.meetMissStats, R.color.whatIsStatistics, R.color.yourToolbox, R.color.probability, R.color.normality, R.color.monty_hall, R.color.quiz};
        arguments = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            HomeArgument argument = new HomeArgument(title[i], subtitle[i], images[i], colors[i]);
            arguments.add(argument);
        }
    }

    public static HomeArgumentsLab getHomeArgumentsLab(Context context) {
        if (argumentsLab == null) {
            argumentsLab = new HomeArgumentsLab(context);
        }
        return argumentsLab;
    }

    public List<HomeArgument> getArguments() {
        return arguments;
    }
}
