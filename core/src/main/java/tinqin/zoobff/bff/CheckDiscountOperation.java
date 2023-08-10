package tinqin.zoobff.bff;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tinqin.zoobff.data.bff.checkfordiscount.CheckDiscount;
import tinqin.zoobff.data.bff.checkfordiscount.CheckDiscountRequest;
import tinqin.zoobff.data.bff.checkfordiscount.CheckDiscountResponse;
import tinqin.zoostorage.ZooStorageRestClient;
import tinqin.zoostorage.model.checkstoragebyitem.CheckStorageByItemResponse;
import tinqin.zoostore.ZooStoreRestClient;
import tinqin.zoostore.data.Item;
import tinqin.zoostore.model.tag.gettagbytitle.GetTagByTitleRequest;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CheckDiscountOperation implements CheckDiscount {
    private final ZooStoreRestClient storeRestClient;
    private final ZooStorageRestClient storageRestClient;
    private final ModelMapper modelMapper;

    @Override
    public CheckDiscountResponse process(CheckDiscountRequest input) {
        Map<String, String> map = new HashMap<>();

        map.put("MONDAY", "Domestic");
        map.put("TUESDAY", "Cheap");
        map.put("WEDNESDAY", "Expensive");
        map.put("THURSDAY", "Domestic");
        map.put("FRIDAY", "Cheap");
        map.put("SATURDAY", "Expensive");
        map.put("SUNDAY", "Domestic");

        LocalDate today = LocalDate.now();

        DayOfWeek dayOfWeek = today.getDayOfWeek();

        GetTagByTitleRequest tagRequest = GetTagByTitleRequest
                .builder()
                .title(map.get(dayOfWeek.toString().toUpperCase()))
                .build();
        UUID tagId = storeRestClient.getByTitle(tagRequest).getTagId();

        List<Item> items = storeRestClient.getItemsByTagId(tagId).getItems();
        List<Item> discoutedItems = new ArrayList<>();

        for (Item item: items) {
            CheckStorageByItemResponse response = storageRestClient.checkStorageByItemId(item.getId());
            if(response.getIsInStorage()) {
                discoutedItems.add(item);
            }
        }

        return CheckDiscountResponse.builder()
                .dayOfTheWeek(dayOfWeek.toString())
                .tagTitle(map.get(dayOfWeek.toString().toUpperCase()))
                .items(discoutedItems)
                .build();
    }
}
