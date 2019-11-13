package au.edu.cat;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

public class WeightConverter {
    @TypeConverter
    public static Data.Weight revert(String string) {
           return new Gson().fromJson(string, Data.Weight.class);

    }

    @TypeConverter
    public static String converter(Data.Weight weight) {
        return new Gson().toJson(weight);
    }
}
