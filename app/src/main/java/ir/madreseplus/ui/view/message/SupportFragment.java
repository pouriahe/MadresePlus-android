package ir.madreseplus.ui.view.message;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import ir.madreseplus.Injection;
import ir.madreseplus.R;
import ir.madreseplus.ViewModelProviderFactory;
import ir.madreseplus.data.model.res.TicketRes;
import ir.madreseplus.databinding.FragmentSupportBinding;
import ir.madreseplus.ui.base.BaseFragment;
import ir.madreseplus.ui.base.OnRvItemClickListener;
import ir.madreseplus.ui.view.daily.DailyScheduleViewModel;
import ir.madreseplus.ui.view.ticket.TicketActivity;
import ir.madreseplus.utilities.Config;
import ir.madreseplus.utilities.LoadingDialog;

public class SupportFragment extends BaseFragment<FragmentSupportBinding> implements SupportNavigator {

    private SupportViewModel mViewModel;
    private SupportAdapter mAdapter;
    private LoadingDialog loadingDialog;

    public static SupportFragment newInstance() {
        SupportFragment fragment = new SupportFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void setBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentSupportBinding.inflate(inflater, container, false);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_support;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ViewModelProviderFactory factory = Injection.providerFactory(getContext());
        mViewModel = ViewModelProviders.of(this, factory).get(SupportViewModel.class);
        mViewModel.setNavigator(this);
        mAdapter = new SupportAdapter();
        loadingDialog = new LoadingDialog(getContext());
        setListeners();
    }

    private void setListeners() {
    }

    @Override
    public void error(Throwable throwable) {
        loadingDialog.hideDialog();
        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void tickets(List<TicketRes> tickets) {
        loadingDialog.hideDialog();
        mAdapter.setItems(tickets);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        binding.recyclerView.setAdapter(mAdapter);
        mAdapter.setOnRvItemClickListener(new OnRvItemClickListener<TicketRes>() {
            @Override
            public void onItemClick(TicketRes item, int position) {
                Intent intent = new Intent(getActivity(), TicketActivity.class);
                intent.putExtra(Config.TICKET_ID_KEY, item.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        loadingDialog.showDialog();
        mViewModel.getTickets();
    }
}