package item;

import characters.Player;
import characters.player.Warrior;
import characters.player.Wizard;
import item.bag.MinorBag;
import item.defensiveequipment.Shield;
import item.defensiveequipment.potion.*;
import item.defensiveequipment.shield.IronShield;
import item.defensiveequipment.shield.WoodenShield;
import item.offensiveequipment.spells.*;
import item.offensiveequipment.weapons.*;
import game.exception.*;

import java.util.Random;

/**
 * Represents a chest in the game that provides random rewards
 * upon being opened, including item.offensiveequipment.weapons, potions, or item.offensiveequipment.spells.
 */
public class SurpriseChest {

    private static Random random = new Random();

    public SurpriseChest() {
    }

    /**
     * Ouvre le coffre et donne une récompense au joueur
     *
     * @param player le joueur qui ouvre le coffre
     * @return l'objet trouvé (arme, item.defensiveequipment.potion ou sort)
     * @throws InvalidChestContentException si le loot est incompatible
     */
    public static Object openChest(Player player) throws InvalidChestContentException {
        int surprise = random.nextInt(10);

        switch (surprise) {
            case 0:
                if (player instanceof Wizard) {
                    throw new InvalidChestContentException("You can't equip a sword with a characters.player.wizard !");
                }
                Sword sword = new Sword("Sword", 5);
                ((Warrior) player).getWeapon(sword);
                System.out.println("You find a " + sword.getName() +
                        ", now you can inflict " + sword.getDamage() + " damages!");
                return sword;

            case 1:
                if (player instanceof Wizard) {
                    throw new InvalidChestContentException("You can't equip a mace with a characters.player.wizard !");
                }
                Mace mace = new Mace("Mace", 3);
                ((Warrior) player).getWeapon(mace);
                System.out.println("You find a " + mace.getName() +
                        ", now you can inflict " + mace.getDamage() + " damages!");
                return mace;

            case 2:
                MajorLifePotion majorPotion = new MajorLifePotion("Major life item.defensiveequipment.potion", 5);
                player.getPotion(majorPotion);
                System.out.println("You find a " + majorPotion.getName() +
                        ", now you can retrieve " + majorPotion.getLife() + " life points!");
                return majorPotion;

            case 3:
                MinorLifePotion minorPotion = new MinorLifePotion("Minor life item.defensiveequipment.potion", 2);
                player.getPotion(minorPotion);
                System.out.println("You find a " + minorPotion.getName() +
                        ", now you can retrieve " + minorPotion.getLife() + " life points!");
                return minorPotion;

            case 4:
                if (player instanceof Warrior) {
                    throw new InvalidChestContentException("You can't equip a spell with a characters.player.warrior !");
                }
                FireBall fireball = new FireBall("Fire Ball", 7);
                ((Wizard) player).getSpell(fireball);
                System.out.println("You find a spell: " + fireball.getName() +
                        ", now you can inflict " + fireball.getDamage() + " damages!");
                return fireball;

            case 5:
                if (player instanceof Warrior) {
                    throw new InvalidChestContentException("You can't equip a spell with a characters.player.warrior !");
                }
                LightninBolt lightning = new LightninBolt("Lightning Bolt", 2);
                ((Wizard) player).getSpell(lightning);
                System.out.println("You find a spell: " + lightning.getName() +
                        ", now you can inflict " + lightning.getDamage() + " damages!");
                return lightning;

            case 6:
                if (player instanceof Wizard) {
                    throw new InvalidChestContentException("You can't equip a shield with a wizard !");
                }
                IronShield ironShield = new IronShield("Iron Shield", 5);
                ((Warrior) player).getShield(ironShield);
                System.out.println("You find a shield: " + ironShield.getName() +
                        ", now you gain " + ironShield.getLife() + " LP !");
                return ironShield;

            case 7:
                if (player instanceof Wizard) {
                    throw new InvalidChestContentException("You can't equip a shield with a wizard !");
                }
                WoodenShield woodenShield = new WoodenShield("Wooden Shield", 2);
                ((Warrior) player).getShield(woodenShield);
                System.out.println("You find a shield: " + woodenShield.getName() +
                        ", now you gain " + woodenShield.getLife() + " LP !");
                return woodenShield;

                case 8:
                    MinorBag minorbag = new MinorBag();
                    player.addBag();


            default:
                return null;
        }
    }

    public static void main(String[] args) throws InvalidChestContentException {

    }
}