package game;

import characters.*;
import items.*;
import spells.Fireball;
import spells.LightningBolt;

import java.util.Random;

public class SurpriseChest {

    private static Random random;

    public SurpriseChest() {
        this.random = new Random();
    }

    /**
     * Ouvre le coffre et donne un objet au joueur
     * @param playableCharacter le personnage qui ouvre le coffre
     */
    public void openChest(PlayableCharacter playableCharacter) {
        int choice = random.nextInt(6);

        switch (choice) {
            case 0:
                // Épée
                Sword sword = new Sword("Épée des Morts", 5);
                playableCharacter.setWeapon(sword); // au lieu de getWeapon()
                System.out.println("Vous trouvez une arme : " + sword.getName() +
                        " (+" + sword.getBonusDamage() + " dégâts)");
                break;

            case 1:
                // Grande potion de vie
                LargeLifePotion largeLifePotion = new LargeLifePotion("Grande Potion de Soin", 5);
                playableCharacter.setPotion(largelifePotion); // Appel sur l'instance
                System.out.println("Vous trouvez une " + largeLifePotion.getName() +
                        " (+" + largeLifePotion.largeBonus() + " PV)");
                break;

            case 2:
                // Petite potion de vie
                MinorLifePotion minorLifePotion = new MinorLifePotion("Petite Potion de Soin", 2);
                playableCharacter.setPotion(minorLifePotion); // Appel sur l'instance
                System.out.println("Vous trouvez une " + minorLifePotion.getName() +
                        " (+" + minorLifePotion.minorBonus() + " PV)"); // Correction: minorBonus au lieu de largeBonus
                break;

            case 3:
                // Masse d'armes
                Mace mace = new Mace("Masse d'Acier", 3);
                playableCharacter.getWeapon(); // Appel sur l'instance
                System.out.println("Vous trouvez une " + mace.getName() +
                        " (+" + mace.maceAttack() + " dégâts)"); // Correction: nom de l'arme au lieu du joueur
                break;

            case 4:
                // Sort de boule de feu
                Fireball fireball = new Fireball("Boule de Feu", 7);
                playableCharacter.getSpell(); // Supposant que cette méthode existe
                System.out.println("Vous trouvez un sort : " + fireball.getName() +
                        " (+" + fireball.getBonusDamage() + " dégâts)"); // Correction: nom du sort
                break;

            case 5:
                // Sort d'éclair
                LightningBolt lightningBolt = new LightningBolt("Éclair Foudroyant", 4);
                playableCharacter.getSpell(); // Supposant que cette méthode existe
                System.out.println("Vous trouvez un sort : " + lightningBolt.getName() +
                        " (+" + lightningBolt.getBonusDamage() + " dégâts)"); // Correction: nom du sort
                break;
        }
    }
    public static void main(String[] args) {
        // Test de la classe (nécessite les autres classes pour fonctionner)
        System.out.println("=== Test de SurpriseChest ===");
        SurpriseChest chest = new SurpriseChest();
    }
}