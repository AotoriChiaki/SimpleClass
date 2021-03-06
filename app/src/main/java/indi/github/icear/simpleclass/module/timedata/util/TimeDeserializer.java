package indi.github.icear.simpleclass.module.timedata.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.sql.Time;


/**
 * Created by icear on 2017/9/22.
 * java.sql.Time类的Json反序列化函数
 */
public class TimeDeserializer implements JsonDeserializer<Time> {
    @Override
    public Time deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Time time = null;
        String date = jsonElement.getAsString();
        String[] result = date.split(":");
        switch (result.length) {
            case 0:
                //error
                break;
            case 1:
                //unknown format
                break;
            case 2:
                //treat as HH:MM
                time = Time.valueOf(date + ":00");
                break;
            case 3:
                //normal format
                time = Time.valueOf(date);
                break;
            default:
                break;

        }
        return time;
    }
}
