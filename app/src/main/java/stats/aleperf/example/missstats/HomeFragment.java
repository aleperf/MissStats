package stats.aleperf.example.missstats;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A  {@link Fragment} subclass that has the task to host the titles of main arguments.
 * Activities that contain this fragment must implement the
 * {HomeFragment.OnHomeFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    public final static String TAG = "HomeFragmentTag";
    private RecyclerView mRecyclerView;
    private StatsAdapter mAdapter;
    private OnHomeFragmentInteractionListener mCallback;

    public HomeFragment() {
        // Required empty public constructor

    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();

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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.home_elements);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HomeArgumentsLab argumentsLab = HomeArgumentsLab.getHomeArgumentsLab(getActivity());
        mAdapter = new StatsAdapter(argumentsLab);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnHomeFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }


    public interface OnHomeFragmentInteractionListener {

        void onArgumentSelectedListener(int position);
    }

    private class StatsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mScrapTitleTextView;
        private TextView mScrapSubtitleTextView;

        public StatsHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mScrapTitleTextView = (TextView) itemView.findViewById(R.id.home_title_text_view);
            mScrapSubtitleTextView = (TextView) itemView.findViewById(R.id.home_subtitle_text_view);
        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mCallback.onArgumentSelectedListener(position);
        }

        public void bindStats(String title, String subtitle) {
            mScrapTitleTextView.setText(title);
            mScrapSubtitleTextView.setText(subtitle);
        }
    }

    private class StatsAdapter extends RecyclerView.Adapter<StatsHolder> {

        private String[] homeTitles;
        private String[] homeSubtitles;

        public StatsAdapter(HomeArgumentsLab argumentLab) {
            homeTitles = argumentLab.getTitles();
            homeSubtitles = argumentLab.getSubtitles();
        }


        @Override
        public StatsHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.home_tile, parent, false);
            return new StatsHolder(view);
        }


        @Override
        public void onBindViewHolder(StatsHolder holder, int position) {

            String title = homeTitles[position];
            String subtitle = homeSubtitles[position];
            holder.bindStats(title, subtitle);
        }

        @Override
        public int getItemCount() {

            return homeTitles.length;
        }


    }

}




