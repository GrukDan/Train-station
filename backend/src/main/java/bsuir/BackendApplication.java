package bsuir;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PreDestroy;

@Slf4j
@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class,args);
        log.info("BackendApplication started...");
    }

    @PreDestroy
    private void destroy(){
        log.info("BackendApplication finished...");
    }
}
