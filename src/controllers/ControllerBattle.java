package controllers;

import models.Pokemon;
import models.Trainer;
import view.battle.ViewBattle;
import models.Combat;

public class ControllerBattle {
    public Trainer trainer1, trainer2;
    private ViewBattle viewBattle;
    private boolean isInBattle = false;
    private Combat combat;

    private static ControllerBattle instance;
    public ControllerBattle(Trainer trainer1, Trainer trainer2) {
        this.trainer1 = trainer1;
        this.trainer2 = trainer2;
        instance = this;
    }

    public static ControllerBattle getInstance(){
        return instance;
    }

    public void setViewBattle(ViewBattle viewBattle) {
        this.viewBattle = viewBattle;
    }

    public void sendMessage(String message){
        viewBattle.sendMessage(message);
    }

    public void startBattle(){
        if (trainer1 == null && trainer2 == null) {
            System.out.println("Por favor, Completa todos los campos");
        }else {
            System.out.println("¡Batalla iniciada!");
            viewBattle.start();
        }

    }

    public void startCombat(int index1, int index2) {
        Pokemon pokemon1 = trainer1.getTeamArray()[index1];
        Pokemon pokemon2 = trainer2.getTeamArray()[index2];
        this.combat = new Combat(pokemon1,pokemon2, this);
        isInBattle = true;
    }

    public void processAttack(int index){
        combat.makeAttack(index);
    }


    public Pokemon getPokemon1(){
        if(isInBattle){
            return combat.getPokemon1();
        }
        throw new RuntimeException("no estan en batalla");
    }

    public Pokemon getPokemon2(){
        if(isInBattle){
            return combat.getPokemon2();
        }
        throw new RuntimeException("no estan en batalla");
    }

    public boolean getTurn(){
        if(isInBattle){
            return combat.getTurn();
        }
        throw new RuntimeException("no estan en batalla!");
    }

    public boolean hasFinish(){
        return combat.hasFinish();
    }

}