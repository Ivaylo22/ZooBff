package tinqin.zoobff.bff;

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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindAllItemsByTagIdOperation implements FindAllItemsByTagId {
    private final ZooStoreRestClient storeRestClient;
    private final ZooStorageRestClient storageRestClient;
    private final ModelMapper modelMapper;

    @Override
    public FindAllItemsByTagIdResponse process(FindAllItemsByTagIdRequest input) {
        UUID tagId = input.getTagId();
        FindAllItemsByTagIdResponse response = new FindAllItemsByTagIdResponse();

        List<FullItemInfoDto> itemsInfo = storeRestClient.getItemsByTagId(tagId).getItems().stream()
                .filter(item -> storageRestClient.checkStorageByItemId(item.getId()).getIsInStorage())
                .map(item -> {
                    GetItemResponse itemResponse = storeRestClient.getItemById(item.getId());
                    Item currentItem = modelMapper.map(itemResponse, Item.class);
                    Storage storage = modelMapper.map(storageRestClient.getInfoByItemId(item.getId()), Storage.class);

                    FullItemInfoDto itemInfo = new FullItemInfoDto();
                    itemInfo.setItem(currentItem);
                    itemInfo.setPrice(storage.getPrice());
                    itemInfo.setQuantity(storage.getQuantity());
                    return itemInfo;
                })
                .collect(Collectors.toList());

        response.setItems(itemsInfo);
        return response;
    }






}
