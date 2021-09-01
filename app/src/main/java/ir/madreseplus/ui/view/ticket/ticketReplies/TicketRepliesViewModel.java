package ir.madreseplus.ui.view.ticket.ticketReplies;

import ir.madreseplus.data.DataManager;
import ir.madreseplus.data.model.req.TicketReplyReq;
import ir.madreseplus.ui.base.BaseViewModel;
import ir.madreseplus.utilities.rx.SchedulerProvider;

public class TicketRepliesViewModel extends BaseViewModel<TicketRepliesNavigator> {

    public TicketRepliesViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


    public void createTicketReply(TicketReplyReq ticketReplyReq) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager().createTicketReply(ticketReplyReq)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(ticketInfoRes -> {
                    setIsLoading(false);
                    getNavigator().createTicketReplyResult(ticketInfoRes);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().error(throwable);
                })
        );
    }

    public void getTicketReplies(Integer ticketIdExtra) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager().getTicketInfo(ticketIdExtra)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(ticketInfoRes -> {
                    setIsLoading(false);
                    getNavigator().ticketReplyResult(ticketInfoRes);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().error(throwable);
                })
        );
    }
}
