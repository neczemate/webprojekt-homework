package hu.nye.webprojekt.HomeWork.util;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateUtil {

    /**
     * Returns the current date.
     *
     * @return the current date
     */
    public Date now() {
        return new Date();
    }

}
