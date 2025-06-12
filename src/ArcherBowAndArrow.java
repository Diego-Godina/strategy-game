import java.util.Scanner;

public class ArcherBowAndArrow extends Archer {

    /**
     * Archer bow and arrow constructor
     *
     * @param sc
     * @param position
     */
    public ArcherBowAndArrow(Scanner sc, int position) {
        super(sc);
        id = position + 1;
        name = "Arqueiro (Arco e Flecha) " + id;
    }
}
