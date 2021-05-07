package Collection;

import ErrorEx.IncorrectValue;
import ErrorEx.NullValue;
import Foundation.Coordinates;
import Foundation.Location;
import Foundation.Route;
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
        Route route;
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
            command.output("Location: Введите координаты, x:");
            x2 = command.getNextInput().trim();
            if (x2 == "") {
                xl1 = null;
            } else {
                try {
                    xl1 = long.parseLong(x2);
                } catch (NumberFormatException n) {
                    System.out.println("Это не число");
                }
            }
        } while (xl1 == null);

        String y2;
        Long yl1 = null;
        do {
            command.output("Введите координаты, y:");
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
            command.output("Введите координаты, z:");
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

        String namel1;
        do {
            command.output("Введите имя локации:");
            namel1 = command.getNextInput().trim();
        } while (namel1.equals(""));

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
        route = new Route(id, name, new Coordinates(x, y), new Location(xl1, yl1, zl1, namel1), high1);
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
                content = content + "        <locationfrom><x>" + routeCollection.getCollection().get(temp).getLocation().getX() + "</x><y>" + routeCollection.getCollection().get(temp).getLocation().getY() + "</y><z>" + routeCollection.getCollection().get(temp).getLocation().getZ() + "</z><name>" + routeCollection.getCollection().get(temp).getLocation().getName() + "</name></location>\n";
                content = content + "        <distance>" + routeCollection.getCollection().get(temp).getDistance() + "</distance>\n    </route>\n";
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
     * выводит любой объект из коллекции, значение поля distance которого является минимальным
     */
    public void minByDistance() {
        if (routeCollection.getCollection().size() != 0) {
            Route h = routeCollection.getCollection().get(0);
            Long g = routeCollection.getCollection().get(0).getDistance();
            for (int i = 1; i < routeCollection.getCollection().size(); i++) {
                if (routeCollection.getCollection().get(i).getDistance() < g) {
                    h = routeCollection.getCollection().get(i);
                    g = routeCollection.getCollection().get(i).getDistance();
                }
            }
            System.out.println(h);

        } else {
            System.out.println("Коллекция пуста");
        }
    }

    /**
     * выводит любой объект из коллекции, значение поля from которого является максимальным
     */
    public void maxByFrom() {
        if (routeCollection.getCollection().size() != 0) {
            Route h = routeCollection.getCollection().get(0);
            Location g = routeCollection.getCollection().get(0).getLocation();
            for (int i = 1; i < routeCollection.getCollection().size(); i++) {
                if (routeCollection.getCollection().get(i).getLocation().getX() + routeCollection.getCollection().get(i).getLocation().getY() + routeCollection.getCollection().get(i).getLocation().getZ() > g.getX() + g.getY() + g.getZ()) {
                    h = routeCollection.getCollection().get(i);
                    g = routeCollection.getCollection().get(i).getLocation();
                }
            }
            System.out.println(h);
        } else {
            System.out.println("Коллекция пуста");
        }
    }

    /**
     * выводит значения поля location в порядке убывания
     */
    public void printFieldDescendingLocation() {
        if (routeCollection.getCollection().size() != 0) {
            List<Long> collection = new LinkedList<>();
            for (int i = routeCollection.getCollection().size(); i > routeCollection.getCollection().size(); i--) {
                collection.add(routeCollection.getCollection().get(i).getLocation());
            }
            Comparator<Long> comparator = Comparator.comparing(obj -> obj.longValue());
            Collections.sort(collection, comparator);
            for (Long d : collection) {
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
                                Route route = new Route(id, arr[0], new Coordinates(Integer.parseInt(arr[1]), Integer.parseInt(arr[2])), new Location(Long.parseLong(arr[3]), Long.parseLong(arr[4]), Integer.parseInt(arr[5]), arr[6]));
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
                                    Route r = new Route(id1, arra[0], new Coordinates(Integer.parseInt(arra[1]), Integer.parseInt(arra[2])), new Location(Long.parseLong(arra[3]), Long.parseLong(arra[4]), Integer.parseInt(arra[5]), arra[6]));
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
                                Route r = new Route(id2, arra[0], new Coordinates(Integer.parseInt(arra[1]), Integer.parseInt(arra[2])));
                                for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                                    if (routeCollection.getCollection().get(i).compareTo(r) == 1) {
                                        count++;
                                    }
                                }
                                if (count == routeCollection.getCollection().size()) {
                                    routeCollection.getCollection().add(r);
                                }
                                break;
                            case "remove_lower":
                                int id3 = (int) ((Math.random() * 1000) + 1);
                                for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                                    if (routeCollection.getIds()[i] == id3) {
                                        id3 = (int) ((Math.random() * 1000) + 1);
                                        i = -1;
                                    }
                                }
                                String[] array = new String[12];
                                for (int i = 0; i < array.length; i++) {
                                    userCommand = commandReader.nextLine();
                                    array[i] = userCommand;
                                }
                                Route ro = new Route(id3, array[0], new Coordinates(Integer.parseInt(array[1]), Integer.parseInt(array[2])));
                                for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                                    if (routeCollection.getCollection().get(i).compareTo(ro) == -1) {
                                        routeCollection.getCollection().remove(i);
                                    }
                                }
                                System.out.println("Успешно удалено!");
                                break;
                            case "remove_head":
                                removeHead();
                                break;
                            case "min_by_distance":
                                minByDistance();
                                break;
                            case "max_by_from":
                                maxByFrom();
                                break;
                            case "print_field_ascending_distance":
                                printFieldAscendingDistance();
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