package ir.madreseplus.ui.view.ticket.ticketReplies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import ir.madreseplus.R;
import ir.madreseplus.data.model.res.ReplyRes;
import ir.madreseplus.ui.base.RvAdapter;
import ir.madreseplus.ui.base.RvViewHolder;

public class TicketRepliesAdapter extends RvAdapter<ReplyRes, TicketRepliesAdapter.TicketReplieViewHolder> {
    @NonNull
    public TicketReplieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TicketReplieViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_ticket_replies_layout, viewGroup, false));
    }

    public class TicketReplieViewHolder extends RvViewHolder<ReplyRes> {
        public TextView desc;
        public TextView name;

        public TicketReplieViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.txtView_name);
            this.desc = (TextView) itemView.findViewById(R.id.txtView_desc);
        }

        public void bind(ReplyRes replyRes) {
            this.name.setText(replyRes.getCreatorFullName());
            this.desc.setText(replyRes.getText());
            if (!replyRes.getOwner().booleanValue()) {
                this.name.setTextColor(this.itemView.getContext().getResources().getColor(R.color.blue));
            }
        }
    }
}
