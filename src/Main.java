import org.xml.sax.SAXParseException;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


/**
 * @author Rymar Vladiclav ISU 309622
 * @version 1.0
 */


public class Main {
    private static File file = null;
    public static Asked asked = new Asked();

    public static void main(String[] args) throws JAXBException, IOException, SAXParseException {

        try {
            String homePATH = System.getenv("homePath");
            file = new File("ww");
            if (!file.exists()) throw new FileNotFoundException();
            if (!file.canRead() || !file.canWrite()) throw new SecurityException();
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    System.out.println("\nВыход...");
                }
            });
            asked.app(file);
        } catch (NullPointerException e) {
            System.out.println("Создайте переменную окружения(homePATH=\"/home/s309622/ww\"\n"
            + "export hleb || ошибка в файле, введите все данные");
            //e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("Файла по указанному пути не существует");
            if (false) System.exit(1);
            //e.printStackTrace();
        } catch (SecurityException se) {
            System.out.println("Файл защищен от чтения и/или записи. Для программы нужны оба разрешения");
            //se.printStackTrace();
            if (false) System.exit(1);


        }
    }
}