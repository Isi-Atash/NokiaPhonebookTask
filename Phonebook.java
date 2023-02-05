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
