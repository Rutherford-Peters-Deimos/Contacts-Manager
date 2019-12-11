
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import util.Input;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainMenu {
    private static Input input = new Input();
    private static HashMap<String,String> list = new HashMap<>();
    private static String[] listArray;
    private static final String directory = "contactslist";
    private static final String filename = "contacts.txt";
    private static Path contactPath;

    public static void main(String[] args) {

        Path dataDirectory = Paths.get(directory);
        contactPath = Paths.get(directory, filename);


        try {
            if (Files.notExists(dataDirectory)) {
                Files.createDirectories(dataDirectory);
            }

            if (!Files.exists(contactPath)) {
                Files.createFile(contactPath);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Menu();

    }


    public static void Menu(){
        int chooseOption = input.getInt("1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5):",1,5);
        input.cleanLines();
        makeMap();

        switch (chooseOption){
            case 1:
//                view contacts
                viewContacts();
                makeMap();
                Menu();
                break;
            case 2:
//                add new contact
                addContact();
                Menu();
                break;
            case 3:
//                search by name
                searchList();
                Menu();
                break;
            case 4:
//                delete a contact
                removeContact();
                Menu();
                break;
            case 5:
//                exit program
                refreshContacts();
                System.out.println("Saving contacts\nExiting...");
                System.exit(0);
                break;
            default:
                System.out.println("good job you beat the system...");
        }
    }

    public static List<String> pulledContacts(){
        List<String> returnContacts = null;
        try {
            returnContacts = Files.readAllLines(contactPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnContacts;
    }

    public static void viewContacts(){
        List<String> conList = pulledContacts();
        if (conList.isEmpty()){
            System.out.println("Error: Contacts List is EMPTY...");
            String yesNo = input.getString("Would you like to create a contact?(yes/no)");
            if (yesNo.equalsIgnoreCase("yes")||yesNo.equalsIgnoreCase("y")){
//                add contact function
                addContact();
            }
            return;
        }
        System.out.println("Name              | Phone number   |\n" +
                "------------------------------------");
//        for (String contact: conList){
//            System.out.println(contact);
//        }
        for(HashMap.Entry<String,String> entry: list.entrySet()){
            System.out.printf("| %-15s | %-14s |%n",entry.getKey(),addDashes(entry.getValue()));
        }
        System.out.println("------------------------------------");
        System.out.println();
    }

    public static void makeMap(){
        List<String> conList = pulledContacts();
        for (String contact: conList){
            String name = contact.substring(0,contact.indexOf("|")-1);
            String number = contact.substring(contact.indexOf("|")+2);
            if (!list.containsKey(name)){
                list.put(name,number);
            }
        }
        makeListArray();
    }

    public static void makeListArray(){
        listArray = new String[list.size()];
        int index = 0;
        for(HashMap.Entry<String,String> entry: list.entrySet()){
            listArray[index] = entry.getKey() + " | " + entry.getValue();
            index++;
        }
    }

    public static void searchList(){
        makeMap();
        String searchName = input.getString("Enter a Name:");
        if (list.containsKey(searchName)){
            String num = list.get(searchName);
            System.out.println(searchName + " | "+ num);
        }else {
            System.out.println("Error: Contact not in the list....");
            String yesNo = input.getString("Would you like to create a contact?(yes/no)");
            if (yesNo.equalsIgnoreCase("yes")||yesNo.equalsIgnoreCase("y")){
//                add contact function
                addContact();
            }

        }

    }

    public static void addContact(){
        String newName = input.getString("Enter a contact Name:");
        System.out.println();
        String newNum = input.getString("Enter phone Number (NO dashes or spaces):");
        System.out.println();
        if (list.containsKey(newName)){
            String editNum = input.getString("Warning, Contact already exist do you want to edit contact's Number? (yes/no)");
            if (editNum.equalsIgnoreCase("yes")||editNum.equalsIgnoreCase("y")){
                list.replace(newName,newNum);
                refreshContacts();
            }else {
                System.out.println("Canceling...");
            }
        } else {
            System.out.println("Adding contact...");
            System.out.println();
            list.put(newName,newNum);
            refreshContacts();
        }

    }

    public  static void removeContact(){
        String newName = input.getString("Enter the name of the contact you wish to remove:");
        System.out.println();
        if (list.containsKey(newName)){
            String prompt = "Are you sure you want to delete " +newName + "? (yes/no)";
            String confirmDelete = input.getString(prompt);
            if (confirmDelete.equalsIgnoreCase("yes")||confirmDelete.equalsIgnoreCase("y")){
                System.out.println("Deleting "+newName+"...");
                list.remove(newName);
                refreshContacts();
                return;
            }else {
                System.out.println("Canceling...");
            }
        }else {
            String tryAgain = input.getString("Error: contact could not be found...\nReturn to main menu? (yes/no)");
            if (tryAgain.equalsIgnoreCase("yes")||tryAgain.equalsIgnoreCase("y")){
                System.out.println("Returning to main menu...");
                Menu();
            }else {
                System.out.println();
                removeContact();
            }
        }

    }

    public static void refreshContacts(){
        makeListArray();
        List<String> contactsList = Arrays.asList(listArray);

        try {
            Files.write(contactPath, contactsList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String addDashes(String number){
        int size = number.length();
        if (size == 7){
            String first3 = number.substring(0,3);
            String last4 = number.substring(3);
            return first3+"-"+last4;
        }else if (size == 10){
            String first3 = number.substring(0,3);
            String next3 = number.substring(3,6);
            String last4 = number.substring(6);
            return first3+"-"+next3+"-"+last4;
        }else if (size == 11){
            String first1 = number.substring(0,1);
            String next3 = number.substring(1,4);
            String nextNext3 = number.substring(4,7);
            String last4 = number.substring(7);
            return first1+"-"+next3+"-"+nextNext3+"-"+last4;
        }else {
            return number;
        }
    }



}
