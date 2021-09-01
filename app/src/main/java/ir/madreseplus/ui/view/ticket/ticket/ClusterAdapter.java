package ir.madreseplus.ui.view.ticket.ticket;

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
import ir.madreseplus.data.model.res.ClusterRes;
import ir.madreseplus.ui.base.BaseArrayAdapter;
import ir.madreseplus.ui.view.login.complete.educationalinfo.Major;

public class ClusterAdapter extends BaseArrayAdapter<ClusterRes> {

    public ClusterAdapter(@NonNull Context context, int resource, List<ClusterRes> items) {
        super(context, resource, items);
    }

    @Override
    public void bind(ClusterRes item) {
        TextView textView = view.findViewById(android.R.id.text1);
        textView.setText(item.getValue());
    }
}