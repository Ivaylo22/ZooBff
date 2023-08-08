package tinqin.zoobff.implementations.bff;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tinqin.zoobff.data.Cart;
import tinqin.zoobff.data.bff.salecart.SellCart;
import tinqin.zoobff.data.bff.salecart.SellCartRequest;
import tinqin.zoobff.data.bff.salecart.SellCartResponse;
import tinqin.zoobff.data.cart.emptycart.EmptyCart;
import tinqin.zoobff.data.cart.emptycart.EmptyCartRequest;
import tinqin.zoobff.repository.CartRepository;
import tinqin.zoostorage.ZooStorageRestClient;
import tinqin.zoostorage.model.addsale.AddSaleRequest;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SellCartImpl implements SellCart {
    private final ZooStorageRestClient storageRestClient;
    private final CartRepository cartRepository;
    private final EmptyCart emptyCart;

    @Override
    public SellCartResponse process(SellCartRequest input) {
        Integer userId = input.getUserId();
        List<Cart> carts = cartRepository.getAllByUserId(userId);

        Map<UUID, Integer> items = carts.stream()
                .collect(Collectors.toMap(Cart::getItemId, Cart::getQuantity, Integer::sum));


        AddSaleRequest request = new AddSaleRequest();
        request.setItems(items);
        request.setUserId(userId);

        storageRestClient.addSale(request);

        EmptyCartRequest sellRequest = EmptyCartRequest.builder().userId(userId).build();
        emptyCart.process(sellRequest);

        return new SellCartResponse();
    }
}
