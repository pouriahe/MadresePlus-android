package ir.madreseplus.ui.view.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import ir.madreseplus.R;
import ir.madreseplus.data.model.enums.EventStateEnum;
import ir.madreseplus.data.model.enums.EventTypeEnum;
import ir.madreseplus.data.model.req.Event;
import ir.madreseplus.ui.base.RvAdapter;
import ir.madreseplus.ui.base.RvViewHolder;

public class EventsAdapter extends RvAdapter<Event, EventsAdapter.TaskViewHolder> {

    private List<Event> events;

    public EventsAdapter(List<Event> events) {
        super(events);
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scmain_layout, parent, false));
    }


    public class TaskViewHolder extends RvViewHolder<Event> {
        private TextView titleTextView, bookNameTextView, chapterTextView, eventTypeTextView;
        private ImageView eventTypeImageView, eventStateImageView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.txtView_title);
            bookNameTextView = itemView.findViewById(R.id.txtView_bookName);
            chapterTextView = itemView.findViewById(R.id.txtView_chapter);
            eventTypeImageView = itemView.findViewById(R.id.imgView_eventType);
            eventTypeTextView = itemView.findViewById(R.id.txtView_eventType);
            eventStateImageView = itemView.findViewById(R.id.imgView_eventState);
        }

        @Override
        public void bind(Event item) {
            titleTextView.setText(item.getTitle());
            bookNameTextView.setText(item.getBookName());
            chapterTextView.setText(item.getChapterName());
            if (item.getSituation().equals(EventStateEnum.DONE)) {
                eventStateImageView.setImageDrawable(itemView.getContext().getResources().getDrawable(R.drawable.ic_done));
            }
            if (item.getKind().equals(EventTypeEnum.PRACTICE)) {
                eventTypeImageView.setImageDrawable(itemView.getContext().getResources().getDrawable(R.drawable.ic_thinking));
            } else if (item.getKind().equals(EventTypeEnum.REVIEW)) {
                eventTypeImageView.setImageDrawable(itemView.getContext().getResources().getDrawable(R.drawable.ic_review));
            }
            eventTypeTextView.setText(item.getKind().getText());
        }
    }
}