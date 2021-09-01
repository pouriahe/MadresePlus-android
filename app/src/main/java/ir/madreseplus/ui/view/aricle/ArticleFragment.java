package ir.madreseplus.ui.view.aricle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

import ir.madreseplus.Injection;
import ir.madreseplus.R;
import ir.madreseplus.ViewModelProviderFactory;
import ir.madreseplus.data.model.enums.ArtCategory;
import ir.madreseplus.data.model.req.Podcast;
import ir.madreseplus.data.model.req._Content;
import ir.madreseplus.ui.base.ViewPagerAdapter;
import ir.madreseplus.ui.view.content.ContentFragment;
import ir.madreseplus.ui.view.podcast.PodcastFragment;

public class ArticleFragment extends Fragment implements ArticleNavigator<_Content> {


    /*private TabLayout tabLayout;*/
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private ArticleViewModel articleViewModel;
    private RadioGroup mCatRadioGroup;
    private ArtCategory artCategory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ViewModelProviderFactory factory = Injection.providerFactory(getContext());
        articleViewModel = ViewModelProviders.of(this, factory).get(ArticleViewModel.class);
        articleViewModel.setNavigator(this);

        initViews(view);
        setupViewPager();
        setListener();
    }

    private void setListener() {
        mCatRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rd_all:
                    filterCategory(1, ArtCategory.ALL);
                    break;
                case R.id.rd_exam:
                    filterCategory(1, ArtCategory.EXAM);
                    break;
                case R.id.rd_resource:
                    filterCategory(1, ArtCategory.RESOURCES);
                    break;
                case R.id.rd_scheduling:
                    filterCategory(1, ArtCategory.SCHEDULING);
                    break;
                case R.id.rd_test:
                    filterCategory(1, ArtCategory.TEST);
                    break;
            }

        });
    }

    private void filterCategory(int page, ArtCategory cat) {
        Fragment fragment = viewPagerAdapter.getItem(viewPager.getCurrentItem());
        artCategory = cat;

        if (fragment instanceof ContentFragment) {
            if (ContentFragment.artCategory != cat)
                ((ContentFragment) fragment).getContent(page, cat);
        } else if (fragment instanceof PodcastFragment) {
            if (PodcastFragment.artCategory != cat)
                ((PodcastFragment) fragment).getPodcasts(page, cat);
        }
    }

    private void setupViewPager() {
        viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
//        viewPagerAdapter.addFragment(PodcastFragment.newInstance(), getString(R.string.podcast_string_tab));
        viewPagerAdapter.addFragment(ContentFragment.newInstance(), getString(R.string.content_string_tab));
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                checkFilterFragment();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        /*tabLayout.setupWithViewPager(viewPager);*/

    }

    private void checkFilterFragment() {
        Fragment fragment = viewPagerAdapter.getItem(viewPager.getCurrentItem());

        if (fragment instanceof ContentFragment) {
            checkRadioButton(ContentFragment.artCategory);
        } else if (fragment instanceof PodcastFragment) {
            checkRadioButton(PodcastFragment.artCategory);
        }
    }

    private void checkRadioButton(ArtCategory artCategory) {
        switch (artCategory) {
            case ALL:
                ((RadioButton) getView().findViewById(R.id.rd_all)).setChecked(true);
                break;
            case EXAM:
                ((RadioButton) getView().findViewById(R.id.rd_exam)).setChecked(true);
                break;
            case TEST:
                ((RadioButton) getView().findViewById(R.id.rd_test)).setChecked(true);
                break;
            case RESOURCES:
                ((RadioButton) getView().findViewById(R.id.rd_resource)).setChecked(true);
                break;
            case SCHEDULING:
                ((RadioButton) getView().findViewById(R.id.rd_scheduling)).setChecked(true);
                break;
        }
    }

    private void initViews(View view) {
       /* tabLayout = view.findViewById(R.id.tabLayout_articleFragment);*/
        viewPager = view.findViewById(R.id.viewPager_mainFragment);
        mCatRadioGroup = view.findViewById(R.id.categoryRadioGroup);
    }


    @Override
    public void error(Throwable throwable) {

    }

    @Override
    public void contents(List<_Content> contents) {

    }

    @Override
    public void podcasts(List<Podcast> contents) {

    }

}
