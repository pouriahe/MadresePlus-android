package ir.madreseplus.ui.view.content;

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
import ir.madreseplus.data.model.req._Content;
import ir.madreseplus.ui.base.OnRvItemClickListener;
import ir.madreseplus.utilities.LoadingDialog;

public class ContentFragment extends Fragment implements ContentNavigator<_Content>, OnRvItemClickListener<_Content> {
    private static final String TAG = ContentFragment.class.getSimpleName();


    private RecyclerView contentRecyclerView;

    private ContentViewModel contentViewModel;
    private ContentAdapter contentAdapter;
    private LoadingDialog loadingDialog;
    public static ArtCategory artCategory;


    public static ContentFragment newInstance() {
        Bundle args = new Bundle();

        ContentFragment fragment = new ContentFragment();
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
        contentViewModel = ViewModelProviders.of(this, factory).get(ContentViewModel.class);
        contentViewModel.setNavigator(this);
        initViews(view);
        loadingDialog = new LoadingDialog(getContext());

        getContent(1, ArtCategory.ALL);
    }

    public void getContent(int page, ArtCategory cat) {
        loadingDialog.showDialog();
        artCategory = cat;
        contentViewModel.content(page, cat.getCatName());
    }

    private void initViews(View view) {
        contentRecyclerView = view.findViewById(R.id.rv_content);
    }

    @Override
    public void error(Throwable throwable) {
        loadingDialog.hideDialog();
        Log.e(TAG, "error: " + throwable.getMessage());
    }

    @Override
    public void contents(List<_Content> contents) {
        loadingDialog.hideDialog();

        contentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        contentAdapter = new ContentAdapter(contents);
        contentRecyclerView.setAdapter(contentAdapter);

        contentAdapter.setOnRvItemClickListener(this);
    }

    @Override
    public void onItemClick(_Content item, int position) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        ReadContentFragment readContentFragment = ReadContentFragment.newInstance(item);
        fm.beginTransaction().add(R.id.frameLayout_mainActivity, readContentFragment, ReadContentFragment.class.getSimpleName())
                .addToBackStack(ReadContentFragment.class.getSimpleName()).commit();
    }
}
