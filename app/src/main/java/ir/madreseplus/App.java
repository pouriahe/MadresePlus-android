package ir.madreseplus;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.orhanobut.hawk.Hawk;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //initialize hawk
        Hawk.init(this).build();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
