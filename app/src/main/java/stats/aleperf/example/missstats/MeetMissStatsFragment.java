package stats.aleperf.example.missstats;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * MeetMissStats introduce Miss Stats, your friendly neighborhood statistician
 * A simple {@link Fragment} subclass.
 * This class host the presentation of Miss Stats
 * Use the MeetMissStatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeetMissStatsFragment extends Fragment {

    public final static String TAG = MeetMissStatsFragment.class.getSimpleName();


    public MeetMissStatsFragment() {
        // Required empty public constructor
    }


    public static MeetMissStatsFragment newInstance() {
        MeetMissStatsFragment fragment = new MeetMissStatsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meet_miss_stats, container, false);
    }


}
