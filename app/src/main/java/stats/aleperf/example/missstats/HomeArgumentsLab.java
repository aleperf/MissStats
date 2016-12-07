package stats.aleperf.example.missstats;

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
        int[] images = {R.drawable.missstats1,R.drawable.placeholder1, R.drawable.placeholder1, R.drawable.placeholder1,
        R.drawable.placeholder1,R.drawable.placeholder1, R.drawable.placeholder1};
        arguments = new ArrayList<>();
        for(int i= 0; i < title.length; i++){
            HomeArgument argument = new HomeArgument(title[i], subtitle[i],images[i], R.color.meetMissStats);
            arguments.add(argument);
        }
    }

    public static HomeArgumentsLab getHomeArgumentsLab(Context context) {
        if (argumentsLab == null) {
            argumentsLab = new HomeArgumentsLab(context);
        }
        return argumentsLab;
    }

    public List<HomeArgument> getArguments(){
        return arguments;
    }
}
