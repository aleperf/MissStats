package stats.aleperf.example.missstats;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * * use the factory method MontyHallFragment.newInstance() to create an instance of this class
 */
public class MontyHallFragment extends Fragment {


    public MontyHallFragment() {
        // Required empty public constructor
    }

    public static MontyHallFragment newInstance(){
        return new MontyHallFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_monty_hall, container, false);
    }

}
