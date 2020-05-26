package de.telekom.sea.mystuff.frontend.android.repo;

import androidx.lifecycle.LiveData;

import java.util.List;

import de.telekom.sea.mystuff.frontend.android.api.ApiResponse;
import de.telekom.sea.mystuff.frontend.android.api.ItemApi;
import de.telekom.sea.mystuff.frontend.android.model.Item;

public class ItemRepo {

    private ItemApi itemApi;

    public ItemRepo(ItemApi itemApi){ this.itemApi = itemApi; }

    //public LiveData<ApiResponse<Item>> getById(Long id) { return itemApi.getById(id);}

    public LiveData<ApiResponse<List<Item>>> getAll() { return itemApi.getAll();}

    //public  LiveData<ApiResponse<Item>> save(Item item) { return itemApi.save(item);}

}
