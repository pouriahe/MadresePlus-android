package ir.madreseplus.ui.view.ticket.ticket;

import java.util.List;

import ir.madreseplus.data.model.res.TicketDictRes;
import ir.madreseplus.data.model.res.TicketInfoRes;
import ir.madreseplus.data.model.res.TicketRes;
import ir.madreseplus.utilities.BaseNavigator;

public interface CreateTicketNavigator extends BaseNavigator {

    void error(Throwable throwable);

    void createTicketResult(List<TicketRes> ticketRes);

    void ticketDictionaryResult(TicketDictRes ticketDictRes);

    void ticketInfoResult(TicketInfoRes ticketInfoRes);
}
