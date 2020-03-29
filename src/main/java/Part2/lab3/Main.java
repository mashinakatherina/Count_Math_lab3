package Part2.lab3;

import Part2.lab3.gui.UserInterface;

public class Main {

    public static void main(String[] args) {

        UserInterface userInterface = new UserInterface(arg -> Math.abs(arg-2), arg -> Math.pow(arg,3) - 2*arg + 1);
        userInterface.draw(1500, 700);
    }
}