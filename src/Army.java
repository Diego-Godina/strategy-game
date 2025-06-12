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

    //Adiciona as unidades militares em um Array List
    public void addMilitaryUnit(MilitaryUnit unit) {
        militaryUnits.add(unit);
        System.out.println("Adicionado com sucesso");
    }

    //Removi as unidades militares a partir da posição
    public void removeMilitaryUnit(int positionUnit) {
         militaryUnits.remove(positionUnit);
    }

    /*
    Itera por todas as unidades de determinado exercito, envia o limite do tabuleiro anteriormente fornecido pelo utilizador
    e envia para o metodo que guarda as duas posições fornecidas pelo utilizador
    */
    public void positionArmyManually(Scanner sc, int limitSizeBoard) {
        for (MilitaryUnit unit : militaryUnits) {
            unit.positionManually(sc, limitSizeBoard);
        }
    }

    /*
    Itera por todas as unidades de um exercíto, envia o limite do tabuleiro fornecido anteriormente para o metodo
    onde será gerado as posições aleatoriamente.
    */
    public void positionArmyRandom(int limitSizeBoard) {
        for (MilitaryUnit unit : militaryUnits) {
            unit.positionRandom(limitSizeBoard);
        }
    }

    //Itera por todas as unidades e chama o metodo que faz cada unidade se mover para diferentes direções.
    public void moveArmy(int limitSizeBoard) {
        for(MilitaryUnit unit : militaryUnits) {
            unit.move(limitSizeBoard);
        }
    }

    /*
    O primeiro for itera as unidades militares atacante e o segundo itera as unidades militares alvo. Antes do ataque é
    calculado o alcance que é a soma das diferenças absoluta entre a unidade atacante e a unidade alvo e caso o alvo estiver
    no alcance, ou seja, o valor do alcance calculado é menor ou igual a valor do alcance dado a unidade atacante, então a unidade
    atacante pode atacar.
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

    //Imprimi as informações ou os dados das unidades militares.
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
