package pv.com.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public class CustomerDTO {

    private Long customerId;
    @NotBlank(message = "Please enter customer first name")
    @Pattern(regexp = "^[A-Za-z]+$",message = "Only alphabets are allowed")
    private String firstName;
    @Pattern(regexp = "^[A-Za-z]+$",message = "Only alphabets are allowed")
    private String lastName;
    @NotBlank(message = "Please enter customer email ID")
    @Email(message = "Please provide valid Email")
    private String email;
    @NotBlank(message = "Please enter address")
    private String addressLine1;
    private String addressLine2;
    @NotBlank(message = "Please enter city")
    private String city;
    @NotBlank(message = "Please enter state")
    private String state;
    @NotBlank(message = "Please enter country")
    private String country;
    @NotBlank(message = "Please enter postal code")
    private String postalCode;

    private List<OrdersDTO> orders = new ArrayList<>();

    public CustomerDTO() {}

    public CustomerDTO(Long customerId, String firstName, String lastName, String email,
                       String addressLine1, String addressLine2, String city, String state,
                       String country, String postalCode, List<OrdersDTO> orders) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.orders = orders;
    }

    // Getters and Setters
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddressLine1() { return addressLine1; }
    public void setAddressLine1(String addressLine1) { this.addressLine1 = addressLine1; }

    public String getAddressLine2() { return addressLine2; }
    public void setAddressLine2(String addressLine2) { this.addressLine2 = addressLine2; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public List<OrdersDTO> getOrders() { return orders; }
    public void setOrders(List<OrdersDTO> orders) { this.orders = orders; }
}
