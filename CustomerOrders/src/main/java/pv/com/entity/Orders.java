package pv.com.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private LocalDateTime orderDate;
    
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    
    private BigDecimal totalAmount;
    
    private Integer quantity;
    
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;
    
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    
    private String deliveryAddress;
    private LocalDate expectedDelivery;
    private String transactionId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Orders() {}

    public Orders(LocalDateTime orderDate, OrderStatus orderStatus, BigDecimal totalAmount, Integer quantity,
                  PaymentMode paymentMode, PaymentStatus paymentStatus, String deliveryAddress,
                  LocalDate expectedDelivery, String transactionId, Customer customer) {
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
        this.quantity = quantity;
        this.paymentMode = paymentMode;
        this.paymentStatus = paymentStatus;
        this.deliveryAddress = deliveryAddress;
        this.expectedDelivery = expectedDelivery;
        this.transactionId = transactionId;
        this.customer = customer;
    }

    // Getters and Setters
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public OrderStatus getOrderStatus() { return orderStatus; }
    public void setOrderStatus(OrderStatus orderStatus) { this.orderStatus = orderStatus; }
    
    public BigDecimal getTotalAmount() {return totalAmount;}
    public void setTotalAmount(BigDecimal totalAmount) {this.totalAmount=totalAmount;}
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public PaymentMode getPaymentMode() { return paymentMode; }
    public void setPaymentMode(PaymentMode paymentMode) { this.paymentMode = paymentMode; }

    public PaymentStatus getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(PaymentStatus paymentStatus) { this.paymentStatus = paymentStatus; }

    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }

    public LocalDate getExpectedDelivery() { return expectedDelivery; }
    public void setExpectedDelivery(LocalDate expectedDelivery) { this.expectedDelivery = expectedDelivery; }
    
    public String getTransactionId() {return transactionId;}
    public void setTransactionId(String transactionId) {this.transactionId = transactionId;}
    

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    
   

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Orders)) return false;
        return orderId != null && orderId.equals(((Orders) o).orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", orderDate=" + orderDate + ", orderStatus=" + orderStatus
				+ ", totalAmount=" + totalAmount + ", quantity=" + quantity + ", paymentMode=" + paymentMode
				+ ", paymentStatus=" + paymentStatus + ", deliveryAddress=" + deliveryAddress + ", expectedDelivery="
				+ expectedDelivery + ", transactionId=" + transactionId + ", customer=" + customer + "]";
	}
}
