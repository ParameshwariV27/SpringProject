package pv.com.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UpdateCustomerDTO {
	private Long customerId;
   
    @Pattern(regexp = "^[A-Za-z]+$",message = "Only alphabets are allowed")
    private String firstName;
    
    @Pattern(regexp = "^[A-Za-z]+$",message = "Only alphabets are allowed")
    private String lastName;
    
   
   // @Email(message = "Please provide valid Email")
    private String email;
    
    
    @Pattern(regexp="([A-Za-z0-9 ., #])+" , message = "Enter valid address1")
    private String addressLine1;
    
    @Pattern(regexp="([A-Za-z0-9 ., #])+" , message = "Enter valid address2")
    private String addressLine2;
    
  
    @Pattern(regexp = "([A-Za-z ])+", message = "Enter valid city")
    private String city;
    
  
    @Pattern(regexp = "([A-Za-z ])+", message = "Enter valid state")
    private String state;
    
   
    @Pattern(regexp = "([A-Za-z ])+", message = "Enter valid country")
    private String country;
    
  
    @Pattern(regexp = "([0-9])+", message = "Enter valid pin code")
    private String postalCode;

    public UpdateCustomerDTO() {}

    public UpdateCustomerDTO(Long customerId, String firstName, String lastName, String email,
                       String addressLine1, String addressLine2, String city, String state,
                       String country, String postalCode) {
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

   

	@Override
	public int hashCode() {
		return Objects.hash(addressLine1, addressLine2, city, country, customerId, email, firstName, lastName,
				postalCode, state);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpdateCustomerDTO other = (UpdateCustomerDTO) obj;
		return Objects.equals(addressLine1, other.addressLine1) && Objects.equals(addressLine2, other.addressLine2)
				&& Objects.equals(city, other.city) && Objects.equals(country, other.country)
				&& Objects.equals(customerId, other.customerId) && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				 && Objects.equals(postalCode, other.postalCode)
				&& Objects.equals(state, other.state);
	}

	@Override
	public String toString() {
		return "UpdateCustomerDTO [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", city="
				+ city + ", state=" + state + ", country=" + country + ", postalCode=" + postalCode + ", orders="
				 + "]";
	}
    
    
}
