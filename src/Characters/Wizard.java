package Characters;

public class Wizard extends PlayableCharacter {

    public Wizard(String name) {
        super(name, 6, 8); // moins de vie, plus d'attaque
    }

    public void castSpell(){
        System.out.println(getName() + " casts a fireball!");
    }
}
