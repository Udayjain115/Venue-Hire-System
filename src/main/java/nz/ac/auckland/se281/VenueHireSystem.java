package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Collections;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  private ArrayList<Venue> venueList = new ArrayList<Venue>();
  private ArrayList<Bookings> bookingList = new ArrayList<Bookings>();
  private ArrayList<Services> serviceList = new ArrayList<Services>();
  private String systemDate;

  public VenueHireSystem() {}

  public void printVenues() {
    // If there are no venues in the venueList then prints out a message to the user saying that
    // there are no venues
    if (venueList.size() == 0) {
      MessageCli.NO_VENUES.printMessage();

    } else if (venueList.size() == 1) {
      // If there is only one venue in the venueList then prints out the venue
      MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
      if (systemDate != null) {
        // If systemDate not set then prints out the venue without the next available date
        MessageCli.VENUE_ENTRY.printMessage(
            venueList.get(0).getVenueName(),
            venueList.get(0).getVenueCode(),
            venueList.get(0).getCapacityInput(),
            venueList.get(0).getHireFeeInput(),
            nextAvailableDate(venueList.get(0).getVenueCode()));
      } else {
        // If systemDate is set then prints out the venue with the next available date
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
          // If systemDate not set then prints out the venue without the next available date
          MessageCli.VENUE_ENTRY.printMessage(
              venueList.get(i).getVenueName(),
              venueList.get(i).getVenueCode(),
              venueList.get(i).getCapacityInput(),
              venueList.get(i).getHireFeeInput(),
              nextAvailableDate(venueList.get(i).getVenueCode()));
        } else {
          // If systemDate is set then prints out the venue with the next available date
          MessageCli.VENUE_ENTRY.printMessage(
              venueList.get(i).getVenueName(),
              venueList.get(i).getVenueCode(),
              venueList.get(i).getCapacityInput(),
              venueList.get(i).getHireFeeInput());
        }
      }
    } else {
      // If there are more than 10 venues then prints out the amount of venues in a numerical
      // represenatation
      MessageCli.NUMBER_VENUES.printMessage("are", String.valueOf(venueList.size()), "s");
      for (int i = 0; i < venueList.size(); i++) {
        if (systemDate != null) {
          // If systemDate not set then prints out the venue without the next available date
          MessageCli.VENUE_ENTRY.printMessage(
              venueList.get(i).getVenueName(),
              venueList.get(i).getVenueCode(),
              venueList.get(i).getCapacityInput(),
              venueList.get(i).getHireFeeInput(),
              nextAvailableDate(venueList.get(i).getVenueCode()));
        } else {
          // If systemDate is set then prints out the venue with the next available date
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
    // If the systemDate has not been set prints error message

    if (systemDate == null) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return;
    }

    // Checks Whether the booking date is in the past
    int[] bookingDate = convertDateToInt(options[1]);
    int[] currentDate = convertDateToInt(systemDate);
    String bookingName = "";
    String bookingCost = "";
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
        bookingCost = code.getHireFeeInput();
        bookingCapacity = Integer.parseInt(code.getCapacityInput());
        break;
      }
    }
    boolean bookingDateAlreadyInUse = false;
    for (Bookings booking : bookingList) {
      // Checks if the booking date is already in use
      if (options[0].equals(booking.getVenueCode()) && options[1].equals(booking.getDate())) {
        bookingDateAlreadyInUse = true;
        break;
      }
    }
    // If the attendees are less than 25% of the capacity adjusts it so that the new attendees are
    // 25% of the capacity
    if (Integer.parseInt(options[3]) < (0.25 * bookingCapacity)) {
      String newAttendeeNumber = String.valueOf(0.25 * bookingCapacity);
      String numWihoutDecimal = String.valueOf(newAttendeeNumber).split("\\.")[0];
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          options[3], numWihoutDecimal, String.valueOf(bookingCapacity));
      options[3] = numWihoutDecimal;
    }
    // If there are more atendees than the capacity adjusts it so that the new number is the
    // capacity
    if (Integer.parseInt(options[3]) > (bookingCapacity)) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          options[3], String.valueOf(bookingCapacity), String.valueOf(bookingCapacity));
      options[3] = String.valueOf(bookingCapacity);
    }

    if (this.systemDate == null) {
      // If the system date is not set then the booking is not made
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return;
    } else if (venueList.size() == 0) {
      // If there are no venues then the booking is not made
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();

    } else if (!doesCodeExist) {
      // If the venue code is not found then the booking is not made
      MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(options[0]);
    } else if (!isDateValid) {
      // If the booking date is in the past then the booking is not made
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(options[1], systemDate);
    } else if (bookingDateAlreadyInUse) {
      // If the booking date is already in use then the booking is not made
      MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(bookingName, options[1]);
    } else {
      // If all the checks are passed then the booking is made
      String bookingRef = BookingReferenceGenerator.generateBookingReference();
      Bookings booking =
          new Bookings(
              options[0],
              options[1],
              options[2],
              options[3],
              bookingRef,
              bookingName,
              systemDate,
              bookingCost);
      bookingList.add(booking);
      MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
          bookingRef, bookingName, options[1], options[3]);
    }
  }

  //
  public void printBookings(String venueCode) {
    // Prints out all the bookings for a specific venue's code
    boolean venueExists = false;
    boolean bookingExists = false;
    String venueName = "";
    for (Bookings bookings : bookingList) {
      if (bookings.getVenueCode().equals(venueCode)) {
        // Checks to see if the the venue has been booked
        bookingExists = true;
        venueName = bookings.getName();
      }
    }
    if (bookingExists == true) {
      MessageCli.PRINT_BOOKINGS_HEADER.printMessage(venueName);
      for (Bookings bookings : bookingList) {
        if (bookings.getVenueCode().equals(venueCode)) {
          // Looks through all the bookings and if the venue code provided matches the venue code of
          // the booking then prints out the booking
          MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(
              bookings.getBookingReference(), bookings.getDate());
        }
      }
    }
    if (bookingExists == false) {
      if (venueList.size() == 0) {
        // If there are no venues then prints out a message to the user saying that there are no
        // venues
        MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
      }
      for (Venue venue : venueList) {
        if (venue.getVenueCode().equals(venueCode)) {
          // If the venue code is not found in the booking list then finds the venue name and prints
          // a statement telling user that that there are no bookings for that venue
          MessageCli.PRINT_BOOKINGS_HEADER.printMessage(venue.getVenueName());
          MessageCli.PRINT_BOOKINGS_NONE.printMessage(venue.getVenueName());
        }
        for (Venue venueExistCheck : venueList) {
          if (venueExistCheck.getVenueCode().equals(venueCode)) {
            venueExists = true;
          }
        }
        if (venueExists == false) {
          // In the case that there is no venue associated with the venue code
          MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
        }
      }
    }
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    boolean bookingReferenceExists = false;
    String cateringTypeName = cateringType.getName();
    int cateringTypeCost = cateringType.getCostPerPerson();

    Catering catering = new Catering(bookingReference, cateringTypeName, cateringTypeCost);
    if (bookingList.size() == 0) {
      // If there is no booking in the booking list then the service is not added and a message is
      // printed to the user saying that the booking was not found
      catering.serviceBookingNotFound(bookingReference);
      return;
    }
    for (Bookings booking : bookingList) {

      if (booking.getBookingReference().equals(bookingReference)) {
        bookingReferenceExists = true;
      }
      if (bookingReferenceExists) {
        // If it finds the correct booking reference adds the service to the service list

        catering.addService(bookingReference);
        serviceList.add(catering);
        return;
      } else {
        // If the booking reference is not found then the service is not added and a message is sent
        // to the user saying the booking was not found
        catering.serviceBookingNotFound(bookingReference);
      }
    }
  }

  public void addServiceMusic(String bookingReference) {

    boolean bookingReferenceExists = false;
    Music music = new Music(bookingReference);
    // If there are no bookings then the service is not added and a message is printed to the user
    // accordingly
    if (bookingList.size() == 0) {
      music.serviceBookingNotFound(bookingReference);
      return;
    }
    for (Bookings booking : bookingList) {
      // Checks for the booking reference in the booking list
      if (booking.getBookingReference().equals(bookingReference)) {
        bookingReferenceExists = true;
      }
      if (bookingReferenceExists) {
        // If the booking reference is found then the service is added to the service list
        music.addService(bookingReference);
        serviceList.add(music);
        return;
      } else {
        music.serviceBookingNotFound(bookingReference);
      }
    }
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {

    int floralTypeCost = floralType.getCost();
    boolean bookingReferenceExists = false;
    String floralTypeName = floralType.getName();

    FloralServices floral = new FloralServices(bookingReference, floralTypeName, floralTypeCost);
    // If there are no bookings then the service is not added and a message is printed to the user
    // accordingly
    if (bookingList.size() == 0) {
      floral.serviceBookingNotFound(bookingReference);
      return;
    }
    for (Bookings booking : bookingList) {

      if (booking.getBookingReference().equals(bookingReference)) {
        bookingReferenceExists = true;
      }
      if (bookingReferenceExists) {
        // If the booking reference is found then the service is added to the service list

        floral.addService(bookingReference);
        serviceList.add(floral);
        return;
      } else {
        floral.serviceBookingNotFound(bookingReference);
      }
    }
  }

  public void viewInvoice(String bookingReference) {
    String cateringType = "";
    int cateringCost = 0;
    int musicCost = 0;
    String floralType = "";
    int floralCost = 0;
    int totalCost = 0;
    boolean bookingReferenceExists = false;
    // If there are no bookings then returns that no invoices can be printed
    if (bookingList.size() == 0) {
      MessageCli.VIEW_INVOICE_BOOKING_NOT_FOUND.printMessage(bookingReference);
      return;
    }
    for (Bookings booking : bookingList) {
      // Looks through all the bookings and if the booking reference is found then it sets the
      // boolean to true
      if (booking.getBookingReference().equals(bookingReference)) {
        bookingReferenceExists = true;
      }
      if (bookingReferenceExists) {
        for (Services service : serviceList) {
          if (service.getBookingReference().equals(bookingReference)) {
            // If the booking reference is found then it looks through the service list and if the
            // booking references match then it finds out what type of service it is and sets the
            // variables to the correct values
            if (service instanceof Catering) {
              Catering catering = (Catering) service;
              cateringType = catering.getCateringType();
              cateringCost = catering.getCost();
            } else if (service instanceof Music) {
              Music music = (Music) service;
              musicCost = music.getCost();
            } else if (service instanceof FloralServices) {
              FloralServices floral = (FloralServices) service;
              floralType = floral.getFloralType();
              floralCost = floral.getCost();
            }
          }
        }
        // Calculates the total cost
        totalCost =
            floralCost
                + musicCost
                + (cateringCost * Integer.parseInt(booking.getNumberOfGuests()))
                + Integer.parseInt(booking.getBookingCost());
        // Prints out the Invoice
        MessageCli.INVOICE_CONTENT_TOP_HALF.printMessage(
            booking.getBookingReference(),
            booking.getCustomerEmail(),
            booking.getBookingDate(),
            booking.getDate(),
            booking.getNumberOfGuests(),
            booking.getName());
        MessageCli.INVOICE_CONTENT_VENUE_FEE.printMessage(booking.getBookingCost());
        MessageCli.INVOICE_CONTENT_CATERING_ENTRY.printMessage(
            cateringType,
            String.valueOf(cateringCost * Integer.parseInt(booking.getNumberOfGuests())));
        MessageCli.INVOICE_CONTENT_MUSIC_ENTRY.printMessage(String.valueOf(musicCost));
        MessageCli.INVOICE_CONTENT_FLORAL_ENTRY.printMessage(
            floralType, String.valueOf(floralCost));
        MessageCli.INVOICE_CONTENT_BOTTOM_HALF.printMessage(String.valueOf(totalCost));
        return;
      } else {
        MessageCli.VIEW_INVOICE_BOOKING_NOT_FOUND.printMessage(bookingReference);
      }
    }
  }
}
