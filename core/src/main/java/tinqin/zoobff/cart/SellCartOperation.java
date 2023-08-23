package tinqin.zoobff.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tinqin.zoobff.data.Cart;
import tinqin.zoobff.model.bff.checkfordiscount.CheckDiscount;
import tinqin.zoobff.model.bff.checkfordiscount.CheckDiscountRequest;
import tinqin.zoobff.model.bff.salecart.SellCart;
import tinqin.zoobff.model.bff.salecart.SellCartRequest;
import tinqin.zoobff.model.bff.salecart.SellCartResponse;
import tinqin.zoobff.model.cart.emptycart.EmptyCart;
import tinqin.zoobff.model.cart.emptycart.EmptyCartRequest;
import tinqin.zoobff.repository.CartRepository;
import tinqin.zoostorage.ZooStorageRestClient;
import tinqin.zoostorage.model.addsale.AddSaleRequest;
import tinqin.zoostorage.model.exportitems.ExportRequest;
import tinqin.zoostorage.model.exportitems.ExportResponse;
import tinqin.zoostore.data.Item;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SellCartOperation implements SellCart {
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
            ExportRequest exportRequest = ExportRequest.builder()
                    .itemId(itemId.toString())
                    .quantity(items.get(itemId))
                    .city(input.getCity())
                    .build();
            if(checkDiscount.process(discountRequest).getItems()
                    .stream().map(Item::getId)
                    .collect(Collectors.toList())
                    .contains(itemId))
            {

                ExportResponse exportResponse = storageRestClient.exportFromStorage(exportRequest);

                discount += (exportResponse.getFinalPrice())*0.1;
                price += exportResponse.getFinalPrice();
            }
            else {
                storageRestClient.exportFromStorage(exportRequest);
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
