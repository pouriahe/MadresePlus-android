package ir.madreseplus.utilities;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ListTableConverter {

    @TypeConverter // note this annotation
    public String fromList(List<String> list) {
        if (list == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        String json = gson.toJson(list, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<String> toList(String string) {
        if (string == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        List<String> productCategoriesList = gson.fromJson(string, type);
        return productCategoriesList;
    }

}
