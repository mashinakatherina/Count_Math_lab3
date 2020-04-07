package Part1.lab3;

import Part1.lab3.gui.UserInterface;

public class Main {

    public static void main(String[] args) {

        UserInterface userInterface = new UserInterface(Math::exp);
        userInterface.draw(1500, 700);
    }
}