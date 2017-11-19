package com.tony.mytonyservice.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tony.mytonyservice.R;
import com.tony.mytonyservice.utility.GetJSON;
import com.tony.mytonyservice.utility.MyConstant;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Tony on 18/11/17.
 */

public class SecondFragment extends Fragment {

    //Explicit
    private String jsonString, dateString, rateString, usdString, answerString;
    private String jsonRateString;
    private double rateDouble;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Read All JSON to Class
        readJSON();

//        Show Date
        showDate();


    }

    private void showDate() {
        TextView textView = getView().findViewById(R.id.txtShowDate);
        try {
            JSONArray jsonArray = new JSONArray("[" + jsonString+ "]");
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            dateString = jsonObject.getString("date");
            Log.d("19nov17", "Date ==> " + dateString);

            textView.setText(getText(R.string.date) + dateString);

            jsonRateString = "[" + jsonObject.getString("rates") + "]";
            Log.d("19nov17", "Rate ==> " + jsonRateString);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void readJSON() {
        String tag = "19nov17";
        MyConstant myConstant = new MyConstant();

        try {
            GetJSON getJSON = new GetJSON(getActivity());
            getJSON.execute(myConstant.getUrlJASON());

            jsonString = getJSON.get();
            Log.d(tag, "JSON ==> " + jsonString);

        } catch (Exception e) {
            Log.d(tag, "e. ==> " + e.toString());
        }

    }

    // Create View Fragment

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        return view;
    }
} // Main Class
