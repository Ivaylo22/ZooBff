package tinqin.zoobff.implementations.bff;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tinqin.zoobff.data.bff.finditemsbytagid.FullItemInfoDto;
import tinqin.zoobff.data.bff.finditemsbytagid.FindAllItemsByTagId;
import tinqin.zoobff.data.bff.FindAllItemsByTagIdRequest;
import tinqin.zoobff.data.bff.finditemsbytagid.FindAllItemsByTagIdResponse;
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
public class FindAllItemsByTagIdImpl implements FindAllItemsByTagId {
    private final ZooStoreRestClient storeRestClient;
    private final ZooStorageRestClient storageRestClient;
    private final ModelMapper modelMapper;

    @Override
    public FindAllItemsByTagIdResponse process(FindAllItemsByTagIdRequest input) {
        UUID tagId = input.getTagId();
        FindAllItemsByTagIdResponse response = new FindAllItemsByTagIdResponse();
        List<FullItemInfoDto> itemsInfo = new ArrayList<>();

        List<Item> items = storeRestClient.getItemsByTagId(tagId).getItems();


        for (Item item: items) {
            UUID itemId = item.getId();
            if(!storageRestClient.checkStorageByItemId(itemId).getIsInStorage()) {
                continue;
            }
            GetItemResponse itemResponse = storeRestClient.getItemById(itemId);
            Item currentItem = modelMapper.map(itemResponse, Item.class);

            Storage storage = modelMapper.map(storageRestClient.getInfoByItemId(itemId), Storage.class);

            FullItemInfoDto itemInfo = new FullItemInfoDto();


            itemInfo.setItem(currentItem);
            itemInfo.setPrice(storage.getPrice());
            itemInfo.setQuantity(storage.getQuantity());

            itemsInfo.add(itemInfo);
        }
        response.setItems(itemsInfo);

        return response;
    }
}
