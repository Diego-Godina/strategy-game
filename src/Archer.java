import Utils.InputValidation;

import java.util.Scanner;

public class Archer extends MilitaryUnit {
    protected int increaseAttackPoints;

    /**
     * Archer constructor
     *
     * @param sc
     */
    public Archer(Scanner sc) {
        super(sc);

        increaseAttackPoints = InputValidation.validateIntGT0(sc, "Introduza o aumento dos pontos de ataque: ");
    }

    /**
     * Attack method considering the increase attack points
     *
     * @param militaryUnit
     */
    public void attack(MilitaryUnit militaryUnit) {
        attackPointsEffect = attackPoints * increaseAttackPoints;

        super.attack(militaryUnit);
    }

    /**
     * Print method
     *
     */
    public void print() {
        super.print();

        System.out.println("Aumento dos pontos de ataque: " + increaseAttackPoints);
    }
}
