package com.example.metaldude.sportflex.views.activities;

import android.os.Build.VERSION_CODES;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.BindView;
import com.example.metaldude.sportflex.App;
import com.example.metaldude.sportflex.R;
import com.example.metaldude.sportflex.api.Api;
import com.example.metaldude.sportflex.entities.Hero;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.lvHeroes)
    public ListView lvMainActivity;

    @Inject
    Retrofit retrofit;
    String[] names;

    private ProgressBar progressBar;


    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initHeroesList();
    }


    public void initHeroesList() {
        getHeroesList();
        lvMainActivity.setAdapter(new ArrayAdapter<>(getApplication(),
            android.R.layout.simple_expandable_list_item_1, names));
    }

    public void getHeroesList() {

        Api api = retrofit.create(Api.class);
        Call<List<Hero>> heroes = api.getHeroes();
        heroes.enqueue(new Callback<List<Hero>>() {
            @RequiresApi(api = VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                if (response.body() != null) {
                    List<Hero> heroes = response.body();
                    if (heroes != null && !heroes.isEmpty()) {
                        names = new String[heroes.size()];
                        for (int i = 0; i < heroes.size(); i++) {
                            names[i] = heroes.get(i).getName();
                        }
                    }

                } else {
                    showAlert("Some wrong...");
                }
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                showAlert(t.getMessage());
            }
        });
    }

    public void showAlert(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT);
    }
    public void showProggress(){
        if (progressBar == null){
            progressBar = new ProgressBar(getApplicationContext());
        }

    }
}
