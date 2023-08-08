package tinqin.zoobff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients(basePackages = {"tinqin.zoobff"})
@ComponentScan(basePackages = "tinqin.zoobff.controllers")
@ComponentScan(basePackages = "tinqin.zoobff.data")
@ComponentScan(basePackages = "tinqin.zoobff.security")
@ComponentScan(basePackages = "tinqin.zoobff.security.auth")
@ComponentScan(basePackages = "tinqin.zoobff.security.config")
@ComponentScan(basePackages = "tinqin.zoobff.security.token")
@EnableJpaRepositories(basePackages = {"tinqin.zoobff.repository", "tinqin.zoobff.security.user", "tinqin.zoobff.security.token"})

public class ZooBffApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZooBffApplication.class, args);
    }

}
