package ir.madreseplus.ui.view.podcast;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.madreseplus.Injection;
import ir.madreseplus.R;
import ir.madreseplus.ViewModelProviderFactory;
import ir.madreseplus.data.model.enums.ArtCategory;
import ir.madreseplus.data.model.req.Podcast;
import ir.madreseplus.ui.base.OnRvItemClickListener;
import ir.madreseplus.utilities.LoadingDialog;

public class PodcastFragment extends Fragment implements PodcastNavigator<Podcast>, OnRvItemClickListener<Podcast> {
    private static final String TAG = PodcastFragment.class.getSimpleName();


    private RecyclerView podcastRecyclerView;
    private PodcastAdapter podcastAdapter;
    private PodcastViewModel podcastViewModel;
    private LoadingDialog loadingDialog;
    public static ArtCategory artCategory;

    public static PodcastFragment newInstance() {
        Bundle args = new Bundle();

        PodcastFragment fragment = new PodcastFragment();
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
        return inflater.inflate(R.layout.fragment_content_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ViewModelProviderFactory factory = Injection.providerFactory(getContext());
        podcastViewModel = ViewModelProviders.of(this, factory).get(PodcastViewModel.class);
        podcastViewModel.setNavigator(this);
        podcastAdapter = new PodcastAdapter();
        initViews(view);


        loadingDialog = new LoadingDialog(getContext());

        getPodcasts(1, ArtCategory.ALL);
    }

    public void getPodcasts(int page, ArtCategory cat) {
        loadingDialog.showDialog();

        artCategory = cat;
        podcastViewModel.podcast(page, cat.getCatName());
    }

    private void initViews(View view) {
        podcastRecyclerView = view.findViewById(R.id.rv_content);
    }

    @Override
    public void error(Throwable throwable) {
        loadingDialog.hideDialog();
        Log.e(TAG, "error: " + throwable.getMessage());
    }

    @Override
    public void podcasts(List<Podcast> podcasts) {
        loadingDialog.hideDialog();

        podcastAdapter.setItems(podcasts);
        podcastRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        podcastRecyclerView.setAdapter(podcastAdapter);
        podcastAdapter.setOnRvItemClickListener(this);
    }


    @Override
    public void onItemClick(Podcast item, int position) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        ReadPodcastFragment readPodcastFragment = ReadPodcastFragment.newInstance(item);
        fm.beginTransaction().add(R.id.frameLayout_mainActivity, readPodcastFragment, ReadPodcastFragment.class.getSimpleName())
                .addToBackStack(ReadPodcastFragment.class.getSimpleName()).commit();
    }
}
