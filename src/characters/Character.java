package characters;

import item.bag.BasicBag;

/**
 * The Character class serves as an abstract base class for all character-related entities
 * in the game, such as players and enemies. It defines common properties and behaviors
 * shared by all characters.
 */
abstract class Character {

    protected String name;
    protected int life;
    protected int damage;
    BasicBag basicBag;

    public Character (String name, int life, int damage){
        this.name = name;
        this.life = life;
        this.damage = damage;
        this.basicBag = new BasicBag();
    }

    public void displayStats() {
        System.out.println("=== " + name + " ===");
        System.out.println("Life : " + life);
        System.out.println("Attack : " + damage);
    }

    public int getAttack(){
        System.out.println(name + " causes " + damage + " damage ! ");
        return damage;
    }
    public void takeDamage(int damage){
        life -= damage;
        if ( life <= 0 ) {
            die();
        }
    }

    public void die(){
        life = 0;
        System.out.println(name + " has died !");
    }
    public boolean isAlive(){
        return life > 0;
    }

    public String getName() { return name; }
    public int getLife() { return life; }
    public int getDamage() { return damage; }

    public void setName( String name ) { this.name = name; }
    public void setLife( int life ) { this.life = life; }
    public void setDamage( int damage ) { this.damage = damage; }
}
