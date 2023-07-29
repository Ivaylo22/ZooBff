package tinqin.zoobff.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tinqin.zoobff.model.finditems.FindItems;
import tinqin.zoobff.model.finditems.FindItemsRequest;
import tinqin.zoobff.model.finditems.FindItemsResponse;
import tinqin.zoobff.model.finditemsbytagid.FindAllItemsByTagId;
import tinqin.zoobff.model.finditemsbytagid.FindAllItemsByTagIdRequest;
import tinqin.zoobff.model.finditemsbytagid.FindAllItemsByTagIdResponse;
import tinqin.zoostore.model.item.getitembytagid.GetItemByTagId;
import tinqin.zoostore.model.item.getitembytagid.GetItemByTagIdRequest;
import tinqin.zoostore.model.item.getitembytagid.GetItemByTagIdResponse;

import java.util.UUID;

@RestController
@RequestMapping("/bff")
@RequiredArgsConstructor
public class BffController {
    private final FindItems findItems;
    private final FindAllItemsByTagId getItemsByTagId;

    @GetMapping("/item/{storageId}")
    public ResponseEntity<FindItemsResponse> getQuantity(@PathVariable UUID storageId) {
        FindItemsRequest request = new FindItemsRequest();
        request.setStorageId(storageId);
        FindItemsResponse response = findItems.process(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/itemsByTag/{tagId}")
    public ResponseEntity<FindAllItemsByTagIdResponse> getItems(@PathVariable UUID tagId){
        FindAllItemsByTagIdRequest request = new FindAllItemsByTagIdRequest(tagId);
        FindAllItemsByTagIdResponse response = getItemsByTagId.process(request);

        return ResponseEntity.ok(response);
    }
}
