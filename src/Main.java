import java.util.ArrayList;
import java.util.Scanner;

import Utils.InputValidation;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        MilitaryUnit unit1 = new MilitaryUnit(sc);
        MilitaryUnit unit2 = new MilitaryUnit(sc);
        MilitaryUnit unit3 = new MilitaryUnit(sc);
        MilitaryUnit unit4 = new MilitaryUnit(sc);
        MilitaryUnit unit5 = new MilitaryUnit(sc);

        ArrayList<MilitaryUnit> units = new ArrayList<>();
        ArrayList<MilitaryUnit> availabilityUnits = new ArrayList<>();


        units.add(unit1);
        units.add(unit2);
        units.add(unit3);
        units.add(unit4);
        units.add(unit5);
        availabilityUnits = new ArrayList<>(units);

        Army army1 = new Army();
        Army army2 = new Army();

        int[] positionXYArmy1;
        int[] positionXYArmy2;

        int numberOfUnitis = -1;
        int limiteCustos;
        int positionUnit;
        int positionUnit2;
        int auxLimitCost = -1;
        int auxLimitCost2 = -1;
        int k = 0;
        int w = 0;
        int resp = 0;
        int qtd_passos = -1;

        int option1 = -1;
        do {
            //Inicio - Menu Principal
            option1 = menuPrincipal(sc);
            switch (option1) {

                case 1:
                    System.out.println("#####1.Unidades Militares######");

                    int option2 = -1;
                    do {
                        //Segundo menu - Unidades Militares
                        option2 = menuUnity(sc);
                        switch (option2) {

                            case 1:
                                System.out.println("#####1.Adiciona Unidade#####");

                                int option3 = -1;
                                do {
                                    //Terceiro menu - Tipos de Unidades
                                    option3 = menuCreateUnity(sc);
                                    switch (option3) {

                                        case 1:
                                            System.out.println("######1.Guerreiro#####");
                                            break;

                                        case 2:
                                            System.out.println("#####2.Arqueiro (Arco e Flecha)####");
                                            break;

                                        case 3:
                                            System.out.println("#######3.Arqueiro (Besta)######");
                                            break;

                                        case 4:
                                            System.out.println("#####4.Feiticeiro#####");
                                            break;
                                    }
                                } while (option3 != 0);
                                break;

                            case 2:
                                System.out.println("######Remover Unidade#####");
                                break;

                            case 3:
                                System.out.println("#####Listar Unidades####3");
                                break;

                        }


                    } while (option2 != 0);
                    break;


                case 2:
                    System.out.println("!#    2. Exercitos            #!");
                    int option4 = -1;
                    do {
                        option4 = menuArmies(sc);
                        switch (option4) {
                            case 1:
                                int option5 = -1;
                                do {
                                    if (units.isEmpty()) {
                                        System.out.println("Nenhuma unidade militar foi encontrada!");
                                        break;
                                    } else {
                                        option5 = menuCreateArmy(sc);
                                        switch (option5) {
                                            case 1:

                                                System.out.println("!#   1. Adicionar Unidade     #!");
                                                if (k == 0) {
                                                    System.out.println("Limite de custos");
                                                    limiteCustos = sc.nextInt();
                                                    army1 = new Army(limiteCustos);
                                                    k++;
                                                    auxLimitCost = limiteCustos;
                                                }

                                                if (auxLimitCost > 0) {

                                                    System.out.println("!#  Unidade Militares    #!");
                                                    System.out.println(auxLimitCost);
                                                    for(int j = 0; j < availabilityUnits.size(); j++) {
                                                        System.out.println(j + " - " + availabilityUnits.get(j));
                                                    }

                                                    System.out.println("Escolha a unidade: ");
                                                    System.out.print("==> ");
                                                    positionUnit = sc.nextInt();

                                                    if(positionUnit < 0 || positionUnit > availabilityUnits.size()) {
                                                        System.out.println("Indice invalido");
                                                        break;
                                                    }

                                                    MilitaryUnit unity = availabilityUnits.get(positionUnit);
                                                    if(unity.getCost() > auxLimitCost) {
                                                        System.out.println("Passou o limite do custo");
                                                        break;
                                                    }
                                                    army1.addMilitaryUnit(unity);
                                                    //army1.addMilitaryUnit(units.get(positionUnit));
                                                    availabilityUnits.remove(positionUnit);
                                                    auxLimitCost -= units.get(positionUnit).getCost();

                                                } else {
                                                    System.out.println("Passou o limite de custos");
                                                }
                                                break;
                                            case 2:
                                                System.out.println("!#   2. Remover Unidade       #!");
                                                if (army1.getMilitaryUnit().isEmpty()) {
                                                    System.out.println("Não há unidades militares");
                                                } else {
                                                    army1.printMilitaryUnit();
                                                    System.out.println("Escolha a unidade: ");
                                                    System.out.print("==> ");
                                                    positionUnit2 = sc.nextInt();
                                                    if(positionUnit2 < 0 || positionUnit2 > army1.getMilitaryUnit().size()) {
                                                        System.out.println("Indice invalido");
                                                        break;
                                                    }
                                                    MilitaryUnit removedUnity = army1.removeMilitaryUnit(positionUnit2);
                                                    availabilityUnits.add(removedUnity);
                                                }

                                                break;

                                            case 3:
                                                if (army1.getMilitaryUnit().isEmpty()) {
                                                    System.out.println("Não há unidades militares");
                                                }else{
                                                    army1.printMilitaryUnit();
                                                }

                                                break;

                                        }
                                    }
                                } while (option5 != 0);

                                break;

                            case 2:
                                System.out.println("!#   2. Exercito 2            #!");
                                int option6 = -1;
                                do {
                                    if (units.isEmpty()) {
                                        System.out.println("Nenhuma unidade militar foi encontrada!");
                                        break;
                                    } else {
                                        option6 = menuCreateArmy(sc);
                                        switch (option6) {
                                            case 1:
                                                System.out.println("!#   1. Adicionar Unidade     #!");
                                                if (w == 0) {
                                                    System.out.println("Limite de custos");
                                                    limiteCustos = sc.nextInt();
                                                    army2 = new Army(limiteCustos);
                                                    w++;
                                                    auxLimitCost2 = limiteCustos;
                                                }

                                                if (auxLimitCost2 > 0) {

                                                    System.out.println("!#  Unidade Militares    #!");
                                                    System.out.println(auxLimitCost2);
                                                    for(int j = 0; j < availabilityUnits.size(); j++) {
                                                        System.out.println(j + " - " + availabilityUnits.get(j));
                                                    }

                                                    System.out.println("Escolha a unidade: ");
                                                    System.out.print("==> ");
                                                    positionUnit = sc.nextInt();
                                                    if(positionUnit < 0 || positionUnit > availabilityUnits.size()) {
                                                        System.out.println("Indice invalido");
                                                        break;
                                                    }

                                                    MilitaryUnit unity = availabilityUnits.get(positionUnit);
                                                    if(unity.getCost() > auxLimitCost2) {
                                                        System.out.println("Passou o limite do custo");
                                                        break;
                                                    }

                                                    army2.addMilitaryUnit(unity);
                                                    //army2.addMilitaryUnit(units.get(positionUnit));
                                                    availabilityUnits.remove(positionUnit);
                                                    auxLimitCost2 -= units.get(positionUnit).getCost();

                                                } else {
                                                    System.out.println("Passou o limite de custos");
                                                }
                                                break;
                                            case 2:
                                                System.out.println("!#   2. Remover Unidade       #!");
                                                if (army2.getMilitaryUnit().isEmpty()) {
                                                    System.out.println("Não há unidades militares");
                                                } else {
                                                    army2.printMilitaryUnit();
                                                    System.out.println("Escolha a unidade: ");
                                                    System.out.print("==> ");
                                                    positionUnit2 = sc.nextInt();
                                                    if(positionUnit2 < 0 || positionUnit2 > army2.getMilitaryUnit().size()) {
                                                        System.out.println("Indice invalido");
                                                        break;
                                                    }
                                                    MilitaryUnit removedUnity = army2.removeMilitaryUnit(positionUnit2);
                                                    availabilityUnits.add(removedUnity);
                                                }

                                                break;

                                            case 3:
                                                if (army2.getMilitaryUnit().isEmpty()) {
                                                    System.out.println("Não há unidades militares");
                                                }else{
                                                    army2.printMilitaryUnit();
                                                }
                                                break;

                                        }
                                    }
                                } while (option6 != 0);
                                break;


                        }
                    } while (option4 != 0);
                    break;


                case 3:
                    System.out.println("!#    3. Combates             #!");
                    int option7 = -1;
                    do {
                        option7 = menuBattle(sc);
                        switch (option7) {
                            case 1:
                                System.out.println("!#   1. Posicionar Manualmente     #!");
                                if(army1.getMilitaryUnit().isEmpty() || army2.getMilitaryUnit().isEmpty()) {
                                    System.out.println("Não há exercitos");
                                    break;
                                }else{
                                    CombatBoard board = new CombatBoard(sc);
                                    positionXYArmy1 = new int[army1.getMilitaryUnit().size() * 2];
                                    for(int j = 0; j < positionXYArmy1.length; j += 2) {
                                        System.out.println("Exercito 1 - Posição X Unitdade ");
                                        positionXYArmy1[j] = sc.nextInt();
                                        System.out.println("Exercito 1 - Posição Y Unitdade ");
                                        positionXYArmy1[j+1] = sc.nextInt();
                                    }
                                    board.setPositionArmy1(positionXYArmy1);
                                    do{
                                        board.showState(army1);
                                        //board.showState2(army1);
                                        System.out.println("Quer dar mais passos?: ");
                                        System.out.println("1. Sim ");
                                        System.out.println("2. Não (Sair)");
                                        System.out.print("==> ");
                                        resp = sc.nextInt();
                                        if(resp == 1) {
                                            do {
                                                System.out.println("Quantidade de passos: ");
                                                qtd_passos = sc.nextInt();
                                                if (qtd_passos <= 0) {
                                                    System.out.println("Quantidade invalida");
                                                }
                                            } while (qtd_passos < 1);
                                        }
                                    }while(resp == 1);

                                }

                                break;

                            case 2:
                                System.out.println("!#   2. Posicionar Aleatoriamente  #!");
                                break;
                        }
                    } while (option7 != 0);
                    break;
            }


        } while (option1 != 0);


        sc.close();
    }

    public static int menuPrincipal(Scanner sc) {
        System.out.println("!##############################!");
        System.out.println("!#     Menu - Benvindo(a)     #!");
        System.out.println("!##############################!");
        System.out.println("!#    1. Unidades Militares   #!");
        System.out.println("!##############################!");
        System.out.println("!#    2. Exercitos            #!");
        System.out.println("!##############################!");
        System.out.println("!#    3. Combates             #!");
        System.out.println("!##############################!");
        System.out.println("!#    0. Sair                 #!");
        System.out.println("!##############################!");
        return InputValidation.validateIntBetween(sc, "=>", 0, 3);
    }

    public static int menuUnity(Scanner sc) {
        System.out.println("!##############################!");
        System.out.println("!#     Unidades Militares     #!");
        System.out.println("!##############################!");
        System.out.println("!#   1. Adicionar Unidade      #!");
        System.out.println("!##############################!");
        System.out.println("!#   2. Remover Unidade       #!");
        System.out.println("!##############################!");
        System.out.println("!#   3. Listar Unidades       #!");
        System.out.println("!##############################!");
        System.out.println("!#   0. Voltar                #!");
        System.out.println("!##############################!");
        return InputValidation.validateIntBetween(sc, "=>", 0, 3);

    }

    public static int menuCreateUnity(Scanner sc) {
        System.out.println("!##################################!");
        System.out.println("!#         Adicionar Unidade      #!");
        System.out.println("!##################################!");
        System.out.println("!#   1. Guerreiro                 #!");
        System.out.println("!##################################!");
        System.out.println("!#   2. Arqueiro (Arco e Flecha)  #!");
        System.out.println("!##################################!");
        System.out.println("!#   3. Arqueiro (Besta)          #!");
        System.out.println("!##################################!");
        System.out.println("!#   4. Feiticeiro                #!");
        System.out.println("!##################################!");
        System.out.println("!#   0. Voltar                    #!");
        System.out.println("!##################################!");
        return InputValidation.validateIntBetween(sc, "=>", 0, 4);
    }

    public static int menuArmies(Scanner sc) {
        System.out.println("!##############################!");
        System.out.println("!#         Exercitos          #!");
        System.out.println("!##############################!");
        System.out.println("!#   1. Exercito 1            #!");
        System.out.println("!##############################!");
        System.out.println("!#   2. Exercito 2            #!");
        System.out.println("!##############################!");
        System.out.println("!#   0. Voltar                #!");
        System.out.println("!##############################!");
        return InputValidation.validateIntBetween(sc, "=>", 0, 2);
    }

    public static int menuCreateArmy(Scanner sc) {
        System.out.println("!##############################!");
        System.out.println("!#          Exercito          #!");
        System.out.println("!##############################!");
        System.out.println("!#   1. Adicionar Unidade     #!");
        System.out.println("!##############################!");
        System.out.println("!#   2. Remover Unidade       #!");
        System.out.println("!##############################!");
        System.out.println("!#   3. Listar Unidades       #!");
        System.out.println("!##############################!");
        System.out.println("!#   0. Voltar                #!");
        System.out.println("!##############################!");
        return InputValidation.validateIntBetween(sc, "=>", 0, 3);
    }

    public static int menuBattle(Scanner sc) {
        System.out.println("!###################################!");
        System.out.println("!#            Combate              #!");
        System.out.println("!###################################!");
        System.out.println("!#   1. Posicionar Manualmente     #!");
        System.out.println("!###################################!");
        System.out.println("!#   2. Posicionar Aleatoriamente  #!");
        System.out.println("!###################################!");
        System.out.println("!#   0. Voltar                     #!");
        System.out.println("!###################################!");
        return InputValidation.validateIntBetween(sc, "=>", 0, 2);
    }
}

