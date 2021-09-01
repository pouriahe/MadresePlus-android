package ir.madreseplus.ui.view.message;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ir.madreseplus.R;
import ir.madreseplus.databinding.FragmentConsultationBinding;
import ir.madreseplus.ui.base.BaseFragment;


public class ConsultationFragment extends BaseFragment<FragmentConsultationBinding> {

    public static ConsultationFragment newInstance() {
        ConsultationFragment fragment = new ConsultationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentConsultationBinding.inflate(inflater,container,false);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_consultation;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }
}