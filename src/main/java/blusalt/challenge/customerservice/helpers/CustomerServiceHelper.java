package blusalt.challenge.customerservice.helpers;

import blusalt.challenge.customerservice.enums.ResponseStatus;
import blusalt.challenge.customerservice.models.ResponseModel;

public class CustomerServiceHelper {


    public static final String SUCCESS_MESSAGE = "Operation successful";
    public static final String ERROR_MESSAGE = "Operation FAILED";
    public static final String REQUEST_FAILED="99";



    public static ResponseModel buildSuccessfulResposne(Object data){
        return new ResponseModel(ResponseStatus.SUCCESS, SUCCESS_MESSAGE, data);
    }

    public static ResponseModel buildErrorResposne(Object data) {
        return new ResponseModel(ResponseStatus.FAILED, ERROR_MESSAGE, data);
    }

    public static ResponseModel buildErrorResposne(){
        return new ResponseModel(ResponseStatus.FAILED, ERROR_MESSAGE, null);
    }
}
