package Input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileInput extends InputInterface {
    public FileInput(String fileName) throws FileNotFoundException {

        String file = fileName;
        this.in = new Scanner(new File(file));
    }

    private Scanner in;
    private String fileLine;

    @Override
    public String getCurrentinput() {
        return fileLine;
    }

    @Override
    public String getNextInput() {

        if (in.hasNext()) {
            fileLine = in.nextLine().toLowerCase();
            System.out.println(fileLine);
            return fileLine;
        } else
            return null;


    }

    @Override
    public void output(String message) {
        System.out.println(message);
    }

    @Override
    public Float getNextFloatInput() {
        return in.nextFloat();
    }

    @Override
    public Long getNextLongInput() {
        return in.nextLong();
    }
}


