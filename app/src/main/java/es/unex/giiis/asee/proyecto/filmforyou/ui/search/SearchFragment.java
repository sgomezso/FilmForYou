package es.unex.giiis.asee.proyecto.filmforyou.ui.search;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.io.Serializable;
import java.util.Collection;

import es.unex.giiis.asee.proyecto.filmforyou.Adapters.MoviesAdapter;
import es.unex.giiis.asee.proyecto.filmforyou.R;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.MovieDetail;
import es.unex.giiis.asee.proyecto.filmforyou.databinding.FragmentSearchBinding;
import es.unex.giiis.asee.proyecto.filmforyou.ui.resultsSearch.ResultsSearchActivity;

public class SearchFragment extends Fragment implements MoviesAdapter.OnListInteractionListener  {
    private FragmentSearchBinding binding;
    public String searchExpresion = "";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Botón Buscar
        Button button = root.findViewById(R.id.idBuscar);
        button.setBackgroundColor(Color.YELLOW);
        button.setTextColor(Color.rgb(0,0,0));
        //Titulo
        EditText title = (EditText) root.findViewById(R.id.idValorTitulo);
        //Género
            Spinner spinner = (Spinner) root.findViewById(R.id.idSpinnerGenero);
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(root.getContext(), R.array.array_generos, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            spinner.setAdapter(adapter);
        //Director
        EditText director = (EditText) root.findViewById(R.id.idValorDirector);
        //Actor
        EditText actor = (EditText) root.findViewById(R.id.idValorActor);
        //Año
        EditText year = (EditText) root.findViewById(R.id.idValorAno);
        button.setOnClickListener(view -> {
            searchExpresion = "";
            title.setError(null);
            director.setError(null);
            actor.setError(null);
            year.setError(null);

            if(title.getText() == null)
                title.setText("");
            if(director.getText() == null)
                director.setText("");
            if(actor.getText()==null)
                actor.setText("");
            if(year.getText()==null)
                year.setText("");

            if(year.getText().toString().compareTo("")!=0){
                if (Integer.parseInt(year.getText().toString()) > 2022) {
                    year.setError("El año debe ser menor o igual que el actual");
                }
            }

            if(title.getError()==null && director.getError()==null && actor.getError()==null && year.getError()==null) {
                searchExpresion = searchExpresion.concat(title.getText().toString());
                searchExpresion = searchExpresion.concat(" ").concat(director.getText().toString());
                searchExpresion = searchExpresion.concat(" ").concat(year.getText().toString());
                searchExpresion = searchExpresion.concat(" ").concat(actor.getText().toString());
                searchExpresion = searchExpresion.concat(" ").concat(spinner.getSelectedItem().toString());
                if(searchExpresion.compareTo("    ")==0){
                    Toast.makeText(root.getContext(),"Debes introducir al menos un dato", Toast.LENGTH_LONG).show();
                }else {
                    Log.i("Busqueda:", searchExpresion);
                    Intent intent = new Intent(getContext(), ResultsSearchActivity.class);
                    intent.putExtra("busqueda", (Serializable) searchExpresion);
                    startActivity(intent);
                }
            }else{
                Toast.makeText(getContext(),"Debes introducir datos correctos", Toast.LENGTH_SHORT);
                Log.i("ERROR", "Fallo search");
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onListInteraction(String url) {

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}