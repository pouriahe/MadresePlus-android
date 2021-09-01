package ir.madreseplus.data.api;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
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
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiService {

    @POST("login")
    Single<User> login(@Body User user);

    @POST("password")
    Single<User> password(@Body User user);

    @POST("confirm")
    Single<ConfirmRes> confirm(@Body User user, @Header("Cookie") String sessionId);

    @GET("user/student")
    Single<List<Student>> profile();

    @GET("home")
    Single<Dashboard> home();

    @GET("media/content")
    Single<List<_Content>> content(@Query("page") int page,
                                   @Query("category") String category);

    @GET("media/podcast")
    Single<List<Podcast>> podcast(@Query("page") int page,
                                  @Query("category") String category);

    @GET("task")
    Single<List<Event>> events(@Query("from") String startPeriod,
                               @Query("to") String endPeriod);

    @GET("event/today")
    Single<List<Event>> getTodayEvents();

    @GET("task")
    Single<Task> getTaskById(@Query("id") int id);

    @PUT("task/set/done")
    Single<SetTaskRes> setTaskDone(@Query("id") int id);

    @POST("event/practice/set/done")
    @FormUrlEncoded
    Single<SetTaskRes> setPracticeDone(@Field("event_id") int event_id);

    @POST("task/set/result")
    @FormUrlEncoded
    Single<SetTaskRes> setTaskResult(
            @Field("id") int id,
            @Field("correct") int correct,
            @Field("wrong") int wrong,
            @Field("no_answer") int noAnswer);

    @GET("auth-checker")
    Single<Splash> authChecker();

    @GET("about-us")
    Single<_Content> aboutUs();

    @GET("how-to-use")
    Single<_Content> usageInstructions();

    @GET("province")
    Single<List<ProvinceRes>> getProvinces();

    @GET("city")
    Single<List<CityRes>> getCities(@Query("province_id") int provinceId);

    @POST("complement")
    Single<ComplementRes> completeRegister(@Body User user);

    @POST("report/mood")
    Single<DailyReportRes> submitReport(@Body DailyReportReq dailyReportReq);

    @GET("report/mood")
    Single<DailyReportRes> getDailyReportReportState();

    @GET("ticket")
    Single<List<TicketRes>> getTickets();

    @GET("ticket/dict")
    Single<TicketDictRes> getTicketDicts();

    @POST("ticket")
    Single<List<TicketRes>> createTicket(@Body TicketReq ticket);

    @POST("ticket/reply")
    Single<TicketInfoRes> createTicketReply(@Body TicketReplyReq ticketReplyReq);

    @GET("ticket")
    Single<TicketInfoRes> getTicketInfo(@Query("id") Integer id);

}
