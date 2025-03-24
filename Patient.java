import java.io.Serializable;

/**
 * Class representing a patient in the healthcare system.
 * This class contains information about the patient's name, 
 * their private status, balance, and the medical facility they are currently associated with.
 */
public class Patient implements Serializable {
    private String name;             // The name of the patient
    private boolean isPrivate;       // Indicates whether the patient is a private patient
    private double balance;          // The balance of the patient, initially set to 0.0
    private MedicalFacility currentFacility; // The medical facility where the patient is currently being treated

    /**
     * Constructor for the Patient class.
     * Initializes the patient's name, private status, and sets the balance to 0.0.
     * @param name The name of the patient.
     * @param isPrivate The private status of the patient.
     */
    public Patient(String name, boolean isPrivate) {
        this.name = name;              // Set the name of the patient
        this.isPrivate = isPrivate;    // Set the private status of the patient
        this.balance = 0.0;            // Initialize balance to 0.0 for the patient
    }

    /**
     * Gets the name of the patient.
     * @return String The name of the patient.
     */
    public String getName() {
        return name; // Return the name of the patient
    }

    /**
     * Gets the private status of the patient.
     * @return boolean Whether the patient is private or not.
     */
    public boolean isPrivate() {
        return isPrivate; // Return the private status of the patient
    }

    /**
     * Gets the balance of the patient.
     * @return double The balance of the patient.
     */
    public double getBalance() {
        return balance; // Return the balance of the patient
    }

    /**
     * Sets the balance of the patient.
     * @param balance The new balance to be set for the patient.
     */
    public void setBalance(double balance) {
        this.balance = balance; // Set the balance for the patient
    }

    /**
     * Gets the medical facility where the patient is currently receiving treatment.
     * @return MedicalFacility The current medical facility.
     */
    public MedicalFacility getCurrentFacility() {
        return currentFacility; // Return the current medical facility
    }

    /**
     * Sets the medical facility for the patient.
     * @param currentFacility The new medical facility to be assigned to the patient.
     */
    public void setCurrentFacility(MedicalFacility currentFacility) {
        this.currentFacility = currentFacility; // Set the current medical facility for the patient
    }

    /**
     * Provides a string representation of the patient.
     * @return String The name of the patient.
     */
    @Override
    public String toString() {
        return name; // Return the name of the patient as the string representation
    }
}
