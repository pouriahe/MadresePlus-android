package ir.madreseplus.ui.view.ticket.ticket;

import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import ir.madreseplus.data.model.res.ClusterRes;
import ir.madreseplus.data.model.res.PriorityRes;
import ir.madreseplus.ui.base.BaseArrayAdapter;

public class PriorityAdapter extends BaseArrayAdapter<PriorityRes> {


    public PriorityAdapter(@NonNull Context context, int resource, List<PriorityRes> items) {
        super(context, resource, items);
    }

    @Override
    public void bind(PriorityRes item) {
        TextView textView = view.findViewById(android.R.id.text1);
        textView.setText(item.getValue());
    }
}