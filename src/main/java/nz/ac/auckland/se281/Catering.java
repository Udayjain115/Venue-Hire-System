package nz.ac.auckland.se281;

public class Catering extends Services {
  private String cateringType;

  public Catering(String bookingReference, String cateringType) {
    super(bookingReference);
    this.cateringType = cateringType;
    // Rest of the Catering constructor
  }

  @Override
  public void addService(String bookingReference) {
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
        "Catering (" + cateringType + ")", bookingReference);
    // Rest of the Catering class
  }
}
