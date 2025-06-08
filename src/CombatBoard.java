import Utils.InputValidation;

import java.util.ArrayList;
import java.util.Scanner;

public class CombatBoard {
    private Army army1;
    private Army army2;
    private int qtnSteps;
    private int positionChoice;
    private int limitSizeBoard;
    private int[] positionArmy1;
    private int[] positionArmy2;


    public CombatBoard() {

    }

    public CombatBoard(Army army1, Army army2, int qtnSteps, int positionChoice, int limitSizeBoard, int[] positionArmy1, int[] positionArmy2) {
        this.army1 = army1;
        this.army2 = army2;
        this.qtnSteps = qtnSteps;
        this.positionChoice = positionChoice;
        this.limitSizeBoard = limitSizeBoard;
        this.positionArmy1 = positionArmy1;
        this.positionArmy2 = positionArmy2;
    }

    public CombatBoard(Scanner sc) {
        System.out.println("Tabuleiro");
        limitSizeBoard = InputValidation.validateIntGT0(sc, "Forneça o tamanho do tabuleiro:");

    }

    public Army getArmy1() {
        return army1;
    }

    public void setArmy1(Army army1) {
        this.army1 = army1;
    }

    public Army getArmy2() {
        return army2;
    }

    public void setArmy2(Army army2) {
        this.army2 = army2;
    }

    public int getQtnSteps() {
        return qtnSteps;
    }

    public void setQtnSteps(int qtnSteps) {
        this.qtnSteps = qtnSteps;
    }

    public int getPositionChoice() {
        return positionChoice;
    }

    public void setPositionChoice(int positionChoice) {
        this.positionChoice = positionChoice;
    }

    public int[] getPositionArmy1() {
        return positionArmy1;
    }

    public int getLimitSizeBoard() {
        return limitSizeBoard;
    }

    public void setLimitSizeBoard(int limitSizeBoard) {
        this.limitSizeBoard = limitSizeBoard;
    }

    public void setPositionArmy1(int[] positions) {
        this.positionArmy1 = positions;
    }

    public int[] getPositionArmy2() {
        return positionArmy2;
    }

    public void setPositionArmy2(int[] positionArmy2) {
        this.positionArmy2 = positionArmy2;
    }

    public int[] getPositionUnityArmy(Scanner sc) {
        for(int j = 0; j < army1.getMilitaryUnit().size() * 2; j++) {
            System.out.println("Posição X do exercito 1: ");
            positionArmy1[j] = sc.nextInt();
            System.out.println("Posição Y do exercito 1: ");
            positionArmy1[j+1] = sc.nextInt();
        }
        return positionArmy1;
    }


    public void showState(Army army1) {
        System.out.println("Estado - Tabuleiro");
        positionArmy1 = getPositionArmy1();
        for(int i = 0; i < getLimitSizeBoard(); i++){
            for(int j = 0; j < getLimitSizeBoard(); j++){
                for(int k = 0; k < army1.getMilitaryUnit().size(); k++){
                    int X = positionArmy1[k * 2];
                    int Y = positionArmy1[k * 2 + 1];
                    if(i == Y && j == X){
                        System.out.print("(  " + army1.getMilitaryUnit().get(k).getName() + " ) " );
                        System.out.println("Posicao: ( " + X + ", " + Y + " )");
                    }

                }
            }
            System.out.println();
        }
    }


    @Override
    public String toString() {
        return "CombatBoard{" +
                "army1=" + army1 +
                ", army2=" + army2 +
                ", qtnSteps=" + qtnSteps +
                ", positionChoice=" + positionChoice +
                ", limitSizeBoard=" + limitSizeBoard +
                ", positionArm1" + positionArmy1 +
                ", positionArm2" + positionArmy2 +
                '}';
    }
}
