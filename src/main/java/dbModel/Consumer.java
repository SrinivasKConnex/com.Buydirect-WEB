package dbModel;

public class Consumer {
    private String scoringId;
    private Passport passport;
    private Address address;
    private String dob;
    private String vToken;
    private String smartAddress;
    private DriversLicense driversLicense;
    private Name name;
    private Contact contact;

    // Constructor
    public Consumer() {}

    // Getters and Setters
    public String getScoringId() {
        return scoringId;
    }

    public void setScoringId(String scoringId) {
        this.scoringId = scoringId;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getvToken() {
        return vToken;
    }

    public void setvToken(String vToken) {
        this.vToken = vToken;
    }

    public String getSmartAddress() {
        return smartAddress;
    }

    public void setSmartAddress(String smartAddress) {
        this.smartAddress = smartAddress;
    }

    public DriversLicense getDriversLicense() {
        return driversLicense;
    }

    public void setDriversLicense(DriversLicense state) {
        this.driversLicense = state;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    // Inner classes representing the nested objects
    public static class Passport {
        private String passKey;
        private String passPhrase;

        // Getters and Setters
        public String getPassKey() {
            return passKey;
        }

        public void setPassKey(String passKey) {
            this.passKey = passKey;
        }

        public String getPassPhrase() {
            return passPhrase;
        }

        public void setPassPhrase(String passPhrase) {
            this.passPhrase = passPhrase;
        }
    }

    public static class Address {
        private String streetName;
        private String zip;
        private String addressLine2;
        private String poBox;
        private addressState state;
        private String apartmentNum;
        private String streetNumber;
        private String address1;
        private String city;

        // Getters and Setters
        public String getStreetName() {
            return streetName;
        }

        public void setStreetName(String streetName) {
            this.streetName = streetName;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public String getAddressLine2() {
            return addressLine2;
        }

        public void setAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
        }

        public String getPoBox() {
            return poBox;
        }

        public void setPoBox(String poBox) {
            this.poBox = poBox;
        }

        public addressState getState() {
            return state;
        }

        public void setState(addressState state) {
            this.state = state;
        }

        public String getApartmentNum() {
            return apartmentNum;
        }

        public void setApartmentNum(String apartmentNum) {
            this.apartmentNum = apartmentNum;
        }

        public String getStreetNumber() {
            return streetNumber;
        }

        public void setStreetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
        }

        public String getAddress1() {
            return address1;
        }

        public void setAddress1(String address1) {
            this.address1 = address1;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }

    public static class addressState {
        private String stateAbbreviation;
        private String stateName;

        // Getters and Setters
        public String getStateAbbreviation() {
            return stateAbbreviation;
        }

        public void setStateAbbreviation(String stateAbbreviation) {
            this.stateAbbreviation = stateAbbreviation;
        }

        public String getaddressStateName() {
            return stateName;
        }

        public void setaddressStateName(String stateName) {
            this.stateName = stateName;
        }
    }
    public static class State {
        private String stateAbbreviation;
        private String stateName;

        // Getters and Setters
        public String getStateAbbreviation() {
            return stateAbbreviation;
        }

        public void setStateAbbreviation(String stateAbbreviation) {
            this.stateAbbreviation = stateAbbreviation;
        }

        public String getStateName() {
            return stateName;
        }

        public void setStateName(String stateName) {
            this.stateName = stateName;
        }
    }

    public static class DriversLicense {
        private State state;
        private String license;

        // Getters and Setters
        public State getState() {
            return state;
        }

        public void setState(State state) {
            this.state = state;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }
    }

    public static class Name {
        private String firstName;
        private String lastName;
        private String gender;

        // Getters and Setters
        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
    }

    public static class Contact {
        private String mobileNumber;
        private String phoneNumber;

        // Getters and Setters
        public String getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
    }

	
}