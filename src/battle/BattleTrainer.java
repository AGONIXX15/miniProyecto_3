package battle;

import models.Trainer;
import models.pokemon.Pokemon;
import view.battle.BattlePokemonGUI;
//import view.PokemonMenu;

import java.util.Scanner;

public class BattleTrainer {





    public static boolean trainerHasLost(Trainer trainer) {
        for(Pokemon pokemon: trainer.getTeamArray()){
            if(pokemon.isAlive()){
                return false;
            }
        }
        return true;
    }


    public static boolean combat(Trainer trainer1, Trainer trainer2) {
        if(!BattleTrainer.trainerHasLost(trainer1) && !BattleTrainer.trainerHasLost(trainer2)) {
            return false;
        }

        if (BattleTrainer.trainerHasLost(trainer1)){
            BattlePokemonGUI.getInstance().putMessage(String.format("%s Perdio la batalla pokemon!\n", trainer1.getNameTrainer()));
            BattlePokemonGUI.getInstance().putMessage(String.format("%s gano la batalla pokemon!\n", trainer2.getNameTrainer()));
            return true;
        } else {
            BattlePokemonGUI.getInstance().putMessage(String.format("%s Gano la batalla pokemonn!", trainer1.getNameTrainer()));
            BattlePokemonGUI.getInstance().putMessage(String.format("%s Perdio la batalla pokemonn!", trainer2.getNameTrainer()));
            return true;
        }
    }
}
