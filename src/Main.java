
import Collection.CollectionManager;
import Collection.CommandManager;


/**
 * @author Rymar Vladiclav ISU 309622
 * @version 1.0
 */

public class Main {
    public static void main(String[] args) trows IOExeption {
        System.out.println("Collection manager activated");
        CommandManager commandManager = new CommandManager(new CollectionManager());
        commandManager.interactiveMod();
    }

}