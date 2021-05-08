package Collection;

import ErrorEx.IncorrectValue;
import ErrorEx.NullValue;
import Foundation.*;
import Input.InputInterface;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
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
                        "\ngroup_counting_by_creation_data: сгруппировать элементы коллекции по значению поля creationDate, вывести количество элементов в каждой группе" +
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
    public Route readElement(InputInterface command) throws IncorrectValue, NullValue {
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
        int x = Integer.parseInt(null);
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
        } while (x == Integer.parseInt(null));

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
            if (x2 == "") {
                xl1 = null;
            } else {
                try {
                    xl1 = Long.parseLong(x2);
                } catch (NumberFormatException n) {
                    System.out.println("Это не число");
                }
            }
        } while (xl1 == null);

        String y2;
        Long yl1 = null;
        do {
            command.output("Введите локацию, y:");
            y2 = command.getNextInput().trim();
            if (y2 == "") {
                yl1 = null;
            } else {
                try {
                    yl1 = Long.parseLong(y2);

                } catch (NumberFormatException n) {
                    System.out.println("Это не число");
                }
            }
        } while (yl1 == null);

        String z1;
        Integer zl1 = null;
        do {
            command.output("Введите локацию, z:");
            z1 = command.getNextInput().trim();
            if (z1 == "") {
                zl1 = null;
            } else {
                try {
                    zl1 = Integer.parseInt(z1);
                } catch (NumberFormatException n) {
                    System.out.println("Это не число");
                }
            }
        } while (zl1 == null);

        String eyeColor1;
        Integer eyeChoose = null;
        do {
            command.output(("Выберите вариант цвета глаз 1 - BLUE, 2 - RED, 3 - Green, впишите нужную цифру: "));
            eyeColor1 = command.getNextInput().trim();
            if (eyeColor1 == "") {
                eyeColor1 = null;
            } else {
                try {
                    eyeChoose = Integer.parseInt(eyeColor1);
                } catch (NumberFormatException n) {
                    System.out.println("Это не число");

              }
            }
        } while (eyeChoose == null);

        String hairColor1;
        Integer hairChoose = null;
        do {
            command.output(("Выберите вариант цвета глаз 1 - BLUE, 2 - RED, 3 - Green, впишите нужную цифру: "));
            hairColor1 = command.getNextInput().trim();
            if (hairColor1 == "") {
                hairColor1 = null;
            } else {
                try {
                    hairChoose = Integer.parseInt(hairColor1);
                } catch (NumberFormatException n) {
                    System.out.println("Это не число");

                }
            }
        } while (hairChoose == null);

        String country1;
        Integer countryChoose = null;
        do {
            command.output(("Выберите вариант цвета глаз 1 - INDIA, 2 - VATICAN, 3 - ITALIA, впишите нужную цифру: "));
            country1 = command.getNextInput().trim();
            if (country1 == "") {
                country1 = null;
            } else {
                try {
                    countryChoose = Integer.parseInt(country1);
                } catch (NumberFormatException n) {
                    System.out.println("Это не число");

                }
            }
        } while (countryChoose == null);

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
        Route route = new Route(id, name, new Coordinates(x, y), new Location(xl1, yl1, zl1), new EyeColor(eyeColor1), new HairColor(hairColor1), new Country(country1));
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
    public void save() throws IOException, ParserConfigurationException, SAXException {
        File outfile = new File(System.getenv("hleb"));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outfile))) {
            String con = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<routecollection>\n";
            writer.write(con);
            System.out.println("----------------------------");
            for (int temp = 0; temp < routeCollection.getCollection().size(); temp++) {
                String content = "    <route>\n" + "        <id>" + routeCollection.getCollection().get(temp).getId() + "</id>\n";
                content = content + "        <name>" + routeCollection.getCollection().get(temp).getName() + "</name>\n";
                content = content + "        <coordinates><x>" + routeCollection.getCollection().get(temp).getCoordinates().getX() + "</x><y>" + routeCollection.getCollection().get(temp).getCoordinates().getY() + "</y></coordinates>\n";
                content = content + "        <location><x>" + routeCollection.getCollection().get(temp).getLocation().getX() + "</x><y>" + routeCollection.getCollection().get(temp).getLocation().getY() + "</y><z>" + routeCollection.getCollection().get(temp).getLocation().getZ() + "</z></location>\n";
                content = content + "        <EyeColor><eyeColor>" + routeCollection.getCollection().get(temp).getEyeColor() + "</eyeColor>\n";
                content = content + "        <HairColor><hairColor>" + routeCollection.getCollection().get(temp).getHairColor() + "</hairColor>\n";
                content = content + "        <Country><country>" + routeCollection.getCollection().get(temp).getCountry() + "</country>\n";
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
    * сгруппировать элементы коллекции по значению поля creationDate,
     * вывести количество элементов в каждой группе
    */
    public void GroupCountingByCreationData(){
        int v = 0;
        if (routeCollection.getCollection().size() != 0) {
            List<Long> collection = new LinkedList<>();
            for (int i = routeCollection.getCollection().size(); i < routeCollection.getCollection().size(); i++) {
                collection.add(routeCollection.getCollection().get(i).getCreationData());
                if (i == routeCollection.getCollection().size()) {
                    v += 1;
                }
            }
            Comparator<Long> comparator = Comparator.comparing(obj -> obj.longValue());
            Collections.sort(collection, comparator);

            for (Long d : collection) {
                System.out.print(d.toString() + " " + v);
            }
        } else {
            System.out.println("Коллекция пуста");
        }
    }



    /**
     * выводит значения поля location в порядке убывания
     */
    public void printFieldDescendingLocation() {
        if (routeCollection.getCollection().size() != 0) {
            List<Location> collection = new LinkedList<>();
            for (int i = routeCollection.getCollection().size(); i > routeCollection.getCollection().size(); i--) {
                collection.add(routeCollection.getCollection().get(i).getLocation());
            }
            Comparator<Long> comparator = Comparator.comparing(obj -> obj.longValue());
            collection.sort(comparator);
            for (Location d : collection) {
                System.out.print(d.toString() + " ");
            }
        } else {
            System.out.println("Коллекция пуста");
        }
    }

    /**
     * Считывает и исполняет скрипт из указанного файла
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
                                String[] arr = new String[12];
                                for (int i = 0; i < arr.length; i++) {
                                    userCommand = commandReader.nextLine().toLowerCase();
                                    arr[i] = userCommand;
                                }
                                Route route = new Route(id, arr[0], new Coordinates(Integer.parseInt(arr[1])), Integer.parseInt(arr[2])), new Location(Integer.parseInt(arr[3]), Integer.parseInt(arr[4]), Integer.parseInt(arr[5])), new EyeColor(Integer.parseInt(arr[6])), new HairColor(Integer.parseInt(arr[7])), new Country(Integer.parseInt(arr[9]));
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
                                    String[] arra = new String[12];
                                    for (int i = 0; i < arra.length; i++) {
                                        userCommand = commandReader.nextLine();
                                        arra[i] = userCommand;
                                    }
                                    Route r = new Route(id, arr[0], new Coordinates(Integer.parseInt(arr[1])), Integer.parseInt(arr[2])), new Location(Integer.parseInt(arr[3]), Integer.parseInt(arr[4]), Integer.parseInt(arr[5])), new EyeColor(Integer.parseInt(arr[6])), new HairColor(Integer.parseInt(arr[7])), new Country(Integer.parseInt(arr[9]));
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
                                save();
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
                                String[] arra = new String[12];
                                for (int i = 0; i < arra.length; i++) {
                                    userCommand = commandReader.nextLine();
                                    arra[i] = userCommand;
                                }
                                Route r = new Route(id, arr[0], new Coordinates(Integer.parseInt(arr[1])), Integer.parseInt(arr[2])), new Location(Integer.parseInt(arr[3]), Integer.parseInt(arr[4]), Integer.parseInt(arr[5])), new EyeColor(Integer.parseInt(arr[6])), new HairColor(Integer.parseInt(arr[7])), new Country(Integer.parseInt(arr[9]));
                                for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                                    if (routeCollection.getCollection().get(i).compareTo(r) == 1) {
                                        count++;
                                    }
                                }
                                if (count == routeCollection.getCollection().size()) {
                                    routeCollection.getCollection().add(r);
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
                                String[] array = new String[12];
                                for (int i = 0; i > array.length; i--) {
                                    userCommand = commandReader.nextLine();
                                    array[i] = userCommand;
                                }
                                Route ro = new Route(id3, array[0], new Coordinates(Integer.parseInt(array[1]), Integer.parseInt(array[2])));
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