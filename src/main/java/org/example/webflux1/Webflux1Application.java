package org.example.webflux1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.blockhound.BlockHound;

@SpringBootApplication
public class Webflux1Application {

    public static void main(String[] args) {
        BlockHound.install();

        SpringApplication.run(Webflux1Application.class, args);
    }

}
