package stats.aleperf.example.missstats;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the GimmeFiveFragment.newInstance factory method to create an instance of this class;
 */
public class GimmeFiveFragment extends Fragment {


    public GimmeFiveFragment() {
        // Required empty public constructor
    }

    public static GimmeFiveFragment newInstance(){
        return new GimmeFiveFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gimme_five, container, false);
    }

}
