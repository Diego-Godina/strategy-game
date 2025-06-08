import java.util.Scanner;

public class ArcherBowAndArrow extends Archer {
    public ArcherBowAndArrow(Scanner sc, int position) {
        super(sc);
        id = position + 1;
        name = "Arqueiro (Arco e Flecha) " + id;
    }
}
