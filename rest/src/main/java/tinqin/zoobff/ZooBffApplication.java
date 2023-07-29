package tinqin.zoobff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "tinqin.zoobff.controllers")
@ComponentScan(basePackages = "tinqin.zoobff.model")
public class ZooBffApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZooBffApplication.class, args);
    }

}
