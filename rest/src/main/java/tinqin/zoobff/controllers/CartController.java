package tinqin.zoobff.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tinqin.zoobff.model.cart.addtocart.AddToCart;
import tinqin.zoobff.model.cart.addtocart.AddToCartRequest;
import tinqin.zoobff.model.cart.addtocart.AddToCartResponse;
import tinqin.zoobff.model.cart.emptycart.EmptyCart;
import tinqin.zoobff.model.cart.emptycart.EmptyCartRequest;
import tinqin.zoobff.model.cart.emptycart.EmptyCartResponse;
import tinqin.zoobff.model.cart.getfullcart.GetFullCart;
import tinqin.zoobff.model.cart.getfullcart.GetFullCartRequest;
import tinqin.zoobff.model.cart.getfullcart.GetFullCartResponse;
import tinqin.zoobff.model.cart.removefromcart.RemoveFromCart;
import tinqin.zoobff.model.cart.removefromcart.RemoveFromCartRequest;
import tinqin.zoobff.model.cart.removefromcart.RemoveFromCartResponse;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final GetFullCart getFullCart;
    private final AddToCart addToCart;
    private final RemoveFromCart removeFromCart;
    private final EmptyCart emptyCart;


    @Operation(summary = "Get cart by user id", description = "Get complete cart information by user's id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    })
    @GetMapping("/getCart/{userId}")
    public ResponseEntity<GetFullCartResponse> getCart(@RequestParam Integer userId) {
        GetFullCartRequest request = new GetFullCartRequest(userId);
        GetFullCartResponse response = getFullCart.process(request);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Add cart", description = "Add item to cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added"),
            @ApiResponse(responseCode = "401", description = "Item/Storage not found")
    })
    @PostMapping("/addToCart")
    public ResponseEntity<AddToCartResponse> addToCart(@RequestBody AddToCartRequest request) {
        AddToCartResponse response = addToCart.process(request);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Empty cart", description = "Empty the whole cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully emptied"),
            @ApiResponse(responseCode = "401", description = "Item not found")
    })
    @PostMapping("/emptyCart")
    public ResponseEntity<EmptyCartResponse> emptyCart(@RequestBody EmptyCartRequest request) {
        EmptyCartResponse response = emptyCart.process(request);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Remove cart", description = "Remove item from cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully removed"),
            @ApiResponse(responseCode = "401", description = "Item not found")
    })
    @PostMapping("/removeFromCart")
    public ResponseEntity<RemoveFromCartResponse> removeFromCart(@RequestBody RemoveFromCartRequest request) {
        RemoveFromCartResponse response = removeFromCart.process(request);

        return ResponseEntity.ok(response);
    }
}
