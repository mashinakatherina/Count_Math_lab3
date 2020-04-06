package Part2.lab3;

import Part2.lab3.gui.UserInterface;

public class Main {

    public static void main(String[] args) {

        UserInterface userInterface = new UserInterface(Math::exp, Math::exp);
        userInterface.draw(1500, 700);
    }
}