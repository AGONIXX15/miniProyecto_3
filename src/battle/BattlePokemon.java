package battle;

import models.Trainer;
import models.pokemon.Pokemon;
import models.pokemon.utils.Attack;
import view.battle.BattlePokemonGUI;
//import view.PokemonMenu;

import java.util.Scanner;

public class BattlePokemon {

    public BattlePokemon() {
    }




    public static boolean findOut(Pokemon pokemon1, Pokemon pokemon2){
        if (pokemon1.isAlive() && pokemon2.isAlive()){
            return false;
        }

        boolean result = false;
        if(pokemon1.isAlive()){
            BattlePokemonGUI.getInstance().putMessage(String.format("%s Gano la batalla pokemonn!\n", pokemon1.getName()));
            result = true;
        }else{
            BattlePokemonGUI.getInstance().putMessage(String.format("%s Gano la batalla pokemonn!\n", pokemon2.getName()));
            result = true;
        }

        return result;
    }
}
