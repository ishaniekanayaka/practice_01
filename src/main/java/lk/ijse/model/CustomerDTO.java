package lk.ijse.model;

public class CustomerDTO {
    private String customerId;
    private String customerName;
    private String address;

    public CustomerDTO() {
    }

    public CustomerDTO(String customerId, String customerName, String address) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
