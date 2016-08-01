package com.intelli.Adapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Pooja on 20-03-2016.
 */
public class portfolioListObject {

    String Code ;
    String Invested_Value ;
    String Change;
    int Change_Direction;
    String Signal ;
    portfolioChildObject childList;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getInvestedValue() {
        return Invested_Value;
    }

    public void setInvestedValue(String value) {
        Invested_Value = value;
    }

    public String getChange() {
        return Change;
    }

    public void setChange(String change) {
        Change = change;
    }

    public int getChangeDirection() {
        return Change_Direction;
    }

    public void setChange_Direction(int direction) {
        Change_Direction = direction;
    }

    public String getSignal() {
        return Signal;
    }

    public void setSignal(String signal) {
        Signal = signal;
    }

    public portfolioChildObject getChildList()
    {
        return childList;
    }

    public void setChildList(portfolioChildObject c)
    {
        childList = c;
    }

    public portfolioListObject(String c, String i, String ch, int d, String s) {
        super();
        this.Code = c;
        this.Invested_Value = i;
        this.Change = ch;
        this.Change_Direction = d;
        this.Signal = s;
    }

    public portfolioListObject() {
        super();
    }

    public void setData(JSONObject data)
    {
        try {
            this.Code = data.getString("Code");
            this.Invested_Value = data.getString("Invested_Value");
            this.Change = data.getString("Change");
            this.Change_Direction = data.getInt("Change_Direction");
            this.Signal = data.getString("Signal");

            portfolioChildObject ch = new portfolioChildObject();
            ch.setSequence(data.getLong("Sequence"));
            this.childList = ch;
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
