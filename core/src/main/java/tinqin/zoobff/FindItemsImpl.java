package tinqin.zoobff;

import org.springframework.stereotype.Service;
import tinqin.zoobff.model.finditems.FindItems;
import tinqin.zoobff.model.finditems.FindItemsRequest;
import tinqin.zoobff.model.finditems.FindItemsResponse;
import tinqin.zoostorage.ZooStorageRestClient;
import tinqin.zoostorage.model.getstorage.GetStorageResponse;
import tinqin.zoostore.ZooStoreRestClient;
import tinqin.zoostore.model.item.getitem.GetItemResponse;

import java.util.UUID;

@Service
public class FindItemsImpl implements FindItems {
    private final ZooStoreRestClient storeRestClient;
    private final ZooStorageRestClient storageRestClient;

    public FindItemsImpl(ZooStoreRestClient storeRestClient, ZooStorageRestClient storageRestClient) {
        this.storeRestClient = storeRestClient;
        this.storageRestClient = storageRestClient;
    }

    @Override
    public FindItemsResponse process(FindItemsRequest input) {
        UUID storageId = input.getStorageId();
        GetStorageResponse storage = storageRestClient.getStorate(storageId);

        UUID itemId = storage.getItemId();
        GetItemResponse item = storeRestClient.getItemById(itemId);

        FindItemsResponse response = new FindItemsResponse();
        response.setStorageId(storageId);
        response.setItemId(itemId);

        return response;
    }
}
