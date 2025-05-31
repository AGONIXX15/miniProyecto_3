package main;

import controller.ControllerTrainer;
import models.Trainer;
import view.MainFrame;
import view.ViewTrainerConsola;

public class Main {
    public static void main(String[] args) {

        Trainer trainer1 = new Trainer();
        Trainer trainer2 = new Trainer();
        ViewTrainerConsola vistaInicial = new ViewTrainerConsola(null); // Se asigna después
        ControllerTrainer controller = ControllerTrainer.getInstance();
        controller.setViewI(vistaInicial);
        vistaInicial.setControllerTrainer(controller);
        vistaInicial.mostrarMenu();
    }
}