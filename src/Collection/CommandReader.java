package Collection;

import Input.InputInterface;

public class CommandReader {
     /**
     * Считывает данные с клавиатуры и отправляет их в CommandHolder
     */
     public String[] returnCommand(InputInterface inputcommand) {
         return inputcommand.getCurrentinput().toLowerCase().trim().split(" ", 2);
        }
    }

