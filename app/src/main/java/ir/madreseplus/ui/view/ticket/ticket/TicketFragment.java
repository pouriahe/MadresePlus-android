package ir.madreseplus.ui.view.ticket.ticket;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.ParseException;
import java.util.List;

import ir.madreseplus.Injection;
import ir.madreseplus.R;
import ir.madreseplus.ViewModelProviderFactory;
import ir.madreseplus.data.model.req.TicketReq;
import ir.madreseplus.data.model.res.ClusterRes;
import ir.madreseplus.data.model.res.PriorityRes;
import ir.madreseplus.data.model.res.TicketDictRes;
import ir.madreseplus.data.model.res.TicketInfoRes;
import ir.madreseplus.data.model.res.TicketRes;
import ir.madreseplus.databinding.FragmentTicketBinding;
import ir.madreseplus.ui.base.BaseFragment;
import ir.madreseplus.ui.base.OnRvItemClickListener;
import ir.madreseplus.ui.view.weekly.WeeklyScheduleViewModel;
import ir.madreseplus.utilities.Config;
import ir.madreseplus.utilities.DateParser;
import ir.madreseplus.utilities.LoadingDialog;
import ir.madreseplus.utilities.StringUtil;
import saman.zamani.persiandate.PersianDate;


public class TicketFragment extends BaseFragment<FragmentTicketBinding> implements CreateTicketNavigator {
    public ClusterAdapter clusterAdapter;
    public TicketViewModel mViewModel;
    public PriorityAdapter priorityAdapter;
    public TicketReq ticketReq;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_ticket;
    }

    public static TicketFragment newInstance(int ticketId) {
        TicketFragment ticketFragment = new TicketFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Config.TICKET_ID_KEY, ticketId);
        ticketFragment.setArguments(bundle);
        return ticketFragment;
    }

    public final Integer getTicketIdExtra() {
        if (getArguments() != null) {
            return Integer.valueOf(getArguments().getInt(Config.TICKET_ID_KEY, -1));
        }
        return null;
    }

    @Override
    protected void setBinding(LayoutInflater inflater, ViewGroup container) {
        this.binding = FragmentTicketBinding.inflate(inflater, container, false);
    }


    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ViewModelProviderFactory factory = Injection.providerFactory(getContext());
        mViewModel = ViewModelProviders.of(this, factory).get(TicketViewModel.class);
        mViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (getTicketIdExtra().intValue() == -1 || getTicketIdExtra() == null) {
            enableFocusable();
            mViewModel.getTicketDictionaries();
        } else {
            binding.progressBar.setVisibility(View.VISIBLE);
            disableViews();
            mViewModel.getTicket(getTicketIdExtra());
        }
        this.ticketReq = new TicketReq();
        setListener();
    }

    public final void enableFocusable() {
        binding.editTextDescription.setFocusable(true);
        binding.editTextTitle.setFocusable(true);
        binding.editTextTitle.requestFocus();
    }

    public final void disableViews() {
        binding.editTextTitle.setFocusable(false);
        binding.editTextDescription.setFocusable(false);
        binding.editTextDescription.setEnabled(false);
        binding.editTextTitle.setEnabled(false);
        binding.autoCompleteSubject.setEnabled(false);
        binding.autoCompletePriority.setEnabled(false);
    }

    public final void setListener() {
        binding.autoCompleteSubject.setInputType(0);
        binding.autoCompletePriority.setInputType(0);
        PersianDate persianDate = new PersianDate();
        binding.txtViewDate.setText(persianDate.getShYear() + StringUtil.SLASH + persianDate.getShMonth() + StringUtil.SLASH + persianDate.getShDay());
        binding.btnSubmit.setOnClickListener((View.OnClickListener) view -> {
            ticketReq.setText(binding.editTextDescription.getText().toString());
            ticketReq.setTitle(binding.editTextTitle.getText().toString());
            mViewModel.createTicket(ticketReq);

        });
    }

    @Override
    public void error(Throwable throwable) {
        Log.i("error", "throwable: " + throwable.getMessage());
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void createTicketResult(List<TicketRes> ticketRes) {
        getActivity().finish();
    }

    public void ticketInfoResult(TicketInfoRes ticketInfoRes) {
        binding.progressBar.setVisibility(View.GONE);
        binding.btnSubmit.setVisibility(View.INVISIBLE);
        binding.editTextDescription.setText(ticketInfoRes.getText());
        binding.editTextTitle.setText(ticketInfoRes.getTitle());
        binding.autoCompleteSubject.setText(ticketInfoRes.getGetClusterDisplay());
        binding.autoCompletePriority.setText(ticketInfoRes.getGetPriorityDisplay());
        try {
            PersianDate persianDate = new PersianDate(DateParser.parse(ticketInfoRes.getCreatedOn()));
            binding.txtViewDate.setText(persianDate.getShYear() + StringUtil.SLASH + persianDate.getShMonth() + StringUtil.SLASH + persianDate.getShDay());
        } catch (ParseException e) {
            Log.i("TAG", "bind: " + e.getMessage());
        }
    }


    @Override
    public void ticketDictionaryResult(TicketDictRes ticketDictRes) {
        clusterAdapter = new ClusterAdapter(getContext(), 17367043, ticketDictRes.getClusters());
        clusterAdapter.setOnItemClickListener((item, position) -> {
            ticketReq.setCluster(String.valueOf(item.getId()));
            binding.autoCompleteSubject.setText(item.getValue());
        });
        binding.autoCompleteSubject.setAdapter(clusterAdapter);


        priorityAdapter = new PriorityAdapter(getContext(), 17367043, ticketDictRes.getPriority());
        priorityAdapter.setOnItemClickListener(new OnRvItemClickListener<PriorityRes>() {
            @Override
            public void onItemClick(PriorityRes item, int position) {
                ticketReq.setPriority(String.valueOf(item.getId()));
                binding.autoCompletePriority.setText(item.getValue());
            }
        });
        binding.autoCompletePriority.setAdapter(this.priorityAdapter);
    }
}