package tinqin.zoobff.model.cart.getfullcart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostore.operation.OperationInput;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetFullCartRequest implements OperationInput {
    private Integer userId;
}
