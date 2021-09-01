package ir.madreseplus.data;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import ir.madreseplus.data.api.ApiService;
import ir.madreseplus.data.db.dao.ContentDao;
import ir.madreseplus.data.db.dao.StudentDao;
import ir.madreseplus.data.model.entity.Content;
import ir.madreseplus.data.model.req.DailyReportReq;
import ir.madreseplus.data.model.req.Dashboard;
import ir.madreseplus.data.model.req.Event;
import ir.madreseplus.data.model.req.Podcast;
import ir.madreseplus.data.model.req.Practice;
import ir.madreseplus.data.model.req.Student;
import ir.madreseplus.data.model.req.Task;
import ir.madreseplus.data.model.req.TicketReplyReq;
import ir.madreseplus.data.model.req.TicketReq;
import ir.madreseplus.data.model.req.User;
import ir.madreseplus.data.model.req._Content;
import ir.madreseplus.data.model.res.CityRes;
import ir.madreseplus.data.model.res.ComplementRes;
import ir.madreseplus.data.model.res.ConfirmRes;
import ir.madreseplus.data.model.res.DailyReportRes;
import ir.madreseplus.data.model.res.ProvinceRes;
import ir.madreseplus.data.model.res.SetTaskRes;
import ir.madreseplus.data.model.res.Splash;
import ir.madreseplus.data.model.res.TicketDictRes;
import ir.madreseplus.data.model.res.TicketInfoRes;
import ir.madreseplus.data.model.res.TicketRes;
import retrofit2.Response;

public class DataManagerImpl implements DataManager {

    private ContentDao mContentDao;
    private ApiService mApiService;
    private StudentDao studentDao;

    public DataManagerImpl(ApiService client, StudentDao studentDao, ContentDao contentDao) {
        this.mApiService = client;
        this.studentDao = studentDao;
        this.mContentDao = contentDao;
    }

    @Override
    public Single<User> login(User user) {
        return mApiService.login(user);
    }

    @Override
    public Single<User> password(User user) {
        return mApiService.password(user);
    }

    @Override
    public Single<ConfirmRes> confirm(User user, String sessionId) {
        return mApiService.confirm(user, sessionId);
    }

    @Override
    public Single<List<Student>> profile() {
        return mApiService.profile();
    }

    @Override
    public Single<Dashboard> home() {
        return mApiService.home();
    }

    @Override
    public Single<List<_Content>> content(int page, String category) {
        return mApiService.content(page, category);
    }

    @Override
    public Single<List<Podcast>> podcast(int page, String category) {
        return mApiService.podcast(page, category);
    }

    @Override
    public Single<List<Event>> events(String start, String end) {
        return mApiService.events(start, end);
    }

    @Override
    public Single<List<Event>> getTodayEvents() {
        return mApiService.getTodayEvents();
    }

    @Override
    public Single<Task> getTaskById(int id) {
        return mApiService.getTaskById(id);
    }

    @Override
    public Single<SetTaskRes> setTaskDone(int event_id) {
        return mApiService.setTaskDone(event_id);
    }


    @Override
    public Single<SetTaskRes> setPracticeDone(int event_id) {
        return mApiService.setPracticeDone(event_id);
    }

    @Override
    public Single<SetTaskRes> setTaskResult( int id, int correct, int wrong, int noAnswer) {
        return mApiService.setTaskResult( id, correct, wrong, noAnswer);
    }

    @Override
    public Single<Splash> authChecker() {
        return mApiService.authChecker();
    }


    @Override
    public Completable insertStudent(ir.madreseplus.data.model.entity.Student student) {
        return studentDao.insertStudent(student);
    }

    @Override
    public Single<List<ir.madreseplus.data.model.entity.Student>> getStudent() {
        return studentDao.getStudent();
    }

    @Override
    public void deleteStudents() {
        studentDao.deleteStudents();
    }

    @Override
    public Completable insertContent(Content content) {
        return mContentDao.insertContent(content);
    }

    @Override
    public Single<List<Content>> getContents() {
        return mContentDao.getContents();
    }

    @Override
    public Single<Content> getContents(int id) {
        return mContentDao.getContents(id);
    }

    @Override
    public void deleteContents() {
        mContentDao.deleteContents();
    }

    @Override
    public void deleteContent(int id) {
        mContentDao.deleteContent(id);
    }

    @Override
    public Single<_Content> aboutUs() {
        return mApiService.aboutUs();
    }

    @Override
    public Single<_Content> usageInstructions() {
        return mApiService.usageInstructions();
    }

    @Override
    public Single<List<ProvinceRes>> getProvinces() {
        return mApiService.getProvinces();
    }

    @Override
    public Single<List<CityRes>> getCities(int provinceId) {
        return mApiService.getCities(provinceId);
    }

    @Override
    public Single<ComplementRes> completeRegister(User user) {
        return mApiService.completeRegister(user);
    }

    @Override
    public Single<DailyReportRes> submitReport(DailyReportReq dailyReportReq) {
        return mApiService.submitReport(dailyReportReq);
    }

    @Override
    public Single<DailyReportRes> getDailyReportReportState() {
        return mApiService.getDailyReportReportState();
    }

    @Override
    public Single<List<TicketRes>> getTickets() {
        return mApiService.getTickets();
    }

    @Override
    public Single<TicketDictRes> getTicketDicts() {
        return mApiService.getTicketDicts();
    }

    @Override
    public Single<List<TicketRes>> createTicket(TicketReq ticket) {
        return mApiService.createTicket(ticket);
    }

    @Override
    public Single<TicketInfoRes> getTicketInfo(Integer id) {
        return mApiService.getTicketInfo(id);
    }

    @Override
    public Single<TicketInfoRes> createTicketReply(TicketReplyReq ticketReplyReq) {
        return mApiService.createTicketReply(ticketReplyReq);
    }
}
