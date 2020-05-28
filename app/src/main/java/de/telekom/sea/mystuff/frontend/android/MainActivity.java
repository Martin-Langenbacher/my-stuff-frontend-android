package de.telekom.sea.mystuff.frontend.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import java.util.ArrayList;

import de.telekom.sea.mystuff.frontend.android.ui.ItemListRecyclerViewAdapter;
import de.telekom.sea.mystuff.frontend.android.ui.ItemListViewModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView itemsList;
    private ItemListViewModel viewModel;
    private ItemListRecyclerViewAdapter itemListAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemsList = findViewById(R.id.rv_items);
        //  --> check the dogsExample:  --> Mayo ==> refreshLayout = findViewById(R.id.refreshRideListLayout);
        viewModel = new ViewModelProvider(this).get(ItemListViewModel.class);                // warum macht man dies so kompliziert? --> ViewModelProvider: der Erzeuger der ViewModel, da die ViewModel kann länger leben als die MainActivity...
        //viewModel.initWithApplication(getApplication());  // springt in MyStuffViewModel, damit bekomme ich den Context --> Api-Factory sind definiert (Zentraler Bereich, wie ich an meine Dienste komme)Zeile macht:                                           // darum legt man die Daten ab und man holt sie die alte Instanz aus dem Speicher...
        itemListAdapter = new ItemListRecyclerViewAdapter(new ArrayList<>());                       // Android kann sehr willkührlich sein.
        itemsList.setLayoutManager(new LinearLayoutManager(this));
        itemsList.setAdapter(itemListAdapter);  // verbindet Adapter mit RecyclerView; RecyclerView benutzt Adapter um die Zeilen aufzubauen.
        observeItemsListViewModel();            // ruft die Methode unten auf...
    }


    // LiveData (leere Hülle) ist, was ich im Hintergrund ändern kann. Der Ladevorgang ist ein Hintergrundtask. Benachrichtige uns, wenn Du fertig bist mit laden...!
    // ObserveForEver / Observe --> xxx / Observe, nur solange die Activity läuft
    private void observeItemsListViewModel() {
        viewModel.getMyItems().observe(this, listApiResponse -> {
            if (listApiResponse.isSuccessful()){
                itemListAdapter.updateList(listApiResponse.body);
            } else {
                Toast.makeText(MainActivity.this, listApiResponse.errorMessage, Toast.LENGTH_LONG).show();
            // ohne myStuff... keine Fehlermeldung, also über Toast...
               // ((MyStuffApplication) getApplication()).getMyStuffContext().sendInfoMessage(listApiResponse.errorMessage);
            }
        });
    }
}


