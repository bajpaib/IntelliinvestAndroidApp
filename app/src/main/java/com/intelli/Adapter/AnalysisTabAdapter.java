package com.intelli.Adapter;

/**
 * Created by Pooja on 26-04-2016.
 */
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.intelli.intelliinvest.R;
import com.intelli.intelliinvest.UpdatePortfolioFragment;
import com.intelli.utils.Constants;
import com.intelli.utils.FlipAnimation;

import java.util.ArrayList;
import java.util.List;

public class AnalysisTabAdapter extends BaseExpandableListAdapter {

    private ArrayList<AnalysisTabObject> originalList;
    Context context;

    public AnalysisTabAdapter(Context context, ArrayList<AnalysisTabObject> listDataHeader) {
        this.context = context;
        this.originalList = listDataHeader;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<AnalysisTabChildObject> childList = originalList.get(groupPosition).getChildList();
//        return childList;
        AnalysisTabChildObject ch = new AnalysisTabChildObject();
        if(childPosition < childList.size()) {
            ch = childList.get(childPosition);
        }
        return ch;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        ChildViewHolder holder = null;

        if (view == null) {
            LayoutInflater inflateObj = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflateObj.inflate(R.layout.analysis_child_row_layout, null);

            holder = new ChildViewHolder();
            holder.title = (TextView) view.findViewById(R.id.tabTitle);
            holder.description = (TextView) view.findViewById(R.id.description);
            view.setTag(holder);
        } else {
            holder = (ChildViewHolder) view.getTag();
        }

        AnalysisTabChildObject childObj = (AnalysisTabChildObject) getChild(groupPosition, childPosition);
        holder.title.setText(childObj.getTitle());
        holder.description.setText(childObj.getDesc());

        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return originalList.get(groupPosition).getChildList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return originalList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return originalList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public class ViewHolder {
        ImageView image;
        TextView title;
        TextView titleBack;
        TextView description;
    }

    public class ChildViewHolder {
        ImageView image;
        TextView title;
        TextView description;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {

        ViewHolder holder = null;
        Log.v("ConvertView", String.valueOf(groupPosition));
        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.analysis_tab_layout, null);

            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.tabTitle);
            holder.titleBack = (TextView) convertView.findViewById(R.id.tabTitleBack);
            holder.description = (TextView) convertView.findViewById(R.id.description);
            convertView.setTag(holder);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//        RelativeLayout rL = (RelativeLayout) convertView.findViewById(R.id.leftTab);
//        rL.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                v.setRotation(v.getRotation() + 180);
//                View front = v.findViewById(R.id.tabTitle);
//                View back = v.findViewById(R.id.tabTitle);
//                flipCard(v, front, back);
//            }
//        });

        AnalysisTabObject rowItem = originalList.get(groupPosition);
        holder.title.setText(rowItem.getTitle());
//        holder.titleBack.setText(rowItem.getDesc());
        holder.description.setText(rowItem.getDesc());

        return convertView;
    }

//    private void flipCard(View rootLayout, View front, View back)
//    {
//        FlipAnimation flipAnimation = new FlipAnimation(front, back);
//
//        if (front.getVisibility() == View.GONE)
//        {
//            flipAnimation.reverse();
//        }
//        rootLayout.startAnimation(flipAnimation);
//    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
