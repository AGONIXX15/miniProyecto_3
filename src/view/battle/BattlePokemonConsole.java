package view.battle;

public class BattlePokemonConsole implements ViewBattle {

    public BattlePokemonConsole() {

    }

    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }
}
