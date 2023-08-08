package tinqin.zoobff;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tinqin.zoostorage.ZooStorageRestClient;
import tinqin.zoostore.ZooStoreRestClient;

@Configuration
@RequiredArgsConstructor
public class RestExportClientFactory {
    @Bean
    public ZooStoreRestClient getMyRestExportClient() {
        ObjectMapper objectMapper = new ObjectMapper();

        return Feign.builder()
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .target(ZooStoreRestClient.class, "http://localhost:8080");
    }

    @Bean(name = "StorageApiClient")
    public ZooStorageRestClient getStorageClient() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        return Feign.builder()
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .target(ZooStorageRestClient.class, "http://localhost:8081");
    }
}
