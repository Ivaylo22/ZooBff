package tinqin.zoobff.data.bff.salecart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoobff.operations.OperationResult;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellCartResponse implements OperationResult {
    private String message = "Cart is sold";
}
