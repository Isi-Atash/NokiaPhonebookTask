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