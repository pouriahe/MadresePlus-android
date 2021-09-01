package ir.madreseplus.ui.view.login.complete.userinformation.province;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.List;

import ir.madreseplus.R;
import ir.madreseplus.data.model.req.Podcast;
import ir.madreseplus.data.model.res.ProvinceRes;
import ir.madreseplus.ui.base.RvAdapter;
import ir.madreseplus.ui.base.RvViewHolder;

public class ProvinceAdapter extends RvAdapter<ProvinceRes, ProvinceAdapter.ProvinceViewHolder> {
    /*private OnItemClickListener onItemClickListener;*/

    @NonNull
    @Override
    public ProvinceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProvinceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_province_layout, parent, false));
    }

    /*public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }*/


    /*@Override
    public void onBindViewHolder(@NonNull ProvinceViewHolder holder, int position) {
        holder.bind(items.get(position));
        *//*holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClicked(items.get(position));
            }
        });*//*
    }*/

    public class ProvinceViewHolder extends RvViewHolder<ProvinceRes> {
        private TextView nameTextView;

        public ProvinceViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textView);
        }

        @Override
        public void bind(ProvinceRes item) {
            nameTextView.setText(item.getName());
        }
    }

    /*public interface OnItemClickListener {
        void onItemClicked(ProvinceRes province);
    }*/
}



