
/**
 * Represents a clinic, a type of medical facility.
 * This class extends MedicalFacility and includes a consultation fee and gap percentage.
 */
public class Clinic extends MedicalFacility {
    private double fee;
    private double gapPercent;

    /**
     * Constructor for the Clinic class.
     * @param name The name of the clinic.
     * @param fee The consultation fee.
     * @param gapPercent The gap percentage for non-private patients.
     */
    public Clinic(String name, double fee, double gapPercent) {
        super(name);
        this.fee = fee;
        this.gapPercent = gapPercent;
    }

    /**
     * Gets the consultation fee.
     * @return double The consultation fee.
     */
    public double getFee() {
        return fee;
    }

    /**
     * Sets the consultation fee.
     * @param fee The new consultation fee.
     */
    public void setFee(double fee) {
        this.fee = fee;
    }

    /**
     * Gets the gap percentage.
     * @return double The gap percentage.
     */
    public double getGapPercent() {
        return gapPercent;
    }

    /**
     * Sets the gap percentage.
     * @param gapPercent The new gap percentage.
     */
    public void setGapPercent(double gapPercent) {
        this.gapPercent = gapPercent;
    }

    /**
     * Simulates a patient visit to the clinic.
     * @param patient The patient visiting the clinic.
     * @return String A message describing the consultation and cost.
     */
    @Override
    public String visit(Patient patient) {
        double cost;
        if (patient.isPrivate()) {
            cost = fee;
        } else {
            cost = fee * gapPercent;
        }
        patient.setBalance(patient.getBalance() + cost);
        return patient.getName() + " receives a consultation at " + getName() +
                ". Cost: $" + cost + ". New balance: $" + patient.getBalance();
    }

    @Override
    public String toString() {
        return "Clinic{" +
                "name='" + getName() + '\'' +
                ", fee=" + fee +
                ", gapPercent=" + gapPercent +
                '}';
    }
}
