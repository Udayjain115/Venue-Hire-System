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
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {

    Venue venue = new Venue(venueName, venueCode, capacityInput, hireFeeInput);

    boolean validVenueName = venue.venueNameValid();
    boolean validVenueCode = true;
    String repeatCode = "";
    String repeatVenueCodeName = "";

    for (Venue code : venueList) {
      if (code.getVenueCode().equals(venueCode)) {
        validVenueCode = false;
        repeatCode = code.getVenueCode();
        repeatVenueCodeName = code.getVenueName();

      } else {
        validVenueCode = true;
      }
    }
    if ((validVenueName && validVenueCode) == true) {
      venueList.add(venue);
      MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);

    } else if (!validVenueName) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
    } else if (!validVenueCode) {

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
