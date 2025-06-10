import Utils.InputValidation;

import java.util.ArrayList;
import java.util.Scanner;

public class CombatBoard {
    private Army army1;
    private Army army2;
    private int limitSizeBoard;

    public CombatBoard(Army army1, Army army2, int limitSizeBoard) {
        this.army1 = army1;
        this.army2 = army2;
        this.limitSizeBoard = limitSizeBoard;
    }

    public CombatBoard(Scanner sc) {
        limitSizeBoard = InputValidation.validateIntGT0(sc, "Forneça o tamanho do tabuleiro: ");
    }

    public int game(Scanner sc) {
        int qtnSteps = InputValidation.validateIntGT0(sc, "Forneça a quantidade de passos: ");
        int winner = -1;

        for (int i=0; i < qtnSteps; i++) {
            army1.moveArmy(limitSizeBoard);
            army2.moveArmy(limitSizeBoard);

            print();

            winner = checkFinishGame();
            if(winner != -1) {
                return checkFinishGame();
            }
        }

        System.out.println("Simulação finalizada");
        return winner;
    }

    public int checkFinishGame() {
        /* FALTA
         * iterar pelo exército 1 e 2 e verificar se há pontos de vida em cada unidade militar,
         * se não houver retornar 1 caso o exército 1 tenha ganhado
         * retornar 2 caso o exército 2 tenha ganhado
         * retornar 3 caso tenha dado empate
         * retornar -1 caso nenhum dos casos acima tenha acontecido
         */
        return -1;
    }

    public void print() {
        System.out.println("!######################################!");
        System.out.println("!#   Estado das unidades militares    #!");
        System.out.println("!######################################!");

        System.out.println("\t\tExército 1");
        army1.print();

        System.out.println();

        System.out.println("\t\tExército 2");
        army2.print();
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

    public int getLimitSizeBoard() {
        return limitSizeBoard;
    }

    public void setLimitSizeBoard(int limitSizeBoard) {
        this.limitSizeBoard = limitSizeBoard;
    }

    @Override
    public String toString() {
        return "CombatBoard{" +
                "army1 =" + army1 +
                ", army2 =" + army2 +
                ", limitSizeBoard =" + limitSizeBoard +
                '}';
    }
}
