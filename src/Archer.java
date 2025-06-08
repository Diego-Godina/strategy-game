import Utils.InputValidation;

import java.util.Scanner;

public class Archer extends MilitaryUnit {
    protected int increaseAttackPoints;

    public Archer(Scanner sc) {
        super(sc);

        increaseAttackPoints = InputValidation.validateIntGT0(sc, "Introduza o aumento dos pontos de ataque: ");
    }

    public void attack(MilitaryUnit militaryUnit) {
        attackPoints = attackPoints * increaseAttackPoints;

        super.attack(militaryUnit);
    }

    public void print() {
        super.print();

        System.out.println("Aumento dos pontos de ataque: " + increaseAttackPoints);
    }
}
