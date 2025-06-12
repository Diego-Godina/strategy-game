import java.util.ArrayList;
import java.util.Scanner;

public class Army {
    private ArrayList<MilitaryUnit> militaryUnits = new ArrayList<>();
    private int limitCost;

    /**
     * Army constructor
     *
     * @param limitCost
     */
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

    /**
     * Adds military units to an Array List
     *
     * @param unit
     */
    public void addMilitaryUnit(MilitaryUnit unit) {
        militaryUnits.add(unit);
        System.out.println("Adicionado com sucesso");
    }

    /**
     * Removes military units from the position
     *
     * @param positionUnit
     */
    public void removeMilitaryUnit(int positionUnit) {
         militaryUnits.remove(positionUnit);
    }

    /**
     * Iterates through all units of a given army, sends the board limit previously provided by the user
     * and sends it to the method that saves the two positions provided by the user
     *
     * @param sc
     * @param limitSizeBoard
     */
    public void positionArmyManually(Scanner sc, int limitSizeBoard) {
        for (MilitaryUnit unit : militaryUnits) {
            unit.positionManually(sc, limitSizeBoard);
        }
    }

    /**
     * Iterates through all units of an army, sends the previously provided board limit to the method
     * where the positions will be generated randomly.
     *
     * @param limitSizeBoard
     */
    public void positionArmyRandom(int limitSizeBoard) {
        for (MilitaryUnit unit : militaryUnits) {
            unit.positionRandom(limitSizeBoard);
        }
    }

    /**
     * Iterates through all units and calls the method that makes each unit move in different directions.
     *
     * @param limitSizeBoard
     */
    public void moveArmy(int limitSizeBoard) {
        for(MilitaryUnit unit : militaryUnits) {
            unit.move(limitSizeBoard);
        }
    }

    /**
     * The first for loop iterates the attacking military units and the second loop iterates the target military units.
     * Before the attack, the range is calculated, which is the sum of the absolute differences between the attacking unit and the target unit.
     * If the target is in range, that is, the calculated range value is less than or equal to the range value given to the attacking unit,
     * then the attacking unit can attack.
     *
     * @param armyToAttack
     */
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

    /**
     * Print the information or data of the military units.
     *
     */
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
