package ir.madreseplus.ui.view.message;

import java.util.List;

import ir.madreseplus.data.model.req.Event;
import ir.madreseplus.data.model.res.TicketRes;
import ir.madreseplus.utilities.BaseNavigator;

public interface SupportNavigator extends BaseNavigator {

    void error(Throwable throwable);

    void tickets(List<TicketRes> tickets);

}
