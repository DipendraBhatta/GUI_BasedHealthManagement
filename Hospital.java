
import java.util.Random;

/**
 * Represents a hospital, a type of medical facility.
 * This class extends MedicalFacility and includes a probability of admission.
 */
public class Hospital extends MedicalFacility  {
    private double probAdmit;
    private Random random = new Random();

    /**
     * Constructor for the Hospital class.
     * @param name The name of the hospital.
     * @param probAdmit The probability of admission for patients.
     */
    public Hospital(String name, double probAdmit) {
        super(name);
        this.probAdmit = probAdmit;
    }

    /**
     * Gets the probability of admission for the hospital.
     * @return double The probability of admission.
     */
    public double getProbAdmit() {
        return probAdmit;
    }

    /**
     * Simulates a patient visit to the hospital.
     * @param patient The patient visiting the hospital.
     * @return String A message describing whether the patient was admitted.
     */
    @Override
    public String visit(Patient patient) {
        if (random.nextDouble() < probAdmit) {
            return patient.getName() + " is admitted to " + getName();
        } else {
            return patient.getName() + " was not admitted to " + getName();
        }
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "name='" + getName() + '\'' +
                ", probAdmit=" + probAdmit +
                '}';
    }
}
