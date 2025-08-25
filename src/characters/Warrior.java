package characters;

public class Warrior extends PlayableCharacter {

    public Warrior(String name) {
        super(name, 10, 5); // vie et attaque de base prédéfinies
    }

    public void swordAttack(){
        System.out.println(getName() + " swings a sword mightily!");
    }

    public void maceAttack(){
        System.out.println(getName() + " smashes with a mace!");
    }
}