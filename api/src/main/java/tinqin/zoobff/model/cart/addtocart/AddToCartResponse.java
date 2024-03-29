package tinqin.zoobff.model.cart.addtocart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostore.operation.OperationResult;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddToCartResponse  implements OperationResult {
    private UUID cartId;
    private Integer quantity;
    private Double finalPrice;
}
