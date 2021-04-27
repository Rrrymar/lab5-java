import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class CollectionManager {

    private final HashSet<Person> classPerson;
    /**
     * коллекция HashSet, чтобы хранить колекции в виде java-object
     */

    private File xmlCollection;
    /**
     * Поля для хранения колекции в файл xml-file
     */

    private ZonedDateTime initializationDate;
    /**
     * Поле для созранение data инициализации
     */

    private boolean wasStart;
    /**
     * Поле, чтобы проверить запуск программы
     */

    private final HashMap<String, String> commandInfo;
    /**
     * Для создания руковадства пользуемся HashMap
     */

    String hash;
    /** Проверка на неизменность файла */
    {

        wasStart = false;

        classPerson = new HashSet<>();



        // Инструкция

        commandInfo = new HashMap<>();

        commandInfo.put("help", " - покажет все справки по доступным командам");

        commandInfo.put("info", " - выведет все элементы в строковом виде на стандартный выход");

        commandInfo.put("show", " - выводит в стандартный поток вывовода все элементы " +
            "коллекции в строковом представлении");
        commandInfo.put("add", " - добавляет новый элемент в коллекцию");
        commandInfo.put("update id", " - оббновляет значение жлемента коллекции, id которого" +
            "равен заданному" +
            " Необходимо ввести ID после ввода комманды.");
        commandInfo.put("remove_by_id id", " - удаляэт элеммент коллекции по его индификатору." +
            " Необходимо ввести ID после ввода комманды.");
        commandInfo.put("clear", " - очищяет всю колекцию, удаляет все элементы.");
        commandInfo.put("save", " - сохраняет всю коллекцию в файл.");
        commandInfo.put("execute_script file_name", " - читает и выполняет скрипт из укахонного файла." +
            " Вы должны ввести путь к файлу после ввода команды.");
        commandInfo.put("exit", " - Завершение работы с программой без её сохранения в файл");
        commandInfo.put("add_if_min", " - добавление нового элемента в коллекцию, " +
            "если его значение меньше самого маленького элемента этой коллекции. " +
            "Вам следует ввести характеристики для сравнения элементов после ввода команды.");
        commandInfo.put("add_if_max", " - добавление нового элемента в коллекцию, " +
            "если его значенье больше самого большего элемента этой коллекции, " +
            "Вам следует ввести характеристики для сравнения элементов после ввода команды");
        commandInfo.put("remove_greater", " - удалить из коллекции все элементы, превышающие заданный." +
            " Вы должны ввести число с которым будет сравниватся после вводы комманды.");
        commandInfo.put("group_counting_by_creation_data", " - сгруппировать элементы коллекции по значению поля " +
            "creationDate, вывести количество элементов в каждой группе " );
        commandInfo.put("filter_countains_name", " - вывести элементы, значение поля name которых содержит заданную подстроку" +
            " Имя элемента, которые должны содержать подстроку.");
        commandInfo.put("print_field_descending_location", " - вывести значения поля location всех элементов в порядке убывания");
    }


    // Создаём коструктор для проверки пути, существования и готовности файла к работе над ним.
    public CollectionManager() {
        Scanner scanner = new Scanner(System.in);
        try {
            for ( ; ; ) {
                System.out.println("Введите полный путь у файлу XML с колекцией");
                String pathToFile = Scanner.nextLine();
                if (checkFile(pathToFile)) {
                    try {
                        final QName qName = new QName("person");
                        InputStream inputStream = new FileInputStream(new File(pathToFile));
                        // create xml event reader for input stream
                        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
                        XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(inputStream);
                        // initialize jaxb
                        JAXBContext context = JAXBContext.newInstance(Person.class);
                        Unmarshaller unmarshaller = context.createUnmarshaller();
                        XMLEvent e;
                        // Field for counting amount of downloaded elements
                        int counterGood = 0;
                        int counterBad = 0;
                        // Loop for unmarshalling the collection
                        while ((e = xmlEventReader.peek()) != null) {
                            // check the event is a Document start element
                            if (e.isStartElement() && ((StartElement) e).getName().equals(qName)) {
                                // unmarshall the document
                                Person unmarshalledPerson = unmarshaller.unmarshal(xmlEventReader, Person.class).getValue();
                                Coordinates newCoordinates = unmarshalledPerson.getCoordinates();
                                Location newLocation = unmarshalledPerson.getLocation();
                                if (unmarshalledPerson.getId() != 0 && !unmarshalledPerson.getName().equals(null) &&
                                    !(newCoordinates.getX() == (null)) && !newCoordinates.getY().equals(null) &&
                                    !unmarshalledPerson.returnCreationData().equals(null) && unmarshalledPerson.getHeight() > 0
                                    && !unmarshalledPerson.getEyeColor().equals(null) && !unmarshalledPerson.getHairColor().equals(null)
                                    && !unmarshalledPerson.getNationality().equals(null) && !newLocation.getX().equals(null) &&
                                    !newLocation.getY().equals(null) && !newLocation.getZ().equals(null)) {
                                        classPerson.add(unmarshalledPerson);
                                        counterGood += 1;
                                    } else counterBad += 1;
                                } else {
                                    xmlEventReader.next();
                                    xmlEventReader.next();
                                }
                            }
                            System.out.println("Collection was loaded successfully. " + counterGood + " elements has been loaded.");
                            System.out.println("Amount of elements which contains invalid values and has not been loaded: " + counterBad);
                            xmlCollection = new File(pathToFile);
                            wasStart = true;
                            initializationDate = ZonedDateTime.now();
                            break;
                        } catch (JAXBException jaxbException) {
                            System.out.println("XML syntax error.");
                        } catch (FileNotFoundException fileNotFoundException) {
                            System.out.println("File not found");
                        } catch (XMLStreamException xmlStreamException) {
                            System.out.println("XML Stream error");
                        }
                    } else System.out.println("Try again.");
                }
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program will be finished now.");
                System.exit(0);
            }
        }
    public boolean checkFile(String pathToFile) {
        File checkingFile = new File(pathToFile);
        if (!checkingFile.exists()) {
            System.out.println("Файл не найден попробуйте ещё раз.");
            return false;
        }
        if (!checkingFile.canRead()) {
            System.out.println("Файл не читаем. Исправте ошибку и попробуйте ещё раз.");
            return false;
        }
        if (!checkingFile.canWrite()) {
            System.out.println("Невозможно произвести запись данных в файл. Исправте ошибку и попробуйте ещё раз.");
            return false;
        }
        return true;
    }

    // Метод для печати руковдста пользователю
    public void help() {
        for (Map.Entry<String, String> entry : commandInfo.entrySet()) {
            System.out.println(entry.getKey() + entry.getValue());
        }
    }

    // Метод для печати информации о колекции
    public void info() {
        System.out.println("Тип колекции: java.util.HashSet");
        System.out.println("Дата инициализации: " + initializationDate);
        System.out.println("Количество элементов в колекции: " + classPerson.size());
        System.out.println("Менеджер колекции запущен:  " + wasStart);
    }

    // Метод для печати элементов колекции в строковом представлении
    public void show() {
        for (Person classPerson : classPerson) {
            System.out.println(classPerson.toString() + "\n");
        }
    }

    /**
     * Метод для получения индификатора элемента
     * @return Long ID
     */
    public Long receiveId() {
        long maxId = 1;
        for (Person classPerson : classPerson) {
            if (classPerson.getId() > maxId) {
                maxId = classPerson.getId();
            }
        }
        return maxId + 1;
    }

    /**
     * Метод для получения имени элемента
     * @return String name
     */
    public String receiveName() {
        for ( ; ; ) {
            try {
                System.out.println("Осторожно! Будте внимательны если информации в этом поле будет слишко много, то вы потеряте часть информации.");
                Scanner scanner = new Scanner(System.in);
                System.out.print("Введите имя: ");
                String name = scanner.nextLine().trim();
                if (name.equals("")) {
                    System.out.println("Имя не может быть пустым. Попробуйте ещё раз.");
                    continue;
                }
                return name;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Это значение должно быть строкой.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Программа успешно остановлена.");
                System.exit(1);
            }
        }
    }

    /**
     * Метод для получения координаты по оси х
     * @return int x
     */
    public long receiveX() {
        for ( ; ; ) {
            try {
                System.out.print("Введите координату по оси Х. Минимальное значение -800. ");
                Scanner scanner = new Scanner(System.in);
                System.out.print("Поле не может быть пустым. Попробуйте ещё раз. ");
                int x = scanner.nextInt();
                System.out.print("Поле не может быть пустым. Попробуйте ещё раз. ");
                String xx = String.valueOf((x));
                if (x < -800) {
                    System.out.println("Минимальное значение -800, Попробуйте ещё раз. ");
                    continue;
                }
                if (xx.equals("") ) {
                    System.out.println("Поле не может быть пустым. Попробуйте ещё раз. ");
                    continue;
                }
                return x;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Это значение должно быть типа int. Попробуйте ещё раз. ");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Программа успешно остановлена. ");
                System.exit(1);
            }
        }
    }

    /**
     * Метод для получения координаты по оси y
     * @return Integer y
     */
    public Integer receiveY() {
        for ( ; ; ) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Поле не может быть пустым. Попробуйте ещё раз. ");
                Integer y = scanner.nextInt();
                System.out.print("Поле не может быть пустым. Попробуйте ещё раз. ");
                String yy = Integer.toString(y);
                if (y > 990) {
                    System.out.println("Максиальное значение 990. Попробуйте ещё раз. ");
                    continue;
                }
                if (yy.equals("") ) {
                    System.out.println("Поле не может быть пустым. Попробуйте ещё раз. ");
                    continue;
                }
                return y;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Это значение должно быть типа Integer. Попробуйте ещё раз. ");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Программа успешно остановлена. ");
                System.exit(1);
            }
        }
    }

    // Метод для получение Координат с помощью методов reciveX() and reciveY()
    public Coordinates receiveCoordinates() {
        return new Coordinates((int) receiveX(), receiveY());
    }


    /**
     * Метод для получения x-координаты локации элемента
     * @return long xLocation
     */
    public long receiveXLocation() {
        for ( ; ; ) {
            try {
                System.out.print("Введите координату X локации. ");
                Scanner scanner = new Scanner(System.in);
                return scanner.nextLong();
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Это значение должно быть типом long. Попробуйте ещё раз.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Программа успешно остановлена. ");
                System.exit(1);
            }
        }
    }

    /**
     * Метод для получения y-координаты локации элемента
     * @return long yLocation
     */
    public long receiveYLocation() {
        for ( ; ; ) {
            try {
                System.out.print("Введите координату У локации. ");
                Scanner scanner = new Scanner(System.in);
                return scanner.nextLong();
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Это значение должно быть типом long. Попробуйте ещё раз.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Программа успешно остановлена. ");
                System.exit(1);
            }
        }
    }

    /**
     * Метод получения локации элемента Z
     * @return Integer zLocation
     */
    public Integer receiveZLocation() {
        for ( ; ; ) {
            try {
                System.out.print("Введите координату У локации. ");
                Scanner scanner = new Scanner(System.in);
                return scanner.nextInt();
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Это значение должно быть типом Integer. Попробуйте ещё раз.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Программа успешно остановлена. ");
                System.exit(1);
            }
        }
    }

    /**
     * Метод для получения локации с помощью методов receiveXLocation()
     * receiveYLocation() and receiveNameLocation()
     * @return Location location
     */
    public Location receiveLocation() {
        return new Location(receiveXLocation(), receiveYLocation(), receiveZLocation());
    }

    /**
     * Метод для получения цвета глаз
     * @return EyeColor eyeColor
     */
    public EyeColor receiveEyeColor() {
        for ( ; ; ) {
            try {
                System.out.println("Выберите вариант цвета глаз. Введите номер, соответствующий нужному параметру. ");
                System.out.print("Варианты: 1 - GREEN, 2 - RED, 3 - YELLOW. Enter 1, 2 or 3: ");
                Scanner scanner = new Scanner(System.in);
                int eyeChoose = scanner.nextInt();
                switch (eyeChoose) {
                    case 1:
                        return EyeColor.GREEN;
                    case 2:
                        return EyeColor.RED;
                    case 3:
                        return EyeColor.YELLOW;
                    default:
                        break;
                }
                System.out.println("Вы должны ввести 1/2/3. Попробуйте ещё раз. ");
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Это значение должно быть числом (1/2/3). Попробуйте ещё раз. ");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Программа была успешно остановлена. ");
                System.exit(1);
            }
        }
    }


    /**
     * Method for receiving height of element
     * @return long height
     */
    public Double receiveHeight() {
        for ( ; ; ) {
            try {
                System.out.print("Введите высоту. Значение должно быть больше 0. ");
                Scanner scanner = new Scanner(System.in);
                double height = scanner.nextDouble();
                if (height <= 0) {
                    System.out.println("Это значение должно быть больше 0. Попробуйте ещё раз. ");
                    continue;
                }
                return height;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Это значение должно быть типом Double. Попробуйте ещё раз. ");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Программа была успешно остановлена. ");
                System.exit(1);
            }
        }
    }





        /**
         * Метод для получения цвета волос
         * @return HairColor hairColor
         */
    public HairColor receiveHairColor() {
        for ( ; ; ) {
            try {
                System.out.println("Выберите вариант цвета глаз. Введите номер, соответствующий нужному параметру. ");
                System.out.print("Варианты: 1 - GREEN, 2 - RED, 3 - BLUE. Введите 1/2/3: \"");
                Scanner scanner = new Scanner(System.in);
                int hairChoice = scanner.nextInt();
                switch (hairChoice) {
                    case 1:
                        return HairColor.GREEN;
                    case 2:
                        return HairColor.RED;
                    case 3:
                        return HairColor.BLUE;
                    default:
                        break;
                }
                System.out.println("Вы должны ввести 1/2/3. Попробуйте ещё раз. ");
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Это значение должно быть числом (1/2/3). Попробуйте ещё раз. ");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Программа была успешно остановлена. ");
                System.exit(1);
            }
        }
    }

    /**
     * Метод для получения национальности для элемента
     * @return Country nationality
     */
    public Country receiveNationality() {
        for ( ; ; ) {
            try {
                System.out.println("Выберите вариант национальности. Введите номер, соответствующий нужному параметру. ");
                System.out.print("Variants: 1 - INDIA, 2 - VATICAN, 3 - ITALY. Введите 1/2/3: ");
                Scanner scanner = new Scanner(System.in);
                int nationalityChoice = scanner.nextInt();
                switch (nationalityChoice) {
                    case 1:
                        return Country.INDIA;
                    case 2:
                        return Country.VATICAN;
                    case 3:
                        return Country.ITALY;
                    default:
                        break;
                }
                System.out.println("Вы должны ввести 1/2/3. Попробуйте ещё раз. ");
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Это значение должно быть числом (1/2/3). Попробуйте ещё раз. ");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Программа была успешно остановлена. ");
                System.exit(1);
            }
        }
    }

    // Метод добавления элемента с использованием всех методов receive-fields
    public void add() {
        Person newPerson = new Person(receiveId(), receiveName(), receiveCoordinates(), returnDate(),
                receiveHeight(), receiveEyeColor(), receiveHairColor(), receiveNationality(), receiveLocation());
        classPerson.add(newPerson);
    }

    // Метод для сохранения (marshaling) коллекции java в XML-файл и обновления хэша файла.
    public void save() {
        try {
            classPerson newClassPerson = new classPerson();
            newClassPerson.setClassPerson(new ArrayList<>(classPerson));
            JAXBContext jaxbContext = JAXBContext.newInstance(classPerson.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //Marshal  список лиц в файле
            jaxbMarshaller.marshal(newClassPerson, xmlCollection);
        } catch (JAXBException jaxbException) {
            System.out.println("Синтаксическая ошибка XML. Попробуйте ещё раз. ");
        }


    }

    // Метод удаления элементов из коллекции, если их высота больше введенной высоты
        public void remove_greater(double height) {
        int counter = 0;
        for (Person person : classPerson) {
            if (person.getHeight() > height) {
                classPerson.remove(person);
                counter += 1;
            }
        }
        System.out.println("Операция была успешно завершена. " + counter + " элементов удалено.");
    }

    //  Метод удаления элементов из коллекции, если его высота меньше введенной высоты.
    public void remove_lower(double height) {
        int counter = 0;
        for (Person person : classPerson) {
            if (person.getHeight() < height) {
                classPerson.remove(person);
                counter += 1;
            }
        }
        System.out.println("Операция завершилась успешно. " + counter + " элементов удалено.");
    }

    // Метод добавления элемента в коллекцию, если его высота меньше введенной высоты
    public void add_if_min(Person example) {
        long minimalHeight = Long.MAX_VALUE;
        for (Person person : classPerson) {
            if (person.getHeight() < minimalHeight) {
                minimalHeight = person.getHeight();
            }
        }
        if (example.getHeight() < minimalHeight) {
            classPerson.add(example);
            System.out.println("Элемент был успешно добавлен.");
        } else {
            System.out.println("Элемент не добавлен в коллекцию, потому что его высота" +
                    "больше, чем высота нижнего элемента в коллекции.");
        }
    }

    // Метод добавления элемента в коллекцию, если его высота больше введенной высоты
    public void add_if_max(Person example) {
        long maximalHeight = Long.MIN_VALUE;
        for (Person person : classPerson) {
            if (person.getHeight() > maximalHeight) {
                maximalHeight = person.getHeight();
            }
        }
        if (example.getHeight() > maximalHeight) {
            classPerson.add(example);
            System.out.println("Элемент был успешно добавлен.");
        } else {
            System.out.println("Элемент не добавлен в коллекцию, потому что его высота" +
                    "больше, чем высота нижнего элемента в коллекции.");
        }
    }

    // Метод для печати суммы высот
    public void sum_of_height() {
        double sum = 0;
        for (Person person : classPerson) {
            sum += person.getHeight();
        }
        System.out.println("Сумма значений высоты в этой коллекции равна " + sum);
    }

    // Метод для завершения работы программы
    public void exit() {
        try {
            System.out.println("Завершение работы. ");
            TimeUnit.SECONDS.sleep(3);
            System.exit(0);
        } catch (InterruptedException interruptedException) {
            System.out.println("Завершение работы. ");
            System.exit(0);
        }
    }

    // Метод для удаления всех элементов в колекции
    public void clear() {
        classPerson.clear();
        System.out.println("Коллекция успешно удалена");
    }

    // Метод подсчета количества элементов, хэш-код которых больше введенной национальности
    public void count_greater_than_nationality (Country country) {
        int exampleHashcode = country.hashCode();
        int counter = 0;
        for (Person person : classPerson) {
            if ((person.getNationality()).hashCode() > exampleHashcode) {
                counter += 1;
            }
        }
        System.out.println("Операция была успешно завершена. " + counter + " элементов.");
    }

    // Метод для удаления элемента по ID
    public void remove_by_id(String id) {
        for (Person person : classPerson) {
            int intId = person.getId();
            String strId = String.valueOf(intId);
            if (strId.equals(id)) {
                classPerson.remove(person);
                System.out.println("Элемент был успешно удален.");
            }
        }
        System.out.println("Элемент с этим идентификатором не найден.");
    }

    // Метод для обновления элемента по его индентификатору
    public void update_by_id(String id) {
        for (Person person : classPerson) {
            int intId = person.getId();
            String strId = String.valueOf(intId);
            if (strId.equals(id)) {
                classPerson.remove(person);
                Person updatedPerson = new Person(intId, receiveName(), receiveCoordinates(), person.returnCreationData(),
                        receiveHeight(), receiveEyeColor(), receiveHairColor(), receiveNationality(), receiveLocation());
                classPerson.add(updatedPerson);
                System.out.println("Элемент был успешно обновлен. ");
            }
        }
        System.out.println("Элемент с этим идентификатором не определен. Попробуйте ещё раз. ");
        System.out.println("Элемент с этим идентификатором не найден. ");
    }

    // Метод для подсчета количества и группировки элементов по национальности
    public void group_counting_by_nationality() {
        int indiaCounter = 0;
        int vaticanCounter = 0;
        int italyCounter = 0;
        for (Person person : classPerson) {
            switch (person.getNationality()) {
                case INDIA:
                    indiaCounter += 1;
                case VATICAN:
                    vaticanCounter += 1;
                case ITALY:
                    italyCounter += 1;
            }
        }
        System.out.println("Элементы колекции сгрупирированный по национальности. ");
        System.out.println("Первая группа: Индия. Количество элементов: " + indiaCounter);
        System.out.println("Вторая группа: Ватикан. Количество элементов: " + vaticanCounter);
        System.out.println("Третья группа: Италия. Количество элементов: " + italyCounter);
    }

    // Метод выполнения скрипта из файла

    public void execute_script(String nameOfFile) {
        try {
            System.out.println("Осторожно. Ваш файл не может быть открыт.");
            BufferedReader reader = new BufferedReader(new FileReader(new File(nameOfFile)));
            //private String userCommand;
            String[] finalUserCommand;
            String command;
            while((command = reader.readLine()) != null) {
                finalUserCommand = command.trim().toLowerCase().split(" ", 2);
                switch (finalUserCommand[0]) {
                    case "help":
                        help();
                        break;
                    case "info":
                        info();
                        break;
                    case "show":
                        show();
                        break;
                    case "add":
                        add();
                        break;
                    case "update_by_id":
                        update_by_id(finalUserCommand[1]);
                        break;
                    case "remove_by_id":
                        remove_by_id(finalUserCommand[1]);
                        break;
                    case "clear":
                        clear();
                        break;
                    case "save":
                        save();
                        break;
                    case "execute_script":
                        System.out.println("Скрипт не может быть выполнен этой командой.");
                        break;
                    case "exit":
                        exit();
                    case "add_if_min":
                        add_if_min(new Person(receiveId(), receiveName(), receiveCoordinates(), returnDate(),
                                receiveHeight(), receiveEyeColor(), receiveHairColor(), receiveNationality(),
                                receiveLocation()));
                    case "add_if_max":
                        add_if_max(new Person(receiveId(), receiveName(), receiveCoordinates(), returnDate(),
                                receiveHeight(), receiveEyeColor(), receiveHairColor(), receiveNationality(),
                                receiveLocation())););
                    case "remove_greater":
                        remove_greater(receiveHeight());
                        break;
                    case "remove_lower":
                        remove_lower(receiveHeight());
                        break;
                    case "sum_of_height":
                        sum_of_height();
                        break;
                    case "group_counting_by_nationality":
                        group_counting_by_nationality();
                        break;
                    case "count_greater_than_nationality":
                        count_greater_than_nationality(receiveNationality());
                        break;
                    default:
                        reader.readLine();
                        break;
                }
                System.out.println("Выполнение команды закончено.");
            }
            System.out.println("Выполнение команд закончено.");
            reader.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Файл не найден. Попробуйте ещё раз");
        } catch (IOException ioException) {
            System.out.println("Ошибка при чтении файла. Попробуйье ещё раз");
        }
    }

    // Метод для вывода Data в строку
    public String returnDate() {
        return ZonedDateTime.now().toString();
    }
}


        }
        }
        }
        }
        }
        }