package tinqin.zoobff.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tinqin.zoobff.data.Cart;
import tinqin.zoobff.data.cart.removefromcart.RemoveFromCart;
import tinqin.zoobff.data.cart.removefromcart.RemoveFromCartRequest;
import tinqin.zoobff.data.cart.removefromcart.RemoveFromCartResponse;
import tinqin.zoobff.repository.CartRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RemoveFromCartOperation implements RemoveFromCart {
    private final CartRepository cartRepository;

    @Override
    public RemoveFromCartResponse process(RemoveFromCartRequest input) {
        Integer userId = input.getUserId();
        UUID itemId = input.getItemId();
        Cart cart = cartRepository.getByItemId(itemId);
        cartRepository.delete(cart);

        return new RemoveFromCartResponse(userId, cart.getId());
    }
}
