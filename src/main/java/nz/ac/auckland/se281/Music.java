package nz.ac.auckland.se281;

public class Music extends Services {

  public Music(String bookingReference) {
    super(bookingReference);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void addService(String bookingReference) {
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Music", bookingReference);
  }

  @Override
  public void serviceBookingNotFound(String bookingReference) {
    MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Music", bookingReference);
  }

  @Override
  public int getCost() {
    return 500;
  }
}
