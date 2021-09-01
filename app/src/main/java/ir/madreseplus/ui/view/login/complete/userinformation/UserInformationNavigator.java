package ir.madreseplus.ui.view.login.complete.userinformation;

import java.util.List;

import ir.madreseplus.data.model.req.Student;
import ir.madreseplus.data.model.req.User;
import ir.madreseplus.data.model.res.CityRes;
import ir.madreseplus.data.model.res.ConfirmRes;
import ir.madreseplus.data.model.res.ProvinceRes;
import ir.madreseplus.utilities.BaseNavigator;
import retrofit2.Response;

public interface UserInformationNavigator extends BaseNavigator {

    void error(Throwable throwable);

    void provinceRes(List<ProvinceRes> provinces);

    void cityRes(List<CityRes> cities);
}
