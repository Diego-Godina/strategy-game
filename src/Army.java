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

    public Army() {

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
        //Cada unidade militar será adicionada a uma lista de Unidades militares
        //E uma lista de uma ou mais unidade compoêm um exercito
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

    public void attackArmy(Army armyToAttack, int limitSizeBoard) {
        for(MilitaryUnit unit : this.militaryUnits) {
            for(MilitaryUnit unit2 : armyToAttack.getMilitaryUnit()) {
                int rangeToCalc = Math.abs(unit.getPositionX() - unit2.getPositionX()) + Math.abs(unit.getPositionY() - unit2.getPositionY());
                if(rangeToCalc <= unit.getRange()) {
                    unit.attack(unit2);
                }
            }
        }

        /**
         * FALTA
         * Verificar se uma unidade militar está no alcance de todas as outras do outro exército
         * se estiver no alcance chamar o método attack da unidade militar atacante
         * e passar como parametro a unidade militar a ser atacada
         */
    }

    public void print() {
        for (int i = 0; i < militaryUnits.size(); i++) {
            militaryUnits.get(i).print();
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "Exercito{" +
                "\nUnidade militar: " + militaryUnits;

    }

}
