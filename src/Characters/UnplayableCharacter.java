package Characters;

public class UnplayableCharacter extends Character {

    public UnplayableCharacter(String name, int life, int attack) {
        super(name, life, attack);
    }

    public void runAway() {
        System.out.println(name + " runs away!");
    }
}