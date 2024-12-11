import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WashingMachineApp {
    private JFrame frame;
    private JTextField idField, modelField, powerField, maxSpeedField, priceField, dateField;
    private JTable table;
    private DefaultTableModel tableModel;
    private List<WashingMachine> machines;
    private List<String> encryptedMachines; 

    public WashingMachineApp() {
        machines = new ArrayList<>();
        frame = new JFrame("Washing Machine Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new BorderLayout());
        encryptedMachines = new ArrayList<>();
        tableModel = new DefaultTableModel(new String[]{"ID", "Model", "Power", "Max Speed", "Date", "Price"}, 0);
        table = new JTable(tableModel);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);


        JPanel inputPanel = new JPanel(new GridLayout(8, 6));
        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Model:"));
        modelField = new JTextField();
        inputPanel.add(modelField);

        inputPanel.add(new JLabel("Power:"));
        powerField = new JTextField();
        inputPanel.add(powerField);

        inputPanel.add(new JLabel("maxSpeed:"));
        maxSpeedField = new JTextField();
        inputPanel.add(maxSpeedField);

        inputPanel.add(new JLabel("Release Date (yyyy-mm-dd):"));
        dateField = new JTextField();
        inputPanel.add(dateField);

        inputPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        frame.add(inputPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"ID", "Model", "Power", "maxSpeed", "Date", "Price"}, 0);
        table = new JTable(tableModel);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("Add Machine");
        addButton.addActionListener(e -> addMachine());
        buttonPanel.add(addButton);

        JButton processStreamsButton = new JButton("Process Streams");
        processStreamsButton.addActionListener(e -> processDataFromTxt());
        buttonPanel.add(processStreamsButton);

        JButton saveButton = new JButton("Save to File");
        saveButton.addActionListener(e -> saveToFile());
        buttonPanel.add(saveButton);

        JButton sortByPriceButton = new JButton("Sort by Price");
        sortByPriceButton.addActionListener(e -> sortByPrice());
        buttonPanel.add(sortByPriceButton);

        JButton sortByDateButton = new JButton("Sort by Date");
        sortByDateButton.addActionListener(e -> sortByDate());
        buttonPanel.add(sortByDateButton);

        JButton encryptButton = new JButton("Encrypt Data");
        encryptButton.addActionListener(e -> encryptData());
        buttonPanel.add(encryptButton);

        JButton decryptButton = new JButton("Decrypt Data");
        decryptButton.addActionListener(e -> decryptData());
        buttonPanel.add(decryptButton);
        JButton archiveButton = new JButton("Archive Data");
        archiveButton.addActionListener(e -> archiveData());
        buttonPanel.add(archiveButton);

        JButton formatButton = new JButton("Format Text");
        formatButton.addActionListener(e -> formatTextFields());
                buttonPanel.add(formatButton);
                
                frame.add(buttonPanel, BorderLayout.SOUTH);
            }
        
            private void processDataFromTxt() {
        try {
            System.out.println("Reading data from TXT file...");
            List<WashingMachine> machinesFromFile = FileManager.readMachinesFromFile("machines.txt");

            System.out.println("Writing data to JSON...");
            JSONManager.writeMachinesToJSON(machinesFromFile, "machines.json");
    
            System.out.println("Writing data to XML...");
            XMLManager.writeMachinesToXML(machinesFromFile, "machines.xml");
    
            System.out.println("Data successfully processed from TXT to JSON and XML!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error processing streams: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void addMachine() {
        try {
            int id = Integer.parseInt(idField.getText());
            String model = modelField.getText();
            int power = Integer.parseInt(powerField.getText());
            int maxSpeed = Integer.parseInt(maxSpeedField.getText());
            Date releaseDate = java.sql.Date.valueOf(dateField.getText());
            double price = Double.parseDouble(priceField.getText());

            WashingMachine machine = new DomesticWashingMachine(id, model, power, maxSpeed, releaseDate, price);
            machines.add(machine);
            tableModel.addRow(new Object[]{id, model, power, maxSpeed, releaseDate, price});
            JOptionPane.showMessageDialog(frame, "Machine added successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error: Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveToFile() {
        try {
            FileManager.writeToFile("machines.txt", machines);
            JOptionPane.showMessageDialog(frame, "Data saved to file!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error saving to file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void archiveData() {
        try {
            ZipManager.createZip("machines.txt", "machines.zip");
            JOptionPane.showMessageDialog(frame, "File machines.txt was archived into machines.zip");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error archiving the file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sortByPrice() {
        machines.sort(Comparator.comparingDouble(WashingMachine::getPrice));
        updateTable();
        JOptionPane.showMessageDialog(frame, "Machines sorted by price!");
    }

    private void sortByDate() {
        machines.sort(Comparator.comparing(WashingMachine::getReleaseDate));
        updateTable();
        JOptionPane.showMessageDialog(frame, "Machines sorted by release date!");
    }

    private void encryptData() {
        try {
            String secretKey = "1234567812345678";
            encryptedMachines.clear(); // Очистить список, чтобы избежать дублирования
            StringBuilder encryptedResults = new StringBuilder("Encrypted Data:\n");
    
            for (WashingMachine machine : machines) {
                String encrypted = EncryptionManager.encrypt(machine.toString(), secretKey);
                encryptedMachines.add(encrypted); // Сохранить зашифрованные данные
                encryptedResults.append(encrypted).append("\n");
            }
    
            JOptionPane.showMessageDialog(frame, encryptedResults.toString(), "Encryption Results", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error encrypting data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void decryptData() {
        try {
            String secretKey = "1234567812345678";
            if (encryptedMachines.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No encrypted data to decrypt!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
    
            StringBuilder decryptedResults = new StringBuilder("Decrypted Data:\n");
    
            for (String encrypted : encryptedMachines) {
                String decrypted = EncryptionManager.decrypt(encrypted, secretKey);
                decryptedResults.append(decrypted).append("\n");
            }
    
            JOptionPane.showMessageDialog(frame, decryptedResults.toString(), "Decryption Results", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error decrypting data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void formatTextFields() {
        try {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    Object value = tableModel.getValueAt(i, j);
    
                    if (value != null) {
                        
                        if (value instanceof String) {
                            String formattedValue = ((String) value).trim();
                            tableModel.setValueAt(formattedValue, i, j);
                        }
                    
                        else if (value instanceof Number) {
                            tableModel.setValueAt(value, i, j); 
                        }
                        else {
                            String formattedValue = value.toString().trim();
                            tableModel.setValueAt(formattedValue, i, j);
                        }
                    }
                }
            }
            JOptionPane.showMessageDialog(frame, "Table formatted successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error formatting table: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateTable() {
        
        tableModel.setRowCount(0);
        for (WashingMachine machine : machines) {
            tableModel.addRow(new Object[]{
                    machine.getId(),
                    machine.getModel(),
                    machine.getPower(),
                    machine.getMaxSpeed(),
                    machine.getReleaseDate(),
                    machine.getPrice()
            });
        }
    }

    public void show() {
        frame.setVisible(true);
    }
}
