package com.medicalstore.pos;

import me.paulschwarz.springdotenv.annotation.EnableDotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableDotenv
public class MedicalStorePosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicalStorePosApplication.class, args);
    }
}





