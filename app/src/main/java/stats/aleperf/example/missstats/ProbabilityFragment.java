package stats.aleperf.example.missstats;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * use the factory method ProbabilityFragment.newInstance() to create an instance of this class
 */
public class ProbabilityFragment extends Fragment {

    public final static String TAG = ProbabilityFragment.class.getSimpleName();

    public ProbabilityFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static ProbabilityFragment newInstance() {
        return new ProbabilityFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_probability, container, false);
        View view = inflater.inflate(R.layout.fragment_toolbox, container, false);
        ViewPager pager = (ViewPager) view.findViewById(R.id.toolbox_view_pager);
        TabLayout tab = (TabLayout) view.findViewById(R.id.sliding_tabs);
        pager.setAdapter(new ProbabilityFragment.ProbabilityPagerAdapter(getChildFragmentManager()));
        pager.setCurrentItem(0);
        tab.setupWithViewPager(pager);
        return view;

    }

    private class ProbabilityPagerAdapter extends FragmentStatePagerAdapter {


        ProbabilityPagerAdapter(FragmentManager manager) {
            super(manager);


        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return StatsPage.newInstance(getString(R.string.probability_intro_title), getString(R.string.probability_intro_text));
                case 1:
                    return StatsPage.newInstance(getString(R.string.probability_2_title), getString(R.string.probability_2_text));
                default:
                    return new RollingDieGameFragment();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public String getPageTitle(int position) {

            switch (position) {
                case 0:
                    return getString(R.string.probability_intro_tab_title);
                case 1:
                    return getString(R.string.probability_2_tab_title);
                default:
                    return getString(R.string.probability_rolling_die);

            }
        }
    }


}
