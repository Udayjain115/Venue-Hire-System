package nz.ac.auckland.se281;

public class Bookings {
  private String venueCode;
  private String Date;
  private String customerEmail;
  private String numberOfGuests;
  private String bookingReference;
  private String venueName;
  private String bookingDate;
  private String bookingCost;

  public Bookings(
      String venueCode,
      String Date,
      String customerEmail,
      String numberOfGuests,
      String bookingReference,
      String venueName,
      String bookingDate,
      String bookingCost) {
    this.venueCode = venueCode;
    this.Date = Date;
    this.customerEmail = customerEmail;
    this.numberOfGuests = numberOfGuests;
    this.bookingReference = bookingReference;
    this.venueName = venueName;
    this.bookingDate = bookingDate;
    this.bookingCost = bookingCost;
  }

  public String getBookingCost() {
    return bookingCost;
  }

  public String getDate() {
    return Date;
  }

  public String getNumberOfGuests() {
    return numberOfGuests;
  }

  public String getBookingDate() {
    return bookingDate;
  }

  public String getName() {
    return venueName;
  }

  public String getVenueCode() {
    return venueCode;
  }

  public String getBookingReference() {
    return bookingReference;
  }

  public String getCustomerEmail() {
    return customerEmail;
  }
}
