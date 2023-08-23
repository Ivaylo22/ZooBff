package tinqin.zoobff.model.cart.addtocart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostore.operation.OperationInput;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddToCartRequest implements OperationInput {
    private Integer userId;
    private UUID itemId;
    private Integer quantity;
    private String city;
}
