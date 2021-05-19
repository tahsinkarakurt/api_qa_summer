package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingPojo {

    private String firstName;
    private String lastName;
    private int totalPrice;
    private boolean depositPaid;
    private BookingDatePojo bookingDates;

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public int getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
    public boolean isDepositPaid() {
        return depositPaid;
    }
    public void setDepositPaid(boolean depositPaid) {
        this.depositPaid = depositPaid;
    }
    public BookingDatePojo getBookingDates() {
        return bookingDates;
    }
    public void setBookingDates(BookingDatePojo bookingDates) {
        this.bookingDates = bookingDates;
    }

    public BookingPojo() {
    }

    public BookingPojo(String firstName, String lastName, int totalPrice, boolean depositPaid, BookingDatePojo bookingDates) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalPrice = totalPrice;
        this.depositPaid = depositPaid;
        this.bookingDates = bookingDates;
    }

    @Override
    public String toString() {
        return "BookingPojo{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", totalPrice=" + totalPrice +
                ", depositPaid=" + depositPaid +
                ", bookingDates=" + bookingDates +
                '}';
    }
}
