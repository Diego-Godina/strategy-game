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

    /* ACHO QUE NÃO VAMOS PRECISAR
    public MilitaryUnit(String name, int cost, int lifeScore, int attackPoints, int defensePoints, int range, int speed) {
        this.name = name;
        this.cost = cost;
        this.lifeScore = lifeScore;
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
        this.range = range;
        this.speed = speed;
        this.positionX = 0;
        this.positionY = 0;
    }*/

    public MilitaryUnit(Scanner sc) {
        cost = InputValidation.validateIntGT0(sc, "Introduza o custo de produção: ");
        lifeScore = InputValidation.validateIntGT0(sc, "Introduza os pontos de vida: ");
        attackPoints = InputValidation.validateIntGT0(sc, "Introduza os pontos de ataque: ");
        defensePoints = InputValidation.validateIntGT0(sc, "Introduza os pontos de defesa: ");
        range = InputValidation.validateIntGT0(sc, "Introduza o alcance: ");
        speed = InputValidation.validateIntGT0(sc, "Introduza a velocidade: ");
        positionX = 0;
        positionY = 0;
    }

    public void positionManually(Scanner sc, int limit) {
        System.out.println("\tIntroduza a posição do exército " + name);
        positionX = InputValidation.validateIntBetween(sc, "Introduza a posição X: ", 0,limit/2);
        positionY = InputValidation.validateIntBetween(sc, "Introduza a posição Y: ", 0,limit/2);
    }

    public void positionRandom(int limit) {
        Random rand = new Random();

        positionX = rand.nextInt(limit);
        positionY = rand.nextInt(limit);

        System.out.println(":> Posição do exército " + name + " foi atribuída para (" + positionX + "," + positionY + ")");
    }

    public void move(int limit) {
        Random rand = new Random();
        int d = rand.nextInt(8);
        int max, min, move;

        limit = limit - 1;
        min = 0;

        System.out.print(":> Exército " + name + " moveu-se de (" + positionX + ", " + positionY + ") para ");

        switch (d) {
            // Forward
            case 0:
                max = (limit - positionY) / speed;
                if (max > 0) {
                    positionY += (rand.nextInt((max - min) + 1) * speed);
                }
                break;

            // Forward diagonally right
            case 1:
                max = Math.min((limit - positionY) / speed, (limit - positionX) / speed);
                if (max > 0) {
                    move = rand.nextInt((max - min) + 1);
                    positionY += (move * speed);
                    positionX += (move * speed);
                }
                break;

            // To right side
            case 2:
                max = (limit - positionX) / speed;
                if (max > 0) {
                    positionX += (rand.nextInt((max - min) + 1) * speed);
                }
                break;

            // Backward diagonally right
            case 3:
                max = Math.min(positionY / speed, (limit - positionX) / speed);
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
                max = Math.min((limit - positionY) / speed, positionX / speed);
                if (max > 0) {
                    move = rand.nextInt((max - min) + 1);
                    positionY += (move * speed);
                    positionX -= (move * speed);
                }
                break;
        }

        System.out.println("(" + positionX + ", " + positionY + ")");
    }

    public void defend(int damage) {
        if(defensePoints == 0) return;

        if(damage <= defensePoints) {
            defensePoints -= damage;
            System.out.println(":> Exército " + name + " perdeu " + damage + " pontos de defesa");
        } else {
            System.out.println(":> Exército " + name + " perdeu " + defensePoints + " pontos de defesa");
            defensePoints = 0;
        }
    }

    public void attack(MilitaryUnit militaryUnit) {
        militaryUnit.defend(attackPoints);

        System.out.println(":> Exército " + name + " atacou o exército " + militaryUnit.getName());
    }

    public boolean search(int id) {
        return this.id == id;
    }

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
}