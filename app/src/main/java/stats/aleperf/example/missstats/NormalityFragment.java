package stats.aleperf.example.missstats;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * * use the factory method NormalityFragment.newInstance() to create an instance of this class
 */
public class NormalityFragment extends Fragment {

    public final static String TAG = NormalityFragment.class.getSimpleName();

    public NormalityFragment() {
        // Required empty public constructor
    }

    public static NormalityFragment newInstance() {
        return new NormalityFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_normality, container, false);
    }

}
