package org.bandana.resthardslightdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



@SpringBootApplication()
public class RestHardsLightDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestHardsLightDemoApplication.class, args);
    }

}
