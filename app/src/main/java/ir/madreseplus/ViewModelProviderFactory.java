package ir.madreseplus;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ir.madreseplus.data.DataManager;
import ir.madreseplus.ui.view.MainActivityViewModel;
import ir.madreseplus.ui.view.aboutus.AboutUsViewModel;
import ir.madreseplus.ui.view.login.complete.CompleteRegisterViewModel;
import ir.madreseplus.ui.view.login.complete.userinformation.UserInformationViewModel;
import ir.madreseplus.ui.view.message.SupportViewModel;
import ir.madreseplus.ui.view.practice.PracticeViewModel;
import ir.madreseplus.ui.view.schedule.dailyreport.DailyReportViewModel;
import ir.madreseplus.ui.view.splash.SplashViewModel;
import ir.madreseplus.ui.view.aricle.ArticleViewModel;
import ir.madreseplus.ui.view.content.ContentViewModel;
import ir.madreseplus.ui.view.podcast.PodcastViewModel;
import ir.madreseplus.ui.view.main.MainFViewModel;
import ir.madreseplus.ui.view.daily.DailyScheduleViewModel;
import ir.madreseplus.ui.view.ticket.ticket.TicketViewModel;
import ir.madreseplus.ui.view.ticket.ticketReplies.TicketRepliesViewModel;
import ir.madreseplus.ui.view.usage.UsageViewModel;
import ir.madreseplus.ui.view.weekly.WeeklyScheduleViewModel;
import ir.madreseplus.ui.view.task.TaskViewModel;
import ir.madreseplus.ui.view.login.LoginViewModel;
import ir.madreseplus.utilities.rx.SchedulerProvider;

public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    private DataManager dataManager;
    private SchedulerProvider schedulerProvider;

    public ViewModelProviderFactory(DataManager dataManager, SchedulerProvider schedulerProvider) {
        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(MainFViewModel.class)) {
            return (T) new MainFViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ArticleViewModel.class)) {
            return (T) new ArticleViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ContentViewModel.class)) {
            return (T) new ContentViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(PodcastViewModel.class)) {
            return (T) new PodcastViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(DailyScheduleViewModel.class)) {
            return (T) new DailyScheduleViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(TaskViewModel.class)) {
            return (T) new TaskViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(PracticeViewModel.class)) {
            return (T) new PracticeViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(MainActivityViewModel.class)) {
            return (T) new MainActivityViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(WeeklyScheduleViewModel.class)) {
            return (T) new WeeklyScheduleViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            return (T) new SplashViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(AboutUsViewModel.class)) {
            return (T) new AboutUsViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(UsageViewModel.class)) {
            return (T) new UsageViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(CompleteRegisterViewModel.class)) {
            return (T) new CompleteRegisterViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(UserInformationViewModel.class)) {
            return (T) new UserInformationViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(DailyReportViewModel.class)) {
            return (T) new DailyReportViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SupportViewModel.class)) {
            return (T) new SupportViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(TicketViewModel.class)) {
            return (T) new TicketViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(TicketRepliesViewModel.class)) {
            return (T) new TicketRepliesViewModel(dataManager, schedulerProvider);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }

}
