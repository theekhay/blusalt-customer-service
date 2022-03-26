package blusalt.challenge.customerservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateCustomerDTO {

    @NotNull(message = "First name is required")
    @NotEmpty(message = "Kindly provide a value for the first name")
    @JsonProperty("first_name")
    private String firstName;

    @NotNull(message = "Last name is required")
    @NotEmpty(message = "Kindly provide a value for the last name")
    @JsonProperty("last_name")
    private String lastName;


    @NotNull(message = "Phone number is required")
    @NotEmpty(message = "Kindly provide a value for the phone number")
    @JsonProperty("phone_number")
    private String phoneNumber;

    @NotNull(message = "Email is required")
    @NotEmpty(message = "Kindly provide a value for the email")
    @Email(message = "Please provide a valid email")
    @JsonProperty("email")
    private String email;
}
