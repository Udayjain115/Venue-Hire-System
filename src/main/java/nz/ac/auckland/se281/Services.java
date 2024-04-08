package nz.ac.auckland.se281;

public abstract class Services {
  protected String bookingReference;

  public Services(String bookingReference) {
    this.bookingReference = bookingReference;
  }

  public abstract void addService(String bookingReference);

  public abstract void serviceBookingNotFound(String bookingReference);

  public abstract int getCost();

  public String getBookingReference() {
    return bookingReference;
  }
}
