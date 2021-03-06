package com.intelli.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.LineChart;
import com.intelli.intelliinvest.R;
import com.intelli.utils.ChartLibrary;

import java.util.ArrayList;
import java.util.List;

public class MacroFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private OnFragmentInteractionListener mListener;

    public MacroFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_macro, container, false);
        setLayout(rootView);
        createChart(rootView);

        return rootView;
    }

    public void setLayout(View rootView)
    {
        Spinner spinner = (Spinner) rootView.findViewById(R.id.macroTypeSpinner);

        List<String> categories = new ArrayList<>();
        categories.add("Exchange Rate");
        categories.add("Interest Rate");
        categories.add("GDP");
        categories.add("FDI & FII");
        categories.add("Inflation");
        categories.add("Overall");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, categories);

        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(this);
    }

    public void createChart(View rootView)
    {
        LineChart chartView = (LineChart) rootView.findViewById(R.id.chartViewMacro);
        ArrayList<Float> chartData = new ArrayList<>();
        chartData.add(4f);
        chartData.add(8f);
        chartData.add(6f);
        chartData.add(2f);
        chartData.add(18f);
        chartData.add(9f);

        ArrayList<String> xLabel = new ArrayList<>();
        xLabel.add("January");
        xLabel.add("February");
        xLabel.add("March");
        xLabel.add("April");
        xLabel.add("May");
        xLabel.add("June");

        ChartLibrary.loadLineChart(getContext(), chartView, chartData, xLabel, "Yearly analysis");
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

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