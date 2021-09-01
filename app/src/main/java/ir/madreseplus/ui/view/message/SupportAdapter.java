package ir.madreseplus.ui.view.message;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import java.text.ParseException;
import java.util.List;

import ir.madreseplus.R;
import ir.madreseplus.data.model.enums.EventStateEnum;
import ir.madreseplus.data.model.req.Event;
import ir.madreseplus.data.model.req._Content;
import ir.madreseplus.data.model.res.TicketRes;
import ir.madreseplus.ui.base.RvAdapter;
import ir.madreseplus.ui.base.RvViewHolder;
import ir.madreseplus.utilities.DateParser;
import ir.madreseplus.utilities.StringUtil;
import saman.zamani.persiandate.PersianDate;

public class SupportAdapter extends RvAdapter<TicketRes, SupportAdapter.TaskViewHolder> {

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_support_layout, parent, false));
    }


    public class TaskViewHolder extends RvViewHolder<TicketRes> {
        private TextView titleTextView, statusTextView, dateTextView;


        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.txtView_title);
            statusTextView = itemView.findViewById(R.id.txtView_status);
            dateTextView = itemView.findViewById(R.id.txtView_date);
        }

        @Override
        public void bind(TicketRes item) {
            try {
                titleTextView.setText(item.getTitle());
                statusTextView.setText(item.getGetStatusDisplay());
                int intValue = item.getStatus().intValue();
                if (intValue == 4) {
                    this.statusTextView.setTextColor(this.itemView.getContext().getResources().getColor(R.color.green));
                } else if (intValue == 6) {
                    this.statusTextView.setTextColor(this.itemView.getContext().getResources().getColor(R.color.red));
                }

                PersianDate persianDate = new PersianDate(DateParser.parse(item.getCreatedOn()));
                TextView textView = this.dateTextView;
                textView.setText(persianDate.getShYear() + StringUtil.SLASH + persianDate.getShMonth() + StringUtil.SLASH + persianDate.getShDay());
            } catch (ParseException | NullPointerException e) {
                Log.i("TAG", "bind: " + e.getMessage());
            }
        }
    }
}