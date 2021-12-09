package sample.models.building;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(record.getMillis());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date date =  calendar.getTime();

        return new StringBuilder()
                .append('[')
                .append(simpleDateFormat.format(date))
                .append(']')
                .append('[')
                .append(record.getLevel())
                .append(']')
                .append(" ")
                .append(record.getMessage())
                .append(" ")
                .append("\n")
                .toString();
    }
}
