package ir.madreseplus.ui.view.aboutus;

import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import ir.madreseplus.Injection;
import ir.madreseplus.R;
import ir.madreseplus.ViewModelProviderFactory;
import ir.madreseplus.data.model.req._Content;
import ir.madreseplus.databinding.ActivityAboutUsBinding;
import ir.madreseplus.ui.base.BaseActivity;
import ir.madreseplus.utilities.PicassoImageGetter;

public class AboutUsActivity extends BaseActivity<ActivityAboutUsBinding> implements AboutUsNavigator<_Content> {

    private AboutUsViewModel viewModel;

    @Override
    protected int layoutRes() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void setBinding() {
        binding = ActivityAboutUsBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();

        ViewModelProviderFactory factory = Injection.providerFactory(this);
        viewModel = ViewModelProviders.of(this, factory).get(AboutUsViewModel.class);
        viewModel.setNavigator(this);
        viewModel.aboutUS();

    }

    private void setListeners() {
        binding.imgViewBack.setOnClickListener(v -> finish());
    }

    @Override
    public void error(Throwable throwable) {
        Toast.makeText(AboutUsActivity.this, R.string.rest_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void contents(_Content content) {


        binding.progressBar.setVisibility(View.GONE); // hiding progressbar


        PicassoImageGetter imageGetter = new PicassoImageGetter(binding.txtViewAboutUs);
        Spannable html;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            html = (Spannable) Html.fromHtml(content.getContext(), Html.FROM_HTML_MODE_LEGACY, imageGetter, null);
        } else {
            html = (Spannable) Html.fromHtml(content.getContext(), imageGetter, null);
        }

        binding.txtViewAboutUs.setText(html);
                  /*  (Html.fromHtml(content.getContext(), new Html.ImageGetter() {
                @Override
                public Drawable getDrawable(String source) {
                    PicassoTargetDrawable d = new PicassoTargetDrawable(TestActivity.this);
                    Picasso.with(TestActivity.this)
                            .load(source)
                            //add placeholder here
                            .into(d);
                    return d;
                }
            }, null));*/

    }

}
