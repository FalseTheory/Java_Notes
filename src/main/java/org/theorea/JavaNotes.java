package org.theorea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class JavaNotes {
    public static void main(String[] args) {
        SpringApplication.run(JavaNotes.class, args);
    }
}
