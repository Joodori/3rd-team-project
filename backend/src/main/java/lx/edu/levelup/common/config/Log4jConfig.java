package lx.edu.levelup.common.config;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;

@Configuration
public class Log4jConfig {

    //pom.xml에 의존성 추가하면 됨 (수현)
    public void init() {
        // 콘솔 Appender 생성
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setLayout(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n"));
        consoleAppender.activateOptions();

        // Root Logger 설정
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.INFO);
        rootLogger.addAppender(consoleAppender);

        // Spring 관련 로거 설정
        Logger springWebLogger = Logger.getLogger("org.springframework.web");
        springWebLogger.setLevel(Level.INFO);

        Logger springBeansLogger = Logger.getLogger("org.springframework.beans");
        springBeansLogger.setLevel(Level.INFO);
    }
}