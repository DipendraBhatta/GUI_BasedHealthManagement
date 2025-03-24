import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import java.util.Collections;
import java.util.List;


/**
 * Main class for the Medical Management System GUI.
 * This class creates the main JFrame and manages all GUI components.
 */
public class MedicalGUI extends JFrame {

    private HealthService healthService;

    // GUI components
    private JTabbedPane tabbedPane;
    private JTextArea outputArea;
    private JTable patientTable, facilityTable, procedureTable;
    private DefaultTableModel patientTableModel, facilityTableModel, procedureTableModel;

    /**
     * Constructor for the MedicalGUI class.
     * Initializes the health service 
     */
    public MedicalGUI() {
        // Initialize the health service and load sample data for demonstration
        healthService = new HealthService("Health Service");
     

        // Initialize GUI
        initializeGUI();


    }

    /**
     * Initializes the GUI components.
     * Sets up the main frame, menu bar, tabbed pane, and output area.
     */
    private void initializeGUI() {
        setTitle("Medical Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLayout(new BorderLayout());

        // Create menu bar
        JMenuBar menuBar = createMenuBar();
        setJMenuBar(menuBar);

        // Create tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Patients", createPatientsPanel());
        tabbedPane.addTab("Facilities", createFacilitiesPanel());
        tabbedPane.addTab("Procedures", createProceduresPanel());
        tabbedPane.addTab("Visit/Operate", createVisitOperationPanel());
        add(tabbedPane, BorderLayout.CENTER);

        // Output area
        outputArea = new JTextArea(10, 50);
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Creates the menu bar with File menu options.
     * @return JMenuBar The created menu bar.
     */
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem loadItem = new JMenuItem("Load");
        JMenuItem exitItem = new JMenuItem("Exit");

        saveItem.addActionListener(e -> saveData());
        loadItem.addActionListener(e -> loadData());
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);
        return menuBar;
    }

    /**
     * Creates the Patients panel with a table and buttons for adding and deleting patients.
     * @return JPanel The created patients panel.
     */
    private JPanel createPatientsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Table for patients
        String[] columnNames = {"Name", "Private"};
        patientTableModel = new DefaultTableModel(columnNames, 0);
        patientTable = new JTable(patientTableModel);

        JButton addPatientButton = new JButton("Add Patient");
        JButton deletePatientButton = new JButton("Delete Patient");

        addPatientButton.addActionListener(e -> addPatientDialog
        ());

        deletePatientButton.addActionListener(e -> deletePatient());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addPatientButton);
        buttonPanel.add(deletePatientButton);

        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(patientTable), BorderLayout.CENTER);

        updatePatientTable();

        return panel;
    }

    /**
     * Creates the Facilities panel with a table and buttons for adding and deleting facilities.
     * @return JPanel The created facilities panel.
     */
    private JPanel createFacilitiesPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Table for facilities
        String[] columnNames = {"Name", "Type"};
        facilityTableModel = new DefaultTableModel(columnNames, 0);
        facilityTable = new JTable(facilityTableModel);

        JButton addFacilityButton = new JButton("Add Facility");
        JButton deleteFacilityButton = new JButton("Delete Facility");

        addFacilityButton.addActionListener(e -> addFacilityDialog());
        deleteFacilityButton.addActionListener(e -> deleteFacility());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addFacilityButton);
        buttonPanel.add(deleteFacilityButton);

        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(facilityTable), BorderLayout.CENTER);

        updateFacilityTable();

        return panel;
    }

    /**
     * Creates the Procedures panel with a table and buttons for adding  procedures.
     * @return JPanel The created procedures panel.
     */
    private JPanel createProceduresPanel() {
      JPanel panel = new JPanel(new BorderLayout());

      // Table for procedures
      String[] columnNames = {"Name", "Description", "Is Elective", "Cost"};
      procedureTableModel = new DefaultTableModel(columnNames, 0);
      procedureTable = new JTable(procedureTableModel);

      JButton addProcedureButton = new JButton("Add Procedure");
       JButton deleteProcedureButton = new JButton("Delete Procedure");

      addProcedureButton.addActionListener(e -> addProcedureDialog());
       deleteProcedureButton.addActionListener(e -> deleteProcedure());

      JPanel buttonPanel = new JPanel();
      buttonPanel.add(addProcedureButton);
       buttonPanel.add(deleteProcedureButton);

      panel.add(buttonPanel, BorderLayout.NORTH);
      panel.add(new JScrollPane(procedureTable), BorderLayout.CENTER);

      updateProcedureTable();

      return panel;
    }

    /**
     * Creates the Visit/Operate panel with separate buttons for Visit and Operate.
     * @return JPanel The created visit/operate panel.
     */
     private JPanel createVisitOperationPanel() {
          JPanel panel = new JPanel(new FlowLayout());

          JButton visitButton = new JButton("Visit Facility");
          JButton operateButton = new JButton("Perform Procedure");

          visitButton.addActionListener(e -> showVisitDialog());
          operateButton.addActionListener(e -> showOperateDialog());

          panel.add(visitButton);
          panel.add(operateButton);

          return panel;
      }

    /**
     * Opens a dialog to add a new patient.
     */
    private void addPatientDialog() {
         AddPatientDialog dialog = new AddPatientDialog(this, healthService, patientTableModel);
         dialog.setVisible(true);

             }
   
    /**
     * Opens a dialog to add a new medical facility (Hospital or Clinic).
     */
    private void addFacilityDialog() {
        AddFacilityDialog dialog = new AddFacilityDialog(this, healthService, facilityTableModel);
        dialog.setVisible(true);
    }

    /**
     * Deletes a selected medical facility from the list.
     */
    private void deleteFacility() {
        int selectedRow = facilityTable.getSelectedRow();
        if (selectedRow >= 0) {
            MedicalFacility facilityToDelete = healthService.getMedicalFacilities().get(selectedRow);
            healthService.removeMedicalFacility(facilityToDelete);
            updateFacilityTable();
            updateOutput("Facility deleted: " + facilityToDelete.getName());
        } else {
            JOptionPane.showMessageDialog(this, "Please select a facility to delete.");
        }
    }

    /**
     * Opens a dialog to add a new procedure.
     */
     private void addProcedureDialog() {
          AddProcedureDialog dialog = new AddProcedureDialog(this, healthService, procedureTableModel);
          dialog.setVisible(true);
      }

   /**
 * Opens a dialog for the visit operation.
 */
private void showVisitDialog() {
    JDialog visitDialog = new JDialog(this, "Visit Facility", true);
    visitDialog.setLayout(new GridLayout(4, 2)); // Increased rows for buttons

    JLabel patientLabel = new JLabel("Select Patient:");
    JComboBox<String> patientComboBox = new JComboBox<>();
    JLabel hospitalLabel = new JLabel("Select Hospital to Admit:");
    JComboBox<String> hospitalComboBox = new JComboBox<>();
    JButton admitButton = new JButton("Admit");
    JButton closeButton = new JButton("Close");

    // Populate hospital combo box
    List<MedicalFacility> facilities = healthService.getMedicalFacilities();
    for (MedicalFacility facility : facilities) {
        if (facility instanceof Hospital) {
            hospitalComboBox.addItem(((Hospital) facility).getName());
        }
    }

    // Populate patient combo box
    List<Patient> patients = healthService.getPatients();
    for (Patient patient : patients) {
        patientComboBox.addItem(patient.getName());
    }

    admitButton.addActionListener(e -> {
        String selectedPatientName = (String) patientComboBox.getSelectedItem();
        String selectedHospitalName = (String) hospitalComboBox.getSelectedItem();

        Patient selectedPatient = null;
        Hospital selectedHospital = null;

        // Find Patient and Hospital objects by name
        for (Patient patient : healthService.getPatients()) {
            if (patient.getName().equals(selectedPatientName)) {
                selectedPatient = patient;
                break;
            }
        }
        for (MedicalFacility facility : healthService.getMedicalFacilities()) {
            if (facility instanceof Hospital && ((Hospital) facility).getName().equals(selectedHospitalName)) {
                selectedHospital = (Hospital) facility;
                break;
            }
        }

        if (selectedPatient != null && selectedHospital != null) {
            // Set the patient's current facility
            selectedPatient.setCurrentFacility(selectedHospital);
            updateOutput(selectedPatient.getName() + " is admitted to " + selectedHospital.getName());
            visitDialog.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Patient or hospital not found.");
        }
    });

    // Close button functionality
    closeButton.addActionListener(e -> visitDialog.dispose());

    // Set the layout for the buttons
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Center-align buttons
    buttonPanel.add(admitButton);
    buttonPanel.add(closeButton);

    // Add components to the dialog
    visitDialog.add(patientLabel);
    visitDialog.add(patientComboBox);
    visitDialog.add(hospitalLabel);
    visitDialog.add(hospitalComboBox);
    visitDialog.add(new JLabel()); // Placeholder
    visitDialog.add(buttonPanel);  // Add the button panel

    visitDialog.setSize(400, 200);
    visitDialog.setLocationRelativeTo(this);
    visitDialog.setVisible(true);
}


       /**
        * Opens a dialog for performing the operate (procedure) operation.
        */
         private void showOperateDialog() {
             JDialog operateDialog = new JDialog(this, "Perform Procedure", true);
             operateDialog.setLayout(new GridLayout(8, 2));

             JLabel hospitalLabel = new JLabel("Select Hospital:");
             JComboBox<String> hospitalComboBox = new JComboBox<>();
             JLabel patientLabel = new JLabel("Select Patient:");
             JComboBox<String> patientComboBox = new JComboBox<>();
             JLabel procedureLabel = new JLabel("Select Procedure:");
             JComboBox<String> procedureComboBox = new JComboBox<>();
             JCheckBox electiveCheckBox = new JCheckBox("Is Elective?");
             JButton performButton = new JButton("Perform Procedure");
             JLabel totalCostLabel = new JLabel("Total Cost: $0.00");
             double[] totalCost = {0.0}; // Using an array to make it effectively final for the lambda

             // 1. Populate hospital combo box
             List<MedicalFacility> facilities = healthService.getMedicalFacilities();
             for (MedicalFacility facility : facilities) {
                 if (facility instanceof Hospital) {
                     hospitalComboBox.addItem(((Hospital) facility).getName());
                 }
             }

             // Action listener for hospital selection
             hospitalComboBox.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     String selectedHospitalName = (String) hospitalComboBox.getSelectedItem();
                     patientComboBox.removeAllItems();
                     procedureComboBox.removeAllItems();

                     // Populate patient combo box with patients at the selected hospital
                     for (Patient patient : healthService.getPatients()) {
                         if (patient.getCurrentFacility() != null && patient.getCurrentFacility().getName().equals(selectedHospitalName)) {
                             patientComboBox.addItem(patient.getName());
                         }
                     }

                     // Populate procedure combo box with procedures available at the selected hospital
                     for (Procedure procedure : healthService.getProcedures()) {
                         procedureComboBox.addItem(procedure.getName());
                     }
                 }
             });

             performButton.addActionListener(e -> {
                 String selectedHospitalName = (String) hospitalComboBox.getSelectedItem();
                 String selectedPatientName = (String) patientComboBox.getSelectedItem();
                 String selectedProcedureName = (String) procedureComboBox.getSelectedItem();
                 boolean isElective = electiveCheckBox.isSelected();

                 Hospital selectedHospital = null;
                 Patient selectedPatient = null;
                 Procedure selectedProcedure = null;

                 // Find objects by name
                 for (MedicalFacility facility : healthService.getMedicalFacilities()) {
                     if (facility instanceof Hospital && ((MedicalFacility) facility).getName().equals(selectedHospitalName)) {
                         selectedHospital = (Hospital) facility;
                         break;
                     }
                 }
                 for (Patient patient : healthService.getPatients()) {
                     if (patient.getCurrentFacility() != null && patient.getCurrentFacility().getName().equals(selectedHospitalName) && patient.getName().equals(selectedPatientName)) {
                         selectedPatient = patient;
                         break;
                     }
                 }

                 int k=0;
                 for (Procedure procedure : healthService.getProcedures())
                  {
                    k=k+1;
                     if (procedure.getName().equals(selectedProcedureName)) {
                         selectedProcedure = procedure;
                        
                         break;
                     }
                 }

                 if (selectedHospital != null && selectedPatient != null && selectedProcedure != null) {
                     // Perform procedure and calculate cost
                     double procedureCost = calculateCost(selectedPatient, selectedProcedure, isElective);
                     totalCost[0] += procedureCost;
                     totalCostLabel.setText("Total Cost: $" + String.format("%.2f", totalCost[0]));
                     updateOutput(((Patient) selectedPatient).getName() + " underwent " + ((Procedure) selectedProcedure).getName() + " at " + selectedHospital.getName() +
                             ". Procedure cost: $" + String.format("%.2f", procedureCost) + ". Total cost: $" + String.format("%.2f", totalCost[0]));

                             int continueChoice = JOptionPane.showConfirmDialog(this, "Do you want to perform another procedure for the same patient?",
                             "Another Procedure?", JOptionPane.YES_NO_OPTION);
                         
                         if (continueChoice == JOptionPane.YES_OPTION) {
                             showAdditionalProcedureDialog(selectedHospital, selectedPatient, totalCost);
                         } else {
                             JOptionPane.showMessageDialog(this, selectedPatient.getName() + 
                                 " is discharged from hospital. Total cost: $" + String.format("%.2f", totalCost[0]));
                             operateDialog.dispose();
                         }
                        }
                  
             });

             
             operateDialog.add(hospitalLabel);
             operateDialog.add(hospitalComboBox);
             operateDialog.add(patientLabel);
             operateDialog.add(patientComboBox);
             operateDialog.add(procedureLabel);
             operateDialog.add(procedureComboBox);
             operateDialog.add(new JLabel()); // Placeholder
             operateDialog.add(electiveCheckBox);
             operateDialog.add(new JLabel()); // Placeholder
             operateDialog.add(totalCostLabel);
             operateDialog.add(new JLabel()); // Placeholder
             operateDialog.add(performButton);

             operateDialog.setSize(400, 300);
             operateDialog.setLocationRelativeTo(this);
             operateDialog.setVisible(true);
         }
         
         
         private void showAdditionalProcedureDialog(Hospital selectedHospital, Patient selectedPatient, double[] totalCost) {
            boolean addMore = true;  // Control loop to keep asking for more procedures
        
            while (addMore) {
                addMore = showProcedureDialog(selectedHospital, selectedPatient, totalCost);
            }
        }
        
        private boolean showProcedureDialog(Hospital selectedHospital, Patient selectedPatient, double[] totalCost) {
            JDialog procedureDialog = new JDialog(this, "Select Additional Procedure", true);
            procedureDialog.setLayout(new GridLayout(4, 2));
        
            JLabel procedureLabel = new JLabel("Select Procedure:");
            JComboBox<String> procedureComboBox = new JComboBox<>();
            JCheckBox electiveCheckBox = new JCheckBox("Is Elective?");
            JButton performButton = new JButton("Perform Procedure");
            JLabel totalCostLabel = new JLabel("Total Cost: $" + String.format("%.2f", totalCost[0]));
        
            // Populate the procedure combo box
            for (Procedure procedure : healthService.getProcedures()) {
                procedureComboBox.addItem(procedure.getName());
            }
        
            // ActionListener to handle when a procedure is performed
            performButton.addActionListener(e -> {
                String selectedProcedureName = (String) procedureComboBox.getSelectedItem();
                boolean isElective = electiveCheckBox.isSelected();
                Procedure selectedProcedure = null;
        
                // Find the selected procedure
                for (Procedure procedure : healthService.getProcedures()) {
                    if (procedure.getName().equals(selectedProcedureName)) {
                        selectedProcedure = procedure;
                        break;
                    }
                }
        
                if (selectedProcedure != null) {
                    double procedureCost = calculateCost(selectedPatient, selectedProcedure, isElective);
                    totalCost[0] += procedureCost;
                    totalCostLabel.setText("Total Cost: $" + String.format("%.2f", totalCost[0]));
        
                    updateOutput(selectedPatient.getName() + " underwent " + selectedProcedure.getName() +
                        " at " + selectedHospital.getName() + ". Procedure cost: $" + 
                        String.format("%.2f", procedureCost) + ". Total cost: $" + 
                        String.format("%.2f", totalCost[0]));
                }
        
                // Ask if user wants to add another procedure
                int continueChoice = JOptionPane.showConfirmDialog(this, 
                    "Do you want to perform another procedure for the same patient?", 
                    "Another Procedure?", JOptionPane.YES_NO_OPTION);
        
                if (continueChoice == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(this, selectedPatient.getName() + 
                        " is discharged from hospital. Total cost: $" + String.format("%.2f", totalCost[0]));
                    procedureDialog.dispose();
                   
                    showOperateDialog();                
                    
                } else{
                     // Clear the combo box and the checkbox after each procedure is added
            procedureComboBox.setSelectedIndex(0); // Reset to the first item in the combo box
            electiveCheckBox.setSelected(false); // Uncheck the "Is Elective?" checkbox

                }
            });
        
            procedureDialog.add(procedureLabel);
            procedureDialog.add(procedureComboBox);
            procedureDialog.add(new JLabel()); // Spacer
            procedureDialog.add(electiveCheckBox);
            procedureDialog.add(new JLabel()); // Spacer
            procedureDialog.add(totalCostLabel);
            procedureDialog.add(new JLabel()); // Spacer
            procedureDialog.add(performButton);
        
            procedureDialog.setSize(350, 200);
            procedureDialog.setLocationRelativeTo(this);
            procedureDialog.setVisible(true);
        
            // Return true if the user wants to continue, otherwise false
            return JOptionPane.showConfirmDialog(this, 
                "Do you want to perform another procedure for the same patient?", 
            
                "Another Procedure?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION; 
            }
            
         

        




    /**
     * Calculates the cost of the procedure based on patient type and elective status.
     * @param patient The patient undergoing the procedure.
     * @param procedure The procedure being performed.
     * @param isElective Whether the procedure is elective.
     * @return The calculated cost of the procedure.
     */
         private double calculateCost(Patient patient, Procedure procedure, boolean isElective) {
             if (!patient.isPrivate()) { // Public patient
                 if (!isElective) { // Non-elective
                     return 0;
                 } else { // Elective
                     return procedure.getCost();
                 }
             } else { // Private patient
                 if (!isElective) { // Non-elective
                     return 1000;
                 } else { // Elective
                     return 2000;
                 }
             }
         }

    /**
     * Saves the health service data to a file.
     */
    private void saveData() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Health Service Data");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Data Files", "dat"));

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try {
                String file = fileChooser.getSelectedFile().getAbsolutePath() + ".dat";
                healthService.saveToFile(file);
                JOptionPane.showMessageDialog(this, "Data saved successfully to " + file);
                updateOutput("Data saved to " + file);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                updateOutput("Error saving data: " + ex.getMessage());
            }
        }
    }

    /**
     * Loads the health service data from a file.
     */
    private void loadData() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Load Health Service Data");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Data Files", "dat"));

        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try {
                String file = fileChooser.getSelectedFile().getAbsolutePath();
                healthService = HealthService.loadFromFile(file);
                updateAllTables();
                updateComboBoxes((JComboBox<String>) ((JPanel) tabbedPane.getComponentAt(3)).getComponent(1),
                    (JComboBox<String>) ((JPanel) tabbedPane.getComponentAt(3)).getComponent(3));
                JOptionPane.showMessageDialog(this, "Data loaded successfully from " + file);
                updateOutput("Data loaded from " + file);
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                updateOutput("Error loading data: " + ex.getMessage());
            }
        }
    }

    /**
     * Updates the patient table with the current list of patients.
     */
    private void updatePatientTable() {
        patientTableModel.setRowCount(0); // Clear existing data
        List<Patient> patients = healthService.getPatients();
        Collections.sort(patients, (p1, p2) -> p1.getName().compareTo(p2.getName()));
        for (Patient patient : patients) {
            Object[] rowData = {patient.getName(), patient.isPrivate()};
            patientTableModel.addRow(rowData);
        }
    }

    /**
     * Updates the facility table with the current list of medical facilities.
     */
    private void updateFacilityTable() {
        facilityTableModel.setRowCount(0); // Clear existing data
        List medicalFacilities = healthService.getMedicalFacilities();
        Collections.sort(medicalFacilities, (f1, f2) -> ((MedicalFacility) f1).getName().compareTo(((MedicalFacility) f2).getName()));
        for (Object facility : medicalFacilities) {
            String type = (facility instanceof Hospital) ? "Hospital" : "Clinic";
            Object[] rowData = {((MedicalFacility) facility).getName(), type};
            facilityTableModel.addRow(rowData);
        }
    }

    /**
     * Updates the procedure table with the current list of procedures.
     */
     private void updateProcedureTable() {
         procedureTableModel.setRowCount(0); // Clear existing data
         List procedures = healthService.getProcedures();
         Collections.sort(procedures, (p1, p2) -> ((Procedure) p1).getName().compareTo(((Procedure) p2).getName()));
         for (Object procedure : procedures) {
         Object[] rowData = {((Procedure) procedure).getName(), ((Procedure) procedure).getDescription(), ((Procedure) procedure).isElective(), ((Procedure) procedure).getCost()};
         procedureTableModel.addRow(rowData);
         }
     }

    /**
     * Updates the combo boxes with the current list of patients and facilities.
     * @param patientComboBox The combo box for patients.
     * @param facilityComboBox The combo box for facilities.
     */
    private void updateComboBoxes(JComboBox<String> patientComboBox, JComboBox<String> facilityComboBox) {
        patientComboBox.removeAllItems();
        List patients = healthService.getPatients();
        for (Object patient : patients) {
            patientComboBox.addItem(((Patient) patient).getName()); // Add patient names
        }

        facilityComboBox.removeAllItems();
        List facilities = healthService.getMedicalFacilities();
        for (Object facility : facilities) {
            facilityComboBox.addItem(((MedicalFacility) facility).getName()); // Add facility names
        }
    }

    /**
     * Updates all tables (patients, facilities, procedures).
     */
    private void updateAllTables() {
        updatePatientTable();
        updateFacilityTable();
        updateProcedureTable();
    }

    

    /**
     * Updates the output area with a new message.
     * @param text The text to append to the output area.
     */
    private void updateOutput(String text) {
        outputArea.append(text + "\n");
    }

    /**
     * Main method to launch the application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MedicalGUI::new);
    }

    // Custom Dialogs

    /**
     * Custom dialog for adding a new patient.
     */
    public class AddPatientDialog extends JDialog {
        private JTextField nameField;
        private JCheckBox privateCheckBox;
        private DefaultTableModel patientTableModel;
        private HealthService healthService;
    
        public AddPatientDialog(JFrame owner, HealthService healthService, DefaultTableModel patientTableModel) {
            super(owner, "Add Patient", true);
            this.healthService = healthService;
            this.patientTableModel = patientTableModel;
            setLayout(new GridLayout(5, 2));
    
            // Create the components
            JLabel nameLabel = new JLabel("Name:");
            nameField = new JTextField();
            JLabel privateLabel = new JLabel("Private:");
            privateCheckBox = new JCheckBox();
    
            // Add button to add the patient
            JButton addButton = new JButton("Add");
            addButton.addActionListener(e -> {
                String name = nameField.getText().trim();
                boolean isPrivate = privateCheckBox.isSelected();
    
                // Check if name is empty
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Name cannot be empty!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
    
                // Check if name contains invalid symbols (using a regex to allow only letters and spaces)
                if (!name.matches("[a-zA-Z ]+")) {
                    JOptionPane.showMessageDialog(this, "Name must contain only letters and spaces.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
    
                // Create a new Patient and add to the health service
                Patient patient = new Patient(name, isPrivate);
                healthService.addPatient(patient);
                updatePatientTable();
    
               // Update combo boxes
               JPanel panel = (JPanel) tabbedPane.getComponentAt(3); // Get the 4th tab (index 3)
               JComboBox<String> patientComboBox = null;
               JComboBox<String> facilityComboBox = null;
               
               for (Component comp : panel.getComponents()) {  
                   if (comp instanceof JComboBox) { 
                       if (patientComboBox == null) {  
                           patientComboBox = (JComboBox<String>) comp;
                       } else {  
                           facilityComboBox = (JComboBox<String>) comp;
                           break;  
                       }
                   }
               }
               
               if (patientComboBox != null && facilityComboBox != null) {
                   updateComboBoxes(patientComboBox, facilityComboBox);  // Call method to update the combo boxes
               }
               
   
                

                dispose(); // Close the dialog after adding the patient
                updateOutput("Patient added: " + patient.getName());
            });
    
            // Create Close button
            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(e -> dispose());  // Close the dialog when Close button is clicked
    
            // Create a JPanel with FlowLayout to place buttons horizontally
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Centered alignment for buttons
            buttonPanel.add(addButton);
            buttonPanel.add(closeButton);
    
            // Add components to the dialog
            add(nameLabel);
            add(nameField);
            add(privateLabel);
            add(privateCheckBox);
            add(new JLabel());  // Placeholder for layout alignment
            add(buttonPanel);  // Add the button panel
    
            setSize(300, 200);
            setLocationRelativeTo(owner);  // Center the dialog relative to the owner window
        }
    }


/**
     * Deletes a selected patient from the list.
     */
    private void deletePatient() {
        int selectedRow = patientTable.getSelectedRow();
        if (selectedRow >= 0) {
            Patient patientToDelete = healthService.getPatients().get(selectedRow);
            healthService.removePatient(patientToDelete);
            updatePatientTable();
            updateOutput("Patient deleted: " + patientToDelete.getName());
        } else {
            JOptionPane.showMessageDialog(this, "Please select a patient to delete.");
        }
    }



    /**
     * Custom dialog for adding a new medical facility (Hospital or Clinic).
     */
   private class AddFacilityDialog extends JDialog implements ActionListener {
       private HealthService healthService;
       private DefaultTableModel facilityTableModel;
       private JButton hospitalButton;
       private JButton clinicButton;

       public AddFacilityDialog(JFrame owner, HealthService healthService, DefaultTableModel facilityTableModel) {
           super(owner, "Add Medical Facility", true);
            this.healthService = healthService;
             this.facilityTableModel = facilityTableModel;
           setLayout(new FlowLayout());

           hospitalButton = new JButton("Add Hospital");
           clinicButton = new JButton("Add Clinic");

           hospitalButton.addActionListener(this);
           clinicButton.addActionListener(this);

           add(hospitalButton);
           add(clinicButton);

           setSize(300, 100);
           setLocationRelativeTo(owner);
       }

       @Override
       public void actionPerformed(ActionEvent e) {
           if (e.getSource() == hospitalButton) {
               AddHospitalDialog hospitalDialog = new AddHospitalDialog((JFrame) getOwner(), healthService, facilityTableModel);
               hospitalDialog.setVisible(true);
           } else if (e.getSource() == clinicButton) {
               AddClinicDialog clinicDialog = new AddClinicDialog((JFrame) getOwner(), healthService, facilityTableModel);
               clinicDialog.setVisible(true);
           }
           dispose();
       }
   }



/*
 * Custom dialog for adding a new hospital.
 */

public class AddHospitalDialog extends JDialog {
    private JTextField nameField;
    private JTextField probAdmitField;
    private HealthService healthService;
    private DefaultTableModel facilityTableModel;

    public AddHospitalDialog(JFrame owner, HealthService healthService, DefaultTableModel facilityTableModel) {
        super(owner, "Add Hospital", true);
        this.healthService = healthService;
        this.facilityTableModel = facilityTableModel;
        setLayout(new GridLayout(4, 2));  // Layout for fields (labels + input)

        // Create labels and text fields
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel probAdmitLabel = new JLabel("Prob. Admit (0 to 1):");
        probAdmitField = new JTextField();

        // Create buttons
        JButton addButton = new JButton("Add");
        JButton closeButton = new JButton("Close");

        // Add action for the 'Add' button
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String probAdmitStr = probAdmitField.getText();

            // Check if the name is empty
            if (name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name cannot be empty!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                // Validate probability input
                double probAdmit = Double.parseDouble(probAdmitStr);
                if (probAdmit < 0 || probAdmit > 1) {
                    // Show error message if the probability is not between 0 and 1
                    JOptionPane.showMessageDialog(this, "Please enter a probability between 0 and 1.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Proceed with adding the hospital if input is valid
                Hospital hospital = new Hospital(name, probAdmit);
                healthService.addMedicalFacility(hospital);
                updateFacilityTable();

                // Update combo boxes 
                JPanel panel = (JPanel) tabbedPane.getComponentAt(3); // Get the 4th tab (index 3)
                JComboBox<String> patientComboBox = null;
                JComboBox<String> facilityComboBox = null;
                
                for (Component comp : panel.getComponents()) {  //
                    if (comp instanceof JComboBox) {  
                        if (patientComboBox == null) {  
                            patientComboBox = (JComboBox<String>) comp;
                        } else {  
                            facilityComboBox = (JComboBox<String>) comp;
                            break;  
                        }
                    }
                }
                
                if (patientComboBox != null && facilityComboBox != null) {
                    updateComboBoxes(patientComboBox, facilityComboBox);  // Call method to update the combo boxes
                }
                
                dispose();  // Close the dialog after adding the hospital
                updateOutput("Hospital added: " + hospital.getName());
            } catch (NumberFormatException ex) {
                // Show error message if the input is not a valid number
                JOptionPane.showMessageDialog(this, "Please enter a valid number for probability.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add action for the 'Close' button
        closeButton.addActionListener(e -> {
            dispose();  // Close the dialog when the Close button is clicked
        });

        // Create a JPanel with FlowLayout to align the buttons horizontally at the bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Buttons will be aligned horizontally, centered
        buttonPanel.add(addButton);
        buttonPanel.add(closeButton);

        // Add components to the dialog
        add(nameLabel);
        add(nameField);
        add(probAdmitLabel);
        add(probAdmitField);
        add(new JLabel());  // Empty label for alignment
        add(new JLabel());  // Empty label for alignment
        add(buttonPanel);   // Add button panel with buttons at the bottom

        setSize(300, 200);
        setLocationRelativeTo(owner);  // Center the dialog relative to the owner
    }
}


   /**
    * Custom dialog for adding a new clinic.
    */
    private class AddClinicDialog extends JDialog {
        private JTextField nameField;
        private JTextField feeField;
        private JTextField gapPercentField;
        private HealthService healthService; 
        private DefaultTableModel facilityTableModel;

        public AddClinicDialog(JFrame owner, HealthService healthService, DefaultTableModel facilityTableModel) {
            super(owner, "Add Clinic", true);
            this.healthService = healthService;
            this.facilityTableModel = facilityTableModel;
            setLayout(new GridLayout(4, 2));

            JLabel nameLabel = new JLabel("Name:");
            nameField = new JTextField();
            JLabel feeLabel = new JLabel("Fee:");
            feeField = new JTextField();
            JLabel gapPercentLabel = new JLabel("Gap % (0 to 1):");
            gapPercentField = new JTextField();

            JButton addButton = new JButton("Add");
            addButton.addActionListener(e -> {
                String name = nameField.getText();
                String feeStr = feeField.getText();
                String gapPercentStr = gapPercentField.getText();

                 // Check if the name is empty
            if (name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name cannot be empty!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

                try {
                    // Validate and parse Fee
                    double fee = Double.parseDouble(feeStr);
    
                    // Validate and parse Gap Percent
                    double gapPercent = Double.parseDouble(gapPercentStr);
    
                    if (gapPercent < 0 || gapPercent > 1) {
                        // Show error if gap percent is out of bounds
                        JOptionPane.showMessageDialog(this, "Please enter a gap percentage between 0 and 1.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
    
                    // Create and add the Clinic
                    Clinic clinic = new Clinic(name, fee, gapPercent);
                    healthService.addMedicalFacility(clinic);
                    updateFacilityTable();
    
                    // Update combo boxes
                    JPanel panel = (JPanel) tabbedPane.getComponentAt(3); // Get the 4th tab (index 3)
                    JComboBox<String> patientComboBox = null;
                    JComboBox<String> facilityComboBox = null;
                    
                    for (Component comp : panel.getComponents()) {  
                        if (comp instanceof JComboBox) {  
                            if (patientComboBox == null) {  
                                patientComboBox = (JComboBox<String>) comp;
                            } else {  
                                facilityComboBox = (JComboBox<String>) comp;
                                break;
                            }
                        }
                    }
                    
                    if (patientComboBox != null && facilityComboBox != null) {
                        updateComboBoxes(patientComboBox, facilityComboBox);  // Call method to update the combo boxes
                    }


                    dispose();  // Close dialog after adding the clinic
                    updateOutput("Clinic added: " + clinic.getName());
                } catch (NumberFormatException ex) {
                    // Show error if the input is not a valid number
                    JOptionPane.showMessageDialog(this, "Please enter valid numeric values for Fee and Gap Percent.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            });

                 // Create 'Close' button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());  // Close dialog when Close button is clicked

        // Create a JPanel with FlowLayout to place buttons horizontally
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Centered alignment for buttons
        buttonPanel.add(addButton);
        buttonPanel.add(closeButton);

             // Add components to the dialog
        add(nameLabel);
        add(nameField);
        add(feeLabel);
        add(feeField);
        add(gapPercentLabel);
        add(gapPercentField);
        add(new JLabel());  // Placeholder for layout alignment
        add(buttonPanel);  // Add the button panel with buttons at the bottom

            setSize(300, 200);
            setLocationRelativeTo(owner);
        }
    }
        /**
         * Custom dialog for adding a new procedure.
         */


    public class AddProcedureDialog extends JDialog {
        private JTextField nameField;
        private JTextField descriptionField;
        private JCheckBox electiveCheckBox;
        private JTextField costField;
        private JComboBox<String> hospitalComboBox;
        private HealthService healthService;
        private DefaultTableModel procedureTableModel;

    public AddProcedureDialog(JFrame owner, HealthService healthService, DefaultTableModel procedureTableModel) {
        super(owner, "Add Procedure", true);
        this.healthService = healthService;
        this.procedureTableModel = procedureTableModel;
        setLayout(new GridLayout(7, 2));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionField = new JTextField();
        JLabel electiveLabel = new JLabel("Is Elective:");
        electiveCheckBox = new JCheckBox();
        JLabel costLabel = new JLabel("Cost:");
        costField = new JTextField();
        JLabel hospitalLabel = new JLabel("Select Hospital:");
        hospitalComboBox = new JComboBox<>();

        // Populate hospital combo box with names
        List facilities = healthService.getMedicalFacilities();
        for (Object facility : facilities) {
            if (facility instanceof Hospital) {
                hospitalComboBox.addItem(((Hospital) facility).getName());
            }
        }

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String description = descriptionField.getText().trim();
            boolean isElective = electiveCheckBox.isSelected();
            double costValue = 0;
            try {
                costValue = Double.parseDouble(costField.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Cost must be a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String selectedHospitalName = (String) hospitalComboBox.getSelectedItem();

            // Check if name is empty or invalid (only letters allowed)
            if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) {
                JOptionPane.showMessageDialog(this, "Name cannot be empty and must contain only letters.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if any field is empty
            if (name.isEmpty() || description.isEmpty() || costField.getText().trim().isEmpty() || selectedHospitalName == null) {
                JOptionPane.showMessageDialog(this, "Please fill all the fields.", "Missing Information", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Find the selected hospital
            Hospital selectedHospital = null;
            for (Object facility : healthService.getMedicalFacilities()) {
                if (facility instanceof Hospital && ((MedicalFacility) facility).getName().equals(selectedHospitalName)) {
                    selectedHospital = (Hospital) facility;
                    break;
                }
            }

            // Create the new procedure
            Procedure procedure = new Procedure(name, description, isElective, costValue);
            healthService.addProcedure(procedure);
            updateProcedureTable();

            // Ask if the user wants to add another procedure
            int option = JOptionPane.showConfirmDialog(this, "Do you want to add another procedure?", "Add Another Procedure", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.NO_OPTION) {
                dispose(); // Close dialog if no
            } else {
                // Reset fields for another entry
                nameField.setText("");
                descriptionField.setText("");
                costField.setText("");
                electiveCheckBox.setSelected(false);
                hospitalComboBox.setSelectedIndex(0);
            }

            updateOutput("Procedure added: " + procedure.getName() + " at " + selectedHospitalName);
        });

        // Create Clear and Close buttons
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            // Reset all fields
            nameField.setText("");
            descriptionField.setText("");
            costField.setText("");
            electiveCheckBox.setSelected(false);
            hospitalComboBox.setSelectedIndex(0);
        });

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());

        // Panel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(closeButton);

        // Add components to the dialog
        add(nameLabel);
        add(nameField);
        add(descriptionLabel);
        add(descriptionField);
        add(electiveLabel);
        add(electiveCheckBox);
        add(costLabel);
        add(costField);
        add(hospitalLabel);
        add(hospitalComboBox);
        add(new JLabel()); // Placeholder for layout alignment
        add(buttonPanel);  // Add the button panel

        setSize(300, 300);
        setLocationRelativeTo(owner); // Center the dialog relative to the owner window
    }
}

        /**
         * Deletes a selected procedure from the list.
         */
        private void deleteProcedure() {
            int selectedRow = procedureTable.getSelectedRow();
            if (selectedRow >= 0) {
                Procedure procedureToDelete = healthService.getProcedures().get(selectedRow);
                healthService.removeProcedure(procedureToDelete);
                updateProcedureTable();
                updateOutput("Procedure deleted: " + procedureToDelete.getName());
            } else {
                JOptionPane.showMessageDialog(this, "Please select a procedure to delete.");
            }
        }



}

