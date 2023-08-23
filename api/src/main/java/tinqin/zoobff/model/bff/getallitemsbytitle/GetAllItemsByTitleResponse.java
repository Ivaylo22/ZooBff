package tinqin.zoobff.model.bff.getallitemsbytitle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoobff.model.bff.finditemsbytagid.FullItemInfoDto;
import tinqin.zoobff.operations.OperationResult;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllItemsByTitleResponse implements OperationResult {
    private List<FullItemInfoDto> items;
}
