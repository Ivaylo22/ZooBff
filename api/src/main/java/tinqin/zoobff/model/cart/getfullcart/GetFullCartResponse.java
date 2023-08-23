package tinqin.zoobff.model.cart.getfullcart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoobff.model.bff.finditemsbytagid.FullItemInfoDto;
import tinqin.zoostore.operation.OperationResult;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetFullCartResponse  implements OperationResult {
    private Integer userId;
    private List<FullItemInfoDto> items;
    private Double finalPrice;
}
