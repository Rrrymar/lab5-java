import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
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

    public static EyeColor app(File file) throws JAXBException, IOException {

        try {
            Route route;
            int id;
            String name;
            int x;
            Integer y;
            int x1;
            int y1;
            Integer z1;
            Integer eyeColor;
            Integer hairColor;
            Integer country;
            long Location;



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
                if (eElement.getElementsByTagName("id").item(0).getTextContent().equals("") || Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent()) < 1) {
                    System.out.println("id не может быть null, а также должен быть больше 0");
                    System.exit(1);
                } else {
                    id = Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent());
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
                            if (eElement.getElementsByTagName("coordinates").item(0).getFirstChild().getTextContent().equals("") || Integer.parseInt(eElement.getElementsByTagName("coordinates").item(0).getFirstChild().getTextContent()) < -801) {
                                System.out.println("Coordinates : x - Значение поля должно быть => -800, Поле не может быть null");
                                System.exit(1);
                            } else {
                                x = Integer.parseInt(eElement.getElementsByTagName("coordinates").item(0).getFirstChild().getTextContent());
                                if (Integer.parseInt(eElement.getElementsByTagName("coordinates").item(0).getLastChild().getTextContent()) < 991) {
                                    System.out.println("Значение поля должно быть =< 990");
                                    System.exit(1);
                                } else {
                                    y = Integer.parseInt(eElement.getElementsByTagName("coordinates").item(0).getLastChild().getTextContent());
                                    if (eElement.getElementsByTagName("location").item(0).getFirstChild().getTextContent().equals(""))
                                        x1 = 0;
                                    else {
                                        x1 = (int) Long.parseLong(eElement.getElementsByTagName("location").item(0).getFirstChild().getTextContent());
                                        }
                                    }
                                    if (eElement.getElementsByTagName("location").item(0).getFirstChild().getNextSibling().getTextContent().equals("")) {
                                        y1 = 0;
                                    } else {
                                        y1 = (int) Long.parseLong(eElement.getElementsByTagName("location").item(0).getFirstChild().getNextSibling().getTextContent());
                                        }
                                    }
                                    if (eElement.getElementsByTagName("location").item(0).getFirstChild().getNextSibling().getNextSibling().getTextContent().equals("")) {
                                        z1 = 0;
                                    } else {
                                        z1 = Integer.parseInt(eElement.getElementsByTagName("location").item(0).getFirstChild().getNextSibling().getNextSibling().getTextContent());
                                    }

                                    if (eElement.getElementsByTagName("Location").item(0).getTextContent().equals("")) {
                                        System.out.println("Поле не может быть null");
                                        System.exit(1);
                                    }
                                    if (eElement.getElementsByTagName("eyeColor").item(0).getFirstChild().getTextContent().equals("")) {
                                        eyeColor = Integer.parseInt(eElement.getElementsByTagName("eyeColor").item(0).getLastChild().getTextContent());
                                        switch (eyeColor) {
                                            case 1:
                                                return EyeColor.GREEN;
                                            case 2:
                                                return EyeColor.RED;
                                            case 3:
                                                return EyeColor.YELLOW;
                                            default:
                                                break;
                                        }
                                    } else{
                                            hairColor = Integer.parseInt(eElement.getElementsByTagName("hairColor").item(0).getLastChild().getTextContent());
                                            if (eElement.getElementsByTagName("hairColor").item(0).getFirstChild().getTextContent().equals("")) {
                                                switch (hairColor) {
                                                    case 1:
                                                        return HairColor.BLUE;
                                                    case 2:
                                                        return HairColor.RED;
                                                    case 3:
                                                        return HairColor.GREEN;
                                                    default:
                                                        break;
                                                }
                                            } else{
                                            country = Integer.parseInt(eElement.getElementsByTagName("country").item(0).getLastChild().getTextContent());
                                            if (eElement.getElementsByTagName("country").item(0).getFirstChild().getTextContent().equals("")) {
                                                switch (hairColor) {
                                                    case 1:
                                                        return Country.INDIA;
                                                    case 2:
                                                        return Country.VATICAN;
                                                    case 3:
                                                        return Country.ITALY;
                                                    default:
                                                        break;
                                                }
                                            }
                                        else{
                                            Location = Long.parseLong(eElement.getElementsByTagName("location").item(0).getTextContent());
                                            route = new Route(id,
                                                    name);
                                            routeCollection.getCollection().add(route);
                                        }
                                    }
                                }
                            }

                    } else {
                        System.out.println("\nЭлемент с таким ID(" + id + ") уже существует введите другой");
                    }
                    if (temp == nList.getLength() - 1) {
                        routeCollection.setDate(new Date());
                        if (routeCollection.getCollection().size() != 0) {
                            System.out.println("\nЭлементы добавлены в коллекцию");
                            System.out.println("----------------------------");
                        }
                        CommandHolder handler = new CommandHolder(routeCollection);
                        TextInput terminal = new TextInput();
                        terminal.output("Здравствуйте, вы находитесь в интерактивном режиме! Введите help для просмотра возможных команд");
                        while (!terminal.getNextInput().equals("exit")) {
                            handler.doCommand(terminal);
                        }
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            System.out.println("Ошибка парсинга");
            ;
        } catch (SAXParseException e) {
            System.out.println("Отредактируйте документ");
        } catch (NullValue nullValue) {
            System.out.println("Значение равно null");
            ;
        } catch (IncorrectValue incorrectValue) {
            System.out.println("Неверное значение");
            ;
        } catch (SAXException e) {
            System.out.println("Ошибка в XML файле");
            ;
        } catch (NumberFormatException e) {
            System.out.println("Я вас умоляю, не вводите строки туда, где должны быть числа");
            e.printStackTrace();


        }
    }
}
