package ir.madreseplus.ui.view.ticket.ticketReplies;

import ir.madreseplus.data.model.res.TicketInfoRes;
import ir.madreseplus.utilities.BaseNavigator;

public interface TicketRepliesNavigator extends BaseNavigator {

    void createTicketReplyResult(TicketInfoRes ticketInfoRes);

    void error(Throwable th);

    void ticketReplyResult(TicketInfoRes ticketInfoRes);

}