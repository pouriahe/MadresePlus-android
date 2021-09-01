package ir.madreseplus.ui.view.ticket.ticketReplies;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ir.madreseplus.Injection;
import ir.madreseplus.R;
import ir.madreseplus.ViewModelProviderFactory;
import ir.madreseplus.data.model.req.TicketReplyReq;
import ir.madreseplus.data.model.res.TicketInfoRes;
import ir.madreseplus.databinding.FragmentTicketRepliesBinding;
import ir.madreseplus.ui.base.BaseFragment;
import ir.madreseplus.utilities.Config;
import ir.madreseplus.utilities.StringUtil;

public class TicketRepliesFragment extends BaseFragment<FragmentTicketRepliesBinding> implements TicketRepliesNavigator {

    public TicketRepliesAdapter mAdapter;
    public TicketRepliesViewModel mViewModel;
    public TicketReplyReq ticketReplyReq;

    public int getLayoutRes() {
        return R.layout.fragment_ticket_replies;
    }

    public static TicketRepliesFragment newInstance(Integer num) {
        TicketRepliesFragment ticketRepliesFragment = new TicketRepliesFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Config.TICKET_ID_KEY, num.intValue());
        ticketRepliesFragment.setArguments(bundle);
        return ticketRepliesFragment;
    }

    public final Integer getTicketIdExtra() {
        if (getArguments() != null) {
            return Integer.valueOf(getArguments().getInt(Config.TICKET_ID_KEY, -1));
        }
        return null;
    }

    public void setBinding(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        this.binding = FragmentTicketRepliesBinding.inflate(layoutInflater, viewGroup, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelProviderFactory factory = Injection.providerFactory(getContext());
        mViewModel = ViewModelProviders.of(this, factory).get(TicketRepliesViewModel.class);
        mViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (!(getTicketIdExtra().intValue() == -1 || getTicketIdExtra() == null)) {
            this.mViewModel.getTicketReplies(getTicketIdExtra());
        }
        mAdapter = new TicketRepliesAdapter();
        ticketReplyReq = new TicketReplyReq();
        setListener();
    }

    public final void setListener() {
        binding.imgSend.setOnClickListener(v -> {
            if (getTicketIdExtra().intValue() == -1 || getTicketIdExtra() == null) {
                Toast.makeText(getContext(), "لطفا ابتدا تیکت خود را ایجاد کنید!", Toast.LENGTH_SHORT).show();
            } else if (replyValid()) {
                this.ticketReplyReq.setText(binding.editTextReply.getText().toString());
                this.ticketReplyReq.setTo(getTicketIdExtra());
                this.mViewModel.createTicketReply(this.ticketReplyReq);
                binding.editTextReply.setText("");
            }
        });
    }

    public final boolean replyValid() {
        return StringUtil.hasText(binding.editTextReply.getText().toString());
    }

    @Override
    public void createTicketReplyResult(TicketInfoRes ticketInfoRes) {
        mAdapter.setItems(ticketInfoRes.getReplies());
        mAdapter.notifyDataSetChanged();
        binding.recyclerView.smoothScrollToPosition(getAdapterPosition());
    }

    @Override
    public void error(Throwable th) {
        binding.progressBar.setVisibility(View.GONE);
        Log.i("TAG", "error: " + th.getMessage());
    }

    @Override
    public void ticketReplyResult(TicketInfoRes ticketInfoRes) {
        binding.progressBar.setVisibility(View.GONE);
        mAdapter.setItems(ticketInfoRes.getReplies());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        binding.recyclerView.setAdapter(mAdapter);
        binding.recyclerView.smoothScrollToPosition(getAdapterPosition());
    }

    public final int getAdapterPosition() {
        if (mAdapter.getItemCount() > 0) {
            return mAdapter.getItemCount() - 1;
        }
        return 0;
    }
}