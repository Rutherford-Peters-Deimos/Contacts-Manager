import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainMenu {

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
}
