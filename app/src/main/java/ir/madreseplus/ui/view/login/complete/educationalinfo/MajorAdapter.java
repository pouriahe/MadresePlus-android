package ir.madreseplus.ui.view.login.complete.educationalinfo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MajorAdapter extends ArrayAdapter<Major> {
    private Context context;
    private int resourceId;
    private List<Major> items;
    private MajorCallback majorCallback;

    public MajorAdapter(@NonNull Context context, int resourceId, ArrayList<Major> items) {
        super(context, resourceId, items);
        this.items = items;
        this.context = context;
        this.resourceId = resourceId;
    }

    public void setMajorCallback(MajorCallback majorCallback) {
        this.majorCallback = majorCallback;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(resourceId, parent, false);
        }
        Major major = getItem(position);
        TextView name = view.findViewById(android.R.id.text1);
        name.setText(major.getName());

        view.setOnClickListener(v -> {
            if (majorCallback != null) {
                majorCallback.onClick(major);
            }
        });

        return view;
    }


    @Nullable
    @Override
    public Major getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public interface MajorCallback {
        void onClick(Major major);
    }

}