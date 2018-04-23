package com.appsbrook.timister.ui.main;

import com.appsbrook.timister.ui.BaseMvpPresenter;
import com.appsbrook.timister.ui.BaseMvpView;

class MainContract {

    interface MvpView extends BaseMvpView<MvpPresenter> {

        void showFabSnackBar();
    }

    interface MvpPresenter extends BaseMvpPresenter {

        void processFabClick();
    }
}
