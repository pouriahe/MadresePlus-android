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

import ir.madreseplus.R;

public class GradeAdapter extends ArrayAdapter<Grade> {
    private Context context;
    private int resourceId;
    private List<Grade> items;
    private GradeCallback gradeCallback;

    public GradeAdapter(@NonNull Context context, int resourceId, ArrayList<Grade> items) {
        super(context, resourceId, items);
        this.items = items;
        this.context = context;
        this.resourceId = resourceId;
    }

    public void setGradeCallback(GradeCallback gradeCallback) {
        this.gradeCallback = gradeCallback;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(resourceId, parent, false);
        }
        Grade grade = getItem(position);
        TextView name = view.findViewById(android.R.id.text1);
        name.setText(grade.getName());

        view.setOnClickListener(v -> {
            if (gradeCallback != null) {
                gradeCallback.onClick(grade);
            }
        });

        return view;
    }


    @Nullable
    @Override
    public Grade getItem(int position) {
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


    public interface GradeCallback {
        void onClick(Grade grade);
    }

}