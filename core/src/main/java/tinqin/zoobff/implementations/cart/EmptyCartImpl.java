package tinqin.zoobff.implementations.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tinqin.zoobff.data.cart.emptycart.EmptyCart;
import tinqin.zoobff.data.cart.emptycart.EmptyCartRequest;
import tinqin.zoobff.data.cart.emptycart.EmptyCartResponse;
import tinqin.zoobff.repository.CartRepository;

@Service
@RequiredArgsConstructor
public class EmptyCartImpl implements EmptyCart {
    private final CartRepository cartRepository;
    @Override
    public EmptyCartResponse process(EmptyCartRequest input) {
        Integer userId = input.getUserId();

        cartRepository.removeAllByUserId(userId);

        EmptyCartResponse response = new EmptyCartResponse();
        response.setMessage("Cart is now empty");

        return response;
    }
}
