import java.util.Scanner;

public class ArcherCrossBow extends Archer {
    public ArcherCrossBow(Scanner sc, int position) {
        super(sc);
        id = position + 1;
        name = "Arqueiro (Besta) " + id;
    }
}
