package stats.aleperf.example.missstats;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * This Fragment introduce the main argument of the App: Statistics
 * A simple {@link Fragment} subclass, this class host the explanation of what statistics i
 * * Use the WhatIsStatisticsFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class WhatIsStatisticsFragment extends Fragment {

    public final static String TAG = WhatIsStatisticsFragment.class.getSimpleName();

    public WhatIsStatisticsFragment() {
        // Required empty public constructor
    }

    public static WhatIsStatisticsFragment newInstance(){
        return new WhatIsStatisticsFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_what_is_statistics, container, false);
    }

}
