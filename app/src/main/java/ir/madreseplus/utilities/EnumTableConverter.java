package ir.madreseplus.utilities;

import androidx.room.TypeConverter;

import ir.madreseplus.data.model.enums.GenderTypeEnum;

public class EnumTableConverter {

    @TypeConverter
    public static GenderTypeEnum toEnum(int id) {
        return GenderTypeEnum.fromValue(id);
    }

    @TypeConverter
    public static int fromEnum(GenderTypeEnum typeEnum) {
        return typeEnum == null ? null : typeEnum.getId();
    }

}
