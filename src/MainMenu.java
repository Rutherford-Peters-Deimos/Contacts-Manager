
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import util.Input;
import java.util.HashMap;
import java.util.List;

public class MainMenu {
    private static Input input = new Input();
    private static HashMap<String,Integer> list = new HashMap<>();
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
                Menu();
                break;
            case 2:
//                add new contact
                break;
            case 3:
//                search by name
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


}
