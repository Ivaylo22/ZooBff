package tinqin.zoobff.data.cart.emptycart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostore.operation.OperationInput;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmptyCartRequest  implements OperationInput {
    private Integer userId;
}
