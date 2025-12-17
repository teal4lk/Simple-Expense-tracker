import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleTracker {
    private static final String CSV = "data_j.csv";
    private static  BufferedReader reader = null;

    /**
     * Initializes the BufferedReader for reading the CSV file.
     * @return BufferedReader object
     */
    private static BufferedReader initializeReader() {
        try {
            reader = new BufferedReader(new FileReader(CSV));
            return reader;
        } catch (IOException e) {
            return null;
        }
    }
    /**
     * Checks if the CSV file exists.
     * @return whether the file exists
     */
    private static boolean ifFileExist(){
        try (BufferedReader br = new BufferedReader(new FileReader(CSV))) {
            br.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    /**
     * Creates a new expense entry in the CSV file.
     * @param date - the date of the expense
     * @param category - the category of the expense
     * @param amount - the amount of the expense
     */
    public void addExpense(Date date, String category, double amount) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (!ifFileExist()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV))) {
                writer.write("Date,Category,Amount\n");
                writer.write(String.format("%s,%s,%.2f\n", sdf.format(date), category, amount));
            } catch (IOException e) {
                System.out.println("An error occurred while creating the CSV file.");
                e.printStackTrace();
            }
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV, true))) {
                        writer.write(String.format("%s,%s,%.2f\n", sdf.format(date), category, amount));
                    } catch (IOException e) {
                        System.out.println("An error occurred while adding the expense.");
                        e.printStackTrace();
                    }
        }
        
    }
    /**
     * Calculates and prints the total expense from the CSV file.
     */
    public void getTotalExpense() {
        double total = 0.0;
        BufferedReader reader = initializeReader();
        if (reader != null) {
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    
                    if (!parts[2].equals("Amount")){
                        total += Double.parseDouble(parts[2]);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        System.out.printf("Total Expense: %.2f\n", total);
    } else {
        System.out.println("No expenses recorded yet.");
    }
    }
    /**
     * Calculates and prints the total expense by category from the CSV file.
     */
    public void getExpenseByCategory() {
        Map<String, Double> categoryTotals = new HashMap<>();
        BufferedReader reader = initializeReader(); 
        if (reader != null) {
            String line;
            try { 
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    String category = parts[1];
                    if (!category.equals("Category")) {
                        double amount = Double.parseDouble(parts[2]);
                        categoryTotals.put(category, categoryTotals.getOrDefault(category, 0.0) + amount);
                    } 
                }
            } catch (IOException e) {
                e.printStackTrace(); 
            }
        } else {
        System.out.println("No expenses recorded yet.");
        return;
        }
        for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
            System.out.printf("Category: %s, Total Expense: %.2f\n", entry.getKey(), entry.getValue());
        }
    }
    /**
     * Calculates and prints the expense trend by date from the CSV file.
     */
    public void getExpenseTrend() {
        Map<String, Double> dateTotals = new HashMap<>();
        BufferedReader reader = initializeReader(); 
        if (reader != null) {
            String line;
            try { 
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    String date = parts[0];
                    if (!date.equals("Date")) {
                        double amount = Double.parseDouble(parts[2]);
                        dateTotals.put(date, dateTotals.getOrDefault(date, 0.0) + amount);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace(); 
            }
        } else {
        System.out.println("No expenses recorded yet.");
        return;
        }   
        String[] dates = dateTotals.keySet().toArray(new String[0]);
        Arrays.sort(dates);
        for (String date : dates) {
            System.out.printf("Date: %s, Total Expense: %.2f\n", date, dateTotals.get(date));
        }
        
    }
    /**
     * Calculates and prints the highest and lowest spend categories from the CSV file.
     */
    public void getHighestAndLowestSpendCategory() {
        Map<String, Double> highest_lowest_map = new HashMap<>();
        reader = initializeReader();
        if (reader != null) {
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    String category = parts[1];
                    if (!category.equals("Category")) {
                        double amount = Double.parseDouble(parts[2]);
                        highest_lowest_map.put(category, highest_lowest_map.getOrDefault(category, 0.0) + amount);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            String highest_category = "";
            String lowest_category = "";
            for (Map.Entry<String, Double> entry : highest_lowest_map.entrySet()) {
                if (entry.getValue() == Collections.max(highest_lowest_map.values())) {
                    highest_category = entry.getKey();
                }
                if (entry.getValue() == Collections.min(highest_lowest_map.values())) {
                    lowest_category = entry.getKey();
                }
            }
            System.out.println("Highest Spend Category: " + highest_category + " \nLowest Spend Category: " + lowest_category);
        } else {
            System.out.println("No expenses recorded yet.");
        }
    }
}