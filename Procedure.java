import java.io.Serializable;

/**
 * Represents a medical procedure.
 * This class stores procedure information such as name, description, elective status, and cost.
 */
class Procedure implements Serializable {
    private String name;
    private String description;
    private boolean isElective;
    private double cost;

    /**
     * Constructor for the Procedure class.
     * @param name The name of the procedure.
     * @param description The description of the procedure.
     * @param isElective Whether the procedure is elective.
     * @param cost The cost of the procedure.
     */
    public Procedure(String name, String description, boolean isElective, double cost) {
        this.name = name;
        this.description = description;
        this.isElective = isElective;
        this.cost = cost;
    }

    /**
     * Gets the name of the procedure.
     * @return String The name of the procedure.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the procedure.
     * @return String The description of the procedure.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the procedure is elective.
     * @return boolean True if the procedure is elective, false otherwise.
     */
    public boolean isElective() {
        return isElective;
    }

    /**
     * Gets the cost of the procedure.
     * @return double The cost of the procedure.
     */
    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Procedure{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isElective=" + isElective +
                ", cost=" + cost +
                '}';
    }
}
