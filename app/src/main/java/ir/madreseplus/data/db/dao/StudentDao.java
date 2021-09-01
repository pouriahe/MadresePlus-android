package ir.madreseplus.data.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import ir.madreseplus.data.model.entity.Student;

@Dao
public interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertStudent(Student student);

    @Query("select * from student")
    Single<List<Student>> getStudent();

    @Query("DELETE FROM student")
    void deleteStudents();

}
