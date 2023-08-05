package tinqin.zoobff.implementations.cart;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tinqin.zoobff.data.Cart;
import tinqin.zoobff.data.cart.addtocart.AddToCart;
import tinqin.zoobff.data.cart.addtocart.AddToCartRequest;
import tinqin.zoobff.data.cart.addtocart.AddToCartResponse;
import tinqin.zoobff.repository.CartRepository;
import tinqin.zoostorage.ZooStorageRestClient;
import tinqin.zoostorage.data.Storage;
import tinqin.zoostore.data.Item;
import tinqin.zoostore.model.item.getitem.GetItemResponse;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddToCartImpl implements AddToCart {
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
