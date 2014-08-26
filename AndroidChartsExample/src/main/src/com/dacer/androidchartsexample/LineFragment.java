package com.dacer.androidchartsexample;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dacer.androidcharts.LineView;
import com.dacer.androidcharts.LineView.ChartDotOnclickListener;
import com.googlecode.android.widgets.DateSlider.SliderContainer;
import com.googlecode.android.widgets.DateSlider.SliderContainer.OnTimeChangeListener;

/**
 * Created by Dacer on 11/15/13.
 */
public class LineFragment extends Fragment implements ChartDotOnclickListener, OnTimeChangeListener {
    int randomint = 31;
    int month = Calendar.getInstance().getTime().getMonth() + 1;
    private SliderContainer mContainer;
    private LineView lineView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_line, container, false);
        lineView = (LineView) rootView.findViewById(R.id.line_view);

        // must*
        // ArrayList<String> test = new ArrayList<String>();
        // for (int i=0; i<randomint; i++){
        // test.add(String.valueOf(i+1));
        // }
        // lineView.setBottomTextList(test);
        lineView.setDrawDotLine(true);
        lineView.setShowPopup(LineView.SHOW_POPUPS_MAXMIN_ONLY);
        lineView.setChartDotOnclickListener(this);
        Button lineButton = (Button) rootView.findViewById(R.id.line_button);
        lineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomSet(lineView);

            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mContainer = (SliderContainer) getActivity().findViewById(R.id.dateSliderContainer);
        mContainer.setTime(Calendar.getInstance());
        mContainer.setOnTimeChangeListener(this);

        randomSet(lineView);
    }

    private void randomSet(LineView lineView) {
        ArrayList<Integer> dataList = new ArrayList<Integer>();
        int random = (int) (Math.random() * 100 + 1);
        for (int i = 0; i < randomint; i++) {
            dataList.add((int) (Math.random() * 100 + 1));
        }

        ArrayList<Integer> dataList2 = new ArrayList<Integer>();
        for (int i = 0; i < randomint; i++) {
            dataList2.add((int) (Math.random() * random));
        }

        ArrayList<Integer> dataList3 = new ArrayList<Integer>();
        ArrayList<String> xLabels = new ArrayList<String>();
        for (int i = 0; i < randomint; i++) {
            dataList3.add((int) (Math.random() * random));
            xLabels.add(month + "." + (i + 1));
        }

        ArrayList<ArrayList<Integer>> dataLists = new ArrayList<ArrayList<Integer>>();
        dataLists.add(dataList);
        // dataLists.add(dataList3);
        lineView.setBottomTextList(xLabels);
        lineView.setDataList(dataLists);

    }

    @Override
    public void ChartDotOnclick(int i) {
        Toast.makeText(getActivity(), "第" + i + "个点", 2000).show();
    }

    @Override
    public void onTimeChange(Calendar time) {
        if (time.getTime().getMonth() + 1 != month) {
            month = time.getTime().getMonth() + 1;
            randomSet(lineView);
        }
    }
}