package de.telekom.sea.mystuff.frontend.android.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

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

    @Getter
    private final List<Item>list;  // final List kann Referenz nicht ändern, da final! --> Inhalt kann geändert werden - trotz final!

    public ItemListRecyclerViewAdapter(List<Item> list){
        this.list = list;
    }


    public void updateList(List<Item> newList){
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();  // Benachrichtigung an Oberfläche: etwas wurde geändert!
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        countOnCreateViewHolder++;
        Timber.d("-----------------------------------------------------------------> countOnCreateViewHolder " + countOnCreateViewHolder);
        MyStuffItemBinding listItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.my_stuff_item, parent, false);
        return new ViewHolder(listItemBinding);
    }


    @Override
    public void onBindViewHolder(@lombok.NonNull ViewHolder holder, int position){
        countOnBindViewHolder++;
        Timber.d("-----------------------------------------------------------------> OnBindViewHolder " + countOnBindViewHolder);
        Item item = getList().get(position);
        holder.getBinding().setItem(item);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }



    public class  ViewHolder extends RecyclerView.ViewHolder {

        @Getter
        private MyStuffItemBinding binding;

        public ViewHolder(@lombok.NonNull MyStuffItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}


// Info, RecyclerView (Link):
// https://androidwave.com/android-recyclerview-example-best-practices/


// Info, more general (Link):
// https://androidwave.com/android-data-binding-recyclerview/

