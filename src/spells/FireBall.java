package spells;

public class FireBall extends Spell {
    public FireBall(String name, int damage) {
        super(name, damage);
    }
    public void fireBallAttack() {
        System.out.println( getName() + " attack with a fire ball and inflict " + getDamage() + " damage!");
    }

    public static void main ( String[] args ) {

        FireBall fireball = new FireBall("Fire ball", 5);

    }

}
