package ir.madreseplus.ui.view.content;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ir.madreseplus.R;
import ir.madreseplus.data.model.req._Content;

public class ReadContentFragment extends Fragment {


    private TextView titleTextView, contentTextView, descTextView,
            authorName, authorPost,dateTextView;
    private ImageView backImageView;
    private final _Content contents;

    public ReadContentFragment(_Content content) {
        this.contents = content;
    }

    public static ReadContentFragment newInstance(_Content content) {
        Bundle args = new Bundle();

        ReadContentFragment fragment = new ReadContentFragment(content);
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
        return inflater.inflate(R.layout.fragment_read_article_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initViews(view);
        setListeners();
        setContents();
    }

    private void setListeners() {
        backImageView.setOnClickListener(view -> {
            getActivity().onBackPressed();
        });
    }

    private void setContents() {
        titleTextView.setText(contents.getTitle());
        descTextView.setText(contents.getDescription());
        authorName.setText(contents.getAuthor());
        authorPost.setText(contents.getAuthorTitle());
//        dateTextView.setText(contents.getCreatedOn());
        contentTextView.setText(Html.fromHtml(contents.getContext()));
    }

    private void initViews(View view) {
        titleTextView = view.findViewById(R.id.txtView_title);
        contentTextView = view.findViewById(R.id.txtView_content);
        contentTextView.setMovementMethod(LinkMovementMethod.getInstance());
        descTextView = view.findViewById(R.id.txtView_desc);
        backImageView = view.findViewById(R.id.imgView_back_readArticle);
        authorName = view.findViewById(R.id.txtView_authorName);
        authorPost = view.findViewById(R.id.txtView_authorPost);
        dateTextView = view.findViewById(R.id.txtView_date);
//        saveImageView = view.findViewById(R.id.imgView_save_readArticle);
    }


}
