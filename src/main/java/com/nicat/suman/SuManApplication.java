package com.nicat.suman;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Slf4j
@SpringBootApplication
public class SuManApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuManApplication.class, args);
    }
}