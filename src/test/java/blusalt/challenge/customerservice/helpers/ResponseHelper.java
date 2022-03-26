package blusalt.challenge.customerservice.helpers;

import blusalt.challenge.customerservice.models.Customer;

public class ResponseHelper {


    public static Customer getSampleCustomer() {
        var customer = new Customer();
        customer.setEmail("test@email.com");
        customer.setFirstName("firstname");
        customer.setLastName("lastname");
        customer.setPhoneNumber("08180897761");
        customer.setAddress("addreess");
        customer.setId(1L);
        return customer;
    }
}
