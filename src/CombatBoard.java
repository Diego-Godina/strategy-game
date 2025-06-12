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

    /*
    Dá ínicio ao combate. Chama os métodos para mover as unidades de cada exercíto, de modo que a cada passo dado os exercitos
    ou as unidades se movam e também é chamada os métodos de ataque, assim sempre que a unidade alvo estiver no alcance da unidade
    atacante poderá efetuar um ataque.
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

    /*
    Verifica o estado do jogo, primeiro verificando se uma ou, as duas unidades têm vida ou até se nenhuma
    tem vida. Se uma tiver vida é a vencedora o jogo termina, se os dois exercitos tiverem o mesmo numero de vida
    terminam empatados e o jogo termina se os dois exercitos ainda tiver vida e o número de passos terminar, o utilizador
    pode adicionar mais passos.
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

    //Imprimi ou apresenta o estado das unidades dos dois exercitos
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
