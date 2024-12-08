package dbms.project.kitchenvault_project.classes;

import java.util.ArrayList;
import java.util.List;

public class fieldMatters {
    public static List<String> getTypes(){
        List<String> types = new ArrayList<>();
        types.add("Protein");
        types.add("Vegetables");
        types.add("Fruits");
        types.add("Grains");
        types.add("Dairy");
        types.add("Fats & Oils");
        types.add("Spices");
        types.add("Beverages");
        types.add("Condiments");
        types.add("Other");

        return types;
    }

    public static List<String> getMonths(){
        List<String> types = new ArrayList<>();
        types.add("01");
        types.add("02");
        types.add("03");
        types.add("04");
        types.add("05");
        types.add("06");
        types.add("07");
        types.add("08");
        types.add("09");
        types.add("10");
        types.add("11");
        types.add("12");

        return types;
    }

    public static List<String> getDays(){
        List<String> types = new ArrayList<>();
        types.add("01");
        types.add("02");
        types.add("03");
        types.add("04");
        types.add("05");
        types.add("06");
        types.add("07");
        types.add("08");
        types.add("09");
        types.add("10");
        types.add("11");
        types.add("12");
        types.add("13");
        types.add("14");
        types.add("15");
        types.add("16");
        types.add("17");
        types.add("18");
        types.add("19");
        types.add("20");
        types.add("21");
        types.add("22");
        types.add("23");
        types.add("24");
        types.add("25");
        types.add("26");
        types.add("27");
        types.add("28");
        types.add("29");
        types.add("30");
        types.add("31");

        return types;
    }

    public static List<String> getYears(){
        List<String> types = new ArrayList<>();
        types.add("2018");
        types.add("2019");
        types.add("2020");
        types.add("2021");
        types.add("2022");
        types.add("2023");
        types.add("2024");
        types.add("2025");
        types.add("2026");
        types.add("2027");
        types.add("2028");
        types.add("2029");

        return types;
    }

    public static String validateNFormatDate(String mm, String dd, String yyyy){
        StringBuilder date = new StringBuilder();
        if(dd != null && mm != "null"){
            date.append(mm);
        }
        if(dd != null && dd != "null"){
            date.append("/").append(dd);
        }
        if(yyyy != null && yyyy != "null"){
            date.append("/").append(yyyy);
        }

        String completeDate = (date.length() == 10) ? ""+date : "null";
        return completeDate;
    }

    public static boolean canConvertToInt(String str) {
        try {
            Integer.parseInt(str);  // Try to parse the string to an integer
            return true;  // If parsing succeeds
        } catch (NumberFormatException e) {
            return false;  // If a NumberFormatException is thrown, it's not an integer
        }
    }

    public static boolean canConvertToDouble(String str) {
        try {
            Double.parseDouble(str);  // Try to parse the string to a double
            return true;  // If parsing succeeds
        } catch (NumberFormatException e) {
            return false;  // If a NumberFormatException is thrown, it's not a double
        }
    }

    public static boolean allInputIsFilled(String name, String type, String quantity, String price, String mm, String dd, String yyyy){
        System.out.println(mm + dd + yyyy);
        if(name.isEmpty() || type == "null" || quantity.isEmpty() || price.isEmpty() || mm == "null" || dd == "null" || yyyy == "null"){
            return false;
        }
        return true;
    }
}


