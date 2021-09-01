package ir.madreseplus.ui.view.podcast;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import java.io.IOException;

import ir.madreseplus.R;
import ir.madreseplus.data.model.req.Podcast;

public class ReadPodcastFragment extends Fragment implements Runnable {
    private static final String TAG = ReadPodcastFragment.class.getSimpleName();

    private TextView titleTextView, writerTextView, descTextView, seekBarHint;
    private ImageView backImageView, playPodcast, pausePodcast, nextPodcast, prevPodcast;
    private SeekBar seekBar;
    private final Podcast podcast;
    private MediaPlayer mediaPlayer;
    boolean wasPlaying = false;

    public ReadPodcastFragment(Podcast podcast) {
        this.podcast = podcast;
    }

    public static ReadPodcastFragment newInstance(Podcast podcast) {
        Bundle args = new Bundle();

        ReadPodcastFragment fragment = new ReadPodcastFragment(podcast);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_podcast_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initViews(view);
        setListeners();
        setContents();
    }

    private void setListeners() {

        playPodcast.setOnClickListener(v -> {
            playSong();
        });
        pausePodcast.setOnClickListener(v -> {
            pauseSong();
        });
        nextPodcast.setOnClickListener(v -> {
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 5000);
        });
        prevPodcast.setOnClickListener(v -> {
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 5000);
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBarHint.setVisibility(View.VISIBLE);
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
                seekBarHint.setVisibility(View.VISIBLE);
                int x = (int) Math.ceil(progress / 1000f);

                if (x < 10)
                    seekBarHint.setText("0:0" + x);
                else
                    seekBarHint.setText("0:" + x);

                double percent = progress / (double) seekBar.getMax();
                int offset = seekBar.getThumbOffset();
                int seekWidth = seekBar.getWidth();
                int val = (int) Math.round(percent * (seekWidth - 2 * offset));
                int labelWidth = seekBarHint.getWidth();
                seekBarHint.setX(offset + seekBar.getX() + val
                        - Math.round(percent * offset)
                        - Math.round(percent * labelWidth / 2));

//                if (progress > 0 && mediaPlayer != null && !mediaPlayer.isPlaying()) {
//                    clearMediaPlayer();
//                    playPodcast.setImageDrawable(ContextCompat.getDrawable(getContext(), android.R.drawable.ic_media_play));
//                    seekBar.setProgress(0);
//                }

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.seekTo(seekBar.getProgress());
                }
            }
        });
        backImageView.setOnClickListener(view -> getActivity().onBackPressed());

/*
        saveImageView.setOnClickListener(v -> {

        });
*/
    }

    private void pauseSong() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            seekBar.setProgress(mediaPlayer.getCurrentPosition());
            wasPlaying = true;
            pausePodcast.setEnabled(false);
            playPodcast.setEnabled(true);
//            playPodcast.setImageDrawable(ContextCompat.getDrawable(getContext(), android.R.drawable.ic_media_play));
        }
    }

    private void setContents() {
        mediaPlayer = new MediaPlayer();
        titleTextView.setText(podcast.getTitle());
        descTextView.setText(podcast.getDescription());
        writerTextView.setText(podcast.getGetCategoryName());
        Glide.with(getContext())
                .load(podcast.getGetImageUrl());
    }

    private void preparePodcast() {
        String url = podcast.getGetFileUrl(); // your URL here
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare(); // might take long! (for buffering, etc)
            seekBar.setMax(mediaPlayer.getDuration());
            pausePodcast.setEnabled(false);
        } catch (IOException e) {
            Toast.makeText(getActivity(), "خطا در فایل", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "preparePodcast: " + e.toString());
        }

    }

    public void playSong() {
        try {
            if (!wasPlaying) {

                if (mediaPlayer == null) {
                    mediaPlayer = new MediaPlayer();
                }
                preparePodcast();
//                playPodcast.setImageDrawable(ContextCompat.getDrawable(getContext(), android.R.drawable.ic_media_next));
                mediaPlayer.start();
                playPodcast.setEnabled(false);
                pausePodcast.setEnabled(true);

                new Thread(this).start();
            } else {
                wasPlaying = false;
                mediaPlayer.start();
                playPodcast.setEnabled(false);
                pausePodcast.setEnabled(true);
                new Thread(this).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void run() {
        int currentPosition = mediaPlayer.getCurrentPosition();
        int total = mediaPlayer.getDuration();
        while (mediaPlayer != null && mediaPlayer.isPlaying() && currentPosition < total) {
            try {
                Thread.sleep(1000);
                currentPosition = mediaPlayer.getCurrentPosition();
            } catch (InterruptedException e) {
                return;
            } catch (Exception e) {
                return;
            }

            seekBar.setProgress(currentPosition);

        }
    }

    private void initViews(View view) {
        titleTextView = view.findViewById(R.id.txtView_podTitle);
        writerTextView = view.findViewById(R.id.txtView_podWriter);
        descTextView = view.findViewById(R.id.txtView_podDesc);
        backImageView = view.findViewById(R.id.imgView_back_podcastLayout);
//        saveImageView = view.findViewById(R.id.imgView_save_podcastLayout);
        playPodcast = view.findViewById(R.id.imgView_play);
        pausePodcast = view.findViewById(R.id.imgView_pause);
        seekBar = view.findViewById(R.id.seekbar);
        seekBarHint = view.findViewById(R.id.seekbarHint);
        nextPodcast = view.findViewById(R.id.imgView_next);
        prevPodcast = view.findViewById(R.id.imgView_prev);
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    @Override
    public void onDetach() {
        super.onDetach();
        clearMediaPlayer();
    }

    private void clearMediaPlayer() {
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}
