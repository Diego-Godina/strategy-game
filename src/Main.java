import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Utils.InputValidation;

public class Main {
    private static final ArrayList<MilitaryUnit> units = new ArrayList<>();
    private static final ArrayList<MilitaryUnit> availabilityUnits = new ArrayList<>();
    private static Army army1 = new Army();
    private static Army army2 = new Army();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        MilitaryUnit unityToAdd;
        CombatBoard combatBoard = null;

        int[] positionXYArmy1;
        int[] positionXYArmy2;

        boolean allMilitaryUnitsPositioned = false;
        boolean combatBoardCreated = false;

        int numberOfUnitis = -1;
        int limitCost;
        int positionUnit;
        int positionUnit2;
        int auxLimitCost = -1;
        int auxLimitCost2 = -1;
        int k = 0;
        int w = 0;
        int resp = 0;
        int qtd_passos = -1;
        int limitSizeBoard = 0;
        int winner = -1;

        int option1 = -1;
        do {
            //Inicio - Menu Principal
            option1 = menuPrincipal(sc);
            switch (option1) {
                case 1:
                    if (allMilitaryUnitsPositioned) {
                        System.out.println("Após o posicionamento das unidades militares nada pode ser alterado");
                        break;
                    }

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
                                            unityToAdd = new Warrior(sc, units.size());
                                            units.add(unityToAdd);
                                            availabilityUnits.add(unityToAdd);
                                            break;

                                        case 2:
                                            System.out.println("#####2.Arqueiro (Arco e Flecha)####");
                                            unityToAdd = new ArcherBowAndArrow(sc, units.size());
                                            units.add(unityToAdd);
                                            availabilityUnits.add(unityToAdd);
                                            break;

                                        case 3:
                                            System.out.println("#######3.Arqueiro (Besta)######");
                                            unityToAdd = new ArcherCrossBow(sc, units.size());
                                            units.add(unityToAdd);
                                            availabilityUnits.add(unityToAdd);
                                            break;

                                        case 4:
                                            System.out.println("#####4.Feiticeiro#####");
                                            unityToAdd = new Wizard(sc, units.size());
                                            units.add(unityToAdd);
                                            availabilityUnits.add(unityToAdd);
                                            break;
                                    }
                                } while (option3 != 0);
                                break;

                            case 2:
                                System.out.println("######Remover Unidade#####");

                                if (units.isEmpty()) {
                                    System.out.println("Não há unidades militares para remover");
                                    break;
                                }

                                MilitaryUnit unitToRemove = validateMilitaryUnit(sc, "Forneça o id da unidade militar para remover: ");
                                units.remove(selectIdMilitaryUnits(unitToRemove.getId()));
                                availabilityUnits.remove(selectIdMilitaryUnitsAvailable(unitToRemove.getId()));

                                System.out.println("Unidade militar " + unitToRemove.getName() + " removida com sucesso!");
                                break;

                            case 3:
                                System.out.println("#####Listar Unidades####");

                                if (units.isEmpty()) {
                                    System.out.println("Não há unidades militares para listar");
                                    break;
                                }

                                for (MilitaryUnit unit : units) {
                                    unit.print();
                                    System.out.println();
                                }
                                break;

                        }


                    } while (option2 != 0);
                    break;


                case 2:
                    if (allMilitaryUnitsPositioned) {
                        System.out.println("Após o posicionamento das tropas nada pode ser alterado");
                        break;
                    }

                    System.out.println("!#    2. Exercitos            #!");
                    int option4 = -1;
                    do {
                        if (units.size() < 2) {
                            System.out.println("Não há unidades militares suficientes");
                            break;
                        }

                        option4 = menuArmies(sc);
                        switch (option4) {
                            case 1:
                                int option5 = -1;

                                do {
                                    option5 = menuCreateArmy(sc);
                                    switch (option5) {
                                        case 1:

                                            System.out.println("!#   1. Adicionar Unidade     #!");
                                            if (k == 0) {
                                                limitCost = InputValidation.validateIntGT0(sc, "Limite de custos: ");
                                                army1 = new Army(limitCost);
                                                k++;
                                                auxLimitCost = limitCost;
                                            }

                                            if (auxLimitCost <= 0) {
                                                System.out.println("Passou o limite de custos");
                                                break;
                                            }

                                            System.out.println("Custo restante: " + auxLimitCost);

                                            MilitaryUnit unity = validateMilitaryUnitAvailable(sc, "Escolha uma unidade: ");
                                            positionUnit = selectIdMilitaryUnitsAvailable(unity.getId());

                                            if (unity.getCost() > auxLimitCost) {
                                                System.out.println("Passou o limite do custo");
                                                break;
                                            }

                                            army1.addMilitaryUnit(unity);
                                            auxLimitCost -= availabilityUnits.get(positionUnit).getCost();
                                            availabilityUnits.remove(positionUnit);
                                            break;

                                        case 2:
                                            System.out.println("!#   2. Remover Unidade       #!");
                                            if (army1.getMilitaryUnit().isEmpty()) {
                                                System.out.println("Não há unidades militares");
                                                break;
                                            }
                                            unity = validateMilitaryUnitArmy1(sc, "Escolha uma unidade: ");
                                            positionUnit = selectIdMilitaryUnitsArmy1(unity.getId());
                                            army1.removeMilitaryUnit(positionUnit);
                                            auxLimitCost += unity.getCost();
                                            availabilityUnits.add(unity);
                                            System.out.println("Unidade militar " + unity.getName() + " removida com sucesso!");

                                            break;

                                        case 3:
                                            if (army1.getMilitaryUnit().isEmpty()) {
                                                System.out.println("Não há unidades militares");
                                            } else {
                                                System.out.println("Custo restante: " + auxLimitCost);
                                                army1.print();
                                            }
                                            break;

                                    }
                                } while (option5 != 0);

                                break;

                            case 2:
                                System.out.println("!#   2. Exercito 2            #!");
                                int option6 = -1;

                                do {
                                    option6 = menuCreateArmy(sc);
                                    switch (option6) {
                                        case 1:
                                            System.out.println("!#   1. Adicionar Unidade     #!");
                                            if (w == 0) {
                                                limitCost = InputValidation.validateIntGT0(sc, "Limite de custos: ");
                                                army2 = new Army(limitCost);
                                                w++;
                                                auxLimitCost2 = limitCost;
                                            }

                                            if (auxLimitCost2 <= 0) {
                                                System.out.println("Passou o limite de custos");
                                                break;
                                            }

                                            System.out.println("Custo restante: " + auxLimitCost2);

                                            MilitaryUnit unity = validateMilitaryUnitAvailable(sc, "Escolha uma unidade: ");
                                            positionUnit = selectIdMilitaryUnitsAvailable(unity.getId());

                                            if (unity.getCost() > auxLimitCost2) {
                                                System.out.println("Passou o limite do custo");
                                                break;
                                            }

                                            army2.addMilitaryUnit(unity);
                                            auxLimitCost2 -= availabilityUnits.get(positionUnit).getCost();
                                            availabilityUnits.remove(positionUnit);
                                            break;

                                        case 2:
                                            System.out.println("!#   2. Remover Unidade       #!");
                                            if (army2.getMilitaryUnit().isEmpty()) {
                                                System.out.println("Não há unidades militares");
                                                break;
                                            }
                                            unity = validateMilitaryUnitArmy2(sc, "Escolha uma unidade: ");
                                            positionUnit = selectIdMilitaryUnitsArmy2(unity.getId());
                                            army2.removeMilitaryUnit(positionUnit);
                                            auxLimitCost2 += unity.getCost();
                                            availabilityUnits.add(unity);
                                            System.out.println("Unidade militar " + unity.getName() + " removida com sucesso!");

                                            break;

                                        case 3:
                                            if (army2.getMilitaryUnit().isEmpty()) {
                                                System.out.println("Não há unidades militares");
                                            } else {
                                                System.out.println("Custo restante: " + auxLimitCost2);
                                                army2.print();
                                            }
                                            break;

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
                        if (army1.getMilitaryUnit().isEmpty() || army2.getMilitaryUnit().isEmpty()) {
                            System.out.println("Não há exércitos militares");
                            break;
                        }

                        option7 = menuBattle(sc);
                        switch (option7) {
                            case 1:
                                System.out.println("!#   1. Posicionar Manualmente     #!");
                                if (allMilitaryUnitsPositioned) {
                                    System.out.println("As unidades militares já estão posicionadas");
                                    break;
                                }

                                limitSizeBoard = InputValidation.validateIntGT0(sc, "Forneça o tamanho do tabuleiro: ");
                                System.out.println("\t\tUnidades militares do exército 1");
                                army1.positionArmyManually(sc, limitSizeBoard);

                                System.out.println();
                                System.out.println("\t\tUnidades militares do exército 2");
                                army2.positionArmyManually(sc, limitSizeBoard);

                                allMilitaryUnitsPositioned = true;

                                break;

                            case 2:
                                System.out.println("!#   2. Posicionar Aleatoriamente  #!");
                                if (allMilitaryUnitsPositioned) {
                                    System.out.println("As unidades militares já estão posicionadas");
                                    break;
                                }

                                limitSizeBoard = InputValidation.validateIntGT0(sc, "Forneça o tamanho do tabuleiro: ");
                                army1.positionArmyRandom(limitSizeBoard);
                                army2.positionArmyRandom(limitSizeBoard);

                                System.out.println("Todas as unidades militares foram posicionadas");

                                allMilitaryUnitsPositioned = true;
                                break;

                            case 3:
                                if (!allMilitaryUnitsPositioned) {
                                    System.out.println("Você precisa posicionar as unidades militares primeiro");
                                    break;
                                }

                                if (!combatBoardCreated) {
                                    combatBoard = new CombatBoard(army1, army2, limitSizeBoard);
                                    combatBoardCreated = true;
                                }

                                combatBoard.print();
                                winner = combatBoard.game(sc);
                                switch (winner) {
                                    case 1:
                                        System.out.println("O exército 1 ganhou a simulação");
                                        option1 = 0;
                                        break;
                                    case 2:
                                        System.out.println("O exército 2 ganhou a simulação");
                                        option1 = 0;
                                        break;
                                    case 3:
                                        System.out.println("A simulação terminou empatada");
                                        option1 = 0;
                                        break;
                                }
                                break;
                        }
                    } while (option7 != 0);
                    break;
            }

        } while (option1 != 0);

        sc.close();
    }

    public static int menuPrincipal(Scanner sc) {
        System.out.println("!###############################!");
        System.out.println("!#     Menu - Bem vindo(a)     #!");
        System.out.println("!###############################!");
        System.out.println("!#    1. Unidades Militares    #!");
        System.out.println("!###############################!");
        System.out.println("!#    2. Exercitos             #!");
        System.out.println("!###############################!");
        System.out.println("!#    3. Combates              #!");
        System.out.println("!###############################!");
        System.out.println("!#    0. Sair                  #!");
        System.out.println("!###############################!");
        return InputValidation.validateIntBetween(sc, "=> ", 0, 3);
    }

    public static int menuUnity(Scanner sc) {
        System.out.println("!##############################!");
        System.out.println("!#     Unidades Militares     #!");
        System.out.println("!##############################!");
        System.out.println("!#   1. Adicionar Unidade     #!");
        System.out.println("!##############################!");
        System.out.println("!#   2. Remover Unidade       #!");
        System.out.println("!##############################!");
        System.out.println("!#   3. Listar Unidades       #!");
        System.out.println("!##############################!");
        System.out.println("!#   0. Voltar                #!");
        System.out.println("!##############################!");
        return InputValidation.validateIntBetween(sc, "=> ", 0, 3);

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
        return InputValidation.validateIntBetween(sc, "=> ", 0, 4);
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
        return InputValidation.validateIntBetween(sc, "=> ", 0, 2);
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
        return InputValidation.validateIntBetween(sc, "=> ", 0, 3);
    }

    public static int menuBattle(Scanner sc) {
        System.out.println("!###################################!");
        System.out.println("!#            Combate              #!");
        System.out.println("!###################################!");
        System.out.println("!#   1. Posicionar Manualmente     #!");
        System.out.println("!###################################!");
        System.out.println("!#   2. Posicionar Aleatoriamente  #!");
        System.out.println("!###################################!");
        System.out.println("!#   3. Executar simulação         #!");
        System.out.println("!###################################!");
        System.out.println("!#   0. Voltar                     #!");
        System.out.println("!###################################!");
        return InputValidation.validateIntBetween(sc, "=> ", 0, 3);
    }

    public static int selectIdMilitaryUnitsAvailable(int id) {
        for (int i = 0; i < availabilityUnits.size(); i++) {
            if(availabilityUnits.get(i).search(id)) {
                return i;
            }
        }

        return -1;
    }

    public static int selectIdMilitaryUnits(int id) {
        for (int i = 0; i < units.size(); i++) {
            if(units.get(i).search(id)) {
                return i;
            }
        }

        return -1;
    }

    public static MilitaryUnit validateMilitaryUnit(Scanner sc, String message) {
        while (true) {
            try {
                int value = InputValidation.validateIntGT0(sc, message);

                for (MilitaryUnit unit : units) {
                    if (unit.search(value)) {
                        return unit;
                    }
                }

                System.out.println("Introduza o id de uma unidade militar válida");
            } catch (InputMismatchException e) {
                System.out.println("Introduza o id de uma unidade militar válida");
                sc.nextLine();
            }
        }
    }

    public static MilitaryUnit validateMilitaryUnitAvailable(Scanner sc, String message) {
        while (true) {
            try {
                int value = InputValidation.validateIntGT0(sc, message);

                for (MilitaryUnit availableUnit : availabilityUnits) {
                    if (availableUnit.search(value)) {
                        return availableUnit;
                    }
                }

                System.out.println("Introduza o id de uma unidade militar válida");
            } catch (InputMismatchException e) {
                System.out.println("Introduza o id de uma unidade militar válida");
                sc.nextLine();
            }
        }
    }

    public static MilitaryUnit validateMilitaryUnitArmy1(Scanner sc, String message) {
        while (true) {
            try {
                int value = InputValidation.validateIntGT0(sc, message);

                for (MilitaryUnit armyMilitaryUnit : army1.getMilitaryUnit()) {
                    if (armyMilitaryUnit.search(value)) {
                        return armyMilitaryUnit;
                    }
                }

                System.out.println("Introduza o id de uma unidade militar vinculada a esse exercito");
            } catch (InputMismatchException e) {
                System.out.println("Introduza o id de uma unidade militar vinculada a esse exercito");
                sc.nextLine();
            }
        }
    }

    public static int selectIdMilitaryUnitsArmy1(int id) {
        for (int i = 0; i < army1.getMilitaryUnit().size(); i++) {
            if(army1.getMilitaryUnit().get(i).search(id)) {
                return i;
            }
        }

        return -1;
    }

    public static MilitaryUnit validateMilitaryUnitArmy2(Scanner sc, String message) {
        while (true) {
            try {
                int value = InputValidation.validateIntGT0(sc, message);

                for (MilitaryUnit armyMilitaryUnit : army2.getMilitaryUnit()) {
                    if (armyMilitaryUnit.search(value)) {
                        return armyMilitaryUnit;
                    }
                }

                System.out.println("Introduza o id de uma unidade militar vinculada a esse exercito");
            } catch (InputMismatchException e) {
                System.out.println("Introduza o id de uma unidade militar vinculada a esse exercito");
                sc.nextLine();
            }
        }
    }

    public static int selectIdMilitaryUnitsArmy2(int id) {
        for (int i = 0; i < army2.getMilitaryUnit().size(); i++) {
            if(army2.getMilitaryUnit().get(i).search(id)) {
                return i;
            }
        }

        return -1;
    }
}

