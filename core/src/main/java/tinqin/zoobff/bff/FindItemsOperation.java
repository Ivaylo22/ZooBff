package tinqin.zoobff.bff;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tinqin.zoobff.model.bff.finditems.FindItems;
import tinqin.zoobff.model.bff.finditems.FindItemsRequest;
import tinqin.zoobff.model.bff.finditems.FindItemsResponse;
import tinqin.zoostorage.ZooStorageRestClient;
import tinqin.zoostorage.model.getstorage.GetStorageRequest;
import tinqin.zoostorage.model.getstorage.GetStorageResponse;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindItemsOperation implements FindItems {
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
