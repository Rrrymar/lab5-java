import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.time.ZonedDateTime;
import java.util.Date;

import Collection.*;
import ErrorEx.ErrorCheck;
import ErrorEx.IncorrectValue;
import ErrorEx.NullValue;
import Foundation.*;
import Input.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Добавляет коллекцию из файла и запускает интерактивное приложение
 */

public class Asked {
    public static RouteCollection routeCollection = new RouteCollection();

    public static void app(File file) throws JAXBException, IOException {

        try {
            Route route;
            Long id;
            String name = null;
            int x = 0;
            long high = 0;
            Integer y = 0;
            long x1 = 0;
            long y1 = 0;
            Integer z1 = 0;
            EyeColor eyeColor = null;
            String eyeColor1 = null;
            String hairColor1 = null;
            HairColor hairColor = null;
            String country1;
            Country country = null;


            System.out.println(String.valueOf(file));
            File fXmlFile = new File(String.valueOf(file));
            BufferedInputStream script = new BufferedInputStream(new FileInputStream(fXmlFile));
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setValidating(true);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            ErrorCheck e = new ErrorCheck();
            dBuilder.setErrorHandler(e);
            Document doc = dBuilder.parse(script);


            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("route");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                int c = 0;
                Node nNode = nList.item(temp);
                org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;
                if (eElement.getElementsByTagName("id").item(0).getTextContent().equals("") || Long.parseLong(eElement.getElementsByTagName("id").item(0).getTextContent()) < 1) {
                    System.out.println("id не может быть null, а также должен быть больше 0");
                    System.exit(1);
                } else {
                    id = Long.parseLong(eElement.getElementsByTagName("id").item(0).getTextContent());
                    for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                        if (routeCollection.getIds()[i] == id) {
                            c++;
                        }
                    }
                    if (c == 0) {
                        if (eElement.getElementsByTagName("name").item(0).getTextContent().equals("")) {
                            System.out.println("Имя не может быть пустой строкой");
                            System.exit(1);
                        } else {
                            name = eElement.getElementsByTagName("name").item(0).getTextContent();
                            if (eElement.getElementsByTagName("coordinates").item(0).getFirstChild().getTextContent().equals("") || Integer.parseInt(eElement.getElementsByTagName("coordinates").item(0).getFirstChild().getTextContent()) < -800) {
                                System.out.println("Coordinates : x - Значение поля должно быть => -800, Поле не может быть null");
                                System.exit(1);
                            } else {
                                x = Integer.parseInt(eElement.getElementsByTagName("coordinates").item(0).getFirstChild().getTextContent());
                                System.out.println(x);
                                if (Integer.parseInt(eElement.getElementsByTagName("coordinates").item(0).getLastChild().getTextContent()) > 990) {
                                    System.out.println("Значение поля должно быть =< 990");
                                    System.exit(1);
                                } else {
                                    y = Integer.parseInt(eElement.getElementsByTagName("coordinates").item(0).getLastChild().getTextContent());
                                    System.out.println(y);
                                    if (eElement.getElementsByTagName("location").item(0).getFirstChild().getTextContent().equals("")) {
                                        x1 = 0;
                                    } else {
                                        x1 = Long.parseLong(eElement.getElementsByTagName("location").item(0).getFirstChild().getTextContent());
                                    }

                                    if (eElement.getElementsByTagName("location").item(0).getFirstChild().getNextSibling().getTextContent().equals("")) {
                                        y1 = 0;
                                    } else {
                                        y1 = Long.parseLong(eElement.getElementsByTagName("location").item(0).getFirstChild().getNextSibling().getTextContent());
                                    }
                                    if (eElement.getElementsByTagName("location").item(0).getFirstChild().getNextSibling().getNextSibling().getTextContent().equals("")) {
                                        z1 = 0;
                                    } else {
                                        z1 = Integer.parseInt(eElement.getElementsByTagName("location").item(0).getFirstChild().getNextSibling().getNextSibling().getTextContent());
                                        }

                                    }
                                    System.out.println(eElement.getElementsByTagName("id").item(0).getTextContent());
                                    System.out.println(eElement.getElementsByTagName("high").item(0).getTextContent());

                                    if (eElement.getElementsByTagName("high").item(0).getTextContent().equals("")) {
                                        System.out.println("Поле не может быть null");
                                        System.exit(1);
                                    } else {
                                        high = Long.parseLong(eElement.getElementsByTagName("high").item(0).getTextContent());
                                    }
                                    if (!eElement.getElementsByTagName("eyeColor").item(0).getTextContent().equals("")) {
                                        eyeColor1 = String.valueOf(eElement.getElementsByTagName("eyeColor").item(0).getLastChild().getTextContent());
                                        System.out.println(eyeColor1);
                                        switch (eyeColor1) {
                                            case "GREEN":
                                                eyeColor = EyeColor.GREEN;
                                                break;
                                            case "RED":
                                                eyeColor = EyeColor.RED;
                                                break;
                                            case "YELLOW":
                                                eyeColor = EyeColor.YELLOW;
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                    if (!eElement.getElementsByTagName("hairColor").item(0).getTextContent().equals("")) {
                                        hairColor1 = String.valueOf(eElement.getElementsByTagName("hairColor").item(0).getLastChild().getTextContent());
                                        System.out.println(hairColor1);
                                        switch (hairColor1) {
                                            case "BLUE":
                                                hairColor = HairColor.BLUE;
                                                break;
                                            case "RED":
                                                hairColor = HairColor.RED;
                                                break;
                                            case "GREEN":
                                                hairColor = HairColor.GREEN;
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                    if (!eElement.getElementsByTagName("country").item(0).getTextContent().equals("")) {
                                        country1 = String.valueOf(eElement.getElementsByTagName("country").item(0).getLastChild().getTextContent());
                                        System.out.println(country1);
                                        switch (country1) {
                                            case "INDIA":
                                                country = Country.INDIA;
                                                break;
                                            case "VATICAN":
                                                country = Country.VATICAN;
                                                break;
                                            case "ITALY":
                                                country = Country.ITALY;
                                                break;
                                            default:
                                                break;
                                    }
                                }
                            }
                        }
                        ZonedDateTime creationDate = ZonedDateTime.now();
                        route = new Route(id,name, creationDate, new Coordinates(x, y), new Location(x1, y1, z1), high, eyeColor, hairColor, country);
                        routeCollection.getCollection().add(route);
                        System.out.println(route);
                    }

                    else{
                        System.out.println("\nЭлемент с таким ID(" + id + ") уже существует введите другой");
                    }
                    } if (temp == nList.getLength() - 1) {
                        routeCollection.setDate(ZonedDateTime.now());
                        if (routeCollection.getCollection().size() != 0) {
                            System.out.println("\nЭлементы добавлены в коллекцию");
                            System.out.println("----------------------------");
                        }
                        CommandHolder handler = new CommandHolder(routeCollection);
                        TextInput terminal = new TextInput();
                        terminal.output("Здравствуйте, вы находитесь в интерактивном режиме! Введите help для просмотра возможных команд");
                        while (!terminal.getNextInput().equals("exit")) {
                            handler.doCommand(terminal,file.getAbsolutePath());
                        }
                    }
                }

        } catch (ParserConfigurationException e) {
            System.out.println("Ошибка парсинга");
            //e.printStackTrace();
        } catch (SAXParseException e) {
            System.out.println("Отредактируйте документ");
            //e.printStackTrace();
        } catch (NullValue nullValue) {
            System.out.println("Значение равно null");
            //nullValue.printStackTrace();
        } catch (IncorrectValue incorrectValue) {
            //incorrectValue.printStackTrace();
            System.out.println("Неверное значение");
        } catch (SAXException e) {
            //e.printStackTrace();
            System.out.println("Ошибка в XML файле");
        } catch (NumberFormatException e) {
            System.out.println("Я вас умоляю, не вводите строки туда, где должны быть числа");
            //e.printStackTrace();
        }
    }
}
