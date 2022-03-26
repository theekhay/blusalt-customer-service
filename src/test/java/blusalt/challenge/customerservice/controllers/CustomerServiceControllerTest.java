package blusalt.challenge.customerservice.controllers;


import blusalt.challenge.customerservice.dto.CreateCustomerDTO;
import blusalt.challenge.customerservice.dto.FundAccountDTO;
import blusalt.challenge.customerservice.models.Customer;
import blusalt.challenge.customerservice.models.ResponseModel;
import blusalt.challenge.customerservice.services.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static blusalt.challenge.customerservice.helpers.ResponseHelper.getSampleCustomer;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@PropertySource("classpath:test.properties")
@WebMvcTest(CustomerServiceController.class)
public class CustomerServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private CustomerServiceImpl customerService;

    @Test
    public void testCreateCustomer422Error() throws Exception {

        Customer customer = getSampleCustomer();

        doReturn(new ResponseModel<Customer>())
                .when(customerService)
                .createCustomer(any());

        mockMvc.perform( post("/customer")
                        .content( asJsonString( new CreateCustomerDTO()) )
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect( status().isUnprocessableEntity() );
    }

    @Test
    public void testCreateCustomerSuccess() throws Exception {

        Customer customer = getSampleCustomer();
        CreateCustomerDTO createCustomerRequest = new CreateCustomerDTO();
        createCustomerRequest.setEmail("test@emal.com");
        createCustomerRequest.setLastName("lasname");
        createCustomerRequest.setFirstName("firstname");
        createCustomerRequest.setPhoneNumber("080990088887");


        doReturn(new ResponseModel<Customer>())
                .when(customerService)
                .createCustomer(any());

        mockMvc.perform( post("/customer")
                        .content( asJsonString( createCustomerRequest) )
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect( status().isOk() );
    }

    @Test
    public void testCustomerFundSuccess() throws Exception {

        FundAccountDTO fundAccountDTO = new FundAccountDTO();
        fundAccountDTO.setCustomerId(1);
        fundAccountDTO.setAmount(100D);
        fundAccountDTO.setCurrency("NGN");
        fundAccountDTO.setClientReference("12345");
        fundAccountDTO.setChannel("CASH");


        doReturn(new ResponseModel<Customer>())
                .when(customerService)
                .fundAccount(any());

        mockMvc.perform( post("/customer/fund")
                        .content( asJsonString( fundAccountDTO) )
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect( status().isOk() );
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
