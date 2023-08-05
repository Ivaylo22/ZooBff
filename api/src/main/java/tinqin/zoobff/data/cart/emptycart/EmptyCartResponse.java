package tinqin.zoobff.data.cart.emptycart;

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
public class EmptyCartResponse implements OperationResult {
    private String message;
}
