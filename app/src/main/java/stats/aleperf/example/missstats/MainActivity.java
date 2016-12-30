package stats.aleperf.example.missstats;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * MainActivity is the entry point of the app and manages the launch of the various Fragments
 * corresponding to different arguments in the homepage of the app.
 */

public class MainActivity extends AppCompatActivity implements HomeFragment.OnHomeFragmentInteractionListener,
SpinningCoinFragment.SpinningCoinCallBack, ProbabilityFragment.CoinDataRetriever {

    final static String KEY_POSITION = "position";
    int mCurrentPosition = 0;

    //Spinning Coin Constants


    //SpinningCoinFields
    int counterUp = 0;
    int counterDown = 0;
    boolean isSpinning = false;
    int lastSeenCoinFace = R.drawable.thumb_up;


    @Override
    public  void saveCoinData(int counterUp, int counterDown, boolean isSpinning, int lastSeenFace) {
        this.counterUp = counterUp;
        this.counterDown = counterDown;
        this.isSpinning = isSpinning;
        this.lastSeenCoinFace = lastSeenFace;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masterdetail);
        boolean dualPane = getResources().getBoolean(R.bool.dual_pane);

        FragmentManager fm = getSupportFragmentManager();
        //if this is the first time the app is initialized, add the needed fragments
        if (savedInstanceState == null) {
            HomeFragment homeFragment = HomeFragment.newInstance();
            fm.beginTransaction().add(R.id.fragment_container, homeFragment, HomeFragment.TAG).commit();
            //if this is a dual pane layout, initialize for the first time the detail_panel
            if (dualPane) {
                //load MeetMissStats in the side panel as initial default argument.
                Fragment detailFragment = MeetMissStatsFragment.newInstance();
                fm.beginTransaction().add(R.id.detail_panel, detailFragment, MeetMissStatsFragment.TAG).commit();
            }
        } else {
            //the app si already initialized
            //if dual pane refresh the side menu and choose the appropriate detail panel
            int position = savedInstanceState.getInt(KEY_POSITION, -1);
            if (dualPane) {
                //if there is an instance of homeFragment put the existing instance in fragment_container
                HomeFragment homeFragment = (HomeFragment) fm.findFragmentByTag(HomeFragment.TAG);
                if (homeFragment != null) {

                    FragmentTransaction transaction = fm.beginTransaction();
                    transaction.replace(R.id.fragment_container, homeFragment, HomeFragment.TAG).commit();
                    transaction.addToBackStack(HomeFragment.TAG);

                } else {
                    // if homeFragment has been destroyed, create a new instance
                    homeFragment = HomeFragment.newInstance();
                    fm.beginTransaction().replace(R.id.fragment_container, homeFragment, HomeFragment.TAG).commit();
                    fm.beginTransaction().addToBackStack(HomeFragment.TAG);
                }
            }
            //load SpinningCoinData
            counterUp = savedInstanceState.getInt(SpinningCoinFragment.COUNTER_THUMBS_UP, 0);
            Log.d("uffa", "sono in Oncreate e counterUp e uguale a " + counterUp);
            counterDown = savedInstanceState.getInt(SpinningCoinFragment.COUNTER_THUMBS_DOWN, 0);
            isSpinning= savedInstanceState.getBoolean(SpinningCoinFragment.IS_SPINNING, isSpinning);
            lastSeenCoinFace = savedInstanceState.getInt(SpinningCoinFragment.LAST_SEEN_FACE, SpinningCoinFragment.THUMB_UP_FACE);


            onArgumentSelectedListener(position);


        }
    }

    /**
     * Remove a fragment from the backstack, remove the same fragment from its container,
     * commit the transaction and force and force every pending transaction to be
     * committed immediately, avoiding problems due to asynchronous callbacks.
     *
     * @param fragment the fragment to remove from container and backstack
     * @param fm       an instance of FragmentManager
     * @param tag      the tag used to add fragment to the backstack.
     */

    private void removeFragment(Fragment fragment, FragmentManager fm, String tag) {

        fm.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.remove(fragment);
        transaction.commit();
        fm.executePendingTransactions();
    }

    /**
     * Method to implement to interact with HomeFragment
     * @param position the position in the home menu of arguments in HomeFragment
     */

    @Override
    public void onArgumentSelectedListener(int position) {

        mCurrentPosition = position;
        Fragment fragment;
        String tag;
        FragmentManager fm = getSupportFragmentManager();
        switch (position) {
            case 0:
                tag = MeetMissStatsFragment.TAG;
                fragment = fm.findFragmentByTag(MeetMissStatsFragment.TAG);
                if (fragment != null) {
                    removeFragment(fragment, fm, tag);
                } else {
                    fragment = MeetMissStatsFragment.newInstance();
                }
                break;
            case 1:
                tag = WhatIsStatisticsFragment.TAG;
                fragment = fm.findFragmentByTag(WhatIsStatisticsFragment.TAG);
                if (fragment != null) {
                    removeFragment(fragment, fm, tag);
                } else {
                    fragment = WhatIsStatisticsFragment.newInstance();
                }
                break;
            case 2:
                tag = ToolboxFragment.TAG;
                fragment = fm.findFragmentByTag(ToolboxFragment.TAG);
                if (fragment != null) {
                    removeFragment(fragment, fm, tag);
                } else {
                    fragment = ToolboxFragment.newInstance();
                }
                break;
            case 3:
                tag = ProbabilityFragment.TAG;
                fragment = fm.findFragmentByTag(ProbabilityFragment.TAG);
                if (fragment != null) {
                    removeFragment(fragment, fm, tag);
                    Log.d("uffa", "sto usando un probability già creato");
                } else {
                    Log.d("uffa", "sto creando un nuovo fragment");
                    fragment = ProbabilityFragment.newInstance(counterUp, counterDown, isSpinning, lastSeenCoinFace);

                }
                break;

            case 4:
                tag = NormalityFragment.TAG;
                fragment = fm.findFragmentByTag(NormalityFragment.TAG);
                if (fragment != null) {
                    removeFragment(fragment, fm, tag);
                } else {
                    fragment = NormalityFragment.newInstance();
                }
                break;
            case 5:
                tag = MontyHallFragment.TAG;
                fragment = fm.findFragmentByTag(MontyHallFragment.TAG);
                if (fragment != null) {
                    removeFragment(fragment, fm, tag);
                } else {
                    fragment = MontyHallFragment.newInstance();
                }
                break;
            case 6:
                tag = QuizFragment.TAG;
                fragment = fm.findFragmentByTag(QuizFragment.TAG);
                if (fragment != null) {
                    removeFragment(fragment, fm, tag);
                } else {
                    fragment = QuizFragment.newInstance();
                }
                break;
            default:
                fragment = MeetMissStatsFragment.newInstance();
                tag = MeetMissStatsFragment.TAG;
        }

        boolean dualPane = getResources().getBoolean(R.bool.dual_pane);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (dualPane) {
            transaction.replace(R.id.detail_panel, fragment, tag);
        } else {
            transaction.replace(R.id.fragment_container, fragment, tag);
        }

        transaction.commit();
        transaction.addToBackStack(tag);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_POSITION, mCurrentPosition);
        outState.putInt(SpinningCoinFragment.COUNTER_THUMBS_UP, counterUp);
        outState.putInt(SpinningCoinFragment.COUNTER_THUMBS_DOWN, counterDown);
        outState.putBoolean(SpinningCoinFragment.IS_SPINNING, isSpinning);
        outState.putInt(SpinningCoinFragment.LAST_SEEN_FACE, lastSeenCoinFace);
        Log.d("uffa", "sono in onSaveInstanceState di MainActivity, counter up + uguale " + counterUp);


    }



    @Override
    public Bundle retrieveCoinData() {
        Bundle bundle = new Bundle();
        bundle.putInt(SpinningCoinFragment.COUNTER_THUMBS_UP, counterUp);
        bundle.putInt(SpinningCoinFragment.COUNTER_THUMBS_DOWN, counterDown);
        bundle.putBoolean(SpinningCoinFragment.IS_SPINNING, isSpinning);
        bundle.putInt(SpinningCoinFragment.LAST_SEEN_FACE, lastSeenCoinFace);
        return bundle;
    }
}
