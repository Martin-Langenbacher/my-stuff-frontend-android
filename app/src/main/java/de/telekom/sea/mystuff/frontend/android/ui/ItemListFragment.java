package de.telekom.sea.mystuff.frontend.android.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import de.telekom.sea.mystuff.frontend.android.MyStuffApplication;
import de.telekom.sea.mystuff.frontend.android.R;


public class ItemListFragment extends Fragment {

    private ItemListRecyclerViewAdapter adapter;
    private ItemListViewModel viewModel;

    private RecyclerView itemsList;
    private ItemListRecyclerViewAdapter itemListAdapter = null;

    public static ItemListFragment newInstance() {
        return new ItemListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_list_fragment, container, false);
        // @Nullable vs NonNull Problem !
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        viewModel = new  ViewModelProvider(this).get(ItemListViewModel.class);
        viewModel.initWithApplication(requireActivity().getApplication());

        /* oder Version...
        viewModel.initWithApplication(requireActivity().getApplication());  // springt in MyStuffViewModel, damit bekomme ich den Context --> Api-Factory sind definiert (Zentraler Bereich, wie ich an meine Dienste komme)Zeile macht:                                           // darum legt man die Daten ab und man holt sie die alte Instanz aus dem Speicher...
        NavController navController = Navigation.findNavController(this.requireActivity(), R.id.navigation_host_fragment);
        itemListAdapter = new ItemListRecyclerViewAdapter(navController);                       // Android kann sehr willkührlich sein.
        itemsList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        itemsList.setAdapter(itemListAdapter);  // verbindet Adapter mit RecyclerView; RecyclerView benutzt Adapter um die Zeilen aufzubauen.  */


        adapter = new ItemListRecyclerViewAdapter(Navigation.findNavController(view));

        RecyclerView rv = view.findViewById(R.id.rv_items);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);


        viewModel.getMyItems().observe(this.getViewLifecycleOwner(), listApiResponse -> {
            if (listApiResponse.isSuccessful()){
                adapter.updateList(listApiResponse.body);
            }else {
                Toast.makeText(getContext(), "Could not load list", Toast.LENGTH_LONG).show();
            }
        });
    }

}

