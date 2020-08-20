package com.tokyo.supermix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.tokyo.supermix.config.FileStorageProperties;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@EnableConfigurationProperties({FileStorageProperties.class})
public class TokyoSupermixServer {

  public static void main(String[] args) {
    SpringApplication.run(TokyoSupermixServer.class, args);
  }

}
