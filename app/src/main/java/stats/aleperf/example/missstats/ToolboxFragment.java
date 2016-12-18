package stats.aleperf.example.missstats;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

/**
 * A ToolBoxFragment is a list of elements of descriptive statistics represented as StatsArgument
 * loaded into a ViewPager
 * Use the ToolboxFragment.newInstance factory method to create an instance of this class;
 */
public class ToolboxFragment extends Fragment {

    public final static String TAG = ToolboxFragment.class.getSimpleName();

    private final String POSITION = "position";
    private int pagePosition = 0;


    public ToolboxFragment() {
        // Required empty public constructor

    }

    public static ToolboxFragment newInstance() {

        return new ToolboxFragment();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(savedInstanceState != null){
            pagePosition = savedInstanceState.getInt(POSITION, 0);
        }
        View view = inflater.inflate(R.layout.fragment_toolbox, container, false);
        ViewPager pager = (ViewPager) view.findViewById(R.id.toolbox_view_pager);
        TabLayout tab = (TabLayout) view.findViewById(R.id.sliding_tabs);
        pager.setAdapter(new ToolboxPageAdapter(getChildFragmentManager()));
        pager.setCurrentItem(pagePosition);
        tab.setupWithViewPager(pager);
        return view;
    }

    private class ToolboxPageAdapter extends FragmentStatePagerAdapter {

        private List<StatsArgument> arguments;

        ToolboxPageAdapter(FragmentManager manager) {
            super(manager);
            arguments = new ToolboxArgsLab().getToolboxArguments();

        }

        @Override
        public Fragment getItem(int position) {
            String title = arguments.get(position).getTitle();
            String text = arguments.get(position).getText();
            StatsPage page = StatsPage.newInstance(title, text);
            return page;
        }

        @Override
        public int getCount() {
            return arguments.size();
        }

        @Override
        public String getPageTitle(int position) {

            //return "Page " + (position + 1);
            pagePosition = position;
            switch(position){
                case 0:return getString(R.string.toolbox_title_page_1);
                case 1:return getString(R.string.toolbox_title_page_2);
                case 2: return getString(R.string.toolbox_title_page_3);
                case 3: return getString(R.string.toolbox_title_page_4);
                case 4: return getString(R.string.toolbox_title_page_5);
                case 5: return getString(R.string.toolbox_title_page_6);
                case 6: return getString(R.string.toolbox_title_page_7);
                case 7: return getString(R.string.toolbox_title_page_8);
                default: return getString(R.string.toolbox_title_page_9);
            }
        }
    }


    /**
     * This class assembles the elements to display in the Toolbox section of the app
     * It retrieves elements from resources and build the necessary StatsArguments
     * If you want to add elements to the Toolbox you have to provide the necessary
     * resources and add them here.
     *
     */

    private class ToolboxArgsLab {

        private List<StatsArgument> toolboxArguments;

        public ToolboxArgsLab(){
            Resources res = getResources();
            String[] title = res.getStringArray(R.array.ToolboxArgumentTitles);
            String[] text = res.getStringArray(R.array.ToolboxDescriptions);
            toolboxArguments = new ArrayList<StatsArgument>();
            for(int i= 0; i < title.length; i++){
                StatsArgument arg = new StatsArgument(title[i], text[i]);
                toolboxArguments.add(arg);
            }
        }

        public List<StatsArgument> getToolboxArguments(){
            return toolboxArguments;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, pagePosition);

    }
}
