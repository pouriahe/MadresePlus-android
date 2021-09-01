package ir.madreseplus.data.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import ir.madreseplus.data.model.entity.Content;


@Dao
public interface ContentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertContent(Content content);

    @Query("select * from content")
    Single<List<Content>> getContents();

    @Query("select * from content where id=:id")
    Single<Content> getContents(int id);

    @Query("DELETE FROM content")
    void deleteContents();

    @Query("DELETE FROM content where id=:id")
    void deleteContent(int id);


}
