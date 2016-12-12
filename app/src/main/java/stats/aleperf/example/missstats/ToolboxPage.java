package stats.aleperf.example.missstats;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ToolboxPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToolboxPage extends Fragment {

    private static final String PAGE_TITLE = "page title";
    private static final String PAGE_TEXT = "page text";


    private String mTitle;
    private String mText;


    public ToolboxPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title TextViewTitle
     * @param text TextViewText
     * @return A new instance of fragment ToolboxPage.
     */

    public static ToolboxPage newInstance(String title, String text) {
        ToolboxPage fragment = new ToolboxPage();
        Bundle args = new Bundle();
        args.putString(PAGE_TITLE, title);
        args.putString(PAGE_TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            mTitle = getArguments().getString(PAGE_TITLE);
            mText = getArguments().getString(PAGE_TEXT);
            Log.d("inside Toolbox Page :", mTitle + " " + mText);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_toolbox_page, container, false);
        TextView title = (TextView)view.findViewById(R.id.toolbox_page_title);
        title.setText(mTitle);
        TextView text = (TextView)view.findViewById(R.id.toolbox_page_text);
        text.setText(mText);

        return view;
    }

}
