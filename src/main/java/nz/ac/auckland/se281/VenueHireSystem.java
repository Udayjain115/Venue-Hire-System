package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  private ArrayList<Venue> venueList = new ArrayList<Venue>();
  private ArrayList<Bookings> bookingList = new ArrayList<Bookings>();
  private String systemDate;

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
        break; // Break statement so that loops is exited if same venue code found

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
    this.systemDate = dateInput;
    MessageCli.DATE_SET.printMessage(systemDate);
  }

  public void printSystemDate() {
    if (systemDate == null) {
      MessageCli.CURRENT_DATE.printMessage("not set");
    } else {
      MessageCli.CURRENT_DATE.printMessage(systemDate);
    }
  }

  public int[] convertDateToInt(String date) {
    String[] dateArray = date.split("/");
    int day = Integer.parseInt(dateArray[0]);
    int month = Integer.parseInt(dateArray[1]);
    int year = Integer.parseInt(dateArray[2]);
    int[] dateInt = {day, month, year};
    return dateInt;
  }

  public void makeBooking(String[] options) {

    // Checks Whether the booking date is in the past
    int[] bookingDate = convertDateToInt(options[1]);
    int[] currentDate = convertDateToInt(systemDate);
    String bookingName = "";
    boolean isDateValid = false;
    if (bookingDate[2] > currentDate[2]) {
      isDateValid = true;
    } else if (bookingDate[2] == currentDate[2]) {
      if (bookingDate[1] > currentDate[1]) {
        isDateValid = true;
      } else if (bookingDate[1] == currentDate[1]) {
        if (bookingDate[0] >= currentDate[0]) {
          isDateValid = true;
        }
      }
    }

    boolean doesCodeExist = false;

    // Checks that the booking code maps to a venue code
    for (Venue code : venueList) {
      if (options[0].equals(code.getVenueCode())) {
        doesCodeExist = true;
        bookingName = code.getVenueName();
        break;
      }
    }

    boolean bookingDateAlreadyInUse = false;

    for (Bookings booking : bookingList) {
      if (options[1].equals(booking.getDate())) {
        bookingDateAlreadyInUse = true;
        break;
      }
    }

    if (systemDate == null) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
    } else if (venueList.size() == 0) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
    } else if (!doesCodeExist) {
      MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(options[0]);
    } else if (!isDateValid) {
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(options[1], systemDate);
    } else if (bookingDateAlreadyInUse) {
      MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(bookingName, options[1]);
    } else {
      String bookingRef = BookingReferenceGenerator.generateBookingReference();
      Bookings booking =
          new Bookings(options[0], options[1], options[2], options[3], bookingRef, bookingName);
      bookingList.add(booking);
      MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
          bookingRef, bookingName, options[1], options[3]);
      // Need to create booking class, which will contain an array list of type booking with all
      // this info, to be checked when printing bookings
    }
  }

  //

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
