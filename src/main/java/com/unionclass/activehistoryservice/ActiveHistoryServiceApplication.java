package com.unionclass.activehistoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableDiscoveryClient
@EnableMongoAuditing
@SpringBootApplication
public class ActiveHistoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActiveHistoryServiceApplication.class, args);
    }

}
