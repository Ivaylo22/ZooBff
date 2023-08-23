package tinqin.zoobff.model.cart.emptycart;

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
public class EmptyCartRequest  implements OperationInput {
    private Integer userId;
}
