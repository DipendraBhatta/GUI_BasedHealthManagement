import java.io.Serializable;

/**
 * Abstract class representing a medical facility.
 * This class provides a base for different types of medical facilities,
 * such as hospitals and clinics.
 */
public abstract class MedicalFacility implements Serializable {
    private String name;

    /**
     * Constructor for the MedicalFacility class.
     * @param name The name of the medical facility.
     */
    public MedicalFacility(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the medical facility.
     * @return String The name of the medical facility.
     */
    public String getName() {
        return name;
    }

    /**
     * Abstract method to simulate a patient visit to the medical facility.
     * @param patient The patient visiting the medical facility.
     * @return String A message describing the visit.
     */
    public abstract String visit(Patient patient);

    @Override
    public String toString() {
        return name; // Display name in combo boxes
    }
}
