package pv.com.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import pv.com.entity.OrderStatus;
import pv.com.entity.PaymentMode;
import pv.com.entity.PaymentStatus;

public class OrderDTO {

    private Long orderId;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    
    @NotNull(message = "Please provide quantity")
    private Integer quantity;
    
    private BigDecimal totalAmount;
    
    @Pattern(regexp ="CARD|UPI|CASH", message = "Please provide valid value")
    private PaymentMode paymentMode;
    
    private PaymentStatus paymentStatus;
    
    @NotBlank(message = "Please provide Delivery Address")
    private String deliveryAddress;
    
    private LocalDate expectedDelivery;
    private String transactionId;
    private Long customerId; // link to customer

    public OrderDTO() {}

    public OrderDTO(Long orderId, LocalDateTime orderDate, OrderStatus orderStatus, BigDecimal totalAmount, Integer quantity,
    		PaymentMode paymentMode, PaymentStatus paymentStatus, String deliveryAddress,
                     LocalDate expectedDelivery,String transactionId, Long customerId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
        this.quantity = quantity;
        this.paymentMode = paymentMode;
        this.paymentStatus = paymentStatus;
        this.deliveryAddress = deliveryAddress;
        this.expectedDelivery = expectedDelivery;
        this.transactionId=transactionId;
        this.customerId = customerId;
    }

    // Getters and Setters
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate;}

    public OrderStatus getOrderStatus() { return orderStatus; }
    public void setOrderStatus(OrderStatus orderStatus)  { this.orderStatus = orderStatus;}
    
    public BigDecimal getTotalAmount() {return totalAmount;}
    public void setTotalAmount(BigDecimal totalAmount) {this.totalAmount=totalAmount;}
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public PaymentMode getPaymentMode() { return paymentMode; }
    public void setPaymentMode(PaymentMode paymentMode) { this.paymentMode = paymentMode; }

    public PaymentStatus getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(PaymentStatus paymentStatus) {this.paymentStatus = paymentStatus; }

    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }

    public LocalDate getExpectedDelivery() { return expectedDelivery; }
    public void setExpectedDelivery(LocalDate expectedDelivery){ this.expectedDelivery = expectedDelivery;}
    
    public String getTransactionId() {return transactionId;}
    public void setTransactionId(String transactionId) {this.transactionId = transactionId; }
    
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

	@Override
	public int hashCode() {
		return Objects.hash(customerId, deliveryAddress, expectedDelivery, orderDate, orderId, orderStatus, paymentMode,
				paymentStatus, quantity, totalAmount, transactionId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDTO other = (OrderDTO) obj;
		return Objects.equals(customerId, other.customerId) && Objects.equals(deliveryAddress, other.deliveryAddress)
				&& Objects.equals(expectedDelivery, other.expectedDelivery)
				&& Objects.equals(orderDate, other.orderDate) && Objects.equals(orderId, other.orderId)
				&& orderStatus == other.orderStatus && paymentMode == other.paymentMode
				&& paymentStatus == other.paymentStatus && Objects.equals(quantity, other.quantity)
				&& Objects.equals(totalAmount, other.totalAmount) && Objects.equals(transactionId, other.transactionId);
	}

	@Override
	public String toString() {
		return "OrdersDTO [orderId=" + orderId + ", orderDate=" + orderDate + ", orderStatus=" + orderStatus
				+ ", quantity=" + quantity + ", totalAmount=" + totalAmount + ", paymentMode=" + paymentMode
				+ ", paymentStatus=" + paymentStatus + ", deliveryAddress=" + deliveryAddress + ", expectedDelivery="
				+ expectedDelivery + ", transactionId=" + transactionId + ", customerId=" + customerId + "]";
	}
    
    
}
