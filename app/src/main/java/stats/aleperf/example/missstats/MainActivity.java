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
            fm.beginTransaction().add(R.id.fragment_container, homeFragment).commit();
            //if this is a dual pane layout, initialize for the first time the detail_panel
            if (dualPane) {
                //load MeetMissStats in the side panel as initial default argument.
                Fragment detailFragment = MeetMissStatsFragment.newInstance();
                fm.beginTransaction().add(R.id.detail_panel, detailFragment).commit();
            }
        } else {
            //the app si already initialized
            //if dual pane refresh the side menu and choose the appropriate detail panel

            if (dualPane) {
                HomeFragment homeFragment = HomeFragment.newInstance();
                fm.beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
            }
            int position = savedInstanceState.getInt(KEY_POSITION, 0);
            onArgumentSelectedListener(position);

        }
    }

    @Override
    public void onArgumentSelectedListener(int position) {

        mCurrentPosition = position;
        Fragment fragment;
        String tag;
        switch (position) {
            case 0:
                fragment = MeetMissStatsFragment.newInstance();
                tag = MeetMissStatsFragment.TAG;
                break;
            case 1:
                fragment = WhatIsStatisticsFragment.newInstance();
                tag = WhatIsStatisticsFragment.TAG;
                break;
            case 2:
                fragment = ToolboxFragment.newInstance();
                tag = ToolboxFragment.TAG;
                break;
            case 3:
                fragment = ProbabilityFragment.newInstance();
                tag = ProbabilityFragment.TAG;
                break;
            case 4:
                fragment = NormalityFragment.newInstance();
                tag = NormalityFragment.TAG;
                break;
            case 5:
                fragment = MontyHallFragment.newInstance();
                tag = MontyHallFragment.TAG;
                break;
            case 6:
                fragment = QuizFragment.newInstance();
                tag = QuizFragment.TAG;

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

        transaction.addToBackStack(null);
        transaction.commit();
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_POSITION, mCurrentPosition);


    }


}
