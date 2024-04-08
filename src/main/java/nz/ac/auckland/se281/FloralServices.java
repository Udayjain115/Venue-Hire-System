package nz.ac.auckland.se281;

public class FloralServices extends Services {
  private String deluxeOrStandard;
  private int cost;

  public FloralServices(String bookingReference, String deluxeOrStandard, int cost) {
    super(bookingReference);
    this.deluxeOrStandard = deluxeOrStandard;
    this.cost = cost;
  }

  @Override
  public void addService(String bookingReference) {
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
        "Floral (" + deluxeOrStandard + ")", bookingReference);
    // Rest of the Catering class
  }

  @Override
  public void serviceBookingNotFound(String bookingReference) {
    MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Floral", bookingReference);
  }

  @Override
  public int getCost() {
    return cost;
  }

  public String getFloralType() {
    return deluxeOrStandard;
  }
}
