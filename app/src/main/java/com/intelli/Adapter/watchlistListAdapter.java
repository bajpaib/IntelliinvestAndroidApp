package com.intelli.Adapter;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.intelli.Fragments.RiskReturnFragment;
import com.intelli.intelliinvest.R;
import com.intelli.intelliinvest.UpdatePortfolioFragment;
import com.intelli.intelliinvest.ViewChartFragment;
import com.intelli.utils.Constants;

import java.util.ArrayList;

/**
 * Created by Pooja on 03-04-2016.
 */
public class watchlistListAdapter extends BaseExpandableListAdapter {

    private ArrayList<portfolioListObject> originalList;
    private Context context;

    public watchlistListAdapter(Context context, ArrayList<portfolioListObject> listDataHeader) {
        this.context = context;
        this.originalList = listDataHeader;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        portfolioChildObject childList = originalList.get(groupPosition).getChildList();
        return childList;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        portfolioChildObject childObj = (portfolioChildObject) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater inflateObj = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflateObj.inflate(R.layout.watchlist_child_row_layout, null);
        }

//        TextView buy = (TextView) view.findViewById(R.id.buyTxt);
//        buy.setText(childObj.getProfit().trim());

        Button add = (Button) view.findViewById(R.id.addBtn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContainer, new UpdatePortfolioFragment(), Constants.FRAGMENT_ADD_PORTFOLIO).commit();
                NavigationView navigationView = (NavigationView) ((FragmentActivity) context).findViewById(R.id.nav_view);
                navigationView.getMenu().getItem(3).setChecked(true);
                ((FragmentActivity) context).setTitle(Constants.FRAGMENT_ADD_PORTFOLIO);
            }
        });

        Button viewChart = (Button) view.findViewById(R.id.viewBtn);
        viewChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContainer, new ViewChartFragment(), Constants.FRAGMENT_VIEW_CHART).commit();
                NavigationView navigationView = (NavigationView) ((FragmentActivity) context).findViewById(R.id.nav_view);
                navigationView.getMenu().getItem(3).setChecked(true);
                ((FragmentActivity) context).setTitle(Constants.FRAGMENT_VIEW_CHART);
            }
        });

        Button risk = (Button) view.findViewById(R.id.riskBtn);
        risk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContainer, new RiskReturnFragment(), Constants.ACTIVITY_RISK).commit();
                NavigationView navigationView = (NavigationView) ((FragmentActivity) context).findViewById(R.id.nav_view);
                navigationView.getMenu().getItem(3).setChecked(true);
                ((FragmentActivity) context).setTitle(Constants.ACTIVITY_RISK);
            }
        });

        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return 1;
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

    private class ViewHolder {
        TextView code ;
        TextView invested_value;
        TextView change;
        TextView signal;
        ImageView iconExpand;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {

        ViewHolder holder = null;
        Log.v("ConvertView", String.valueOf(groupPosition));
        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.portfolio_row_layout, null);

            holder = new ViewHolder();
            holder.code = (TextView) convertView.findViewById(R.id.stockCodeTxt);
            holder.invested_value = (TextView) convertView.findViewById(R.id.investedTxt);
            holder.change = (TextView) convertView.findViewById(R.id.changeTxt);
            holder.signal = (TextView) convertView.findViewById(R.id.signalTxt);
            holder.iconExpand = (ImageView) convertView.findViewById(R.id.iconExpand);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        portfolioListObject ob = originalList.get(groupPosition);
        holder.code.setText(ob.getCode());
        holder.invested_value.setText(ob.getInvestedValue());
        holder.change.setText(ob.getChange());

        if(ob.getChangeDirection() == -1) {
            holder.change.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_down, 0);
        }else {
            holder.change.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.arrow_up,0);
        }

        if (isExpanded) {
            holder.iconExpand.setImageResource(R.drawable.icon_minus);
        } else {
            holder.iconExpand.setImageResource(R.drawable.icon_plus);
        }

        holder.signal.setText(ob.getSignal());

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
