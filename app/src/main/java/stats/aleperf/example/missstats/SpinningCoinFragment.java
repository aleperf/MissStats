package stats.aleperf.example.missstats;


import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activity Hosting This Fragment must implement the ProbabilityFragment.CoinDataRetrieverInterface
 */
public class SpinningCoinFragment extends Fragment {

    public static final int THUMB_UP_FACE = R.drawable.thumb_up;
    public static final int THUMB_DOWN_FACE = R.drawable.thumb_down;
    public static final String LAST_SEEN_FACE = "last seen face";
    public static final String IS_SPINNING = "is spinning";
    public static final String COUNTER_THUMBS_UP = "counter thumbs up";
    public static final String COUNTER_THUMBS_DOWN = "counter thumbs down";
    ImageView mSpinningCoinImage;
    TextView mThumbsUpCounterTextView;
    TextView mThumbsDownCounterTextView;
    AnimationDrawable mSpinningCoinAnimation;
    private int mCounterThumbsUp = 0;
    private int mCounterThumbsDown = 0;
    private boolean mIsSpinning = false;
    private int mLastSeenFace = THUMB_UP_FACE;
    private ProbabilityFragment.CoinDataRetriever mCallback;

    public SpinningCoinFragment() {
        // Required empty public constructor
        setRetainInstance(true);
    }

    public static SpinningCoinFragment newInstance(int counterUp, int counterDown, boolean isSpinning, int lastSeenFace) {
        Bundle bundle = new Bundle();
        bundle.putInt(COUNTER_THUMBS_UP, counterUp);
        bundle.putInt(COUNTER_THUMBS_DOWN, counterDown);
        bundle.putBoolean(IS_SPINNING, isSpinning);
        bundle.putInt(LAST_SEEN_FACE, lastSeenFace);

        SpinningCoinFragment fragment = new SpinningCoinFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (ProbabilityFragment.CoinDataRetriever) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement ProbabilityFragment.CoinDataRetriever");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_spinning_coin, container, false);
        mSpinningCoinImage = (ImageView) rootView.findViewById(R.id.spinning_coin);
        mThumbsUpCounterTextView = (TextView) rootView.findViewById(R.id.thumb_up_counter_text_view);
        mThumbsDownCounterTextView = (TextView) rootView.findViewById(R.id.thumb_down_counter_text_view);
        mSpinningCoinImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSpinning();
            }
        });
        if (savedInstanceState == null) {
            mCounterThumbsUp = getArguments().getInt(COUNTER_THUMBS_UP, 0);
            mCounterThumbsDown = getArguments().getInt(COUNTER_THUMBS_DOWN, 0);
            mIsSpinning = getArguments().getBoolean(IS_SPINNING, false);
            mLastSeenFace = getArguments().getInt(LAST_SEEN_FACE, THUMB_UP_FACE);
        } else {
            ProbabilityFragment.CoinDataRetriever coinDataRetriever = (ProbabilityFragment.CoinDataRetriever) getActivity();
            Bundle bundle = coinDataRetriever.retrieveCoinData();
            mCounterThumbsUp = bundle.getInt(SpinningCoinFragment.COUNTER_THUMBS_UP, 0);
            mCounterThumbsDown = bundle.getInt(SpinningCoinFragment.COUNTER_THUMBS_DOWN);
            mIsSpinning = bundle.getBoolean(SpinningCoinFragment.IS_SPINNING, false);
            mLastSeenFace = bundle.getInt(SpinningCoinFragment.LAST_SEEN_FACE, SpinningCoinFragment.THUMB_UP_FACE);
        }


        mThumbsUpCounterTextView.setText(String.valueOf(mCounterThumbsUp));
        mThumbsDownCounterTextView.setText(String.valueOf(mCounterThumbsDown));

        if (mIsSpinning) {//if the coin is spinning, keep it spinning
            mSpinningCoinImage.setBackgroundResource(R.drawable.shiny_coin_spinning);
            mSpinningCoinAnimation = (AnimationDrawable) mSpinningCoinImage.getBackground();
            mSpinningCoinAnimation.start();
        } else {// show the last seen face of the coin
            if (mLastSeenFace == THUMB_UP_FACE) {
                mSpinningCoinImage.setBackgroundResource(THUMB_UP_FACE);
            } else {
                mSpinningCoinImage.setBackgroundResource(THUMB_DOWN_FACE);
            }

        }

        Button resetButton = (Button) rootView.findViewById(R.id.spinning_coin_reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLastSeenFace = THUMB_UP_FACE;
                mCounterThumbsUp = 0;
                mCounterThumbsDown = 0;
                if (mSpinningCoinAnimation != null) {
                    mSpinningCoinAnimation.stop();
                    mSpinningCoinAnimation = null;
                }
                mIsSpinning = false;
                mSpinningCoinImage.setBackgroundResource(mLastSeenFace);
                mThumbsUpCounterTextView.setText(String.valueOf(mCounterThumbsUp));
                mThumbsDownCounterTextView.setText(String.valueOf(mCounterThumbsDown));
            }
        });

        return rootView;
    }

    private void updateSpinning() {
        if (!mIsSpinning) {
            mIsSpinning = true;
            mSpinningCoinImage.setBackgroundResource(R.drawable.shiny_coin_spinning);
            mSpinningCoinAnimation = (AnimationDrawable) mSpinningCoinImage.getBackground();
            mSpinningCoinAnimation.start();
        } else {
            mIsSpinning = false;
            Random random = new Random();
            int face = random.nextInt(2);
            if (mSpinningCoinAnimation != null) {
                mSpinningCoinAnimation.stop();
            }
            if (face == 0) {
                mSpinningCoinImage.setBackgroundResource(R.drawable.thumb_up);
                mLastSeenFace = THUMB_UP_FACE;
                mCounterThumbsUp++;
                mThumbsUpCounterTextView.setText(String.valueOf(mCounterThumbsUp));
            } else {
                mSpinningCoinImage.setBackgroundResource(R.drawable.thumb_down);
                mLastSeenFace = THUMB_DOWN_FACE;
                mCounterThumbsDown++;
                mThumbsDownCounterTextView.setText(String.valueOf(mCounterThumbsDown));
            }
            mSpinningCoinAnimation = null;

        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mCallback.saveCoinData(mCounterThumbsUp, mCounterThumbsDown, mIsSpinning, mLastSeenFace);
        outState.putInt(SpinningCoinFragment.COUNTER_THUMBS_UP, mCounterThumbsUp);
        outState.putInt(SpinningCoinFragment.COUNTER_THUMBS_DOWN, mCounterThumbsDown);
        outState.putBoolean(SpinningCoinFragment.IS_SPINNING, mIsSpinning);
        outState.putInt(SpinningCoinFragment.LAST_SEEN_FACE, SpinningCoinFragment.THUMB_UP_FACE);


    }

    @Override
    public void onPause() {
        super.onPause();
        mCallback.saveCoinData(mCounterThumbsUp, mCounterThumbsDown, mIsSpinning, mLastSeenFace);

    }

}
