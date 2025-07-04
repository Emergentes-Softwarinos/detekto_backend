package org.softwarinos.platform.detekto_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DetektoBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(DetektoBackApplication.class, args);
    }

}
