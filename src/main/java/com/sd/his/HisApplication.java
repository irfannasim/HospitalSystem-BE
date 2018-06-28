package com.sd.his;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;*/

/**
 * @author Irfan Nasim
 * @description To create JAR Packaging
 * @since 05-Jun-2018
 */
@SpringBootApplication
public class HisApplication {
    private final Logger logger = LoggerFactory.getLogger(HisApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HisApplication.class, args);
    }
}

/**
 * @author Irfan Nasim
 * @description To create WAR Packaging
 * @since 05-Jun-2018
 */
/*@SpringBootApplication
public class HisApplication extends SpringBootServletInitializer {
    private final Logger logger = LoggerFactory.getLogger(HisApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HisApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HisApplication.class);
    }
}*/
