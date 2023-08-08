    package tinqin.zoobff.rest.controller;

    import com.fasterxml.jackson.databind.ObjectMapper;
    import org.junit.jupiter.api.AfterEach;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.modelmapper.ModelMapper;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
    import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.boot.test.mock.mockito.MockBean;
    import org.springframework.http.MediaType;
    import org.springframework.test.web.servlet.MockMvc;
    import tinqin.zoobff.data.Cart;
    import tinqin.zoobff.data.cart.addtocart.AddToCartRequest;
    import tinqin.zoobff.data.cart.emptycart.EmptyCartRequest;
    import tinqin.zoobff.data.cart.removefromcart.RemoveFromCartRequest;
    import tinqin.zoobff.data.cart.removefromcart.RemoveFromCartResponse;
    import tinqin.zoobff.repository.CartRepository;
    import tinqin.zoostorage.ZooStorageRestClient;
    import tinqin.zoostorage.data.Storage;
    import tinqin.zoostorage.model.getinfobyid.GetInfoByIdResponse;


    import java.util.List;
    import java.util.UUID;

    import static org.junit.jupiter.api.Assertions.assertTrue;
    import static org.mockito.Mockito.*;
    import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
    import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
    import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
    import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
    import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

    @SpringBootTest
    @AutoConfigureMockMvc
    public class CartControllerTest {
        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private CartRepository cartRepository;

        @Autowired
        private ObjectMapper objectMapper;

        @MockBean
        private ZooStorageRestClient storageRestClient;


        private Cart cart;

        @AfterEach
        void tearDown() {
            cartRepository.deleteAll();
        }

        @Test
        void testEmptyCartEndpoint() throws Exception {
            Cart cart = new Cart();
            cart.setUserId(1);
            cart.setItemId(UUID.randomUUID());
            cart.setFinalPrice(100.0);
            cart.setQuantity(2);
            cartRepository.save(cart);

            EmptyCartRequest emptyCartRequest = EmptyCartRequest.builder()
                    .userId(cart.getUserId())
                    .build();

            mockMvc.perform(
                            post("/cart/emptyCart")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(emptyCartRequest))
                                    .accept(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.message").value("Cart is now empty"));

            List<Cart> cartsAfterDeletion = cartRepository.getAllByUserId(cart.getUserId());
            assertTrue(cartsAfterDeletion.isEmpty());
        }

        @Test
        public void testAddToCartEndpoint() throws Exception {
            Cart cart = new Cart();
            cart.setUserId(1);
            cart.setItemId(UUID.randomUUID());
            cart.setQuantity(2);

            GetInfoByIdResponse storage = new GetInfoByIdResponse();
            storage.setPrice(50.0);
            when(storageRestClient.getInfoByItemId(cart.getItemId())).thenReturn(storage);

            AddToCartRequest request = AddToCartRequest.builder()
                    .userId(cart.getUserId())
                    .itemId(cart.getItemId())
                    .quantity(cart.getQuantity())
                    .build();

            mockMvc.perform(
                            post("/cart/addToCart")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(request))
                                    .accept(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$['quantity']").value(cart.getQuantity()))
                    .andExpect(jsonPath("$['finalPrice']").value(cart.getQuantity() * storage.getPrice()));

        }

    }
