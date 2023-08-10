package tinqin.zoobff.data.bff.getallitemsbytitle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoobff.operations.OperationInput;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllItemsByTitleRequest implements OperationInput {
    private String title;
    private Integer page;
    private Integer pageSize;
}
