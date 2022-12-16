package es.unex.giiis.asee.proyecto.filmforyou;

import android.app.Activity;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;

public class loadingDialog {

    private Activity activity;
    private AlertDialog alertDialog;

    public loadingDialog(Activity myactivity) {
        activity = myactivity;
    }

    public void startLoadingDialog() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog_loading, null));
        builder.setCancelable(false);

        alertDialog = builder.create();
        alertDialog.show();
    }

    public void dismisDialog() {
        alertDialog.dismiss();
    }
}
