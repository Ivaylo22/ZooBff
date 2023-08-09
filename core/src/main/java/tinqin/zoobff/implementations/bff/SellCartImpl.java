package tinqin.zoobff.implementations.bff;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tinqin.zoobff.data.Cart;
import tinqin.zoobff.data.bff.checkfordiscount.CheckDiscount;
import tinqin.zoobff.data.bff.checkfordiscount.CheckDiscountRequest;
import tinqin.zoobff.data.bff.salecart.SellCart;
import tinqin.zoobff.data.bff.salecart.SellCartRequest;
import tinqin.zoobff.data.bff.salecart.SellCartResponse;
import tinqin.zoobff.data.cart.emptycart.EmptyCart;
import tinqin.zoobff.data.cart.emptycart.EmptyCartRequest;
import tinqin.zoobff.repository.CartRepository;
import tinqin.zoostorage.ZooStorageRestClient;
import tinqin.zoostorage.model.addsale.AddSaleRequest;
import tinqin.zoostore.data.Item;

import java.time.DayOfWeek;
import java.time.LocalDate;
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
    private final CheckDiscount checkDiscount;

    @Override
    public SellCartResponse process(SellCartRequest input) {
        Integer userId = input.getUserId();
        List<Cart> carts = cartRepository.getAllByUserId(userId);
        double discount = 0.0;
        double price = 0.0;

        CheckDiscountRequest discountRequest = new CheckDiscountRequest();

        Map<UUID, Integer> items = carts.stream()
                .collect(Collectors.toMap(Cart::getItemId, Cart::getQuantity, Integer::sum));

        for (UUID itemId: items.keySet()) {
            if(checkDiscount.process(discountRequest).getItems()
                    .stream().map(Item::getId)
                    .collect(Collectors.toList())
                    .contains(itemId))
            {
                Double singlePrice = storageRestClient.getInfoByItemId(itemId).getPrice();
                Integer quantity = items.get(itemId);
                discount += (singlePrice*quantity)*0.1;
                price += singlePrice*quantity;
            }
        }


        AddSaleRequest request = AddSaleRequest.builder()
            .items(items)
            .userId(userId)
            .savedMoney(discount)
            .price(price)
            .build();

        storageRestClient.addSale(request);

        EmptyCartRequest sellRequest = EmptyCartRequest
                .builder()
                .userId(userId)
                .build();
        emptyCart.process(sellRequest);

        return new SellCartResponse();
    }
}
