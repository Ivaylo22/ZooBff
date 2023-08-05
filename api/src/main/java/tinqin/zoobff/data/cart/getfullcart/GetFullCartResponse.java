package tinqin.zoobff.data.cart.getfullcart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoobff.data.bff.finditemsbytagid.FullItemInfoDto;
import tinqin.zoostore.data.Item;
import tinqin.zoostore.model.item.getitem.GetItemResponse;
import tinqin.zoostore.operation.OperationResult;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetFullCartResponse  implements OperationResult {
    private Integer userId;
    private List<FullItemInfoDto> items;
    private Double finalPrice;
}
