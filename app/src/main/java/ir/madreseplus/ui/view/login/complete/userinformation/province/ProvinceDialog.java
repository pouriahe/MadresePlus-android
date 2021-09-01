package ir.madreseplus.ui.view.login.complete.userinformation.province;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.madreseplus.R;
import ir.madreseplus.data.model.res.ProvinceRes;
import ir.madreseplus.ui.base.OnRvItemClickListener;


public class ProvinceDialog extends DialogFragment implements OnRvItemClickListener<ProvinceRes>{
    private List<ProvinceRes> provinceList;
    private RecyclerView recyclerView;
    private ProvinceAdapter adapter;

    private ProvinceCallback provinceCallback;

    public ProvinceDialog(List<ProvinceRes> provinceList) {
        this.provinceList = provinceList;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ProvinceAdapter();

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_province_layout, null);
        recyclerView = view.findViewById(R.id.recyclerView);


        adapter.setItems(provinceList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        adapter.setOnRvItemClickListener(this);
        builder.setView(view);
        return builder.create();
    }

    public void setProvinceCallback(ProvinceCallback provinceCallback) {
        this.provinceCallback = provinceCallback;
    }


    public static ProvinceDialog newInstance(List<ProvinceRes> res) {
        ProvinceDialog fragment = new ProvinceDialog(res);
        return fragment;
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onItemClick(ProvinceRes item, int position) {
        this.dismiss();
        if (provinceCallback != null) {
            provinceCallback.onProvinceSelect(item);
        }
    }

}
