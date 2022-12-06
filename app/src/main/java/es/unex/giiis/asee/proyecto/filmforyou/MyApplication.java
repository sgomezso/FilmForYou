package es.unex.giiis.asee.proyecto.filmforyou;

import android.app.Application;

public class MyApplication extends Application {
    public AppContainer appContainer;

    /**
    * Se realiza para pasarle el contexto a la clase room despues de cada activity y fragment
     * es la primera clase que se procesa
     */
    @Override
    public void onCreate() {
        super.onCreate();
        appContainer = new AppContainer(this);
    }
}