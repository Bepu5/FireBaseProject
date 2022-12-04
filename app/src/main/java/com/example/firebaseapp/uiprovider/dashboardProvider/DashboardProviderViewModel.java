package com.example.firebaseapp.uiprovider.dashboardProvider;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardProviderViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public DashboardProviderViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("quina pasada1");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
