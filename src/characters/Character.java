package characters;

public abstract class Character {
    protected String name;
    protected int life;
    protected int damage;

    public Character(String name, int life, int damage) {
        this.name = name;
        this.life = life;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = Math.max(0, life);
    }

    public int getAttack() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = Math.max(0, damage);
    }

    public boolean isAlive() {
        return life > 0;
    }

    public void takeDamage(int amount) {
        setLife(life - amount);
    }

    public void displayStats() {
        System.out.println(name + " | Life: " + life + " | Damage: " + damage);
    }
}