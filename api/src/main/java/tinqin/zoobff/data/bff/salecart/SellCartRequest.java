package tinqin.zoobff.data.bff.salecart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoobff.operations.OperationInput;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellCartRequest implements OperationInput {
    private Integer userId;
}
