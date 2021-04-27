import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class CommandManager {

    /** Менеджер коллекций для реализации команд пользователя */
    private final CollectionManager collectionManager;
    /** Поле для получения команды пользователя */
    private String userCommand;
    /** Поле для разделения пользовательского ввода на команду и аргумент к ней */
    private String[] finalUserCommand;

    {
        userCommand = "";
    }

    /**
     * Конструктор для Менеджера команд
     * @param manager - объект класса ColManager, который будет реализовывать команды пользователя
     */
    public CommandManager(CollectionManager manager) {
        this.collectionManager = manager;
    }

    /** Метод запуска интерактивного настроения */
    public void mod() {
        try {
            try (Scanner commandReader = new Scanner(System.in)) {
                while (!userCommand.equals("exit")) {
                    System.out.print("Введите команду: ");
                    userCommand = commandReader.nextLine();
                    finalUserCommand = userCommand.trim().toLowerCase().split(" ", 2);
                    try {
                        switch (finalUserCommand[0]) {
                            case "":
                                break;
                            case "help":
                                collectionManager.help();
                                break;
                            case "info":
                                collectionManager.info();
                                break;
                            case "show":
                                collectionManager.show();
                                break;
                            case "add":
                                collectionManager.add();
                                break;
                            case "update id":
                                collectionManager.update_by_id(finalUserCommand[1]);
                                break;
                            case "remove_by_id":
                                collectionManager.remove_by_id(finalUserCommand[1]);
                                break;
                            case "clear":
                                collectionManager.clear();
                                break;
                            case "save":
                                collectionManager.save();
                                break;
                            case "execute_script":
                                collectionManager.execute_script(finalUserCommand[1]);
                                break;
                            case "exit":
                                collectionManager.exit();
                                break;
                            case "add_if_min":
                                System.out.println("Введите характеристики элемента, который будет сравниваться с элементами коллекции.");
                                collectionManager.add_if_min(new Person(collectionManager.receiveId(), collectionManager.receiveName(),
                                        collectionManager.receiveCoordinates(), collectionManager.returnDate(),
                                        collectionManager.receiveHeight(), collectionManager.receiveEyeColor(),
                                        collectionManager.receiveHairColor(), collectionManager.receiveNationality(),
                                        collectionManager.receiveLocation()));
                                break;
                            case "add_if_max":
                                System.out.println("Введите характеристики элемента, который будет сравниваться с элементами коллекции.");
                                collectionManager.add_if_max(new Person(collectionManager.resiveId(), collectionManager.reciveName(),
                                        collectionManager.receiveCoordinates(), collectionManager.returnDate(),
                                        collectionManager.receiveHeight(), collectionManager.receiveEyeColor(),
                                        collectionManager.receiveHairColor(), collectionManager.receiveNationality(),
                                        collectionManager.receiveLocation()));
                            case "remove_greater":
                                System.out.println("Введите характеристики элемента, который будет сравниваться с элементами коллекции.");
                                collectionManager.remove_greater(collectionManager.receiveHeight());
                                break;





                            /**
                            case "remove_lower":
                                System.out.println("Введите характеристики элемента, который будет сравниваться с элементами коллекции.");
                                collectionManager.remove_lower(collectionManager.receiveHeight());
                                break;
                            case "sum_of_height":
                                collectionManager.sum_of_height();
                                break;
                            case "group_counting_by_nationality":
                                collectionManager.group_counting_by_nationality();
                                break;
                            case "count_greater_than_nationality":
                                System.out.println("Enter nationality, which will be compared with element`s nationality.");
                                collectionManager.count_greater_than_nationality(collectionManager.receiveNationality());
                                break;
                             */








                            default:
                                System.out.println("Неизвестная команда. Воспользуйтесь командой help");
                                break;
                        }
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        System.out.println("Аргумент команды строки отсутствует. Воспользуйтесь командой help.");
                    }
                }
            }
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println("Завершение работы программы...");
            System.exit(1);
        }
    }

    /** Метод сравнения элемнтов */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommandManager)) return false;
        CommandManager commander = (CommandManager) o;
        return Objects.equals(commander, commander.collectionManager);
    }

    /** Метод получения хэш-кода элемента */
    @Override
    public int hashCode() {
        int result = Objects.hash(collectionManager, userCommand);
        result = 42 * result + Arrays.hashCode(finalUserCommand);
        return result;
    }
}
