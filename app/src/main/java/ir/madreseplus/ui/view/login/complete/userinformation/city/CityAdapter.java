package ir.madreseplus.ui.view.login.complete.userinformation.city;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import ir.madreseplus.R;
import ir.madreseplus.data.model.res.CityRes;
import ir.madreseplus.data.model.res.ProvinceRes;
import ir.madreseplus.ui.base.RvAdapter;
import ir.madreseplus.ui.base.RvViewHolder;

public class CityAdapter extends RvAdapter<CityRes, CityAdapter.CityViewHolder> {

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CityViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city_layout, parent, false));
    }


    public class CityViewHolder extends RvViewHolder<CityRes> {
        private TextView nameTextView;

        public CityViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textView);
        }

        @Override
        public void bind(CityRes item) {
            nameTextView.setText(item.getName());
        }
    }
}