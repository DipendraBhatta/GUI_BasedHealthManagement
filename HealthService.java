import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the health service, including patients and medical facilities.
 * This class provides methods to add, remove, and retrieve information about
 * patients and medical facilities.
 */
public class HealthService implements Serializable {

    private String name;
    private List<Patient> patients;
    private List<MedicalFacility> medicalFacilities;
    private List<Procedure> procedures;

    /**
     * Constructor for the HealthService class.
     * @param name The name of the health service.
     */
    public HealthService(String name) {
        this.name = name;
        this.patients = new ArrayList<>();
        this.medicalFacilities = new ArrayList<>();
        this.procedures = new ArrayList<>();
    }

    /**
     * Gets the name of the health service.
     * @return String The name of the health service.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the health service.
     * @param name The new name of the health service.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the list of patients in the health service.
     * @return List The list of patients.
     */
    public List<Patient> getPatients() {
        return patients;
    }

    /**
     * Gets the list of medical facilities in the health service.
     * @return List The list of medical facilities.
     */
    public List<MedicalFacility> getMedicalFacilities() {
        return medicalFacilities;
    }

    /**
     * Gets the list of procedures in the health service.
     * @return List The list of procedures.
     */
     public List<Procedure> getProcedures() {
         return procedures;
     }

    /**
     * Adds a patient to the health service.
     * @param patient The patient to add.
     */
    public void addPatient(Patient patient) {
        this.patients.add(patient);
    }

    /**
     * Removes a patient from the health service.
     * @param patient The patient to remove.
     */
    public void removePatient(Patient patient) {
        this.patients.remove(patient);
    }

    /**
     * Adds a medical facility to the health service.
     * @param medicalFacility The medical facility to add.
     */
    public void addMedicalFacility(MedicalFacility medicalFacility) {
        this.medicalFacilities.add(medicalFacility);
    }

    /**
     * Removes a medical facility from the health service.
     * @param medicalFacility The medical facility to remove.
     */
    public void removeMedicalFacility(MedicalFacility medicalFacility) {
        this.medicalFacilities.remove(medicalFacility);
    }

    /**
     * Adds a procedure to the health service.
     * @param procedure The procedure to add.
     */
     public void addProcedure(Procedure procedure) {
         this.procedures.add(procedure);
     }

     /**
      * Removes a procedure from the health service.
      * @param procedure The procedure to remove.
      */
     public void removeProcedure(Procedure procedure) {
         this.procedures.remove(procedure);
     }

    /**
     * Saves the health service data to a file.
     * @param filename The name of the file to save the data to.
     * @throws IOException If an I/O error occurs.
     */
    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
        }
    }

    /**
     * Loads the health service data from a file.
     * @param filename The name of the file to load the data from.
     * @return HealthService The loaded health service.
     * @throws IOException If an I/O error occurs.
     * @throws ClassNotFoundException If the class of a serialized object cannot be found.
     */
    public static HealthService loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (HealthService) ois.readObject();
        }
    }
}
