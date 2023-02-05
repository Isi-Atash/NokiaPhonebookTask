import java.util.HashMap;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class PhonebookHashMap {
    // I am using HashMaps to store phone numbers and subscribers
    // HashMap ensures that there are no duplicates
    // And that the search is fast.
    // O(1) time complexity for both searching by phone number and by subscriber

    private HashMap<String, String> phoneNumberToSubscriber;
    private HashMap<String, ArrayList<String>> subscriberToPhoneNumbers;
    private Pattern phoneNumberPattern;

    public PhonebookHashMap(String phoneNumberRegex) {
        phoneNumberToSubscriber = new HashMap<>();
        subscriberToPhoneNumbers = new HashMap<>();
        phoneNumberPattern = Pattern.compile(phoneNumberRegex);
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumberPattern.matcher(phoneNumber).matches();
    }

    private boolean isPhoneNumberExists(String phoneNumber) {
        return phoneNumberToSubscriber.containsKey(phoneNumber);
    }

    private boolean isSubscriberExists(String subscriber) {
        return subscriberToPhoneNumbers.containsKey(subscriber);
    }

    public void addEntry(String subscriber, String phoneNumber) throws Exception {
        if (!isPhoneNumberValid(phoneNumber)) {
            throw new Exception("Invalid phone number format");
        }
        if (isPhoneNumberExists(phoneNumber)) {
            throw new Exception("Phone number already exists");
        }

        phoneNumberToSubscriber.put(phoneNumber, subscriber);

        if (!isSubscriberExists(subscriber)) {
            subscriberToPhoneNumbers.put(subscriber, new ArrayList<>());
        }

        subscriberToPhoneNumbers.get(subscriber).add(phoneNumber);
    }

    public void deleteEntry(String subscriber, String phoneNumber) throws Exception {
        if (!isPhoneNumberExists(phoneNumber)) {
            throw new Exception("Phone number does not exist");
        }
        if (!isSubscriberExists(subscriber)) {
            throw new Exception("Subscriber does not exist");
        }

        phoneNumberToSubscriber.remove(phoneNumber);

        // I do not know if I needed to delete subscriber and all of his phone numbers,
        // or only to delete phone number from subscriber's list of phone numbers
        // So here is both options

        // This option deletes only phone number from subscriber's list of phone numbers
        // subscriberToPhoneNumbers.get(subscriber).remove(phoneNumber);

        // This option deletes subscriber and all of his phone numbers
        subscriberToPhoneNumbers.remove(subscriber);
    }


    //Provide search by phone number function (returns a string or throws an exception).
    public String searchEntryByPhoneNumber(String phoneNumber) throws Exception {
        if (!isPhoneNumberExists(phoneNumber)) {
            throw new Exception("Phone number does not exist");
        }

        return phoneNumberToSubscriber.get(phoneNumber);
    }

    //Provide search by subscriber function (returns a list of phone numbers).
    public ArrayList<String> searchEntryBySubscriber(String subscriber) throws Exception {
        if (!isSubscriberExists(subscriber)) {
            throw new Exception("Subscriber does not exist");
        }

        return subscriberToPhoneNumbers.get(subscriber);
    }
}