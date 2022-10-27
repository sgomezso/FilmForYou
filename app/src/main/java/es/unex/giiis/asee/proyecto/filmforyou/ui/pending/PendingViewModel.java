package es.unex.giiis.asee.proyecto.filmforyou.ui.pending;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PendingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PendingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is favorites fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}