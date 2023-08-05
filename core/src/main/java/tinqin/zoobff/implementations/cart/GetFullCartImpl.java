package tinqin.zoobff.implementations.cart;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tinqin.zoobff.data.Cart;
import tinqin.zoobff.data.bff.finditemsbytagid.FullItemInfoDto;
import tinqin.zoobff.data.cart.getfullcart.GetFullCart;
import tinqin.zoobff.data.cart.getfullcart.GetFullCartRequest;
import tinqin.zoobff.data.cart.getfullcart.GetFullCartResponse;
import tinqin.zoobff.repository.CartRepository;
import tinqin.zoostorage.ZooStorageRestClient;
import tinqin.zoostorage.data.Storage;
import tinqin.zoostore.ZooStoreRestClient;
import tinqin.zoostore.data.Item;
import tinqin.zoostore.model.item.getitem.GetItemResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetFullCartImpl implements GetFullCart {
    private final ZooStoreRestClient storeRestClient;
    private final ZooStorageRestClient storageRestClient;
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;

    @Override
    public GetFullCartResponse process(GetFullCartRequest input) {
        Integer userId = input.getUserId();
        List<Cart> cartList = cartRepository.getAllByUserId(userId);
        List<FullItemInfoDto> itemsInfo = new ArrayList<>();
        double finalPrice = 0.0;

        for (Cart cart: cartList) {
            UUID itemId = cart.getItemId();

            GetItemResponse itemResponse = storeRestClient.getItemById(itemId);
            Item currentItem = modelMapper.map(itemResponse, Item.class);

            Storage storage = modelMapper.map(storageRestClient.getInfoByItemId(itemId), Storage.class);

            FullItemInfoDto itemInfo = new FullItemInfoDto();

            itemInfo.setItem(currentItem);
            itemInfo.setPrice(storage.getPrice());
            itemInfo.setQuantity(cart.getQuantity());

            finalPrice += cart.getQuantity() * storage.getPrice();

            itemsInfo.add(itemInfo);
        }
        return GetFullCartResponse.builder()
                .items(itemsInfo)
                .userId(userId)
                .finalPrice(finalPrice)
                .build();
    }
}
