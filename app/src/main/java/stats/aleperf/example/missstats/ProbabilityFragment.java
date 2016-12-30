package stats.aleperf.example.missstats;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * use the factory method ProbabilityFragment.newInstance() to create an instance of this class
 * Activities hosting this fragment must implement the CoinDataRetriever Interface and the SpinningCoinFragment.SpinningCoinCallback
 *
 */
public class ProbabilityFragment extends Fragment {

    public final static String TAG = ProbabilityFragment.class.getSimpleName();
    private ProbabilityPagerAdapter mAdapter;
    private static final int THUMB_UP_FACE = R.drawable.thumb_up;
    private static final int THUMB_DOWN_FACE = R.drawable.thumb_down;
    private static final String LAST_SEEN_FACE = "last seen face";
    private static final String IS_SPINNING = "is spinning";
    private static final String COUNTER_THUMBS_UP = "counter thumbs up";
    private static final String COUNTER_THUMBS_DOWN = "counter thumbs down";

    //SpinningCoinFields
    int counterUp = 0;
    int counterDown = 0;
    boolean isSpinning = false;
    int lastSeenCoinFace = R.drawable.thumb_up;


    public ProbabilityFragment() {
        // Required empty public constructor
       // setRetainInstance(true);
    }

    public interface CoinDataRetriever {
        Bundle retrieveCoinData();
    }


    public static ProbabilityFragment newInstance(int counterUp, int counterDown, boolean isSpinning, int lastSeenCoinFace) {
        Bundle bundle = new Bundle();
        bundle.putInt(COUNTER_THUMBS_UP, counterUp);
        bundle.putInt(COUNTER_THUMBS_DOWN, counterDown);
        bundle.putBoolean(IS_SPINNING, isSpinning);
        bundle.putInt(LAST_SEEN_FACE, lastSeenCoinFace);
        ProbabilityFragment fragment = new ProbabilityFragment();
        fragment.setArguments(bundle);
        Log.d("uffa", "ho fatto Probability New Instance");
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_probability, container, false);
        ViewPager pager = (ViewPager) root.findViewById(R.id.probability_view_pager);
        TabLayout tab = (TabLayout) root.findViewById(R.id.sliding_tabs);
        mAdapter = new ProbabilityPagerAdapter(getChildFragmentManager(), (CoinDataRetriever) getActivity());
        Bundle bundle = getArguments();
        if(bundle != null){
            counterUp = bundle.getInt(SpinningCoinFragment.COUNTER_THUMBS_UP, 0);
            counterDown = bundle.getInt(SpinningCoinFragment.COUNTER_THUMBS_DOWN, 0);
            isSpinning = bundle.getBoolean(SpinningCoinFragment.IS_SPINNING, false);
            lastSeenCoinFace = bundle.getInt(SpinningCoinFragment.LAST_SEEN_FACE, SpinningCoinFragment.THUMB_UP_FACE);
        }
        pager.setAdapter(mAdapter);
        pager.setCurrentItem(0);
        tab.setupWithViewPager(pager);
        return root;

    }


    private class ProbabilityPagerAdapter extends FragmentStatePagerAdapter {

        private int counterThumbsUp = counterUp;
        private int counterThumbsDown = counterDown;
        private boolean isSpinningAdapter = isSpinning;
        private int lastFace = lastSeenCoinFace;
        private CoinDataRetriever data;


        ProbabilityPagerAdapter(FragmentManager manager, CoinDataRetriever data) {
            super(manager);
            this.data = data;
            Bundle bundle = data.retrieveCoinData();
            if (bundle != null) {
                counterThumbsUp = bundle.getInt(SpinningCoinFragment.COUNTER_THUMBS_UP, 0);
                counterThumbsDown = bundle.getInt(SpinningCoinFragment.COUNTER_THUMBS_DOWN, 0);
                isSpinningAdapter = bundle.getBoolean(SpinningCoinFragment.IS_SPINNING, false);
                lastFace = bundle.getInt(SpinningCoinFragment.LAST_SEEN_FACE, SpinningCoinFragment.THUMB_UP_FACE);
            }


        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return StatsPage.newInstance(getString(R.string.probability_intro_title), getString(R.string.probability_intro_text));
                case 1:
                    return StatsPage.newInstance(getString(R.string.probability_2_title), getString(R.string.probability_2_text));
                case 2:
                    Log.d("uffa!", "Sto creando un nuovo SpinningCoinFragment con newInstance, counterThumbsUp = " + counterThumbsUp);
                    return SpinningCoinFragment.newInstance(counterThumbsUp, counterThumbsDown, isSpinningAdapter, lastFace);
                default:
                    return new RollingDieGameFragment();
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public String getPageTitle(int position) {

            switch (position) {
                case 0:
                    return getString(R.string.probability_intro_tab_title);
                case 1:
                    return getString(R.string.probability_2_tab_title);
                case 2:
                    return getString(R.string.probability_in_practice_title);
                default:
                    return getString(R.string.probability_rolling_die);

            }
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {

            Bundle bundle = data.retrieveCoinData();
            if (bundle != null) {
                counterThumbsUp = bundle.getInt(SpinningCoinFragment.COUNTER_THUMBS_UP, 0);
                counterThumbsDown = bundle.getInt(SpinningCoinFragment.COUNTER_THUMBS_DOWN, 0);
                isSpinningAdapter = bundle.getBoolean(SpinningCoinFragment.IS_SPINNING, false);
                lastSeenCoinFace = bundle.getInt(SpinningCoinFragment.LAST_SEEN_FACE, SpinningCoinFragment.THUMB_UP_FACE);
            }

        }
    }

}
