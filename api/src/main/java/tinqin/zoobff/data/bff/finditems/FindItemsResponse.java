package tinqin.zoobff.data.bff.finditems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoobff.operations.OperationResult;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindItemsResponse implements OperationResult {
    private UUID storageId;
    private UUID itemId;
}
