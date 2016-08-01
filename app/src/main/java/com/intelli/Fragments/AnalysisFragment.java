package com.intelli.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;

import com.intelli.Adapter.AnalysisTabAdapter;
import com.intelli.Adapter.AnalysisTabChildObject;
import com.intelli.Adapter.AnalysisTabObject;
import com.intelli.intelliinvest.R;
import com.intelli.utils.Constants;
import com.intelli.utils.FlipAnimation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AnalysisFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AnalysisFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnalysisFragment extends Fragment {

    private ArrayList<AnalysisTabObject> rowItems;

    private ExpandableListView tabList;

    private OnFragmentInteractionListener mListener;

    static final int tabId[] = {1, 2, 3, 4, 5, 6};

    public AnalysisFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AnalysisFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AnalysisFragment newInstance(String param1, String param2) {
        AnalysisFragment fragment = new AnalysisFragment();
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

        View rootView = inflater.inflate(R.layout.fragment_analysis, container, false);

//        tabList = (ExpandableListView) rootView.findViewById(R.id.analysisTabList);
//        tabList.setOnGroupClickListener(tabClick);
//        setListView();

        setTabs(rootView);

        return rootView;
    }

    public void setTabs(View rootView)
    {
        RelativeLayout rL1 = (RelativeLayout) rootView.findViewById(R.id.tab1);
        rL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                v.setRotation(v.getRotation() + 180);
                View front = v.findViewById(R.id.tab1Front);
                View back = v.findViewById(R.id.tab1Back);
                flipCard(v, front, back, tabId[0]);
            }
        });

        RelativeLayout rL2 = (RelativeLayout) rootView.findViewById(R.id.tab2);
        rL2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View front = v.findViewById(R.id.tab2Front);
                View back = v.findViewById(R.id.tab2Back);
                flipCard(v, front, back, tabId[1]);
            }
        });

        RelativeLayout rL3 = (RelativeLayout) rootView.findViewById(R.id.tab3);
        rL3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View front = v.findViewById(R.id.tab3Front);
                View back = v.findViewById(R.id.tab3Back);
                flipCard(v, front, back, tabId[2]);
            }
        });

        RelativeLayout rL4 = (RelativeLayout) rootView.findViewById(R.id.tab4);
        rL4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View front = v.findViewById(R.id.tab4Front);
                View back = v.findViewById(R.id.tab4Back);
                flipCard(v, front, back, tabId[3]);
            }
        });

        RelativeLayout rL5 = (RelativeLayout) rootView.findViewById(R.id.tab5);
        rL5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View front = v.findViewById(R.id.tab5Front);
                View back = v.findViewById(R.id.tab5Back);
                flipCard(v, front, back, tabId[4]);
            }
        });

        RelativeLayout rL6 = (RelativeLayout) rootView.findViewById(R.id.tab6);
        rL6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View front = v.findViewById(R.id.tab6Front);
                View back = v.findViewById(R.id.tab6Back);
                flipCard(v, front, back, tabId[5]);
            }
        });
    }

    private void flipCard(View rootLayout, View front, View back, int id)
    {
        FlipAnimation flipAnimation = new FlipAnimation(front, back);

        if (front.getVisibility() == View.GONE)
        {
//            flipAnimation.reverse();
            Fragment currentFrag = newInstance("", "");
            Bundle args = new Bundle();
            String fragName = "";

            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.addToBackStack(null);

            switch (id)
            {
                case 1:
                    fragName = Constants.ACTIVITY_MACRO;
                    currentFrag = new MacroFragment();
//                    args.putInt("GroupPosition", groupPosition);
//                    args.putInt("ChildPosition", childPosition);
                    currentFrag.setArguments(args);
                    break;

                case 2:
                    fragName = Constants.ACTIVITY_INDUSTRY;
                    currentFrag = new IndustryFragment();
                    currentFrag.setArguments(args);
                    break;

                case 3:
                    fragName = Constants.ACTIVITY_FUNDAMENTAL;
                    currentFrag = new FundamentalFragment();
                    currentFrag.setArguments(args);
                    break;

                case 4:
                    fragName = Constants.ACTIVITY_TECHNICAL;
                    currentFrag = new TechnicalFragment();
                    currentFrag.setArguments(args);
                    break;

                case 5:
                    fragName = Constants.ACTIVITY_BUBBLE;
                    currentFrag = new BubbleFragment();
                    currentFrag.setArguments(args);
                    break;

                case 6:
                    fragName = Constants.ACTIVITY_FORECAST;
                    currentFrag = new ForecastFragment();
                    currentFrag.setArguments(args);
                    break;
            }

            ft.replace(R.id.fragmentContainer, currentFrag, fragName).setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
        } else {
            rootLayout.startAnimation(flipAnimation);
        }
    }

    public void setListView()
    {
        rowItems = new ArrayList<AnalysisTabObject>();

        String[] titles = {"Macro Analysis","Industry Analysis","Fundamental Analysis","Technical Analysis","Is it bubble yet?","Forecasting"};
        String[] descriptions = {"Check what the macro trends are","Which sector should you invest in?","Check out what your stocks will give you","Analysis description","Analysis description","Analysis description"};

        JSONObject childData = new JSONObject();
        JSONArray childArr = new JSONArray();
        JSONObject c = new JSONObject();

        try {
            c.put("title", "Exchange Rate");
            c.put("description", "Exchange Rate");
            childArr.put(c);
            c = new JSONObject();
            c.put("title", "Interest Rate");
            c.put("description", "Interest Rate");
            childArr.put(c);
            c = new JSONObject();
            c.put("title", "GDP");
            c.put("description", "GDP");
            childArr.put(c);
            c = new JSONObject();
            c.put("title", "FDI & FII");
            c.put("description", "FDI & FII");
            childArr.put(c);
            c = new JSONObject();
            c.put("title", "Inflation");
            c.put("description", "Inflation");
            childArr.put(c);
            c = new JSONObject();
            c.put("title", "Overall");
            c.put("description", "Overall");
            childArr.put(c);
            childData.put("Macro Analysis", childArr);

            childArr = new JSONArray();
            c.put("title", "Auto");
            c.put("description", "Auto");
            childArr.put(c);
            c = new JSONObject();
            c.put("title", "IT");
            c.put("description", "IT");
            childArr.put(c);
            c = new JSONObject();
            c.put("title", "Pharmaceutical");
            c.put("description", "Pharmaceutical");
            childArr.put(c);
            childData.put("Industrial Analysis", childArr);

            childArr = new JSONArray();
            c.put("title", "Fundamental");
            c.put("description", "Fundamental");
            childArr.put(c);
            c = new JSONObject();
            c.put("title", "Technical");
            c.put("description", "Technical");
            childArr.put(c);
            childData.put("Is is Bubble yet?", childArr);

            childArr = new JSONArray();
            c.put("title", "Analyze");
            c.put("description", "Analyze");
            childArr.put(c);
            c = new JSONObject();
            c.put("title", "Predict");
            c.put("description", "Predict");
            childArr.put(c);
            childData.put("Fundamental Analysis", childArr);

            childArr = new JSONArray();
            c.put("title", "Analyze");
            c.put("description", "Analyze");
            childArr.put(c);
            c = new JSONObject();
            c.put("title", "Predict");
            c.put("description", "Predict");
            childArr.put(c);
            childData.put("Technical Analysis", childArr);

        }catch (JSONException e) {

        }

        //Populate the List
        for (int i = 0; i < titles.length; i++) {
            AnalysisTabObject item = new AnalysisTabObject(titles[i], descriptions[i]);

            String[] childTitles = {"Macro Analysis","Industry Analysis"};
            String[] childDescriptions = {"Check what your company is worth","Which sector should you invest in?"};

            ArrayList<AnalysisTabChildObject> childL = new ArrayList<>();
            for (int j=0; j<childTitles.length; j++)
            {
                AnalysisTabChildObject it = new AnalysisTabChildObject(childTitles[j], childDescriptions[j]);
                childL.add(it);
            }
            item.setChildList(childL);

            rowItems.add(item);
        }

        AnalysisTabAdapter adapter = new AnalysisTabAdapter(getActivity(), rowItems);
        if(tabList != null) {
            tabList.setAdapter(adapter);
        }
    }

    private ExpandableListView.OnGroupClickListener tabClick =  new ExpandableListView.OnGroupClickListener() {

        public boolean onGroupClick(ExpandableListView parent, View v,
                                    int groupPosition, long id) {

//            //get the group header
//            HeaderInfo headerInfo = deptList.get(groupPosition);
//            //display it or do something with it
//            Toast.makeText(getBaseContext(), "Child on Header " + headerInfo.getName(),
//                    Toast.LENGTH_LONG).show();

            return false;
        }

    };

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
