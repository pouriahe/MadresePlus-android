package ir.madreseplus.ui.view.daily;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import java.util.List;

import ir.madreseplus.R;
import ir.madreseplus.data.model.enums.EventStateEnum;
import ir.madreseplus.data.model.req.Event;
import ir.madreseplus.data.model.req._Content;
import ir.madreseplus.ui.base.RvAdapter;
import ir.madreseplus.ui.base.RvViewHolder;

public class DailyScheduleAdapter extends RvAdapter<Event, DailyScheduleAdapter.TaskViewHolder> {

    private List<_Content> contents;

    public DailyScheduleAdapter(List<Event> events) {
        super(events);
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule_layout, parent, false));
    }


    public class TaskViewHolder extends RvViewHolder<Event> {
        private TextView titleTextView, chapterTextView, partTextView, suggCount, suggTime, eventType;
        private ImageView taskSituationImageView;
        private CardView sugTimeCardView, sugCountCardView, eventTypeCardView;


        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.txtView_title_scheduleLayout);
            chapterTextView = itemView.findViewById(R.id.txtView_chapter_scheduleLayout);
            partTextView = itemView.findViewById(R.id.txtView_part_scheduleLayout);
            eventType = itemView.findViewById(R.id.txtView_eventType_scheduleLayout);
            suggCount = itemView.findViewById(R.id.txtView_suggCount_scheduleLayout);
            suggTime = itemView.findViewById(R.id.txtView_suggTime_scheduleLayout);
            taskSituationImageView = itemView.findViewById(R.id.imgView_taskSituation_scheduleLayout);
            sugTimeCardView = itemView.findViewById(R.id.cv_sugTime);
            sugCountCardView = itemView.findViewById(R.id.cv_sugCount);
            eventTypeCardView = itemView.findViewById(R.id.cv_eventType);
        }

        @Override
        public void bind(Event item) {
            titleTextView.setText(item.getTitle());
            chapterTextView.setText(item.getChapterName());
            partTextView.setText(item.getPartName());
            eventType.setText(item.getKind().getText());
            suggCount.setText(String.valueOf(item.getSuggestedCount()) + " تست");
            suggTime.setText(item.getSuggestedTime());

            if (item.getSituation().equals(EventStateEnum.DONE)) {
                taskSituationImageView.setImageDrawable(itemView.getContext().getResources().getDrawable(R.drawable.ic_checked));
            }

            switch (item.getKind()) {
                case READ:
                    eventTypeCardView.setCardBackgroundColor(itemView.getContext().getResources().getColor(R.color.purple_500));
                    sugCountCardView.setVisibility(View.INVISIBLE);
                    break;
                case REVIEW:
                    eventTypeCardView.setCardBackgroundColor(itemView.getContext().getResources().getColor(R.color.green_500));
                    sugCountCardView.setVisibility(View.INVISIBLE);
                    break;
                case PRACTICE:
                    eventTypeCardView.setCardBackgroundColor(itemView.getContext().getResources().getColor(R.color.pink_500));
                    break;
            }
        }
    }
}