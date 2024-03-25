package nz.ac.auckland.se281;

public class Venue {

  private String venueName;
  private String venueCode;
  private String capacityInput;
  private String hireFeeInput;

  public Venue(String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    this.venueName = venueName;
    this.venueCode = venueCode;
    this.capacityInput = capacityInput;
    this.hireFeeInput = hireFeeInput;
  }

  public String getVenueName() {
    return venueName;
  }

  public String getVenueCode() {
    return venueCode;
  }

  public String getCapacityInput() {
    return capacityInput;
  }

  public String getHireFeeInput() {
    return hireFeeInput;
  }

  public boolean venueNameValid() {
    if (this.venueName.trim().isEmpty() == true) {
      return false;
    }

    return true;
  }

  // Checks For Capacity Validity by First trying to parse it as an integer,
  // if that doesnt work then throws error at user,
  // if it does work but Num is negative then also throws error at user
  public boolean isCapacityValid(String convertingNum) {
    try {
      int Num = Integer.parseInt(convertingNum);
      if (Num < 1) {
        int hi = 1;
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
        return false;
      }
      return true;
    } catch (Exception e) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
      return false;
    }
  }

  // Same logic as isCapacityValid but uses hire fee instead
  public boolean isHireFeeValid(String hireFeeInput2) {
    try {
      int Num = Integer.parseInt(hireFeeInput2);
      if (Num < 1) {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
        return false;
      }
      return true;
    } catch (Exception e) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
      return false;
    }
  }
}
