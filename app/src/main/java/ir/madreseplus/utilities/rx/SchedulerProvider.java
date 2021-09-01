package ir.madreseplus.utilities.rx;

import io.reactivex.Scheduler;

public interface SchedulerProvider {
    Scheduler io();

    Scheduler ui();
}
