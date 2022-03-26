package blusalt.challenge.customerservice.controllers;


import blusalt.challenge.customerservice.dto.CreateCustomerDTO;
import blusalt.challenge.customerservice.dto.FundAccountDTO;
import blusalt.challenge.customerservice.interfaces.ICustomerService;
import blusalt.challenge.customerservice.models.Customer;
import blusalt.challenge.customerservice.models.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerServiceController {

    private ICustomerService customerService;

    @Autowired
    public CustomerServiceController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<ResponseModel<Customer>> createCustomer(@Validated @RequestBody CreateCustomerDTO createCustomerDTO){
        return ResponseEntity.ok(customerService.createCustomer(createCustomerDTO));
    }

    @PostMapping("/fund")
    public ResponseEntity<ResponseModel<Customer>> fundAccount(@Validated @RequestBody FundAccountDTO fundAccountDTO){
        return ResponseEntity.ok(customerService.fundAccount(fundAccountDTO));
    }
}
