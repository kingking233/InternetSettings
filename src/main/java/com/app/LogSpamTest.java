//package com.app;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//@EnableScheduling
//public class LogSpamTest {
//    private static final Logger logger = LoggerFactory.getLogger(LogSpamTest.class);
//
//    // Log a large message every 100ms to quickly fill up the log file
//    @Scheduled(fixedRate = 100)
//    public void spamLogs() {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < 1000; i++) {
//            sb.append("0123456789"); // 10KB per log entry
//        }
//        logger.info("Spam log: {}", sb.toString());
//    }
//}
//
