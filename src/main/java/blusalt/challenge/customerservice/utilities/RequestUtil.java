package blusalt.challenge.customerservice.utilities;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RequestUtil {

    private final Logger logger = LoggerFactory.getLogger(RequestUtil.class);
    private static final String APPLICATION_JSON = "application/json";
    private static final String ACCEPT_HEADER = "accept";
    private static final  String CONTENT_TYPE_HEADER = "Content-Type";

    public HttpResponse<JsonNode> sendPostReqWithHeaders(String url, String requestBody, Map<String, String> headers) throws UnirestException {

        logger.info("url {}", url);

        Map<String, String> map3 = new HashMap<String, String>(){
                {
                        put(ACCEPT_HEADER, APPLICATION_JSON);
                        put(CONTENT_TYPE_HEADER, APPLICATION_JSON);
                }
        };
        map3.putAll(headers);

        return Unirest.post(url)
                .headers(map3)
                .body(requestBody)
                .asJson();
    }

    public HttpResponse<JsonNode> sendPostReq(String url, String requestBody) throws UnirestException {

        logger.info("url {}", url);

        Map<String, String> headers = new HashMap<String, String>(){
            {
                put(ACCEPT_HEADER, APPLICATION_JSON);
                put(CONTENT_TYPE_HEADER, APPLICATION_JSON);
            }
        };

        return Unirest.post(url).headers(headers).body(requestBody).asJson();
    }

}

