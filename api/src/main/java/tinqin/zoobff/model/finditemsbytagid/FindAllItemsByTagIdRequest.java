package tinqin.zoobff.model.finditemsbytagid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoobff.operations.OperationInput;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindAllItemsByTagIdRequest implements OperationInput {
    private UUID tagId;
}
