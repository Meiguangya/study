package com.cool.logappend;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author meiguangya
 * @Description
 * @create 2021-09-17 12:21
 */
@Data
public class MyAppender extends AppenderBase<ILoggingEvent> {

    private static Logger logger = LoggerFactory.getLogger(MyAppender.class);

    @Override
    public void start() {
        System.out.println("MyAppender start....");
        super.start();
    }

    @Override
    protected void append(ILoggingEvent eventObject) {
        String msg = eventObject.getFormattedMessage();
        System.out.println("append->"+msg);
    }
}
