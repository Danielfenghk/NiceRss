package com.appsbrook.nicerss.presentation.presenter;


import android.widget.ArrayAdapter;

import com.appsbrook.nicerss.presentation.view.OneRssSourceView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class OneRssSourcePresenter extends MvpPresenter<OneRssSourceView> {

    public void setAdapterData() {

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Automobile");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");

        getViewState().setAdapterData(categories);
    }
}
