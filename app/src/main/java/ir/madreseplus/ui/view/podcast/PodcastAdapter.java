package ir.madreseplus.ui.view.podcast;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.List;

import ir.madreseplus.R;
import ir.madreseplus.data.model.req.Podcast;
import ir.madreseplus.ui.base.RvAdapter;
import ir.madreseplus.ui.base.RvViewHolder;

public class PodcastAdapter extends RvAdapter<Podcast, PodcastAdapter.TaskViewHolder> {

    private List<Podcast> podcasts;


    public PodcastAdapter() {

    }

    public PodcastAdapter(List<Podcast> podcasts) {
        super(podcasts);
    }


    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_art_common_layout, parent, false));
    }


    public class TaskViewHolder extends RvViewHolder<Podcast> {
        private TextView titleTextView,dateTextView;
        private TextView catNameTextView;
        private ImageView imgView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.txtView_title);
            catNameTextView = itemView.findViewById(R.id.txtView_catName);
            imgView = itemView.findViewById(R.id.imageView_artCommonLayout);
            dateTextView = itemView.findViewById(R.id.txtView_date_artCommonLayout);
        }

        @Override
        public void bind(Podcast item) {
            titleTextView.setText(item.getTitle());
            catNameTextView.setText(item.getGetCategoryName());
            // TODO: 3/15/2020 do this
//            dateTextView.setText();
            Glide.with(itemView.getContext())
                    .load(item.getGetImageUrl()).into(imgView);
        }
    }
}