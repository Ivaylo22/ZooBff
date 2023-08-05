package tinqin.zoobff.data.bff.finditems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoobff.operations.OperationInput;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindItemsRequest implements OperationInput {
    private UUID storageId;
}
