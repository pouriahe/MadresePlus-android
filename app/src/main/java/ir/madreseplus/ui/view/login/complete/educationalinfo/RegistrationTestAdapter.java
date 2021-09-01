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

public class RegistrationTestAdapter extends ArrayAdapter<RegistrationTest> {
    private Context context;
    private int resourceId;
    private List<RegistrationTest> items;
    private RegistrationTestCallback callback;

    public RegistrationTestAdapter(@NonNull Context context, int resourceId, ArrayList<RegistrationTest> items) {
        super(context, resourceId, items);
        this.items = items;
        this.context = context;
        this.resourceId = resourceId;
    }

    public void setRegistrationTestCallback(RegistrationTestCallback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(resourceId, parent, false);
        }
        RegistrationTest item = getItem(position);
        TextView name = view.findViewById(android.R.id.text1);
        name.setText(item.getName());

        view.setOnClickListener(v -> {
            if (callback != null) {
                callback.onClick(item);
            }
        });

        return view;
    }


    @Nullable
    @Override
    public RegistrationTest getItem(int position) {
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


    public interface RegistrationTestCallback {
        void onClick(RegistrationTest item);
    }

}