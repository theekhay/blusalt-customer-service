package blusalt.challenge.customerservice.interfaces;


import blusalt.challenge.customerservice.dto.CreateCustomerDTO;
import blusalt.challenge.customerservice.dto.FundAccountDTO;
import blusalt.challenge.customerservice.models.Customer;
import blusalt.challenge.customerservice.models.ResponseModel;

public interface ICustomerService {

    ResponseModel<Customer> createCustomer(CreateCustomerDTO createCustomerDTO);

    ResponseModel<Customer> fetchCustomer(Long customerId);

    ResponseModel<Customer> fundAccount(FundAccountDTO fundAccountDTO);
}
