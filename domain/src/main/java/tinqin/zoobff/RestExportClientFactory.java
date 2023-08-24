package tinqin.zoobff;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tinqin.zoostorage.ZooStorageRestClient;
import tinqin.zoostore.ZooStoreRestClient;

@Configuration
@RequiredArgsConstructor
public class RestExportClientFactory {
    @Value("${zoostore.url}")
    private String zoostoreUrl;

    @Value("${zoostorаgе.url}")
    private String zoostorageUrl;

    @Bean
    public ZooStoreRestClient getMyRestExportClient() {
        ObjectMapper objectMapper = new ObjectMapper();

        return Feign.builder()
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .target(ZooStoreRestClient.class, zoostoreUrl);
    }

    @Bean(name = "StorageApiClient")
    public ZooStorageRestClient getStorageClient() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        return Feign.builder()
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .target(ZooStorageRestClient.class, zoostorageUrl);
    }
}
