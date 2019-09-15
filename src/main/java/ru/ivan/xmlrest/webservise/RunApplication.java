package ru.ivan.xmlrest.webservise;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RunApplication implements CommandLineRunner {

    Logger logger = LogManager.getLogger(RunApplication.class);
    @Autowired
    XMLParser xmlParser;
    @Override
    public void run(String... args) throws Exception {
        logger.debug("Application RUN!");

        if (!StringUtils.isBlank(args[0])) {
            System.out.println(args[0]);
            xmlParser.parseFileXML(args[0]);
        }
    }
}
