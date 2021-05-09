package com.epam.consumer.services;


import com.epam.common.model.Quote;
import lombok.SneakyThrows;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.Field;

import static org.json.simple.JSONObject.escape;

public class ConvertQuoteToJson implements ConvertToJson {

    @SneakyThrows
    @Override
    public String convert(Quote quote) {

        JSONObject obj = new JSONObject();
        Field[] fields = quote.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
           // obj.put( field.getName(), field.get(quote).toString());
            String str = field.get(quote).toString();
            obj.put( field.getName(), str);
            //field.get(quote).toString()) ;

        }

        return obj.toJSONString();

    }
}
