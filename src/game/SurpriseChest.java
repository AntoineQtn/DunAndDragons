package game;

import characters.*;
import potion.*;
import spells.*;
import weapons.*;

import java.util.*;

public class SurpriseChest {

    private static Random random = new Random();

    public SurpriseChest(){

    }

    public static void openChest(Player player){
        int surprise = random.nextInt(6);

        switch (surprise) {
            case 0:
                Sword sword = new Sword("Sword", 5);
                player.getWeapon(sword);
                System.out.println("You find a " + sword.getName() + " , now you can inflict " + sword.getDamage() + " damages ! ");
                break;

            case 1:
                Mace mace = new Mace("Mace", 3);
                player.getWeapon(mace);
                System.out.println("You find a " + mace.getName() + " , now you can inflict " + mace.getDamage() + " damages ! ");
                break;

            case 2:
                MajorLifePotion majorlifepotion = new MajorLifePotion("Major life potion", 5);
                player.getPotion(majorlifepotion);
                System.out.println("You find a " + majorlifepotion.getName() + ", now you can retrieve " + majorlifepotion.getLife() + " life points !");
                break;

            case 3:
                MinorLifePotion minorlifepotion = new MinorLifePotion("Minor life potion", 2);
                player.getPotion(minorlifepotion);
                System.out.println("You find a " + minorlifepotion.getName() + ", now you can retrieve " + minorlifepotion.getLife() + " life points !");
                break;

            case 4:
                FireBall fireball = new FireBall("Fire Ball", 7);
                player.getSpell(fireball);
                System.out.println("You find a spell of " + fireball.getName() + ", now you can inflict " + fireball.getDamage() + " damages ! ");
                break;

            case 5:
                LightninBolt lightninbolt = new LightninBolt("Lightnin Bolt", 2);
                player.getSpell(lightninbolt);
                System.out.println("You find a spell of " + lightninbolt.getName() + ", now you can inflict " + lightninbolt.getDamage() + " damages ! ");
                break;
        }
    }
}