package tinqin.zoobff.data.bff.checkfordiscount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoobff.operations.OperationResult;
import tinqin.zoostore.data.Item;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckDiscountResponse implements OperationResult {
    private String dayOfTheWeek;
    private String tagTitle;
    private List<Item> items;
}
