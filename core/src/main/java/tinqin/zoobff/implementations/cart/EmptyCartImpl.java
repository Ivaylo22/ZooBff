package tinqin.zoobff.implementations.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tinqin.zoobff.data.Cart;
import tinqin.zoobff.data.cart.emptycart.EmptyCart;
import tinqin.zoobff.data.cart.emptycart.EmptyCartRequest;
import tinqin.zoobff.data.cart.emptycart.EmptyCartResponse;
import tinqin.zoobff.repository.CartRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmptyCartImpl implements EmptyCart {
    private final CartRepository cartRepository;
    @Override
    public EmptyCartResponse process(EmptyCartRequest input) {
        Integer userId = input.getUserId();
        List<Cart> cartList = cartRepository.getAllByUserId(userId);

        cartRepository.deleteAll(cartList);

        EmptyCartResponse response = new EmptyCartResponse();
        response.setMessage("Cart is now empty");

        return response;
    }
}
