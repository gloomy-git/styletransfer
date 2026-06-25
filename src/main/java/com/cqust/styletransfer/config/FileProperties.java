package com.cqust.styletransfer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
@Data
public class FileProperties {
    private String docDir;
}
