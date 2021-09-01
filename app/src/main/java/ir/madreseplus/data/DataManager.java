package ir.madreseplus.data;

import ir.madreseplus.data.api.ApiService;
import ir.madreseplus.data.db.dao.ContentDao;
import ir.madreseplus.data.db.dao.StudentDao;

public interface DataManager extends ApiService, StudentDao , ContentDao {

}
