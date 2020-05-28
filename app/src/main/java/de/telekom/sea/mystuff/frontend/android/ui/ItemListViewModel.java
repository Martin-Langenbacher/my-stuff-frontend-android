package de.telekom.sea.mystuff.frontend.android.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import de.telekom.sea.mystuff.frontend.android.api.ApiFactory;
import de.telekom.sea.mystuff.frontend.android.api.ApiResponse;
import de.telekom.sea.mystuff.frontend.android.api.ItemApi;
import de.telekom.sea.mystuff.frontend.android.model.Item;
import de.telekom.sea.mystuff.frontend.android.repo.ItemRepo;

public class ItemListViewModel extends ViewModel {



    // Ohne ...Context, ....
    public LiveData<ApiResponse<List<Item>>> getMyItems(){
        ApiFactory apiFactory = new ApiFactory();
        ItemApi itemApi = apiFactory.createApi(ItemApi.class); // Hier erzeuge ich das itemApi
        //ItemRepo itemRepo = new ItemRepo(itemApi);
        //return itemRepo.getAll();
        return itemApi.getAll(); // Grund für Repo: Google-Empfehlung (wegen offline-Nutzung!)
    }

}
