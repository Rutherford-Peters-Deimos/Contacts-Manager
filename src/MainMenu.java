
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import util.Input;
import java.util.HashMap;
public class MainMenu {
    private static Input input = new Input();
    private static HashMap<String,Integer> list = new HashMap<>();
    static final String directory = "contactslist";
    static final String filename = "contacts";

    public static void main(String[] args) {

        Path dataDirectory = Paths.get(directory);
        Path dataFile = Paths.get(directory, filename);


        try {
            if (Files.notExists(dataDirectory)) {
                Files.createDirectories(dataDirectory);
            }

            if (!Files.exists(dataFile)) {
                Files.createFile(dataFile);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

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

}
