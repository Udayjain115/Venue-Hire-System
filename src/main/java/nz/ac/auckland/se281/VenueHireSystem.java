package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  private ArrayList<Venue> venueList = new ArrayList<Venue>();

  public VenueHireSystem() {}

  public void printVenues() {
    if (venueList.size() == 0) {
      MessageCli.NO_VENUES.printMessage();
    } else if (venueList.size() == 1) {
      MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
      MessageCli.VENUE_ENTRY.printMessage(
          venueList.get(0).getVenueName(),
          venueList.get(0).getVenueCode(),
          venueList.get(0).getCapacityInput(),
          venueList.get(0).getHireFeeInput());
    }
    // Prints out Each Venue in venueList, converts the amount of venues
    // to a word representation
    else if ((venueList.size() > 1) && (venueList.size() < 10)) {
      String numWord = convertStringtoNum(venueList.size());
      MessageCli.NUMBER_VENUES.printMessage("are", numWord, "s");
      for (int i = 0; i < venueList.size(); i++) {
        MessageCli.VENUE_ENTRY.printMessage(
            venueList.get(i).getVenueName(),
            venueList.get(i).getVenueCode(),
            venueList.get(i).getCapacityInput(),
            venueList.get(i).getHireFeeInput());
      }
    } else {
      MessageCli.NUMBER_VENUES.printMessage("are", String.valueOf(venueList.size()), "s");
      for (int i = 0; i < venueList.size(); i++) {
        MessageCli.VENUE_ENTRY.printMessage(
            venueList.get(i).getVenueName(),
            venueList.get(i).getVenueCode(),
            venueList.get(i).getCapacityInput(),
            venueList.get(i).getHireFeeInput());
      }
    }
  }

  // Uses a switch case, to turn and integer into a string representation
  public String convertStringtoNum(int number) {
    switch (number) {
      case 1:
        return "one";
      case 2:
        return "two";
      case 3:
        return "three";
      case 4:
        return "four";
      case 5:
        return "five";
      case 6:
        return "six";
      case 7:
        return "seven";
      case 8:
        return "eight";
      case 9:
        return "nine";
      default: // Should never be used because only called when venueList.size() is larger than one
        // and smaller than 10
        return "many";
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {

    Venue venue =
        new Venue(
            venueName,
            venueCode,
            capacityInput,
            hireFeeInput); // Creates a seperate class for venues

    // boolean values to check for validity of all variables
    boolean validVenueName = venue.venueNameValid();
    boolean validVenueCode = true;
    boolean validCapacityInput = venue.isCapacityValid(capacityInput);
    boolean validHireFeeInput = venue.isHireFeeValid(hireFeeInput);
    // empty strings that are only set to non-empty strings and used when validVenueCode is false
    String repeatCode = "";
    String repeatVenueCodeName = "";

    // Checks if a venue code is used in any other venue in the array list
    for (Venue code : venueList) {
      if (code.getVenueCode().equals(venueCode)) {
        validVenueCode = false;
        repeatCode = code.getVenueCode();
        repeatVenueCodeName = code.getVenueName();
        break;

      } else {
        validVenueCode = true;
      }
    }
    // checks if all the validity checkers are valid and only then adds the venue to the arraylist
    if ((validVenueName && validVenueCode && validCapacityInput && validHireFeeInput) == true) {
      venueList.add(venue);
      MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
      // if venue name is empty sends message to user
    } else if (!validVenueName) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
    } else if (!validVenueCode) {
      // if venue code is already in use tells the user
      MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(repeatCode, repeatVenueCodeName);
    }
  }

  public void setSystemDate(String dateInput) {
    // TODO implement this method
  }

  public void printSystemDate() {
    // TODO implement this method
  }

  public void makeBooking(String[] options) {
    // TODO implement this method
  }

  public void printBookings(String venueCode) {
    // TODO implement this method
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // TODO implement this method
  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method
  }
}
