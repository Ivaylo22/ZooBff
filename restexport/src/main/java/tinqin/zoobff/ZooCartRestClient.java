package tinqin.zoobff;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tinqin.zoobff.data.cart.getfullcart.GetFullCartResponse;

@Headers({"Content-Type: application/json"})
public interface ZooCartRestClient {
    @RequestLine("GET /cart/getCart/{userId}")
    GetFullCartResponse getCart(@Param("userId") Integer userId);
}
