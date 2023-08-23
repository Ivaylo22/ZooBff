package tinqin.zoobff.model.cart.removefromcart;

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
public class RemoveFromCartResponse implements OperationResult {
    private Integer userId;
    private UUID cartId;
}
