package tinqin.zoobff.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tinqin.zoobff.model.finditems.FindItems;
import tinqin.zoobff.model.finditems.FindItemsRequest;
import tinqin.zoobff.model.finditems.FindItemsResponse;
import tinqin.zoostorage.model.getquantitybyid.GetQuantityByIdRequest;
import tinqin.zoostorage.model.getquantitybyid.GetQuantityByIdResponse;
import tinqin.zoostore.model.item.getitem.GetItemRequest;
import tinqin.zoostore.model.item.getitem.GetItemResponse;

import java.util.UUID;

@RestController
@RequestMapping("/bff")
public class BffController {
    private final FindItems findItems;

    public BffController(FindItems findItems) {
        this.findItems = findItems;
    }

    @GetMapping("/item/{storageId}")
    public ResponseEntity<FindItemsResponse> getQuantity(@PathVariable UUID storageId) {
        FindItemsRequest request = new FindItemsRequest();
        request.setStorageId(storageId);
        FindItemsResponse response = findItems.process(request);

        return ResponseEntity.ok(response);
    }
}
