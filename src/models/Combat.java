package models;

import controllers.ControllerBattle;

public class Combat {
    private boolean turn;
    private Pokemon pokemon1;

    public boolean getTurn() {
        return turn;
    }

    public Pokemon getPokemon1() {
        return pokemon1;
    }

    public Pokemon getPokemon2() {
        return pokemon2;
    }

    private Pokemon pokemon2;
    private ControllerBattle controller;


    public Combat(Pokemon pokemon1, Pokemon pokemon2, ControllerBattle controller) {
        this.controller = controller;
        turn = pokemon1.getHealthMax() >= pokemon2.getHealthMax();
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
    }

    public void changeTurn(){
        turn = !turn;
    }

    public void makeAttack(int index){
        if(turn){
            attack(pokemon1, index, pokemon2, controller.trainer1.getNameTrainer());
        } else {
            attack(pokemon2, index, pokemon1, controller.trainer2.getNameTrainer());
        }
        changeTurn();
    }


    public void attack(Pokemon pokemon1,int index, Pokemon pokemon2, String trainerName) {
        Attack attack = pokemon1.getAttacks()[index];
        float advantage = (pokemon1.hasAdvantage(pokemon2)) ? 1.3f : 1;
        if(advantage > 1){
            controller.sendMessage("El ataque ha sido efectivo!!");
            // BattlePokemonGUI.getInstance().sendMessage("El ataque ha sido efectivo!!");
        }
        int damage = (int) (advantage * attack.getPower());
        //BattlePokemonGUI.getInstance().sendMessage(String.format("%s realizo %s hacia %s con un daño de %d\n",name, attack.getName(), enemy.getName(), damage));
        controller.sendMessage(String.format("%s realizo %s hacia %s con un daño de %d\n",trainerName, attack.getName(), pokemon2.getName(), damage));
        pokemon2.takeDamage(damage);
    }

    public boolean hasFinish(){
        return !(pokemon1.isAlive() && pokemon2.isAlive());
    }


}
