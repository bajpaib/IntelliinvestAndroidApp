package com.intelli.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.intelli.Adapter.portfolioListAdapter;
import com.intelli.Adapter.portfolioListObject;
import com.intelli.intelliinvest.R;
import com.intelli.utils.ApiConstants;
import com.intelli.utils.ConnectionHelper;
import com.intelli.utils.Constants;
import com.intelli.utils.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PortfolioFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PortfolioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PortfolioFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ExpandableListView portfolioListView;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PortfolioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PortfolioFragment newInstance(String param1, String param2) {
        PortfolioFragment fragment = new PortfolioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public PortfolioFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_portfolio, container, false);

        JSONArray jsonArray= new JSONArray();
        JSONObject obj = new JSONObject();
        try {

            obj.put("Code","REL");
            obj.put("Invested_Value", "10000");
            obj.put("Change","10");
            obj.put("Change_Direction",1);
            obj.put("Signal","BUY");
            obj.put("Sequence", 1);
        }

        catch (JSONException e)
        {
            Logger.logE(Constants.ACTIVITY_PORTFOLIO,""+e);
        }
        jsonArray.put(obj);

        JSONObject obj1 = new JSONObject();
        try {

            obj1.put("Code","INF");
            obj1.put("Invested_Value", "100000");
            obj1.put("Change","20");
            obj1.put("Change_Direction", -1);
            obj1.put("Signal","SELL");
            obj1.put("Sequence",2);
        }

        catch (JSONException e)
        {
            Logger.logE(Constants.ACTIVITY_PORTFOLIO,""+e);
        }
        jsonArray.put(obj1);

        portfolioListView = (ExpandableListView) rootView.findViewById(R.id.portFolioList);

        setListView(jsonArray);

        //listener for child row click
//        portfolioListView.setOnChildClickListener(portfolioListChildClick);
        //listener for group heading click
        portfolioListView.setOnGroupClickListener(portfolioListClick);

        // Inflate the layout for this fragment
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setListView(JSONArray data)
    {
        ArrayList<portfolioListObject> dataList = new ArrayList<>();

        for (int i=0; i<data.length(); i++)
        {
            portfolioListObject obj = new portfolioListObject();
            try {
                obj.setData(data.getJSONObject(i));
            } catch (JSONException e){
                e.printStackTrace();
            }
            dataList.add(obj);
        }

//create an ArrayAdapter from the String Array
        portfolioListAdapter dataAdapter = new portfolioListAdapter(getActivity(), dataList);

// Assign adapter to ListView
        if(portfolioListView != null) {
            portfolioListView.setAdapter(dataAdapter);
        }

    }

    //our child listener
//    private ExpandableListView.OnChildClickListener portfolioListChildClick =  new ExpandableListView.OnChildClickListener() {
//
//        public boolean onChildClick(ExpandableListView parent, View v,
//                                    int groupPosition, int childPosition, long id) {
//
////            //get the group header
////            HeaderInfo headerInfo = deptList.get(groupPosition);
////            //get the child info
////            DetailInfo detailInfo =  headerInfo.getProductList().get(childPosition);
////            //display it or do something with it
////            Toast.makeText(getBaseContext(), "Clicked on Detail " + headerInfo.getName()
////                    + "/" + detailInfo.getName(), Toast.LENGTH_LONG).show();
//
//
////            Intent intent = new Intent(getActivity(), UpdatePortfolioActivity.class);
////            startActivity(intent);
//
//            return false;
//        }
//
//    };

    //our group listener
    private ExpandableListView.OnGroupClickListener portfolioListClick =  new ExpandableListView.OnGroupClickListener() {

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
        public void onFragmentInteraction( Uri uri);
    }

}
