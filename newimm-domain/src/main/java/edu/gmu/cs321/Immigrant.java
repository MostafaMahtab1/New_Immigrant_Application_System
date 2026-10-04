// Contributor - Shane Birckhead, Kavon Barr, Mostafa Mahtab
// This is the Immigrant class. It implements Hash Map data structure.
// Making this change to fulfill the Feature Branch requirement - Mostafa Mahtab

package edu.gmu.cs321;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Immigrant {

    // Registry
    private static final Map<String, Immigrant> registry = new HashMap<>();
    private static int nextId = 100000000;

    // Codes
    public static final int CODE_SUCCESS = 0;
    public static final int CODE_INVALID_FIRST_NAME = 1;
    public static final int CODE_INVALID_LAST_NAME = 2;
    public static final int CODE_INVALID_DOB = 3;
    public static final int CODE_INVALID_PHONE = 4;
    public static final int CODE_INVALID_EMAIL = 5;
    public static final int CODE_INVALID_ADDRESS_CITY_STATE = 6;
    public static final int CODE_INVALID_ZIP = 7;
    public static final int CODE_INVALID_STATUS = 8;
    public static final int CODE_DUPLICATE = 9;

    // Fields
    private String firstName;
    private String lastName;
    private String dob;
    private String city;
    private String state;
    private String zip;
    private String immigrationStatus;
    private String phone;
    private String email;

    private Immigrant alienRelative;
    private final String alienNumber;

    // Regex patterns
    private static final Pattern phonePattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
    private static final Pattern emailPattern = Pattern.compile(".+@.+\\..+");
    private static final Pattern zipPattern = Pattern.compile("\\d{5}");

    // ======== Constructor ========
    public Immigrant(String name, String dob, String state, String zip,
                     String status, String phone, String email) {

        final String[] parts = name.split(" ");
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid name");
        }
        this.firstName = parts[0];
        this.lastName = parts[1];
        this.dob = dob;
        this.state = state;
        this.zip = zip;
        this.immigrationStatus = status;
        this.phone = phone;
        this.email = email;

        this.alienNumber = generateAlienNumber();
    }

    // ======== CreateResult Class ========
    public static class CreateResult {
        public final int code;
        public final String alienNumber;

        public CreateResult(int code, String alienNumber) {
            this.code = code;
            this.alienNumber = alienNumber;
        }
    }

    // ======== Static Factory Method ========
    @SuppressWarnings("java:S107") // method signature required by unit tests
    public static CreateResult createImmigrant(String firstName, String lastName,
                                               String street, String city, String state, String zip,
                                               String dob, String status,
                                               String phone, String email) {

        // Validate inputs
        int code = validateInputs(firstName, lastName, street, city, state, zip, dob, status, phone, email);
        if (code != CODE_SUCCESS) {
            return new CreateResult(code, null);
        }

        // Duplicate check
        if (isDuplicate(firstName, lastName, dob)) {
            return new CreateResult(CODE_DUPLICATE, null);
        }

        // Create immigrant
        Immigrant im = new Immigrant(firstName + " " + lastName,
                                     dob, state, zip, status, phone, email);

        registry.put(im.alienNumber, im);

        return new CreateResult(CODE_SUCCESS, im.alienNumber);
    }

    // ======== Validation Helper ========
     @SuppressWarnings("java:S107")
    private static int validateInputs(String firstName, String lastName,
                                      String street, String city, String state, String zip,
                                      String dob, String status,
                                      String phone, String email) {

        if (firstName == null || firstName.isEmpty()) {

            return CODE_INVALID_FIRST_NAME;
        }

        if (lastName == null || lastName.isEmpty() || lastName.matches(".*\\d.*")) {
            return CODE_INVALID_LAST_NAME;
        }
        if (!dob.matches("\\d{2}/\\d{2}/\\d{4}")) {
            return CODE_INVALID_DOB;
        }
        if (!phonePattern.matcher(phone).matches()) {
            return CODE_INVALID_PHONE;
        }
        if (!emailPattern.matcher(email).matches()) {
            return CODE_INVALID_EMAIL;
        }
        if (street.isEmpty() || city.isEmpty() || !isValidState(state)) {
            return CODE_INVALID_ADDRESS_CITY_STATE;
        }

        if (!zipPattern.matcher(zip).matches()) {
            return CODE_INVALID_ZIP;
        }
        if (!isValidStatus(status)) {
            return CODE_INVALID_STATUS;
        }
        return CODE_SUCCESS;
    }

    private static boolean isValidState(String state) {
        return state.equals("VA") || state.equals("MD") || state.equals("DC");
    }

    private static boolean isValidStatus(String status) {
        return status.equals("Visitor")
                || status.equals("Green Card")
                || status.equals("Citizen");
    }

    private static boolean isDuplicate(String firstName, String lastName, String dob) {
        for (Immigrant im : registry.values()) {
            if (im.firstName.equals(firstName)
                    && im.lastName.equals(lastName)
                    && im.dob.equals(dob)) {
                return true;
            }
        }
        return false;
    }

    // ======== Registry Helpers ========
    public static Immigrant getImmigrant(String alienNumber) {
        return registry.get(alienNumber);
    }

    public static void resetRegistryForTests() {
        registry.clear();
        nextId = 100000000;
    }

    // ======== Update Methods ========
    public int updateFirstName(String name) {
        if (name == null || name.isEmpty()) {
            return CODE_INVALID_FIRST_NAME;
        }
        this.firstName = name;
        return CODE_SUCCESS;
    }

    public int updateLastName(String name) {
        //if (name == null || name.isEmpty() || name.matches(".*\\d.*")){
        //if (name != null && !name.isEmpty() && name.matches("^[^0-9]*$")) {
        if (name != null && !name.isEmpty() && name.matches("\\D*")) {
            return CODE_INVALID_LAST_NAME;
        }
        this.lastName = name;
        return CODE_SUCCESS;
    }

    public int updateDOB(String dob) {
        if (!dob.matches("\\d{2}/\\d{2}/\\d{4}")) return CODE_INVALID_DOB;
        this.dob = dob;
        return CODE_SUCCESS;
    }

    public void updatePhone(String phone) {
        if (!phonePattern.matcher(phone).matches())
            throw new IllegalArgumentException("Invalid phone");
        this.phone = phone;
    }

    public void updateEmail(String email) {
        if (!emailPattern.matcher(email).matches())
            throw new IllegalArgumentException("Invalid email");
        this.email = email;
    }

    public void updateAddressCityState(String street, String city, String st) {
        if (street.isEmpty() || city.isEmpty() || !isValidState(st))
            throw new IllegalArgumentException("Invalid address/city/state");

        this.city = city;
        this.state = st;
    }

    public void setZip(String zip) {
        if (!zipPattern.matcher(zip).matches())
            throw new IllegalArgumentException("Invalid zip");
        this.zip = zip;
    }

    public void setAlienRelative(Immigrant relative) {
        this.alienRelative = relative;
    }

    public Immigrant getAlienRelative() {
        if (this.alienRelative == null)
            throw new IllegalArgumentException("No relative set");
        return this.alienRelative;
    }

    // ======== Getters ========
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getDOB() { return dob; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getZip() { return zip; }
    public String getImmigrationStatus() { return immigrationStatus; }
    public String getName() { return firstName + " " + lastName; }

    // ======== Helper ========
    private static String generateAlienNumber() {
        return "A-" + (nextId++);
    }
}
