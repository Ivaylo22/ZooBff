package tinqin.zoobff.cart;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tinqin.zoobff.data.Cart;
import tinqin.zoobff.model.cart.addtocart.AddToCart;
import tinqin.zoobff.model.cart.addtocart.AddToCartRequest;
import tinqin.zoobff.model.cart.addtocart.AddToCartResponse;
import tinqin.zoobff.repository.CartRepository;
import tinqin.zoostorage.ZooStorageRestClient;
import tinqin.zoostorage.data.Storage;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddToCartOperation implements AddToCart {
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;
    private final ZooStorageRestClient storageRestClient;

    @Override
    public AddToCartResponse process(AddToCartRequest input) {
        Cart cart = new Cart();
        cart.setItemId(input.getItemId());
        cart.setQuantity(input.getQuantity());
        cart.setUserId(input.getUserId());

        UUID itemId = cart.getItemId();

        Storage storage = modelMapper.map(storageRestClient.getInfoByItemId(itemId), Storage.class);
        Double price = storage.getPrice();
        cart.setFinalPrice(price* input.getQuantity());

        Cart savedCart = cartRepository.save(cart);
        return modelMapper.map(savedCart, AddToCartResponse.class);
    }
}
