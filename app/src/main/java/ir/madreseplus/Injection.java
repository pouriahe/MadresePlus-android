package ir.madreseplus;

import android.content.Context;

import ir.madreseplus.data.DataManager;
import ir.madreseplus.data.DataManagerImpl;
import ir.madreseplus.data.api.ApiClient;
import ir.madreseplus.data.api.ApiService;
import ir.madreseplus.data.db.AppDatabase;
import ir.madreseplus.utilities.rx.AppSchedulerProvider;
import retrofit2.Retrofit;

public class Injection {

    public static DataManager provideDataManger(Context context) {
        Retrofit retrofit = ApiClient.getClient();
        ApiService apiService = retrofit.create(ApiService.class);

        AppDatabase appDatabase = AppDatabase.getInstance(context);

        return new DataManagerImpl(apiService,
                appDatabase.studentDao(),
                appDatabase.contentDao());
    }

    public static ViewModelProviderFactory providerFactory(Context context) {
        DataManager dataManager = provideDataManger(context);
        return new ViewModelProviderFactory(dataManager, new AppSchedulerProvider());
    }

}
