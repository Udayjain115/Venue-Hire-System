package nz.ac.auckland.se281;

public class Floral extends Services {
  private String Deluxe_Or_Floral;
  private int cost;

  public Floral(String bookingReference, String Deluxe_Or_Floral, int cost) {
    super(bookingReference);
    this.Deluxe_Or_Floral = Deluxe_Or_Floral;
    this.cost = cost;

    // TODO Auto-generated constructor stub
  }

  @Override
  public void addService(String bookingReference) {
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
        "Floral (" + Deluxe_Or_Floral + ")", bookingReference);
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
    return Deluxe_Or_Floral;
  }
}
