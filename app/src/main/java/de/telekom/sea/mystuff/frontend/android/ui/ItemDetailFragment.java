package de.telekom.sea.mystuff.frontend.android.ui;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.sql.Date;
import java.time.LocalDate;

import de.telekom.sea.mystuff.frontend.android.MyStuffApplication;
import de.telekom.sea.mystuff.frontend.android.MyStuffContext;
import de.telekom.sea.mystuff.frontend.android.R;
import de.telekom.sea.mystuff.frontend.android.api.ApiFactory;
import de.telekom.sea.mystuff.frontend.android.api.ApiResponse;
import de.telekom.sea.mystuff.frontend.android.api.ItemApi;
import de.telekom.sea.mystuff.frontend.android.databinding.FragmentDetailBinding;
import de.telekom.sea.mystuff.frontend.android.databinding.FragmentDetailBindingImpl;
import de.telekom.sea.mystuff.frontend.android.databinding.MyStuffItemBindingImpl;
import de.telekom.sea.mystuff.frontend.android.model.Item;
import lombok.NonNull;


public class ItemDetailFragment extends Fragment {

    private FragmentDetailBinding binding;


    //private final ItemApi itemApi = ApiFactory.getInstance().createApi(ItemApi.class);   // ????????????????????????????????????????????????????????????????????????????????

    public static ItemDetailFragment newInstance() {
        return new ItemDetailFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);

        //binding.setItem((Item) getArguments().getSerializable("item"));

        return binding.getRoot();
        // return inflater.inflate(R.layout.fragment_detail, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        long itemId = getArguments().getLong("itemId");
        Log.d("ItemDetailFragment", "ItemId: " + itemId);
        Toast.makeText(getContext(), "ItemId: " + itemId, Toast.LENGTH_LONG);
        // wir müssen an dieser Stelle an den Context kommen:
        MyStuffApplication application = (MyStuffApplication) requireActivity().getApplication();
        MyStuffContext myStuffContext = application.getMyStuffContext();  // alt + enter


        //viewModel.initWithApplication(requireActivity().getApplication());


        myStuffContext.getItemRepo().getById(itemId).observe(this.getViewLifecycleOwner(), itemApiResponse -> {
            if (itemApiResponse.isSuccessful()){
                binding.setItem(itemApiResponse.body);
            }else {
                Toast.makeText(getContext(),"Could not load item", Toast.LENGTH_LONG).show();
            }
        });

        //Item item = Item.builder().name("Test Rasenmäher").description("ML Test").amount(1).lastUsed(Date.valueOf("2020-05-29")).location("ML-Basement").build();
        //binding.setItem(item);
    }

}



