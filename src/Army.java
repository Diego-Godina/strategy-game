import java.util.ArrayList;
import java.util.Scanner;

public class Army {
    private ArrayList<MilitaryUnit> militaryUnits = new ArrayList<>();
    private int limitCost;
    String nameUnit = null;
    int positionUnit;

    public Army(int limitCost) {
        this.limitCost = limitCost;
    }

    public ArrayList<MilitaryUnit> getMilitaryUnit() {
        return militaryUnits;
    }

    public void setMilitaryUnit(ArrayList<MilitaryUnit> militaryUnit) {
        this.militaryUnits = militaryUnit;
    }

    public int getLimitCost() {
        return limitCost;
    }

    public void setLimitCost(int limitCost) {
        this.limitCost = limitCost;
    }

    public void addMilitaryUnit(MilitaryUnit unit) {
        militaryUnits.add(unit);
        System.out.println("Adicionado com sucesso");
    }

    public void removeMilitaryUnit(int positionUnit) {
         militaryUnits.remove(positionUnit);
    }

    public void positionArmyManually(Scanner sc, int limitSizeBoard) {
        for (MilitaryUnit unit : militaryUnits) {
            unit.positionManually(sc, limitSizeBoard);
        }
    }

    public void positionArmyRandom(int limitSizeBoard) {
        for (MilitaryUnit unit : militaryUnits) {
            unit.positionRandom(limitSizeBoard);
        }
    }

    public void moveArmy(int limitSizeBoard) {
        for(MilitaryUnit unit : militaryUnits) {
            unit.move(limitSizeBoard);
        }
    }

    public void attackArmy(Army armyToAttack) {
        for(MilitaryUnit unit : militaryUnits) {
            for(MilitaryUnit unit2 : armyToAttack.getMilitaryUnit()) {
                int rangeToCalc = Math.abs(unit.getPositionX() - unit2.getPositionX()) + Math.abs(unit.getPositionY() - unit2.getPositionY());
                if(rangeToCalc <= unit.getRange()) {
                    unit.attack(unit2);
                }
            }
        }
    }

    public void print() {
        for (MilitaryUnit militaryUnit : militaryUnits) {
            if(militaryUnit.getLifeScore() <= 0) continue;

            militaryUnit.print();
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "Exercito{" +
                "\nUnidade militar: " + militaryUnits;

    }

}
