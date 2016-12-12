package stats.aleperf.example.missstats;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;


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
