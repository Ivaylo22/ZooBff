package tinqin.zoobff.model.finditemsbytagid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoobff.model.FullItemInfoDto;
import tinqin.zoobff.operations.OperationResult;
import tinqin.zoostorage.model.getinfobyid.GetInfoByIdResponse;
import tinqin.zoostore.data.Item;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindAllItemsByTagIdResponse implements OperationResult {
    List<FullItemInfoDto> items;
}
