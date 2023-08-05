package tinqin.zoobff.data.bff.finditemsbytagid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoobff.operations.OperationResult;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindAllItemsByTagIdResponse implements OperationResult {
    List<FullItemInfoDto> items;
}
