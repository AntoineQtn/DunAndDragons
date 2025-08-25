package Characters;

public class Character {
    protected String name;
    protected int life;
    protected int attack;

    public Character(String name, int life, int attack) {
        this.name = name;
        this.life = life;
        this.attack = attack;
    }

    public void displayStats() {
        System.out.println("=== " + name + " ===");
        System.out.println("Life : " + life);
        System.out.println("Attack : " + attack);
    }

    public void strike() {
        System.out.println(name + " strikes for " + attack + " damage!");
    }

    public void die() {
        life = 0;
        System.out.println(name + " has died!");
    }

    // ➕ Ajout de méthodes utiles
    public boolean isAlive() {
        return life > 0;
    }

    public void takeDamage(int damage) {
        life -= damage;
        if (life <= 0) {
            die();
        }
    }

    // Getters
    public String getName() { return name; }
    public int getLife() { return life; }
    public int getAttack() { return attack; }

    // Setters
    public void setLife(int life) { this.life = life; }
    public void setAttack(int attack) { this.attack = attack; }
}
