package Part1.lab3;

import Part1.lab3.gui.UserInterface;

public class Main {

    public static void main(String[] args) {

        UserInterface userInterface = new UserInterface((arg) -> {return Math.abs(Math.pow(arg, 3) - arg) - 2*arg +2;});
        userInterface.draw(1500, 700);
    }
}