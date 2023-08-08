package tinqin.zoobff.implementations.bff;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tinqin.zoobff.data.bff.finditems.FindItems;
import tinqin.zoobff.data.bff.finditems.FindItemsRequest;
import tinqin.zoobff.data.bff.finditems.FindItemsResponse;
import tinqin.zoostorage.ZooStorageRestClient;
import tinqin.zoostorage.model.getstorage.GetStorageRequest;
import tinqin.zoostorage.model.getstorage.GetStorageResponse;
import tinqin.zoostore.ZooStoreRestClient;
import tinqin.zoostore.model.item.getitem.GetItemResponse;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindItemsImpl implements FindItems {
    private final ZooStorageRestClient storageRestClient;

    @Override
    public FindItemsResponse process(FindItemsRequest input) {
        GetStorageRequest request = new GetStorageRequest();
        UUID storageId = input.getStorageId();
        request.setStorageId(storageId);

        GetStorageResponse storage = storageRestClient.getStorage(request);

        UUID itemId = storage.getItemId();

        FindItemsResponse response = new FindItemsResponse();
        response.setStorageId(storageId);
        response.setItemId(itemId);

        return response;
    }
}
