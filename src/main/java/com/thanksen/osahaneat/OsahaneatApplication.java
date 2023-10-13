package com.thanksen.osahaneat;

import com.thanksen.osahaneat.Service.FileService;
import com.thanksen.osahaneat.Service.storage.StorageProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
@EnableConfigurationProperties(StorageProperties.class)
@EnableCaching
public class OsahaneatApplication {

	public static void main(String[] args) {
		SpringApplication.run(OsahaneatApplication.class, args);
	}
	@Bean
	CommandLineRunner init(FileService fileService){
		return (args) -> {
			fileService.init();
		};
	}

}
