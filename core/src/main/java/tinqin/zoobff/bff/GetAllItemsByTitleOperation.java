package tinqin.zoobff.bff;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tinqin.zoobff.data.bff.finditemsbytagid.FullItemInfoDto;
import tinqin.zoobff.data.bff.getallitemsbytitle.GetAllItemsByTitle;
import tinqin.zoobff.data.bff.getallitemsbytitle.GetAllItemsByTitleRequest;
import tinqin.zoobff.data.bff.getallitemsbytitle.GetAllItemsByTitleResponse;
import tinqin.zoostorage.ZooStorageRestClient;
import tinqin.zoostorage.data.Storage;
import tinqin.zoostorage.model.getinfobyid.GetInfoByIdResponse;
import tinqin.zoostore.ZooStoreRestClient;
import tinqin.zoostore.data.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetAllItemsByTitleOperation implements GetAllItemsByTitle {
    private final ZooStoreRestClient storeRestClient;
    private final ZooStorageRestClient storageRestClient;
    private final ModelMapper modelMapper;

    @Override
    public GetAllItemsByTitleResponse process(GetAllItemsByTitleRequest input) {
        List<Item> items = storeRestClient.getItemsByTitle(input.getTitle(), input.getPage(), input.getPageSize()).getItems();
        List<UUID> ids = items
                .stream()
                .map(Item::getId)
                .toList();

        List<Storage> storages = ids.parallelStream()
                .map(id -> {
                    GetInfoByIdResponse storageDto = storageRestClient.getInfoByItemId(id);
                    Storage storage = Storage.builder()
                            .itemId(storageDto.getItemId())
                            .quantity(storageDto.getQuantity())
                            .price(storageDto.getPrice())
                            .build();
                    return storage;
                })
                .collect(Collectors.toList());

        GetAllItemsByTitleResponse response = GetAllItemsByTitleResponse
                .builder()
                .items(new ArrayList<>())
                .build();

        List<FullItemInfoDto> fullItemInfoList = items.stream()
                .map(item -> {
                    FullItemInfoDto fullItemInfoDto = modelMapper.map(item, FullItemInfoDto.class);

                    storages.stream()
                            .filter(s -> s.getItemId().equals(item.getId()))
                            .findFirst()
                            .ifPresent(s -> {
                                fullItemInfoDto.setQuantity(s.getQuantity());
                                fullItemInfoDto.setPrice(s.getPrice());
                            });

                    return fullItemInfoDto;
                })
                .collect(Collectors.toList());
        response.getItems().addAll(fullItemInfoList);

        return response;
    }
}
