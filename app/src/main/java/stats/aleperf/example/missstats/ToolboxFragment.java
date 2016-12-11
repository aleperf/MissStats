package stats.aleperf.example.missstats;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the ToolboxFragment.newInstance factory method to create an instance of this class;
 */
public class ToolboxFragment extends Fragment {


    public ToolboxFragment() {
        // Required empty public constructor
    }

    public static ToolboxFragment newInstance(){
        return new ToolboxFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_toolbox, container, false);
    }

}
