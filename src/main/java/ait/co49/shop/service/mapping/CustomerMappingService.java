package ait.co49.shop.service.mapping;

import ait.co49.shop.model.dto.CustomerDto;
import ait.co49.shop.model.dto.ProductDto;
import ait.co49.shop.model.entity.Customer;
import ait.co49.shop.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMappingService {
    @Mapping(target = "id", ignore = true)
    Customer mapDtoToEntity(CustomerDto dto);

    CustomerDto mapEntityToDto (Customer entity);
}
