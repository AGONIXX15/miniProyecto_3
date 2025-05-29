package models.pokemon.utils;

public enum TypePokemon {
    ELECTRICO(new String[]{"AGUA","VOLADOR"}),
    FUEGO(new String[]{"PLANTA"}),
    AGUA(new String[]{"FUEGO"}),
    PLANTA(new String[]{"PLANTA"}),
    VOLADOR(new String[]{"PLANTA"})
    ;

    public String[] strong;
    TypePokemon(String[] strong) {
        this.strong = strong;
    }

    String[] getStrong() {
        return strong;
    }
}