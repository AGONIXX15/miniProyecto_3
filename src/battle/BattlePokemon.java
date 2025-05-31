package battle;

import models.Pokemon;
import view.battle.BattlePokemonGUI;
//import view.PokemonMenu;


public class BattlePokemon {

    public BattlePokemon() {
    }




    public static boolean findOut(Pokemon pokemon1, Pokemon pokemon2){
        if (pokemon1.isAlive() && pokemon2.isAlive()){
            return false;
        }

        boolean result = false;
        if(pokemon1.isAlive()){
            BattlePokemonGUI.getInstance().sendMessage(String.format("%s Gano la batalla pokemonn!\n", pokemon1.getName()));
            result = true;
        }else{
            BattlePokemonGUI.getInstance().sendMessage(String.format("%s Gano la batalla pokemonn!\n", pokemon2.getName()));
            result = true;
        }

        return result;
    }
}
