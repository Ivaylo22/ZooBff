package tinqin.zoobff.data.cart.removefromcart;

import tinqin.zoobff.data.cart.addtocart.AddToCartRequest;
import tinqin.zoobff.data.cart.addtocart.AddToCartResponse;
import tinqin.zoostore.operation.OperationProcessor;

public interface RemoveFromCart  extends OperationProcessor<RemoveFromCartRequest, RemoveFromCartResponse> {
}
