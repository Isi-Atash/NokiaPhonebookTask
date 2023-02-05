// Phonebook Class Documentation
// The Phonebook class is a Java class that provides basic functionalities for a phonebook application. 
//It allows adding, searching, and deleting entries from a phonebook. 
//Each phonebook entry consists of a subscriber name and one or more phone numbers.

// Class Members
// PhonebookDataType[] phonebookEntries
// An array of PhonebookDataType objects representing phonebook entries.

// Pattern phoneNumberPattern
// A pattern object to validate the format of phone numbers.

// Constructor
// Phonebook(String phoneNumberRegex)
// This constructor initializes an instance of the Phonebook class with an empty phonebook
// entries array and sets a pattern for phone number validation. 
//The phoneNumberRegex parameter specifies the pattern to use for phone number validation.

// Methods
// boolean isPhoneNumberValid(String phoneNumber)
// This method validates the format of a phone number by checking if it matches the phone number pattern set in the constructor.

// boolean isPhoneNumberExists(String phoneNumber)
// This method checks if the given phone number exists in the phonebook entries.

// boolean isSubscriberExists(String subscriber)
// This method checks if the given subscriber name exists in the phonebook entries.

// void addEntry(String subscriber, String phoneNumber) throws Exception
// This method adds a new phonebook entry with the given subscriber name and phone number. 
//It validates the phone number format and throws an exception if it's invalid. 
//If the phone number already exists in the phonebook, 
//it throws an exception. If the subscriber already exists in the phonebook, it adds the phone number to the existing subscriber's entry.

// String searchPhonebookByPhoneNumber(String phoneNumber)
// This method searches the phonebook entries for a subscriber with the given phone number and returns the subscriber's name. 
//If the phone number is not found in the phonebook, it returns null.

// void deleteEntry(String subscriber, String phoneNumber) throws Exception
// This method deletes a phonebook entry with the given subscriber name and phone number. 
//If the phone number does not exist in the phonebook, it throws an exception. If the subscriber does not exist in the phonebook, it throws an exception.

// PhonebookDataType[] deleteArrayElement(PhonebookDataType[] entries, int index)
// This method deletes an element from an array of PhonebookDataType objects. 
//The index parameter specifies the index of the element to be deleted. If the index is out of range, it throws an ArrayIndexOutOfBoundsException.

import java.util.regex.Pattern;
import java.util.Arrays;

public class Phonebook {

    PhonebookDataType[] phonebookEntries;
    Pattern phoneNumberPattern;

    public Phonebook(String phoneNumberRegex) {
        this.phonebookEntries = new PhonebookDataType[0];
        this.phoneNumberPattern = Pattern.compile(phoneNumberRegex);
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumberPattern.matcher(phoneNumber).matches();
    }

    public boolean isPhoneNumberExists(String phoneNumber) {
        for (PhonebookDataType entry : phonebookEntries) {
            String[] phoneNumbers = entry.GetPhoneNumbers();
            for (String number : phoneNumbers) {
                if (number == phoneNumber) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isSubscriberExists(String subscriber) {
        for (PhonebookDataType entry : phonebookEntries) {
            if (entry.GetSubscriber() == subscriber) {
                return true;
            }
        }
        return false;
    }

    public void addEntry(String subscriber, String phoneNumber) throws Exception {
        if (!isPhoneNumberValid(phoneNumber)) {
            throw new Exception("Invalid phone number format");
        }
        if (isPhoneNumberExists(phoneNumber)) {
            throw new Exception("Phone number already exists");
        }

        if (!isSubscriberExists(subscriber)) {
            phonebookEntries = ArrayUtils.increaseArraySize(phonebookEntries, phonebookEntries.length + 1);
            phonebookEntries[phonebookEntries.length - 1] = new PhonebookDataType(subscriber, phoneNumber);
        } else {
            for (PhonebookDataType entry : phonebookEntries) {
                if (entry.GetSubscriber() == subscriber) {
                    entry.AddPhoneNumber(phoneNumber);
                }
            }
        }
    }

    public String searchPhonebookByPhoneNumber(String phoneNumber) {
        for (PhonebookDataType entry : phonebookEntries) {
            String[] phoneNumbers = entry.GetPhoneNumbers();
            for (String number : phoneNumbers) {
                if (number == phoneNumber) {
                    return entry.GetSubscriber();
                }
            }
        }
        return null;
    }

    public void deleteEntry(String subscriber, String phoneNumber) throws Exception {
        if (!isPhoneNumberExists(phoneNumber)) {
            throw new Exception("Phone number does not exist");
        }
        if (!isSubscriberExists(subscriber)) {
            throw new Exception("Subscriber does not exist");
        }

        // delete from phonebookEntries with the given subscriber
        for (PhonebookDataType entry : phonebookEntries) {
            if (entry.GetSubscriber() == subscriber) {
                int index = Arrays.asList(phonebookEntries).indexOf(entry);
                phonebookEntries = deleteArrayElement(phonebookEntries, index);
            }
        }
    }

    public PhonebookDataType[] deleteArrayElement(PhonebookDataType[] entries, int index) {
        int length = entries.length;
        if (index < 0 || index >= length) {
            throw new ArrayIndexOutOfBoundsException("Index out of range");
        }

        PhonebookDataType[] newEntries = new PhonebookDataType[length - 1];
        for (int i = 0, j = 0; i < length; i++) {
            if (i != index) {
                newEntries[j++] = entries[i];
            }
        }
        return newEntries;
    }

}
