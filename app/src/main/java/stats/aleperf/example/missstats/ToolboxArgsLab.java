package stats.aleperf.example.missstats;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

/**
 * This class assembles the elements to display in the Toolbox section of the app
 * It retrieves elements from resources and build the necessary ToolboxArguments
 * If you want to add elements to the Toolbox you have to provide the necessary
 * resources and add them here.
 *
 */


public class ToolboxArgsLab {

    private List<ToolboxArgument> toolboxArguments;

    public ToolboxArgsLab(Context context){
        Resources res = context.getResources();
        String[] title = res.getStringArray(R.array.ToolboxArgumentTitles);
        String[] text = res.getStringArray(R.array.ToolboxDescriptions);
        toolboxArguments = new ArrayList<ToolboxArgument>();
        for(int i= 0; i < title.length; i++){
            ToolboxArgument arg = new ToolboxArgument(title[i], text[i]);
            toolboxArguments.add(arg);
        }
    }

    public List<ToolboxArgument> getToolboxArguments(){
        return toolboxArguments;
    }
}
