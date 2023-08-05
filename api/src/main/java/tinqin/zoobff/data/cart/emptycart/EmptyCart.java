package tinqin.zoobff.data.cart.emptycart;

import tinqin.zoobff.data.cart.addtocart.AddToCartRequest;
import tinqin.zoobff.data.cart.addtocart.AddToCartResponse;
import tinqin.zoostore.operation.OperationProcessor;

public interface EmptyCart  extends OperationProcessor<EmptyCartRequest, EmptyCartResponse> {
}
