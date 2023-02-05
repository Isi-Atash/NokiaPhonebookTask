// The PhonebookDataType class is a data structure used to store phonebook entries.
// It has two private fields, subscriber and phoneNumbers, which store the subscriber's
// name and their phone numbers,respectively.The class provides several public methods to
// interact with the stored data:

// GetSubscriber() returns the subscriber's name.
// GetPhoneNumbers() returns an array of strings that represent the subscriber's phone numbers.
// AddPhoneNumber(String phoneNumber) increases the size of the phoneNumbers array 
//and adds the new phone number to the end of the array.
// DeletePhoneNumber(String phoneNumber) deletes the given phone number from the phoneNumbers array.

// The class has a constructor that takes a subscriber's name and a phone number as parameters 
// and creates a new PhonebookDataType object with the given subscriber and phone number.
// The class uses an array of strings to store the phone numbers 
//as one subscriber can have multiple phone numbers. 
// The AddPhoneNumber method uses the ArrayUtils.increaseArraySize method 
//to increase the size of the phoneNumbers array dynamically when adding a new phone number. 
// The DeletePhoneNumber method implements the logic 
//for deleting a phone number from the phoneNumbers array.

class PhonebookDataType {

    private String subscriber;
    private String[] phoneNumbers;

    public PhonebookDataType(String subscriber, String phoneNumber) {
        this.subscriber = subscriber;
        this.phoneNumbers = new String[1];
        this.phoneNumbers[0] = phoneNumber;
    }

    public String GetSubscriber() {
        return this.subscriber;
    }

    public String[] GetPhoneNumbers() {
        return this.phoneNumbers;
    }

    public void AddPhoneNumber(String phoneNumber) {

        this.phoneNumbers = ArrayUtils.increaseArraySize(this.phoneNumbers, this.phoneNumbers.length + 1);
        this.phoneNumbers[this.phoneNumbers.length - 1] = phoneNumber;
    }

    // if we will ever need to only delete one phone number and not the whole
    // subscriber
    public void DeletePhoneNumber(String phoneNumber) {
        int originalArrayLength = this.phoneNumbers.length;
        String[] newPhoneNumbers = new String[originalArrayLength - 1];
        int newPhoneNumbersIndex = 0;
        for (int i = 0; i < originalArrayLength; i++) {
            if (this.phoneNumbers[i] != phoneNumber) {
                newPhoneNumbers[newPhoneNumbersIndex] = this.phoneNumbers[i];
                newPhoneNumbersIndex++;
            }
        }
        this.phoneNumbers = newPhoneNumbers;
    }

}