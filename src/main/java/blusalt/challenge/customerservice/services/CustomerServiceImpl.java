package blusalt.challenge.customerservice.services;

import blusalt.challenge.customerservice.dao.CustomerDao;
import blusalt.challenge.customerservice.dao.WalletDao;
import blusalt.challenge.customerservice.dto.CreateCustomerDTO;
import blusalt.challenge.customerservice.dto.FundAccountDTO;
import blusalt.challenge.customerservice.enums.ResponseStatus;
import blusalt.challenge.customerservice.interfaces.ICustomerService;
import blusalt.challenge.customerservice.models.Customer;
import blusalt.challenge.customerservice.models.ResponseModel;
import blusalt.challenge.customerservice.models.billingservice.FundRequest;
import blusalt.challenge.customerservice.utilities.Common;
import blusalt.challenge.customerservice.utilities.RequestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static blusalt.challenge.customerservice.helpers.CustomerServiceHelper.buildErrorResposne;
import static blusalt.challenge.customerservice.helpers.CustomerServiceHelper.buildSuccessfulResposne;
import static blusalt.challenge.customerservice.utilities.Common.generateRef;

@Slf4j
@Service("ICustomerService")
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerDao customerDao;
    private final ModelMapper modelMapper;
    private final WalletDao walletDao;
    private final RequestUtil requestUtil;

    @Value("${billing.service.base-url}") String billingServiceBaseUrl;
    @Value("${billing.service.fund}") String billingServiceFundEndpoint;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao, ModelMapper modelMapper, WalletDao walletDao, RequestUtil requestUtil) {
        this.customerDao = customerDao;
        this.modelMapper = modelMapper;
        this.walletDao = walletDao;
        this.requestUtil = requestUtil;
    }

    @Override
    public ResponseModel<Customer> createCustomer(CreateCustomerDTO createCustomerDTO) {

        try {
            var customer = modelMapper.map(createCustomerDTO, Customer.class);
            return  buildSuccessfulResposne(this.customerDao.save(customer));
        } catch (Exception ex){
            return buildErrorResposne();
        }
    }

    @Override
    public ResponseModel<Customer> fetchCustomer(Long customerId) {
        return null;
    }

    @Override
    public ResponseModel<Customer> fundAccount(FundAccountDTO fundAccountDTO) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            HttpResponse<JsonNode> billingServiceResponse =  requestUtil
                    .sendPostReq(billingServiceBaseUrl.concat(billingServiceFundEndpoint), objectMapper.writeValueAsString(fundAccountDTO)  );


            log.info("response body {}", billingServiceResponse.getBody());
            //var response = objectMapper.treeToValue((TreeNode) billingServiceResponse.getBody(), ResponseModel.class);

            var response =  billingServiceResponse.getBody();
            log.info("resp {}", response);
            if(response.getObject().getString("status").equalsIgnoreCase(ResponseStatus.SUCCESS.getCode())){
                return buildSuccessfulResposne(response.getObject().getString("message"));
            }
            return buildErrorResposne();

        } catch (UnirestException | JsonProcessingException e) {
            log.info("error is {}", e.getLocalizedMessage());
            return buildErrorResposne();
        }


    }
}
