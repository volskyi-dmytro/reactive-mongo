package com.stpunk47.reactive_mongo.mappers;

import com.stpunk47.reactive_mongo.domain.Customer;
import com.stpunk47.reactive_mongo.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerDTO customerToCustomerDto(Customer customer);

    Customer customerDtoToCustomer(CustomerDTO customerDTO);
}
