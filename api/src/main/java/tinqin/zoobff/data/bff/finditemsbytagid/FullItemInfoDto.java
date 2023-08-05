package tinqin.zoobff.data.bff.finditemsbytagid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostore.data.Item;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FullItemInfoDto {
    private Item item;
    private Integer quantity;
    private Double price;
}
