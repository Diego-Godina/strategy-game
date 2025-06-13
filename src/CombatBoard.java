import Utils.InputValidation;

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

    /**
     * It starts the combat.
     * It calls the methods to move the units of each army, so that with each step taken the armies or units move and it also calls the attack methods,
     * so that whenever the target unit is within range of the attacking unit it can carry out an attack.
     *
     * @param sc
     * @return
     */
    public int game(Scanner sc) {
        int qtnSteps = InputValidation.validateIntGT0(sc, "Forneça a quantidade de passos: ");
        int winner = -1;

        for (int i=0; i < qtnSteps; i++) {
            army1.moveArmy(limitSizeBoard);
            army2.moveArmy(limitSizeBoard);

            army1.attackArmy(army2);
            army2.attackArmy(army1);

            print();

            winner = checkFinishGame();
            if(winner != -1) {
                return checkFinishGame();
            }
        }

        System.out.println("Simulação finalizada");
        return winner;
    }

    /**
     * Check the game status, first checking if one or both units have life or even if neither has life.
     * If one has life, it is the winner and the game ends. If both armies have the same number of life,
     * they end in a draw and the game ends. If both armies still have life and the number of steps ends, the user can add more steps.
     *
     * @return
     */
    public int checkFinishGame() {
         int pointScore1 = 0;
         int pointScore2 = 0;

         for (int i = 0; i < army1.getMilitaryUnit().size(); i++) {
            pointScore1 += army1.getMilitaryUnit().get(i).lifeScore;
         }

         for(int j = 0; j < army2.getMilitaryUnit().size(); j++) {
             pointScore2 += army2.getMilitaryUnit().get(j).lifeScore;
         }

         if(pointScore1 > 0 && pointScore2 == 0 ) {
             return 1;
         } else if(pointScore1 == 0 && pointScore2 > 0) {
            return 2;
         } if(pointScore1 == 0 && pointScore2 == 0) {
             return 3;
         }

         return -1;
    }

    /**
     * Print or display the status of the units of both armies
     *
     */
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
