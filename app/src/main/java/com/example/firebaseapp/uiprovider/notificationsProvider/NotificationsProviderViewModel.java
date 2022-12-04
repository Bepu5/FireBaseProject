package com.example.firebaseapp.uiprovider.notificationsProvider;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificationsProviderViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public NotificationsProviderViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("quina pasada3");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
