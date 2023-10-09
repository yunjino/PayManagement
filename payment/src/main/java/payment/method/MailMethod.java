package payment.method;

public class MailMethod extends PaymentMethod {
    private final String address;

    public MailMethod(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
