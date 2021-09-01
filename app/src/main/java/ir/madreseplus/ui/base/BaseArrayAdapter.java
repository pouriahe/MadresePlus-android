package ir.madreseplus.ui.base;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


/**
 * Ahmad Nosratian created on 10/30/20
 */

public abstract class BaseArrayAdapter<T> extends ArrayAdapter<T> {

    private Context context;
    private int resourceId;
    private List<T> items;
    private OnRvItemClickListener<T> onItemClickListener;
    protected View view;

    public BaseArrayAdapter(@NonNull Context context, int resource, List<T> items) {
        super(context, resource, items);
        this.items = items;
        this.context = context;
        this.resourceId = resource;
    }

    public void setOnItemClickListener(OnRvItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(resourceId, parent, false);
        }

        bind(items.get(position));

        view.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(items.get(position), position);
            }
        });

        return view;
    }

    public abstract void bind(T item);

    @Nullable
    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }
}
