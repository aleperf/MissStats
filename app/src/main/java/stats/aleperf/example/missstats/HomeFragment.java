package stats.aleperf.example.missstats;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


/**
 * Load the various HomeArgument contained in the HomeArgumentsLab to create
 * a list of argument to choose.
 * Activities that contain this fragment must implement the
 * {HomeFragment.OnHomeFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the  HomeFragment.newInstance factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    public final static String TAG = HomeFragment.class.getSimpleName();
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

        private TextView mTitle;
        private TextView mSubTitle;
        private ImageView mImage;
        private LinearLayout mLayout;


        public StatsHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitle = (TextView) itemView.findViewById(R.id.home_title_text_view);
            mSubTitle = (TextView) itemView.findViewById(R.id.home_subtitle_text_view);
            mImage = (ImageView) itemView.findViewById(R.id.image_arg);
            mLayout = (LinearLayout) itemView.findViewById(R.id.home_linear_layout);
        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mCallback.onArgumentSelectedListener(position);
        }

        public void bindStats(String title, String subtitle, int image, int color) {
            mTitle.setText(title);
            mSubTitle.setText(subtitle);
            mImage.setImageDrawable(ResourcesCompat.getDrawable(getResources(), image, null));
            mLayout.setBackgroundColor(ContextCompat.getColor(getContext(), color));
        }

    }

    private class StatsAdapter extends RecyclerView.Adapter<StatsHolder> {

        private List<HomeArgument> arguments;


        public StatsAdapter(HomeArgumentsLab argumentLab) {
            arguments = argumentLab.getArguments();
        }


        @Override
        public StatsHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.home_tile, parent, false);
            return new StatsHolder(view);
        }


        @Override
        public void onBindViewHolder(StatsHolder holder, int position) {
            HomeArgument argument = arguments.get(position);
            String title = argument.getTitle();
            String subtitle = argument.getSubtitle();
            int image = argument.getDrawable();
            int color = argument.getColor();
            holder.bindStats(title, subtitle, image, color);
        }

        @Override
        public int getItemCount() {

            return arguments.size();
        }


    }

}




