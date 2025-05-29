package view;
import models.Trainer;
import models.pokemon.utils.ReproduceSound;
import utils.CustomFont;
import utils.PokemonFactory;
import javax.imageio.ImageIO;
import java.net.URL;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import view.utils.Pokedex;
import view.battle.BattlePokemonGUI;


public class ViewTrainer extends JFrame {
    Trainer trainer1;
    Trainer trainer2;
    String nombre1, nombre2;
    JPanel contenedor;
    JLabel entrenador1, entrenador2,textoBienvenida;
    JButton iniciarBatalla, mostrarEquipo, asignarEntreadores;
    TextField entrenador1Texto, entrenador2Texto;
    Boolean entreadoresIntroduccidos,asignacionDeEquipos = false;
    private boolean tried;

    public ViewTrainer() {
        tried = false;
        setTitle("Seleccionar Entrenadores");

        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 16));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 14));
        UIManager.put("Button.background", new Color(205, 64, 64));
        UIManager.put("Button.foreground", Color.WHITE);

        ImageIcon fondoIcon,fondoBotonSeleccionar,fondoBotonAsignar,fondoBotonBatalla;

        fondoIcon = new ImageIcon("src/models/pokemon/utils/images/fondoSeleccion.png");
        fondoBotonSeleccionar = new ImageIcon("src/models/pokemon/utils/images/fondoBotonSeleccionar3.png");
        fondoBotonAsignar = new ImageIcon("src/models/pokemon/utils/images/fondoBotonAsignar.png");
        fondoBotonBatalla = new ImageIcon("src/models/pokemon/utils/images/fondoBotonBatalla.png");
        JLabel fondo = new JLabel(fondoIcon);
        fondo.setBounds(0, 0, fondoIcon.getIconWidth(), fondoIcon.getIconHeight());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contenedor = new JPanel();
        contenedor.setLayout(null);
        contenedor.add(fondo);
        setContentPane(fondo);

        // Labels y TextFields
        textoBienvenida = new JLabel("Ingresa los entrenadores y selecciona equipos");

        textoBienvenida.setFont(CustomFont.loadfont(55f));
        textoBienvenida.setForeground(Color.WHITE);
        textoBienvenida.setBounds(280, 10, 1500, 50);
        fondo.add(textoBienvenida);

        entrenador1 = new JLabel("Entrenador 1:");
        entrenador1.setFont(CustomFont.loadfont(40f));
        entrenador1.setForeground(Color.BLACK);
        entrenador1.setBounds(300, 300, 400, 30);
        fondo.add(entrenador1);

        entrenador1Texto = new TextField();
        entrenador1Texto.setBounds(250, 350, 300, 30);
        fondo.add(entrenador1Texto);

        entrenador2 = new JLabel("Entrenador 2:");
        entrenador2.setFont(CustomFont.loadfont(40f));
        entrenador2.setForeground(Color.BLACK);
        entrenador2.setBounds(950, 300, 400, 30);
        fondo.add(entrenador2);

        entrenador2Texto = new TextField();
        entrenador2Texto.setBounds(900, 350, 300, 30);
        fondo.add(entrenador2Texto);

        // Botones
        asignarEntreadores = new JButton("Ingresar entrenadores",fondoBotonSeleccionar);
        asignarEntreadores.setBounds(600, 500, 300, 60);
        fondo.add(asignarEntreadores);

        mostrarEquipo = new JButton("Asignar y Mostrar Equipo",fondoBotonAsignar);
        mostrarEquipo.setBounds(600, 600, 300, 60);
        fondo.add(mostrarEquipo);

        iniciarBatalla = new JButton("Iniciar Batalla",fondoBotonBatalla);
        iniciarBatalla.setBounds(600, 700, 300, 60);
        fondo.add(iniciarBatalla);

        asignarEntreadores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AlertasignarEquipo();
            }
        });

        mostrarEquipo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (entreadoresIntroduccidos) {
                    asignacionDeEquipos = true;
                    mostrarEquipo();
                    return;
                }
                JOptionPane.showMessageDialog(null, "ponga los entrenadores");
            }

        });
        setVisible(true);

        iniciarBatalla.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(entreadoresIntroduccidos && asignacionDeEquipos && !tried) {
                    tried = true;
                    MainFrame.reproduceSound.stopSound(); // parar sonido de inicio
                    ReproduceSound reproduceSound = new ReproduceSound();
                    reproduceSound.loadSound("src/models/pokemon/utils/ready-fight-37973.wav");
                    reproduceSound.playSound();
                    Timer t = new Timer(1000, event -> {
                        setVisible(false);
                        new BattlePokemonGUI(trainer1, trainer2);
                    });
                    t.setRepeats(false);
                    t.start();
                    return;
                }
                if (!tried){
                    JOptionPane.showMessageDialog(null, "ponga los entrenadores y asigne su equipo");
                }

            }
        });


    }


    //al darle al boton de ingresar entrenadores
    public void AlertasignarEquipo() {
        nombre1 = entrenador1Texto.getText();
        nombre2 = entrenador2Texto.getText();
        if (!nombre1.isEmpty() && !nombre2.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Entrenadores ingresados");
            entreadoresIntroduccidos = true;
            return;
        }
        JOptionPane.showMessageDialog(null, "Introduce los entrenadores");
        entreadoresIntroduccidos = false;
    }


    public void mostrarEquipo() {




        PokemonFactory cargasEquipos = new PokemonFactory();
            trainer1 = new Trainer(nombre1, cargasEquipos.loadAvailablePokemons());
            trainer1.randomTeam();
            trainer2 = new Trainer(nombre2, cargasEquipos.loadAvailablePokemons());
            trainer2.randomTeam();

            JPanel panel = new JPanel(new GridLayout(1, 2));



            //mostrar equipo de entranador1
            JPanel panel1 = new JPanel();

            panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
            JLabel textoEquipo =new JLabel("Equipo de " + trainer1.getNameTrainer() + ":");
            textoEquipo.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
            panel1.add(textoEquipo);
        for (models.pokemon.Pokemon p : trainer1.getTeamArray()) {
            JPanel pokemonPanel = new JPanel(new BorderLayout());
            pokemonPanel.setBorder(BorderFactory.createLineBorder(Color.blue, 2)); // Borde negro
            pokemonPanel.setBackground(Color.cyan); // Fondo blanco (opcional)
            pokemonPanel.setPreferredSize(new Dimension(450, 70)); // Tamaño fijo opcional

            Integer id = Pokedex.pokedex.get(p.getName());
            if (id != null) {
                String imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + id + ".png";
                try {
                    URL url = new URL(imageUrl);
                    Image imagen = ImageIO.read(url);
                    Image escalaImagen = imagen.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                    JLabel iconLabel = new JLabel(new ImageIcon(escalaImagen));
                    pokemonPanel.add(iconLabel, BorderLayout.WEST);
                } catch (Exception ex) {
                    System.out.println("Error cargando imagen para " + p.getName() + ": " + ex.getMessage());
                }
            }

            JLabel infoLabel = new JLabel("Nombre: " + p.getName() + ", Tipo: " + p.getType() + ", Vida: " + p.getHealth());
            infoLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
            pokemonPanel.add(infoLabel, BorderLayout.CENTER);

            panel1.add(Box.createRigidArea(new Dimension(10,3))); // Espaciado entre recuadros
            panel1.add(pokemonPanel);
        }

            //mostrar equipo de entranador2
            JPanel panel2 = new JPanel();
            panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
            JLabel textoEquipo2 = new JLabel("Equipo de " + trainer2.getNameTrainer() + ":");
            textoEquipo2.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
            panel2.add(textoEquipo2);
        for (models.pokemon.Pokemon p : trainer2.getTeamArray()) {
            JPanel pokemonPanel = new JPanel(new BorderLayout());
            pokemonPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2)); // Borde negro
            pokemonPanel.setBackground(new Color(205, 64, 64)); // Fondo blanco (opcional)
            pokemonPanel.setPreferredSize(new Dimension(450, 70)); // Tamaño fijo opcional

            Integer id = Pokedex.pokedex.get(p.getName());
            if (id != null) {
                String imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + id + ".png";
                try {
                    URL url = new URL(imageUrl);
                    Image imagen = ImageIO.read(url);
                    Image escalaImagen = imagen.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                    JLabel iconLabel = new JLabel(new ImageIcon(escalaImagen));
                    pokemonPanel.add(iconLabel, BorderLayout.WEST);
                } catch (Exception ex) {
                    System.out.println("Error cargando imagen para " + p.getName() + ": " + ex.getMessage());
                }
            }

            JLabel infoLabel = new JLabel("Nombre: " + p.getName() + ", Tipo: " + p.getType() + ", Vida: " + p.getHealth());
            infoLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
            pokemonPanel.add(infoLabel, BorderLayout.CENTER);

            panel2.add(Box.createRigidArea(new Dimension(10,3))); // Espaciado entre recuadros
            panel2.add(pokemonPanel);
        }


        panel.add(panel1);
        panel.add(panel2);


            JOptionPane.showMessageDialog(this, panel, "Equipos", JOptionPane.PLAIN_MESSAGE);
        }


    public static void StartSelectTrainerTeam (){
        new ViewTrainer();

    }
}
