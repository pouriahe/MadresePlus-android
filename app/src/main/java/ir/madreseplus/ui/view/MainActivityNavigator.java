package ir.madreseplus.ui.view;

import java.util.List;

import ir.madreseplus.data.model.entity.Student;
import ir.madreseplus.utilities.BaseNavigator;

public interface MainActivityNavigator extends BaseNavigator {

    void error(Throwable throwable);

    void getProfile(List<Student> studentList);
}
