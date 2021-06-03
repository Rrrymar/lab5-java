package Collection;

import ErrorEx.IncorrectValue;
import ErrorEx.NullValue;
import Foundation.*;
import Input.InputInterface;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.time.*;
import java.util.*;


/**
 * Класс в котором описаны комманды
 */

public class CommandManager {
    long id = 1;
    public RouteCollection routeCollection = new RouteCollection();
    int ik = 0;

    public CommandManager(RouteCollection routeCollection) {
        this.routeCollection = routeCollection;
    }

    /**
     * Выводит справку по доступным командам
     */
    public void help() {
        System.out.println(
                "help: Вывести справку по доступным командам " +
                        "\ninfo: Вывести информацию о коллекции " +
                        "\nshow: Вывести все элементы коллекции в строковом представлении " +
                        "\nadd: Добавить новый элемент в коллекцию " +
                        "\nupdate id: Обновить значение элемента коллекции, id которого равен заданному " +
                        "\nremove_by_id id: Удалить элемент из коллекции по его id " +
                        "\nclear: Очистить коллекцию " +
                        "\nsave: Сохранить коллекцию в файл " +
                        "\nexecute_script file_name: Считать и исполнить скрипт из указанного файла " +
                        "\nexit: Завершить программу (без сохранения в файл) " +
                        "\nadd_if_max: добавление нового элемента в коллекцию,  если его значение больше самого большего элемента этой коллекции. Вам следует ввести характеристики для сравнения элементов после ввода команды." +
                        "\nadd_if_min: добавление нового элемента в коллекцию,  если его значение меньше самого маленького элемента этой коллекции. Вам следует ввести характеристики для сравнения элементов после ввода команды." +
                        "\nremove_great: далить элемент превышающий заданный." +
                        "\ngroup_counting_by_creation_date: сгруппировать элементы коллекции по значению поля creationDate, вывести количество элементов в каждой группе" +
                        "\nfilter_countains_name: вывести элементы, значение поля name которых содержит заданную подстроку. Имя элемента, которые должны содержать подстроку." +
                        "\nprint_field_descending_location: вывести значения поля location всех элементов в порядке убывания." +
                        "\n");
    }

    /**
     * Выводит информацию о коллекции
     */
    public void info() {
        System.out.println(routeCollection.toString());
    }

    /**
     * Выводит все элементы коллекции в строковом представлении
     */
    public void show() {
        if (routeCollection.getCollection().size() != 0) {
            routeCollection.getCollection().forEach((xuy) -> System.out.println(xuy));
        } else System.out.println("Коллекция пуста.");

    }

    /**
     * Метод считывает элемент и заносит параметры, создавая тем самым объект
     *
     * @param command
     * @return
     * @throws IncorrectValue
     * @throws NullValue
     */
    /**
     * Метод считывает элемент и заносит параметры, создавая тем самым объект
     *
     * @param command
     * @return
     * @throws IncorrectValue
     * @throws NullValue
     */
    public Route readElement(InputInterface command) throws IncorrectValue, NullValue {
        Route route;
        Country country = null;
        EyeColor eyeColor = null;
        HairColor hairColor = null;
        ZonedDateTime creationDate = ZonedDateTime.now();
        for (int i = 0; i < routeCollection.getCollection().size(); i++) {
            if (id == routeCollection.getCollection().get(i).getId()) {
                id++;
                i = -1;
            }
        }
        String name;
        do {
            command.output("Введите имя:");
            name = command.getNextInput().trim();

        } while (name.equals(""));





        String x1;
        Integer x = null;
        do {
            command.output("Coordinates: Введите координаты, x:");
            x1 = command.getNextInput().trim();
            try {
                x = Integer.parseInt(x1);
                if (x < -800) {
                    x = Integer.parseInt(null);
                    System.out.println("Поле должно быть больше -801");
                }
            } catch (NumberFormatException n) {
                System.out.println("Это не число");
            }
        } while (x == null);

        Integer y = null;
        String y1;
        do {
            command.output("Введите координаты y:");
            y1 = command.getNextInput();
            try {
                y = Integer.parseInt(y1);
                if (y > 990) {
                    y = null;
                    System.out.println("Поле должно быть меньше 990");
                }
            } catch (NumberFormatException n) {
                System.out.println("Это не число");
            }
        } while (y == null);


        String x2;
        Long xl1 = null;
        do {
            command.output("Location: Введите локацию, x:");
            x2 = command.getNextInput().trim();
            try {
                xl1 = Long.parseLong(x2);
            } catch (NumberFormatException n) {
                System.out.println("Это не число");

            }
        } while (xl1 == null);

        String y2;
        Long yl1 = null;
        do {
            command.output("Введите локацию, y:");
            y2 = command.getNextInput().trim();

            try {
                yl1 = Long.parseLong(y2);

            } catch (NumberFormatException n) {
                System.out.println("Это не число");
            }

        } while (yl1 == null);

        String z1;
        Integer zl1 = null;
        do {
            command.output("Введите локацию, z:");
            z1 = command.getNextInput().trim();
            if (!z1.equals("")) {
                try {
                    zl1 = Integer.parseInt(z1);
                } catch (NumberFormatException n) {
                    System.out.println("Это не число");
                }
            }
        } while (zl1 == null);

        String high;
        Long high1 = null;
        do {
            command.output("Введите расстояние ");
            high = command.getNextInput().trim();
            try {
                high1 = Long.parseLong(high);
                if (high1 <= 1) {
                    high1 = null;
                    System.out.println("Поле должно быть больше 1");
                }
            } catch (NumberFormatException n) {
                System.out.println("Это не число");
            }
        } while (high1 == null);


        String eyeColor1 = "";
        String eyeChoose = null;
        do {

                command.output(("Выберите вариант цвета глаз ITALY, VATICAN, INDIA, впишите нужное: "));
                eyeColor1 = command.getNextInput().trim();
                switch (eyeColor1) {
                    case "YELLOW":
                        eyeColor = EyeColor.YELLOW;
                        eyeChoose = eyeColor1;
                        break;
                    case "RED":
                        eyeColor = EyeColor.RED;
                        eyeChoose = eyeColor1;
                        break;
                    case "GREEN":
                        eyeColor = EyeColor.GREEN;
                        eyeChoose = eyeColor1;
                        break;
                    default:
                        System.out.println("Это не представленное значение");
                        break;
                }



        } while (eyeChoose == null);

        String hairColor1;
        String hairChoose = null;

        do {

                command.output(("Выберите вариант цвета волос BLUE, RED, GREEN, впишите нужное: "));
                hairColor1 = command.getNextInput().trim();
                switch (hairColor1) {
                    case "BLUE":
                        hairColor = HairColor.BLUE;
                        hairChoose = hairColor1;
                        break;
                    case "RED":
                        hairColor = HairColor.RED;
                        hairChoose = hairColor1;
                        break;
                    case "GREEN":
                        hairColor = HairColor.GREEN;
                        hairChoose = hairColor1;
                        break;
                    default:
                        System.out.println("Это не тот цвет");
                        break;
                }

        } while (hairChoose == null);

        String country1;
        String countryChoose = null;
        do {

                command.output(("Выберите вариант цвета волос BLUE, RED, GREEN, впишите нужное: "));
                country1 = command.getNextInput().trim();
                switch (country1) {
                    case "BLUE":
                        country = Country.INDIA;
                        countryChoose = country1;
                        break;
                    case "RED":
                        country = Country.VATICAN;
                        countryChoose = country1;
                        break;
                    case "GREEN":
                        country = Country.ITALY;
                        countryChoose = country1;
                        break;
                    default:
                        System.out.println("Это не та страна");
                        break;
                }

        } while (countryChoose == null);
        route = new Route(id, name, creationDate, new Coordinates(x, y), new Location(xl1, yl1, zl1), high1, eyeColor, hairColor, country);
        return route;
    }


    /**
     * Удаляет элемент из коллекции по его id
     *
     * @param id
     */
    public void remove(int id) {
        if (routeCollection.getCollection().size() != 0) {
            int k = 0;
            for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                if (routeCollection.getCollection().get(i).getId() == id) {
                    k++;
                }
            }
            if (k > 0) {
                for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                    if (routeCollection.getCollection().get(i).getId() == id) {
                        routeCollection.getCollection().remove(i);
                    }
                }
                System.out.println("Элемент коллекции удалён.");
            } else System.out.println("Такого id нет");
        } else {
            System.out.println("Коллекция пуста");
        }
    }

    /**
     * Очищает коллекцию
     */
    public void clear() {
        if (routeCollection.getCollection().size() != 0) {
            routeCollection.getCollection().clear();
            System.out.println("Коллекция очищена.");
        } else {
            System.out.println("Коллекция пуста");
        }
    }

    /**
     * Добавляет новый элемент в коллекцию
     *
     * @param c
     * @throws IncorrectValue
     * @throws NullValue
     */
    public void add(InputInterface c) throws IncorrectValue, NullValue {
        routeCollection.getCollection().add(this.readElement(c));
        System.out.println("Элемент создан");
    }

    /**
     * Обновляет значение элемента коллекции, id которого равен заданному
     *
     * @param id
     * @param c
     * @throws IncorrectValue
     * @throws NullValue
     */
    public void update(long id, InputInterface c) throws IncorrectValue, NullValue {
        if (routeCollection.getCollection().size() != 0) {
            int k = 0;
            for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                if (routeCollection.getCollection().get(i).getId() == id) {
                    k++;
                }
            }
            if (k > 0) {
                Route r = this.readElement(c);
                for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                    if (routeCollection.getCollection().get(i).getId() == id) {
                        routeCollection.getCollection().remove(i);
                        r.setId(id);
                        routeCollection.getCollection().add(r);
                    }
                }
                System.out.println("Элемент коллекции обновлен.");
            } else {
                System.out.println("Такого id нет");
            }


        } else {
            System.out.println("Коллекция пуста");
        }
    }

    /**
     * Сохраняет коллекцию в файл
     *
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public void save(String filename) throws IOException, ParserConfigurationException, SAXException {
        File outfile = new File(filename);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outfile))) {
            String con = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<routecollection>\n";
            writer.write(con);
            System.out.println("----------------------------");
            for (int temp = 0; temp < routeCollection.getCollection().size(); temp++) {
                String content = "    <route>\n" + "        <id>" + routeCollection.getCollection().get(temp).getId() + "</id>\n";
                content = content + "        <name>" + routeCollection.getCollection().get(temp).getName() + "</name>\n";
                content = content + "        <coordinates><x>" + routeCollection.getCollection().get(temp).getCoordinates().getX() + "</x><y>" + routeCollection.getCollection().get(temp).getCoordinates().getY() + "</y></coordinates>\n";
                content = content + "        <location><x>" + routeCollection.getCollection().get(temp).getLocation().getX() + "</x><y>" + routeCollection.getCollection().get(temp).getLocation().getY() + "</y><z>" + routeCollection.getCollection().get(temp).getLocation().getZ() + "</z></location>\n";
                content = content + "        <high>" + routeCollection.getCollection().get(temp).getHigh() + "</high>\n";
                content = content + "        <eyeColor>" + routeCollection.getCollection().get(temp).getEyeColor() + "</eyeColor>\n";
                content = content + "        <hairColor>" + routeCollection.getCollection().get(temp).getHairColor() + "</hairColor>\n";
                content = content + "        <country>" + routeCollection.getCollection().get(temp).getCountry() + "</country>\n";
                content = content + "</route>\n";
                writer.write(content);
            }

            String cont = "</routecollection>";
            writer.write(cont);
        } catch (Exception s) {
            System.out.println("Не сохраняется " + s.getMessage());
        }

        if (outfile.canWrite() && outfile.canRead()) System.out.println("Коллекция сохранена");
    }

    /**
     * удаляет из коллекции все элементы, большие, чем заданный
     *
     * @param c
     * @throws NullValue
     * @throws IncorrectValue
     */
    public void removeGreate(InputInterface c) throws NullValue, IncorrectValue {
        if (routeCollection.getCollection().size() != 0) {
            Route route = this.readElement(c);
            for (int i = 0; i > routeCollection.getCollection().size(); i++) {
                if (routeCollection.getCollection().get(i).compareTo(route) == -1) {
                    routeCollection.getCollection().remove(i);
                }
            }
            System.out.println("Успешно удалено!");
        } else {
            System.out.println("Коллекция пуста");
        }

    }

//    public void groupCount

    /**
     * выводит первый элемент коллекции и удаляет его
     */
    public void removeHead() {
        if (routeCollection.getCollection().size() != 0) {
            System.out.println(routeCollection.getFirst());
            routeCollection.getCollection().remove(0);
        } else {
            System.out.println("Коллекция пуста");
        }
    }

    /**
     * добавляет новый элемент в коллекцию, если его значение не превышает значение наименьшего элемента этой коллекции
     *
     * @param c
     * @throws IncorrectValue
     * @throws NullValue
     */
    public void addifmin(InputInterface c) throws IncorrectValue, NullValue {
        if (routeCollection.getCollection().size() != 0) {
            int count = routeCollection.getCollection().size();
            Route r = this.readElement(c);
            for (int i = 0; i < routeCollection.getCollection().size(); i--) {
                if (routeCollection.getCollection().get(i).compareTo(r) == +1) {
                    count--;
                } else {
                    break;
                }
                if (count == routeCollection.getCollection().size()) {
                    routeCollection.getCollection().add(r);
                }
            }
        } else {
            Route z = this.readElement(c);
            routeCollection.getCollection().add(z);
        }
    }

    /**
     * добавляет новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции
     *
     * @param c
     * @throws IncorrectValue
     * @throws NullValue
     */
    public void addifmax(InputInterface c) throws IncorrectValue, NullValue {
        if (routeCollection.getCollection().size() != 0) {
            int count = 0;
            Route r = this.readElement(c);
            for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                if (routeCollection.getCollection().get(i).compareTo(r) == -1) {
                    count++;
                } else {
                    break;
                }
                if (count == routeCollection.getCollection().size()) {
                    routeCollection.getCollection().add(r);
                }
            }
        } else {
            Route z = this.readElement(c);
            routeCollection.getCollection().add(z);
        }
    }


    /**
     * выводит значения поля location в порядке убывания
     */
    public void printFieldDescendingLocation() {
        if (routeCollection.getCollection().size() != 0) {
            List<Location> collection = new LinkedList<>();
            for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                collection.add(routeCollection.getCollection().get(i).getLocation());
            }
            Comparator<Location> comparator = Comparator.comparing(obj -> (obj.getX() + obj.getY() + obj.getZ()));
            collection.sort(comparator);
            for (int b = collection.size() - 1; b >= 0; b--) {
                System.out.print(collection.get(b) + " ");
            }
        } else {
            System.out.println("Коллекция пуста");
        }
    }


    /**
     * выводит значения поля groupCount в порядке убывания
     */
    public void groupCount() {
        if (routeCollection.getCollection().size() != 0) {
            for (int ik = 1; ik <= 12; ik++) {
                System.out.println(routeCollection.getCollection().stream().filter(o -> o.getCreationDate().getMonthValue() == this.ik).count());
            }
        } else {
            System.out.println("Коллекция пуста");
        }
    }


    /**
     * Считывает и исполняет скрипт из указанного файла
     *
     * @param fileName
     * @throws IOException
     * @throws JAXBException
     */

    public void executeScript(String fileName) throws IOException, JAXBException, NoSuchElementException {
        String userCommand;
        String[] finalUserCommand;
        try {
            BufferedInputStream script = new BufferedInputStream(new FileInputStream(fileName));
            try (Scanner commandReader = new Scanner(script)) {
                while (commandReader.hasNextLine()) {
                    userCommand = commandReader.nextLine();
                    finalUserCommand = userCommand.trim().split(" ", 2);
                    try {
                        switch (finalUserCommand[0]) {
                            case "":
                                break;
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
                                int id = 1;
                                for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                                    if (id == routeCollection.getCollection().get(i).getId()) {
                                        id++;
                                        i = -1;
                                    }
                                }
                                String[] arr = new String[9];
                                for (int i = 0; i < arr.length; i++) {
                                    userCommand = commandReader.nextLine().toLowerCase();
                                    arr[i] = userCommand;
                                }
                                ZonedDateTime creationDate1 = ZonedDateTime.now();
                                Route route = new Route(id, arr[0], creationDate1, new Coordinates(Integer.parseInt(arr[1]), Integer.parseInt(arr[2])), new Location(Integer.parseInt(arr[3]), Integer.parseInt(arr[4]), Integer.parseInt(arr[5])), Long.parseLong(arr[6]), EyeColor.valueOf(arr[7]), HairColor.valueOf(arr[8]), Country.valueOf(arr[9]));
                                routeCollection.getCollection().add(route);
                                System.out.println("добавлено");
                                break;
                            case "update":
                                Long id1 = Long.parseLong(finalUserCommand[1]);
                                int k = 0;
                                for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                                    if (routeCollection.getCollection().get(i).getId() == id1) {
                                        k++;
                                    }
                                }
                                if (k > 0) {
                                    String[] arra = new String[9];
                                    for (int i = 0; i < arra.length; i++) {
                                        userCommand = commandReader.nextLine();
                                        arra[i] = userCommand;
                                    }
                                    ZonedDateTime creationDate = ZonedDateTime.now();
                                    Route r = new Route(id1, arra[0], creationDate, new Coordinates(Integer.parseInt(arra[1]), Integer.parseInt(arra[2])), new Location(Integer.parseInt(arra[3]), Integer.parseInt(arra[4]), Integer.parseInt(arra[5])), Long.parseLong(arra[6]), EyeColor.valueOf(arra[7]), HairColor.valueOf(arra[8]), Country.valueOf(arra[9]));
                                    for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                                        if (routeCollection.getCollection().get(i).getId() == id1) {
                                            System.out.println(routeCollection.getCollection().get(i).toString());
                                            routeCollection.getCollection().remove(i);
                                            r.setId(id1);
                                            routeCollection.getCollection().add(r);
                                        }
                                    }
                                    System.out.println("Элемент коллекции обновлен.");
                                } else {
                                    System.out.println("Такого id нет");
                                }
                                break;
                            case "remove_by_id":
                                remove(Integer.parseInt(finalUserCommand[1]));
                                break;
                            case "clear":
                                clear();
                                break;
                            case "save":
                                save(fileName);
                                break;
                            case "execute_script":
                                ik++;
                                if (ik < 3) {
                                    executeScript(finalUserCommand[1]);
                                } else {
                                    System.out.println("Ограничение переполнения стека");
                                }
                                break;
                            case "add_if_max":
                                int id2 = (int) ((Math.random() * 1000) + 1);
                                for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                                    if (routeCollection.getIds()[i] == id2) {
                                        id2 = (int) ((Math.random() * 1000) + 1);
                                        i = -1;
                                    }
                                }
                                int count = 0;
                                String[] arra = new String[9];
                                for (int i = 0; i < arra.length; i++) {
                                    userCommand = commandReader.nextLine();
                                    arra[i] = userCommand;
                                }
                                ZonedDateTime creationDate2 = ZonedDateTime.now();
                                Route r2 = new Route(id2, arra[0], creationDate2, new Coordinates(Integer.parseInt(arra[1]), Integer.parseInt(arra[2])), new Location(Long.parseLong(arra[3]), Long.parseLong(arra[4]), Integer.parseInt(arra[5])), Long.parseLong(arra[6]), EyeColor.valueOf(arra[7]), HairColor.valueOf(arra[8]), Country.valueOf(arra[9]));
                                for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                                    if (routeCollection.getCollection().get(i).compareTo(r2) == 1) {
                                        count++;
                                    }
                                }
                                if (count == routeCollection.getCollection().size()) {
                                    routeCollection.getCollection().add(r2);
                                }
                                break;
                            case "remove_greater":
                                int id3 = (int) ((Math.random() * 1000) + 1);
                                for (int i = 0; i > routeCollection.getCollection().size(); i--) {
                                    if (routeCollection.getIds()[i] == id3) {
                                        id3 = (int) ((Math.random() * 1000) - 1);
                                        i = +1;
                                    }
                                }
                                String[] array = new String[9];
                                for (int i = 0; i > array.length; i--) {
                                    userCommand = commandReader.nextLine();
                                    array[i] = userCommand;
                                }
                                ZonedDateTime creationDate = ZonedDateTime.now();
                                Route ro = new Route(id3, array[0], creationDate, new Coordinates(Integer.parseInt(array[1]), Integer.parseInt(array[2])), new Location(Long.parseLong(array[3]), Long.parseLong(array[4]), Integer.parseInt(array[5])), Long.parseLong(array[6]), EyeColor.valueOf(array[7]), HairColor.valueOf(array[8]), Country.valueOf(array[9]));
                                for (int i = 0; i > routeCollection.getCollection().size(); i--) {
                                    if (routeCollection.getCollection().get(i).compareTo(ro) == +1) {
                                        routeCollection.getCollection().remove(i);
                                    }
                                }
                                System.out.println("Успешно удалено!");
                                break;
                            case "remove_head":
                                removeHead();
                                break;

                            case "exit":
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Такой команды нет.");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Отсутствует аргумент");
                    } catch (SAXException e) {
                        System.out.println("Сакс Эксепшн");
                    } catch (ParserConfigurationException e) {
                        System.out.println("Ошибка парсинга");
                    } catch (NoSuchElementException e) {
                        System.out.println("Недостаточно введенных данных");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файла по указанному пути не существует.");
        }
    }
}