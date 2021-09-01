package ir.madreseplus.ui.view.ticket;


import android.os.Bundle;

import ir.madreseplus.R;

import ir.madreseplus.databinding.ActivityTicketBinding;
import ir.madreseplus.ui.base.BaseActivity;
import ir.madreseplus.ui.base.ViewPagerAdapter;
import ir.madreseplus.ui.view.ticket.ticket.TicketFragment;
import ir.madreseplus.ui.view.ticket.ticketReplies.TicketRepliesFragment;
import ir.madreseplus.utilities.Config;

public class TicketActivity extends BaseActivity<ActivityTicketBinding> {

    public Integer ticketId;
    public ViewPagerAdapter viewPagerAdapter;

    @Override
    protected int layoutRes() {
        return R.layout.activity_ticket;
    }

    @Override
    protected void setBinding() {
        binding = ActivityTicketBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.ticketId = Integer.valueOf(getIntent().getIntExtra(Config.TICKET_ID_KEY, -1));
        setupViewPager();
        setListener();
    }

    public final void setupViewPager() {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(TicketFragment.newInstance(ticketId), "تیکت");
        viewPagerAdapter.addFragment(TicketRepliesFragment.newInstance(ticketId), "سوال و جواب");
        binding.viewPager.setAdapter(viewPagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    private void setListener() {
        binding.imgViewBack.setOnClickListener(v -> {
            finish();
        });
    }


}