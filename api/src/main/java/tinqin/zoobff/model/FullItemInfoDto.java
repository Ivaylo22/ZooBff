package tinqin.zoobff.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostore.data.Item;
import tinqin.zoostore.data.Multimedia;
import tinqin.zoostore.data.Tag;
import tinqin.zoostore.data.Vendor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullItemInfoDto {
    private Item item;
    private Integer quantity;
    private Double price;
}
