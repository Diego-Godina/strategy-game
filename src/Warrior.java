import Utils.InputValidation;

import java.util.Scanner;

public class Warrior extends MilitaryUnit {
    protected int shieldPoints;

    public Warrior(Scanner sc, int position) {
        super(sc);
        shieldPoints = InputValidation.validateIntGT0(sc, "Introduza os pontos de blindagem: ");
        id = position + 1;
        name = "Guerreiro " + id;
    }

    public void defend(int damage) {
        if(shieldPoints > 0) {
            if(damage <= shieldPoints) {
                shieldPoints -= damage;
                System.out.println("Guerreiro " + name + " perdeu " + damage + " pontos de blindagem");
            } else {
                System.out.println("Guerreiro " + name + " perdeu " + shieldPoints + " pontos de blindagem");
                damage = damage - shieldPoints;
                shieldPoints = 0;
                super.defend(damage);
            }
        }

        super.defend(damage);
    }

    public void print() {
        super.print();

        System.out.println("Pontos de blindagem: " + shieldPoints);
    }
}
