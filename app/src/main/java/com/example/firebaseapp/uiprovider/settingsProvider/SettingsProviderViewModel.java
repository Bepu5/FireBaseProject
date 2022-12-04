package com.example.firebaseapp.uiprovider.settingsProvider;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SettingsProviderViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SettingsProviderViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("quina pasada4");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
