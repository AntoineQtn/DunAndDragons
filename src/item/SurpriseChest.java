package item;

import characters.Player;
import player.Warrior;
import player.Wizard;
import potion.*;
import spells.*;
import weapons.*;
import exception.*;

import java.util.Random;

/**
 * Represents a chest in the game that provides random rewards
 * upon being opened, including weapons, potions, or spells.
 */
public class SurpriseChest {

    private static Random random = new Random();

    public SurpriseChest() {
    }

    private static Warrior warrior;
    private static Wizard wizard;

    /**
     * Ouvre le coffre et donne une récompense au joueur
     * @param player le joueur qui ouvre le coffre
     * @return l'objet trouvé (arme, potion ou sort)
     * @throws InvalidChestContentException si le loot est incompatible
     */
    public static Object openChest(Player player) throws InvalidChestContentException {
        int surprise = random.nextInt(6);

        switch (surprise) {
            case 0:
                if (player instanceof Wizard) {
                    throw new InvalidChestContentException("You can't equip a sword with a wizard !");
                }
                Sword sword = new Sword("Sword", 5);
                warrior.getWeapon(sword);
                System.out.println("You find a " + sword.getName() +
                        ", now you can inflict " + sword.getDamage() + " damages!");
                return sword;

            case 1:
                if (player instanceof Wizard) {
                    throw new InvalidChestContentException("You can't equip a mace with a wizard !");
                }
                Mace mace = new Mace("Mace", 3);
                warrior.getWeapon(mace);
                System.out.println("You find a " + mace.getName() +
                        ", now you can inflict " + mace.getDamage() + " damages!");
                return mace;

            case 2:
                MajorLifePotion majorPotion = new MajorLifePotion("Major life potion", 5);
                player.getPotion(majorPotion);
                System.out.println("You find a " + majorPotion.getName() +
                        ", now you can retrieve " + majorPotion.getLife() + " life points!");
                return majorPotion;

            case 3:
                MinorLifePotion minorPotion = new MinorLifePotion("Minor life potion", 2);
                player.getPotion(minorPotion);
                System.out.println("You find a " + minorPotion.getName() +
                        ", now you can retrieve " + minorPotion.getLife() + " life points!");
                return minorPotion;

            case 4:
                if (player instanceof Warrior) {
                    throw new InvalidChestContentException("You can't equip a spell with a warrior !");
                }
                FireBall fireball = new FireBall("Fire Ball", 7);
                wizard.getSpell(fireball);
                System.out.println("You find a spell: " + fireball.getName() +
                        ", now you can inflict " + fireball.getDamage() + " damages!");
                return fireball;

            case 5:
                if (player instanceof Warrior) {
                    throw new InvalidChestContentException("You can't equip a spell with a warrior !");
                }
                LightninBolt lightning = new LightninBolt("Lightning Bolt", 2);
                wizard.getSpell(lightning);
                System.out.println("You find a spell: " + lightning.getName() +
                        ", now you can inflict " + lightning.getDamage() + " damages!");
                return lightning;

            default:
                return null;
        }
    }
}
