package com.intelli.Adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Pooja on 26-04-2016.
 */
public class AnalysisTabObject {
    private int imageId;
    private String title;
    private String description;
    ArrayList<AnalysisTabChildObject> childList;

    public AnalysisTabObject(int imageId, String title, String desc) {
        this.imageId = imageId;
        this.title = title;
        this.description = desc;
    }

    public AnalysisTabObject(String title, String desc) {
        this.title = title;
        this.description = desc;
    }

    public  AnalysisTabObject () {}

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

    public ArrayList<AnalysisTabChildObject> getChildList() {
        return childList;
    }

    public void setChildList(ArrayList<AnalysisTabChildObject> list) {
        this.childList = list;
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

    public void setChildData(JSONArray data)
    {
        try {

            childList = new ArrayList<>();
            for(int i=0; i<data.length(); i++)
            {
                AnalysisTabChildObject ob = new AnalysisTabChildObject();
                JSONObject obj = data.getJSONObject(i);
                ob.setTitle(obj.getString("title"));
                ob.setDesc(obj.getString("description"));

                if(obj.has("imageId")) {
                    ob.setImageId(obj.getInt("imageId"));
                }
                childList.add(ob);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
