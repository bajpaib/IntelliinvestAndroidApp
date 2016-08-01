package com.intelli.Adapter;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Pooja on 15-03-2016.
 */
public class portfolioChildObject {
    long sequence;

    public void setSequence(long s)
    {
        sequence = s;
    }

    public long getSequence ()
    {
        return sequence;
    }

    public portfolioChildObject(long s) {
        super();
        this.sequence = s;
    }

    public portfolioChildObject() {
        super();
    }

    public void setData(JSONObject data)
    {
        try {
            this.sequence = data.getLong("Sequence");
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
