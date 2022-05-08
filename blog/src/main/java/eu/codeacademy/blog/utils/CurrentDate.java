package eu.codeacademy.blog.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CurrentDate {


    public String getCurrentDate() {
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:ss");
        return dateFormat.format(new Date());
    }


}
