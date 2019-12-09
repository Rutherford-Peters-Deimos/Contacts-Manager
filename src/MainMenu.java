
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import util.Input;
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

        switch (chooseOption){
            case 1:
//                view contacts
                viewContacts();
                makeMap();
                Menu();
                break;
            case 2:
//                add new contact
                break;
            case 3:
//                search by name
                searchList();
                Menu();
                break;
            case 4:
//                delete a contact
                break;
            case 5:
//                exit program
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
        System.out.println("Name | Phone number\n" +
                "---------------");
        for (String contact: conList){
            System.out.println(contact);
        }
        System.out.println("---------------");
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
            }

        }






    }


}
