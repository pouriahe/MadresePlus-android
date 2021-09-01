package ir.madreseplus.ui.view.weekly;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import ir.madreseplus.R;
import ir.madreseplus.data.model.enums.EventStateEnum;
import ir.madreseplus.data.model.enums.EventTypeEnum;
import ir.madreseplus.data.model.req.Event;
import ir.madreseplus.ui.base.RvAdapter;
import ir.madreseplus.ui.base.RvViewHolder;

public class WeeklyScheduleAdapter extends RvAdapter<Event, WeeklyScheduleAdapter.TaskViewHolder> {

    private List<Event> contents;

    public WeeklyScheduleAdapter(List<Event> events) {
        super(events);
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weekly_layout, parent, false));
    }


    public class TaskViewHolder extends RvViewHolder<Event> {
        private TextView titleTextView, partTextView;
        private RelativeLayout containerRelativeLayout;
        private ImageView eventTypeImageView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.txtView_title);
            partTextView = itemView.findViewById(R.id.txtView_part);
            containerRelativeLayout = itemView.findViewById(R.id.rl_weeklyContainer);
            eventTypeImageView = itemView.findViewById(R.id.imgView_eventType);
        }

        @Override
        public void bind(Event item) {
            titleTextView.setText(item.getTitle());
            partTextView.setText(item.getPartName());

            if (item.getSituation().equals(EventStateEnum.DONE)) {
                containerRelativeLayout.setBackground(itemView.getContext().getResources().getDrawable(R.drawable.selector_bg_done_card));
            }
            if (item.getKind().equals(EventTypeEnum.PRACTICE)) {
                eventTypeImageView.setImageDrawable(itemView.getContext().getResources().getDrawable(R.drawable.ic_thinking_black));
            }
        }
    }
}