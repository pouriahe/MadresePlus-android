package ir.madreseplus.ui.view.ticket.ticket;

import ir.madreseplus.data.DataManager;
import ir.madreseplus.data.model.req.TicketReq;
import ir.madreseplus.ui.base.BaseViewModel;
import ir.madreseplus.ui.view.ticket.ticket.CreateTicketNavigator;
import ir.madreseplus.utilities.rx.SchedulerProvider;

public class TicketViewModel extends BaseViewModel<CreateTicketNavigator> {

    public TicketViewModel(DataManager mDataManager, SchedulerProvider mSchedulerProvider) {
        super(mDataManager, mSchedulerProvider);
    }


    public void getTicketDictionaries() {
        setIsLoading(true);
        getCompositeDisposable().add(
                getDataManager().getTicketDicts()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(ticketDictRes -> {
                            setIsLoading(false);
                            getNavigator().ticketDictionaryResult(ticketDictRes);
                        }, throwable -> {
                            setIsLoading(false);
                            getNavigator().error(throwable);
                        })
        );
    }

    public void createTicket(TicketReq ticket) {
        setIsLoading(true);
        getCompositeDisposable().add(
                getDataManager().createTicket(ticket)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(
                                ticketRes -> {
                                    setIsLoading(false);
                                    getNavigator().createTicketResult(ticketRes);
                                }, throwable -> {
                                    setIsLoading(false);
                                    getNavigator().error(throwable);
                                }
                        )
        );
    }


    public void getTicket(Integer ticketIdExtra) {
        setIsLoading(false);
        getCompositeDisposable().add(
                getDataManager().getTicketInfo(ticketIdExtra)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(
                                ticketInfoRes -> {
                                    setIsLoading(false);
                                    getNavigator().ticketInfoResult(ticketInfoRes);
                                }, throwable -> {
                                    setIsLoading(false);
                                    getNavigator().error(throwable);
                                }
                        )
        );
    }
}
