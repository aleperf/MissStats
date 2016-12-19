package stats.aleperf.example.missstats;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * MainActivity is the entry point of the app and manages the launch of the various Fragments
 * corresponding to different arguments in the homepage of the app.
 */

public class MainActivity extends AppCompatActivity implements HomeFragment.OnHomeFragmentInteractionListener {

    final static String KEY_POSITION = "position";
    int mCurrentPosition = 0;

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
                } else {
                    fragment = ProbabilityFragment.newInstance();
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


    }


}
