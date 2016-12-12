package stats.aleperf.example.missstats;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * A ToolBoxFragment is a list of elements of descriptive statistics represented as ToolboxArgument
 * A simple {@link Fragment} subclass.
 * Use the ToolboxFragment.newInstance factory method to create an instance of this class;
 */
public class ToolboxFragment extends Fragment {

    private List<ToolboxArgument> arguments;


    public ToolboxFragment() {
        // Required empty public constructor

    }

    public static ToolboxFragment newInstance(){

        return new ToolboxFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_toolbox, container, false);
        ViewPager pager = (ViewPager)view.findViewById(R.id.toolbox_view_pager);
        PagerTabStrip tab = (PagerTabStrip) view.findViewById(R.id.pager_header);
        tab.setTabIndicatorColor(getActivity().getResources().getColor(R.color.colorPrimaryDark));
        pager.setAdapter(new ToolboxPageAdapter(getActivity(), getChildFragmentManager()));
        pager.setCurrentItem(0);
        return view;
    }

    private class ToolboxPageAdapter extends FragmentStatePagerAdapter{

        private List<ToolboxArgument> arguments;
        private Context context;

        ToolboxPageAdapter(Context context,  FragmentManager manager){
            super(manager);
            this.context = context;
            arguments = new ToolboxArgsLab(getActivity()).getToolboxArguments();
            Log.d("argument ha grandezza: " ,  "" + arguments.size());
        }

        @Override
        public Fragment getItem(int position){
            String title = arguments.get(position).getTitle();
            String text = arguments.get(position).getText();
            ToolboxPage page = ToolboxPage.newInstance(title, text);
            return page;
        }
          @Override
        public int getCount(){
              return arguments.size();
          }

       @Override
        public String getPageTitle(int position){
           return "Page " + (position + 1) ;
       }
    }

}
