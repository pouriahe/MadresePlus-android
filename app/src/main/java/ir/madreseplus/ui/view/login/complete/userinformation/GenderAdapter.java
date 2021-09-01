package ir.madreseplus.ui.view.login.complete.userinformation;

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

import ir.madreseplus.ui.view.login.complete.educationalinfo.Grade;

public class GenderAdapter extends ArrayAdapter<Gender> {
    private Context context;
    private int resourceId;
    private List<Gender> items;
    private GenderCallback genderCallback;

    public GenderAdapter(@NonNull Context context, int resourceId, ArrayList<Gender> items) {
        super(context, resourceId, items);
        this.items = items;
        this.context = context;
        this.resourceId = resourceId;
    }

    public void setGenderCallback(GenderCallback genderCallback) {
        this.genderCallback = genderCallback;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(resourceId, parent, false);
        }
        Gender gender = getItem(position);
        TextView name = view.findViewById(android.R.id.text1);
        name.setText(gender.getName());

        view.setOnClickListener(v -> {
            if (genderCallback != null) {
                genderCallback.onClick(gender);
            }
        });

        return view;
    }


    @Nullable
    @Override
    public Gender getItem(int position) {
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


    public interface GenderCallback {
        void onClick(Gender grade);
    }

}