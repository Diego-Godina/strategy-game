import Utils.InputValidation;

import java.util.Random;
import java.util.Scanner;

public class MilitaryUnit implements Militarys {
    protected int id;
    protected String name;
    protected int cost;
    protected int lifeScore;
    protected int attackPoints;
    protected int defensePoints;
    protected int range;
    protected int positionX;
    protected int positionY;
    protected int speed;
    protected int attackPointsEffect;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getLifeScore() {
        return lifeScore;
    }

    public void setLifeScore(int lifeScore) {
        this.lifeScore = lifeScore;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public int getDefensePoints() {
        return defensePoints;
    }

    public void setDefensePoints(int defensePoints) {
        this.defensePoints = defensePoints;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public MilitaryUnit(Scanner sc) {
        cost = InputValidation.validateIntGT0(sc, "Introduza o custo de produção: ");
        lifeScore = InputValidation.validateIntGT0(sc, "Introduza os pontos de vida: ");
        attackPoints = InputValidation.validateIntGT0(sc, "Introduza os pontos de ataque: ");
        defensePoints = InputValidation.validateIntGT0(sc, "Introduza os pontos de defesa: ");
        range = InputValidation.validateIntGT0(sc, "Introduza o alcance: ");
        speed = InputValidation.validateIntGT0(sc, "Introduza a velocidade: ");
        positionX = 0;
        positionY = 0;
        attackPointsEffect = attackPoints;
    }

    /**
     * Manually positions military units on the combat board, always considering the board's border,
     * in order to prevent any unit from crossing the board's border.
     *
     * @param sc
     * @param limitSizeBoard
     */
    public void positionManually(Scanner sc, int limitSizeBoard) {
        System.out.println("\tIntroduza a posição do " + name);
        positionX = InputValidation.validateIntBetween(sc, "Introduza a posição X: ", 0, limitSizeBoard);
        positionY = InputValidation.validateIntBetween(sc, "Introduza a posição Y: ", 0, limitSizeBoard);
    }

    /**
     * Sets the X and Y positions randomly.
     *
     * @param limitSizeBoard
     */
    public void positionRandom(int limitSizeBoard) {
        Random rand = new Random();

        positionX = rand.nextInt(limitSizeBoard);
        positionY = rand.nextInt(limitSizeBoard);

        System.out.println(":> Posição do exército " + name + " foi atribuída para (" + positionX + "," + positionY + ")");
    }

    /**
     * Moves all military units randomly to a direction in a range from 0 to 7, being,
     * 0 forward, 1 forward diagonally right, 2 to the right side, 3 backward diagonally right,
     * 4 backward, 5 backward diagonally left, 6 to the left side, 7 forward diagonally left
     * When moving in a certain direction, the new position will be calculated, taking into account the speed of each
     * military unit.
     *
     * @param limitSizeBoard
     */
    public void move(int limitSizeBoard) {
        if(lifeScore <= 0) return;

        Random rand = new Random();
        int d = rand.nextInt(8);
        int max, min, move;

        limitSizeBoard = limitSizeBoard - 1;
        min = 1;

        System.out.print(":> Exército " + name + " moveu-se de (" + positionX + ", " + positionY + ") para ");

        switch (d) {
            // Forward
            case 0:
                max = (limitSizeBoard - positionY) / speed;
                if (max > 0) {
                    positionY += (rand.nextInt((max - min) + 1) * speed);
                }
                break;

            // Forward diagonally right
            case 1:
                max = Math.min((limitSizeBoard - positionY) / speed, (limitSizeBoard - positionX) / speed);
                if (max > 0) {
                    move = rand.nextInt((max - min) + 1);
                    positionY += (move * speed);
                    positionX += (move * speed);
                }
                break;

            // To right side
            case 2:
                max = (limitSizeBoard - positionX) / speed;
                if (max > 0) {
                    positionX += (rand.nextInt((max - min) + 1) * speed);
                }
                break;

            // Backward diagonally right
            case 3:
                max = Math.min(positionY / speed, (limitSizeBoard - positionX) / speed);
                if (max > 0) {
                    move = rand.nextInt((max - min) + 1);
                    positionY -= (move * speed);
                    positionX += (move * speed);
                }
                break;

            // Backward
            case 4:
                max = positionY / speed;
                if (max > 0) {
                    positionY -= (rand.nextInt((max - min) + 1) * speed);
                }
                break;

            // Backward diagonally left
            case 5:
                max = Math.min(positionY / speed, positionX / speed);
                if (max > 0) {
                    move = rand.nextInt((max - min) + 1);
                    positionY -= move * speed;
                    positionX -= move * speed;
                }
                break;

            // To left side
            case 6:
                max = positionX / speed;
                if (max > 0) {
                    positionX -= (rand.nextInt((max - min) + 1) * speed);
                }
                break;

            // Forward diagonally left
            case 7:
                max = Math.min((limitSizeBoard - positionY) / speed, positionX / speed);
                if (max > 0) {
                    move = rand.nextInt((max - min) + 1);
                    positionY += (move * speed);
                    positionX -= (move * speed);
                }
                break;
        }

        System.out.println("(" + positionX + ", " + positionY + ")");
    }

    /**
     * Called whenever a unit attacks another.
     * First, only the defense points are removed, and if they reach zero, the attack points are removed.
     * Even though the Damage is initially the total attack points, if the defense points are lower than the Damage,
     * the remaining value of the attack is discounted from the life points.
     *
     * @param damage
     */
    public void defend(int damage) {
        if(lifeScore == 0) return;

        if(damage <= defensePoints) {
            defensePoints -= damage;
            System.out.println(":> Exército " + name + " perdeu " + damage + " pontos de defesa");
        } else {
            System.out.println(":> Exército " + name + " perdeu " + defensePoints + " pontos de defesa");
            damage -= defensePoints;
            defensePoints = 0;

            if(damage <= lifeScore) {
                lifeScore -= damage;
                System.out.println(":> Exército " + name + " perdeu " + damage + " pontos de vida");
            } else {
                System.out.println(":> Exército " + name + " perdeu " + lifeScore + " pontos de vida");
                lifeScore = 0;
            }
        }

        if(lifeScore == 0) {
            System.out.println(":> Exército " + name + " faleceu...");
        }
    }

    /**
     * Called when a unit attacks another and passes the attack points to the defend() method, which reduce
     * first the armor points if it is a warrior, then the defense points and finally the life points
     *
     * @param militaryUnit
     */
    public void attack(MilitaryUnit militaryUnit) {
        if(lifeScore <= 0 || militaryUnit.getLifeScore() <= 0) return;

        System.out.println(":> Exército " + name + " atacou o exército " + militaryUnit.getName() + " com " + attackPointsEffect + " pontos de ataque");

        militaryUnit.defend(attackPointsEffect);
    }

    public boolean search(int id) {
        return this.id == id;
    }

    /**
     * Print information regarding military units.
     *
     */
    public void print() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Custo: " + cost);
        System.out.println("Pontos de vida: " + lifeScore);
        System.out.println("Pontos de ataque: " + attackPoints);
        System.out.println("Pontos de defesa: " + defensePoints);
        System.out.println("Alcance: " + range);
        System.out.println("Velocidade: " + speed);
        System.out.println("Posicao: (" + positionX + ", " + positionY + ")");
    }

    @Override
    public String toString() {
        return "\nID: " + id +
                "\nNome: " + name +
                "\nCusto: " + cost +
                "\nPontos de vida: " + lifeScore +
                "\nPontos de ataque: " + attackPoints +
                "\nPontos de defesa: " + defensePoints +
                "\nAlcance: " + range +
                "\nVelocidade: " + speed;
    }
}