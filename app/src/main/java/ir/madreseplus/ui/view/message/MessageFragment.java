package ir.madreseplus.ui.view.message;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ir.madreseplus.R;
import ir.madreseplus.databinding.FragmentMessageLayoutBinding;
import ir.madreseplus.ui.base.BaseFragment;
import ir.madreseplus.ui.base.ViewPagerAdapter;
import ir.madreseplus.ui.view.ticket.TicketActivity;

public class MessageFragment extends BaseFragment<FragmentMessageLayoutBinding> {

    private ViewPagerAdapter viewPagerAdapter;

    public static MessageFragment newInstance() {
        MessageFragment fragment = new MessageFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentMessageLayoutBinding.inflate(inflater, container, false);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_message_layout;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setupViewPager();
        setListeners();
    }

    private void setupViewPager() {
        viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
//        viewPagerAdapter.addFragment(ConsultationFragment.newInstance(), "مشاوره");
        viewPagerAdapter.addFragment(SupportFragment.newInstance(), "پشتیبانی");
        binding.viewPager.setAdapter(viewPagerAdapter);
//        binding.tablayout.setupWithViewPager(binding.viewPager);
    }

    private void setListeners() {
        binding.floatingCreateTicket.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), TicketActivity.class));
        });
    }
}
