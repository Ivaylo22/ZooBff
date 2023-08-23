package tinqin.zoobff.model.cart.removefromcart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostore.operation.OperationInput;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemoveFromCartRequest implements OperationInput {
    private Integer userId;
    private UUID itemId;
}
