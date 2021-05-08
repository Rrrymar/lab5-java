package Collection;

import ErrorEx.IncorrectValue;
import ErrorEx.NullValue;
import Foundation.*;
import Input.InputInterface;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RESERV {
    /**
     * Метод считывает элемент и заносит параметры, создавая тем самым объект
     *
     * @param command
     * @return
     * @throws IncorrectValue
     * @throws NullValue
     */
    public EyeColor readElement(InputInterface command) throws IncorrectValue, NullValue {
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
        long xl1 = 0;
        do {
            command.output("Location: Введите локацию, x:");
            x2 = command.getNextInput().trim();
            try {
                xl1 = Long.parseLong(x2);
            } catch (NumberFormatException n) {
                System.out.println("Это не число");

            }
        } while (xl1 == 0);

        String y2;
        long yl1 = 0;
        do {
            command.output("Введите локацию, y:");
            y2 = command.getNextInput().trim();

            try {
                yl1 = Long.parseLong(y2);

            } catch (NumberFormatException n) {
                System.out.println("Это не число");
            }

        } while (xl1 == 0);

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
        String eyeChoose = null;
        do {
            try {
                command.output(("Выберите вариант цвета глаз YELLOW, RED, GREEN, впишите нужное: "));
                eyeColor1 = command.getNextInput().trim();
                switch (eyeColor1) {
                    case "YELLOW":
                        return EyeColor.YELLOW;
                    case "RED":
                        return EyeColor.RED;
                    case "GREEN":
                        return EyeColor.GREEN;
                    default:
                        break;

                }
                eyeChoose = eyeColor1;
            }catch (NumberFormatException n) {
                System.out.println("Это не представленное значение");
            }

        } while (eyeChoose == null);

        String hairColor1;
        String hairChoose = null;

        do {
            try {
                command.output(("Выберите вариант цвета волос BLUE, RED, GREEN, впишите нужное: "));
                hairColor1 = command.getNextInput().trim();
                switch (hairColor1) {
                    case "BLUE":
                        return HairColor.BLUE;
                    case "RED":
                        return HairColor.RED;
                    case "GREEN":
                        return HairColor.GREEN;
                    default:
                        break;
                }
                hairChoose = hairColor1;
            } catch (NumberFormatException n) {
                System.out.println("Это не тот цвет");

            }

        } while (hairChoose == null);

        String country1;
        String countryChoose = null;
        do {
            command.output(("Выберите вариант страны INDIA, VATICAN, ITALY, впишите нужное: "));
            country1 = command.getNextInput().trim();
            if (country1.equals("INDIA") || country1.equals("VATICAN") || country1.equals("ITALY")) {
                countryChoose = country1;
            } else {
                try {
                    hairChoose = (hairColor1);
                } catch (NumberFormatException n) {
                    System.out.println("Это не та страна");

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
        Route route = new Route(id, name, new Coordinates(x, y), new Location(xl1, yl1, zl1), new EyeColor(eyeChoose), new HairColor(hairChoose), new Country(countryChoose));
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
}
