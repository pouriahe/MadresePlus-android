package ir.madreseplus.ui.view.main;

import java.util.List;

import ir.madreseplus.data.model.entity.Student;
import ir.madreseplus.utilities.BaseNavigator;

public interface MainFNavigator<T> extends BaseNavigator {

    void error(Throwable throwable);

    void home(T t);

    void profile(List<Student> studentList);
}
