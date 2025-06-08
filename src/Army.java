import java.util.ArrayList;

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

    public MilitaryUnit removeMilitaryUnit(int positionUnit) {
        return militaryUnits.remove(positionUnit);
    }

    public void printMilitaryUnit() {
        for (int i = 0; i < militaryUnits.size(); i++) {
            System.out.println("Unidade " + (i + 1) + ":" + militaryUnits.get(i));
            System.out.println();
        }
        System.out.println("\n");
    }

    @Override
    public String toString() {
        return "Exercito{" +
                "\nUnidade militar: " + militaryUnits;

    }

}
