package tinqin.zoobff.data.bff.getallitemsbytitle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoobff.data.bff.finditemsbytagid.FullItemInfoDto;
import tinqin.zoobff.operations.OperationResult;
import tinqin.zoostore.data.Item;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllItemsByTitleResponse implements OperationResult {
    private List<FullItemInfoDto> items;
}
