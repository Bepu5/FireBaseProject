package com.example.firebaseapp.uiprovider.homeProvider;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeProviderViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeProviderViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("quina pasada2");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
