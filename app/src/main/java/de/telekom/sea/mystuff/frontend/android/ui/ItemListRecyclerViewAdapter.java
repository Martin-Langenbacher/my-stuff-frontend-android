package de.telekom.sea.mystuff.frontend.android.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.telekom.sea.mystuff.frontend.android.R;
import de.telekom.sea.mystuff.frontend.android.databinding.MyStuffItemBinding;
import de.telekom.sea.mystuff.frontend.android.model.Item;
import lombok.Getter;
import timber.log.Timber;

// ViewAdapter ist zur Anzeige der View zustän

public class ItemListRecyclerViewAdapter extends RecyclerView.Adapter<ItemListRecyclerViewAdapter.ViewHolder> {

    private static int countOnBindViewHolder = 0;
    private static int countOnCreateViewHolder = 0;

    private final List<Item> items;  // final List kann Referenz nicht ändern, da final! --> Inhalt kann geändert werden - trotz final!
    private NavController navController;

    public ItemListRecyclerViewAdapter(NavController navController){
        this.items = new ArrayList<>();
        this.navController = navController;
    }


    public void updateList(List<Item> list){
        this.items.clear();
        this.items.addAll(list);
        notifyDataSetChanged();  // Benachrichtigung an Oberfläche: etwas wurde geändert!
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        countOnCreateViewHolder++;
        Timber.d("-----------------------------------------------------------------> countOnCreateViewHolder " + countOnCreateViewHolder);
        MyStuffItemBinding listItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.my_stuff_item, parent, false);
        return new ViewHolder(listItemBinding, false);
    }


    @Override
    public void onBindViewHolder(@lombok.NonNull ViewHolder holder, int position){
        countOnBindViewHolder++;
        Timber.d("-----------------------------------------------------------------> OnBindViewHolder " + countOnBindViewHolder);
        Item item = this.items.get(position);
        holder.binding.setItem(item);

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("ItemListRecycler", "Click row : ", v);
                Bundle bundle = new Bundle();  // Bundle ist so etwas wie eine map
                bundle.putLong("itemId", item.getId());
               // bundle.putSerializable("item", (Serializable) item); // ???????????????????????????????????????????????????????????????????????????? cast ???
                //navController.navigate(R.id.actionToDetail, bundle);
                navController.navigate(R.id.actionToDetail, bundle);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (items != null) {
            return items.size();
        }else {
            return 0;
        }
    }



    static class  ViewHolder extends RecyclerView.ViewHolder {


        final MyStuffItemBinding binding;

        ViewHolder(MyStuffItemBinding binding, Boolean received) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}




// Info, RecyclerView (Link):
// https://androidwave.com/android-recyclerview-example-best-practices/


// Info, more general (Link):
// https://androidwave.com/android-data-binding-recyclerview/

