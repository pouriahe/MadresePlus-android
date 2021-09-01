package ir.madreseplus.ui.view.login;

import java.util.List;

import ir.madreseplus.data.model.req.Student;
import ir.madreseplus.data.model.req.User;
import ir.madreseplus.data.model.res.ConfirmRes;
import ir.madreseplus.utilities.BaseNavigator;
import retrofit2.Response;

public interface LoginNavigator<T> extends BaseNavigator {

    void error(Throwable throwable);

    void login(User userResponse);

    void password(T t);

    void profile(List<Student> students);

    void confirmResult(ConfirmRes response);
}
