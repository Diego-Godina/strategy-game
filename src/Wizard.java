import Utils.InputValidation;

import java.util.Scanner;

public class Wizard extends MilitaryUnit {
    protected int maxNumberSpells;

    public Wizard(Scanner sc, int position) {
        super(sc);

        maxNumberSpells = InputValidation.validateIntGT0(sc, "Introduza a quantidade de feitiços: ");
        id = position + 1;
        name = "Feiticeiro " + id;
    }

    public void attack(MilitaryUnit militaryUnit) {
        if(maxNumberSpells <= 0) return;

        super.attack(militaryUnit);
        maxNumberSpells--;
    }

    public void print() {
        super.print();

        System.out.println("Número de feitiços: " + maxNumberSpells);
    }
}
