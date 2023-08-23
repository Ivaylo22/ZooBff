package tinqin.zoobff;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import tinqin.zoobff.model.cart.getfullcart.GetFullCartResponse;

@Headers({"Content-Type: application/json"})
public interface ZooCartRestClient {
    @RequestLine("GET /cart/getCart/{userId}")
    GetFullCartResponse getCart(@Param("userId") Integer userId);
}
