package com.cqust.styletransfer;

import com.cqust.styletransfer.config.FileProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@MapperScan("com.cqust.styletransfer.mapper")
@SpringBootApplication
@EnableConfigurationProperties({FileProperties.class})
public class StyletransferApplication {

    public static void main(String[] args) {
        SpringApplication.run(StyletransferApplication.class, args);
    }

}
