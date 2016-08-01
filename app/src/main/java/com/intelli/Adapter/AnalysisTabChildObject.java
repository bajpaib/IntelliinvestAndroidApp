package com.intelli.Adapter;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Pooja on 08-05-2016.
 */
public class AnalysisTabChildObject {
    private int imageId;
    private String title;
    private String description;

    public AnalysisTabChildObject(int imageId, String title, String desc) {
        this.imageId = imageId;
        this.title = title;
        this.description = desc;
    }

    public AnalysisTabChildObject(String title, String desc) {
        this.title = title;
        this.description = desc;
    }

    public AnalysisTabChildObject() {
        super();
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getDesc() {
        return description;
    }

    public void setDesc(String desc) {
        this.description = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title + "\n" + description;
    }

    public void setData(JSONObject data)
    {
        try {
            this.title = data.getString("title");
            this.description = data.getString("description");

            if(data.has("imageId")) {
                this.imageId = data.getInt("imageId");
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

