package tinqin.zoobff.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tinqin.zoobff.data.bff.finditems.FindItems;
import tinqin.zoobff.data.bff.finditems.FindItemsRequest;
import tinqin.zoobff.data.bff.finditems.FindItemsResponse;
import tinqin.zoobff.data.bff.finditemsbytagid.FindAllItemsByTagId;
import tinqin.zoobff.data.bff.FindAllItemsByTagIdRequest;
import tinqin.zoobff.data.bff.finditemsbytagid.FindAllItemsByTagIdResponse;
import tinqin.zoobff.data.bff.salecart.SellCart;
import tinqin.zoobff.data.bff.salecart.SellCartRequest;
import tinqin.zoobff.data.bff.salecart.SellCartResponse;
import tinqin.zoobff.data.cart.getfullcart.GetFullCartRequest;
import tinqin.zoobff.data.cart.getfullcart.GetFullCartResponse;

import java.util.UUID;

@RestController
@RequestMapping("/bff")
@RequiredArgsConstructor
public class BffController {
    private final FindItems findItems;
    private final FindAllItemsByTagId getItemsByTagId;
    private final SellCart sellCart;

    @Operation(summary = "Sell items from cart", description = "You delete the whole cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully sold"),
            @ApiResponse(responseCode = "401", description = "Item/Storage not found")
    })
    @DeleteMapping("/saleCart/{userId}")
    public ResponseEntity<SellCartResponse> sellCart(@PathVariable Integer userId) {
        SellCartRequest request = SellCartRequest.builder().userId(userId).build();
        sellCart.process(request);
        SellCartResponse response = new SellCartResponse();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get item by storageId", description = "Get complete item information by storage's id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "401", description = "Item/Storage not found")
    })
    @GetMapping("/item/{storageId}")
    public ResponseEntity<FindItemsResponse> getQuantity(@PathVariable UUID storageId) {
        FindItemsRequest request = new FindItemsRequest();
        request.setStorageId(storageId);
        FindItemsResponse response = findItems.process(request);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get items by tagId", description = "Get list of items by tag id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "401", description = "Tag  not found")
    })
    @GetMapping("/itemsByTag/{tagId}")
    public ResponseEntity<FindAllItemsByTagIdResponse> getItems(@PathVariable UUID tagId){
        FindAllItemsByTagIdRequest request = new FindAllItemsByTagIdRequest(tagId);
        FindAllItemsByTagIdResponse response = getItemsByTagId.process(request);

        return ResponseEntity.ok(response);
    }

}
