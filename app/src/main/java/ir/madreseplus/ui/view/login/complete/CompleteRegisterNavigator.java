package ir.madreseplus.ui.view.login.complete;

import java.util.List;

import ir.madreseplus.data.model.req.Student;
import ir.madreseplus.data.model.res.ComplementRes;
import ir.madreseplus.data.model.res.ConfirmRes;
import ir.madreseplus.utilities.BaseNavigator;

public interface CompleteRegisterNavigator extends BaseNavigator {

    void error(Throwable throwable);

    void complementResult(ComplementRes complementRes);

    void profileResult(List<Student> student);
}
