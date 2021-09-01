package ir.madreseplus.ui.view.login.complete.userinformation.city;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.madreseplus.R;
import ir.madreseplus.data.model.res.CityRes;
import ir.madreseplus.data.model.res.ProvinceRes;
import ir.madreseplus.ui.base.OnRvItemClickListener;


public class CityDialog extends DialogFragment implements OnRvItemClickListener<CityRes> {
    private List<CityRes> cityList;
    private RecyclerView recyclerView;
    private CityAdapter adapter;

    private CityCallback cityCallback;

    public CityDialog(List<CityRes> cityList) {
        this.cityList = cityList;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new CityAdapter();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_city_layout, null);
        recyclerView = view.findViewById(R.id.recyclerView);


        adapter.setItems(cityList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        adapter.setOnRvItemClickListener(this);

        builder.setView(view);
        return builder.create();
    }

    public void setCityCallback(CityCallback cityCallback) {
        this.cityCallback = cityCallback;
    }


    public static CityDialog newInstance(List<CityRes> res) {
        CityDialog fragment = new CityDialog(res);
        return fragment;
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onItemClick(CityRes item, int position) {
        this.dismiss();
        if (cityCallback != null) {
            cityCallback.onCitySelect(item);
        }
    }
}
