package nz.ac.auckland.se281;

public class Bookings {
  private String venueCode;
  private String Date;
  // private String customerEmail;
  // private String Capacity;
  private String bookingReference;
  private String venueName;

  public Bookings(
      String venueCode,
      String Date,
      String customerEmail,
      String Capacity,
      String bookingReference,
      String venueName) {
    this.venueCode = venueCode;
    this.Date = Date;
    // this.customerEmail = customerEmail;
    // this.Capacity = Capacity;
    this.bookingReference = bookingReference;
    this.venueName = venueName;
  }

  public String getDate() {
    return Date;
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
}
