package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Collections;
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
      if (systemDate != null) {
        MessageCli.VENUE_ENTRY.printMessage(
            venueList.get(0).getVenueName(),
            venueList.get(0).getVenueCode(),
            venueList.get(0).getCapacityInput(),
            venueList.get(0).getHireFeeInput(),
            nextAvailableDate(venueList.get(0).getVenueCode()));
      } else {
        MessageCli.VENUE_ENTRY.printMessage(
            venueList.get(0).getVenueName(),
            venueList.get(0).getVenueCode(),
            venueList.get(0).getCapacityInput(),
            venueList.get(0).getHireFeeInput());
      }
    }
    // Prints out Each Venue in venueList, converts the amount of venues
    // to a word representation
    else if ((venueList.size() > 1) && (venueList.size() < 10)) {
      String numWord = convertStringtoNum(venueList.size());
      MessageCli.NUMBER_VENUES.printMessage("are", numWord, "s");
      for (int i = 0; i < venueList.size(); i++) {
        if (systemDate != null) {
          MessageCli.VENUE_ENTRY.printMessage(
              venueList.get(i).getVenueName(),
              venueList.get(i).getVenueCode(),
              venueList.get(i).getCapacityInput(),
              venueList.get(i).getHireFeeInput(),
              nextAvailableDate(venueList.get(i).getVenueCode()));
        } else {
          MessageCli.VENUE_ENTRY.printMessage(
              venueList.get(i).getVenueName(),
              venueList.get(i).getVenueCode(),
              venueList.get(i).getCapacityInput(),
              venueList.get(i).getHireFeeInput());
        }
      }
    } else {
      MessageCli.NUMBER_VENUES.printMessage("are", String.valueOf(venueList.size()), "s");
      for (int i = 0; i < venueList.size(); i++) {
        if (systemDate != null) {
          MessageCli.VENUE_ENTRY.printMessage(
              venueList.get(i).getVenueName(),
              venueList.get(i).getVenueCode(),
              venueList.get(i).getCapacityInput(),
              venueList.get(i).getHireFeeInput(),
              nextAvailableDate(venueList.get(i).getVenueCode()));
        } else {
          MessageCli.VENUE_ENTRY.printMessage(
              venueList.get(i).getVenueName(),
              venueList.get(i).getVenueCode(),
              venueList.get(i).getCapacityInput(),
              venueList.get(i).getHireFeeInput());
        }
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

  public ArrayList<Bookings> getBookinglist() {
    return bookingList;
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

  public String getSystemDate() {
    return systemDate;
  }

  public void printSystemDate() {
    if (systemDate == null) {
      MessageCli.CURRENT_DATE.printMessage("not set");
    } else {
      MessageCli.CURRENT_DATE.printMessage(systemDate);
    }
  }

  // Method that converts a date string into an integer array
  public int[] convertDateToInt(String date) {
    String[] dateArray = date.split("/");
    int day = Integer.parseInt(dateArray[0]);
    int month = Integer.parseInt(dateArray[1]);
    int year = Integer.parseInt(dateArray[2]);
    int[] dateInt = {day, month, year};
    return dateInt;
  }

  // Method that compares two dates and returns true if date1 is bigger than date2
  public boolean dateBiggerThan(String date1, String date2) {
    int[] date1Array = convertDateToInt(date1);
    int[] date2Array = convertDateToInt(date2);
    if (date1Array[2] > date2Array[2]) {
      return true;
    } else if (date1Array[2] == date2Array[2]) {
      if (date1Array[1] > date2Array[1]) {
        return true;
      } else if (date1Array[1] == date2Array[1]) {
        if (date1Array[0] > date2Array[0]) {
          return true;
        }
      }
    }
    return false;
  }

  public String datePlusOne(String date) {

    int[] dateArray = convertDateToInt(date);
    dateArray[0] = dateArray[0] + 1;
    // Implements very Crude Date Incrementation
    if (dateArray[0] > 30) {
      dateArray[0] = 1;
      dateArray[1] = dateArray[1] + 1;
      if (dateArray[1] > 12) {
        dateArray[1] = 1;
        dateArray[2] = dateArray[2] + 1;
      }
    }

    // Ensures correct format for date is kept
    String day = dateArray[0] + "";
    String month = dateArray[1] + "";
    String year = dateArray[2] + "";
    if (dateArray[0] < 10) {
      day = "0" + dateArray[0];
    }
    if (dateArray[1] < 10) {
      month = "0" + dateArray[1];
    }
    if (dateArray[2] < 10) {
      year = "0" + dateArray[2];
    }
    return day + "/" + month + "/" + year;
  }

  public String nextAvailableDate(String code) {
    ArrayList<String> bookedDates = new ArrayList<String>();
    boolean bookingExists = false;
    String nextDate = systemDate;
    for (Bookings booking : bookingList) {
      if (booking.getVenueCode().equals(code)) {
        bookingExists = true;
      }
    }
    if (bookingExists == false) {
      return systemDate;
    }
    for (Bookings booking : bookingList) {
      if (booking.getVenueCode().equals(code)) {
        bookedDates.add(booking.getDate());
        // Adds all the dates that are booked for that venue to an arrayList
      }
    }
    Collections.sort(bookedDates);
    // Sorts all the dates in array list
    for (String date : bookedDates) {
      if (date.equals(nextDate)) {
        nextDate = datePlusOne(nextDate);
        // Goes through sorted array list and if the date is already booked then increments it by
        // one
      }
    }
    return nextDate;
  }

  public void makeBooking(String[] options) {

    if (systemDate == null) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return;
    }

    // Checks Whether the booking date is in the past
    int[] bookingDate = convertDateToInt(options[1]);
    int[] currentDate = convertDateToInt(systemDate);
    String bookingName = "";
    int bookingCapacity = 0;
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
        bookingCapacity = Integer.parseInt(code.getCapacityInput());
        break;
      }
    }
    boolean bookingDateAlreadyInUse = false;
    for (Bookings booking : bookingList) {
      if (options[0].equals(booking.getVenueCode()) && options[1].equals(booking.getDate())) {
        bookingDateAlreadyInUse = true;
        break;
      }
    }
    if (Integer.parseInt(options[3]) < (0.25 * bookingCapacity)) {
      String newAttendeeNumber = String.valueOf(0.25 * bookingCapacity);
      String numWihoutDecimal = String.valueOf(newAttendeeNumber).split("\\.")[0];
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          options[3], numWihoutDecimal, String.valueOf(bookingCapacity));
      options[3] = numWihoutDecimal;
    }
    if (Integer.parseInt(options[3]) > (bookingCapacity)) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          options[3], String.valueOf(bookingCapacity), String.valueOf(bookingCapacity));
      options[3] = String.valueOf(bookingCapacity);
    }

    if (this.systemDate == null) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return;
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
          new Bookings(
              options[0], options[1], options[2], options[3], bookingRef, bookingName, systemDate);
      bookingList.add(booking);
      MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
          bookingRef, bookingName, options[1], options[3]);
    }
  }

  //
  public void printBookings(String venueCode) {
    boolean venueExists = false;
    boolean bookingExists = false;
    String venueName = "";
    for (Bookings bookings : bookingList) {
      if (bookings.getVenueCode().equals(venueCode)) {
        bookingExists = true;
        venueName = bookings.getName();
      }
    }
    if (bookingExists == true) {
      MessageCli.PRINT_BOOKINGS_HEADER.printMessage(venueName);
      for (Bookings bookings : bookingList) {
        if (bookings.getVenueCode().equals(venueCode)) {
          MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(
              bookings.getBookingReference(), bookings.getDate());
        }
      }
    }
    if (bookingExists == false) {
      for (Venue venue : venueList) {
        if (venue.getVenueCode().equals(venueCode)) {
          MessageCli.PRINT_BOOKINGS_HEADER.printMessage(venue.getVenueName());
          MessageCli.PRINT_BOOKINGS_NONE.printMessage(venue.getVenueName());
        }
        for (Venue venueExistCheck : venueList) {
          if (venueExistCheck.getVenueCode().equals(venueCode)) {
            venueExists = true;
          }
        }
        if (venueExists == false) {
          MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
        }
      }
    }
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    boolean bookingReferenceExists = false;
    String cateringTypeName = cateringType.getName();
    Catering catering = new Catering(bookingReference, cateringTypeName);
    if (bookingList.size() == 0) {
      catering.serviceBookingNotFound(bookingReference);
      return;
    }
    for (Bookings booking : bookingList) {

      if (booking.getBookingReference().equals(bookingReference)) {
        bookingReferenceExists = true;
      }
      if (bookingReferenceExists) {

        catering.addService(bookingReference);
        return;
      } else {
        catering.serviceBookingNotFound(bookingReference);
      }
    }
  }

  public void addServiceMusic(String bookingReference) {
    boolean bookingReferenceExists = false;
    Music music = new Music(bookingReference);
    if (bookingList.size() == 0) {
      music.serviceBookingNotFound(bookingReference);
      return;
    }
    for (Bookings booking : bookingList) {

      if (booking.getBookingReference().equals(bookingReference)) {
        bookingReferenceExists = true;
      }
      if (bookingReferenceExists) {

        music.addService(bookingReference);
        return;
      } else {
        music.serviceBookingNotFound(bookingReference);
      }
    }
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    boolean bookingReferenceExists = false;
    String floralTypeName = floralType.getName();
    Floral floral = new Floral(bookingReference, floralTypeName);
    if (bookingList.size() == 0) {
      floral.serviceBookingNotFound(bookingReference);
      return;
    }
    for (Bookings booking : bookingList) {

      if (booking.getBookingReference().equals(bookingReference)) {
        bookingReferenceExists = true;
      }
      if (bookingReferenceExists) {

        floral.addService(bookingReference);
        return;
      } else {
        floral.serviceBookingNotFound(bookingReference);
      }
    }
  }

  public void viewInvoice(String bookingReference) {
    boolean bookingReferenceExists = false;
    if (bookingList.size() == 0) {
      MessageCli.VIEW_INVOICE_BOOKING_NOT_FOUND.printMessage(bookingReference);
      return;
    }
    for (Bookings booking : bookingList) {
      if (booking.getBookingReference().equals(bookingReference)) {
        bookingReferenceExists = true;
      }
      if (bookingReferenceExists) {
        MessageCli.INVOICE_CONTENT_TOP_HALF.printMessage(
            booking.getBookingReference(),
            booking.getCustomerEmail(),
            booking.getBookingDate(),
            booking.getDate(),
            booking.getNumberOfGuests(),
            booking.getName());
        return;
      } else {
        MessageCli.VIEW_INVOICE_BOOKING_NOT_FOUND.printMessage(bookingReference);
      }
    }
  }
}
