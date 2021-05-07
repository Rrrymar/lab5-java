package Input;

import java.util.Scanner;

/**
 * Класс работающий с вводом с клавиатуры
 */
public class TextInput implements InputInterface {
    private String currentInput;

    @Override
    public String getNextInput() {
        Scanner sc = new Scanner(System.in);
        currentInput = sc.nextLine().trim();
        return currentInput;
    }

    @Override
    public void output(String s) {
        System.out.println(s);
    }

    @Override
    public Float getNextFloatInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextFloat();
    }

    @Override
    public Long getNextLongInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLong();
    }

    @Override
    public String getCurrentinput() {
        return currentInput;
    }
}

