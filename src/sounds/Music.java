/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sounds;

/*
 importamos librerias
 */
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;

/**
 *
 * @author sebath
 */
public class Music extends JFrame {

    // declaramos las variables que contendran nuestra musica
    private Clip music1;
    private Clip music2;

    // declaramos los botones que vamos a usar 
    private JButton playMusic1;
    private JButton playMusic2;
    private JButton loopMusic1;
    private JButton loopMusic2;
    private JButton stopButton;

    // Creamos el constructor principal de la aplicacion
    public Music() {

        //configuramos los valores de la ventana
        setTitle("Mi reproductor casero");
        setSize(450, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); // para que no se redimencione
        setLayout(new BorderLayout());

        // creamos un panel para todos los botones
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // añadimos un titulo en la parte superior
        JLabel titleLabel = new JLabel("Reproductor Casero");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        // creamos los botones que estan declarados
        playMusic1 = new JButton("Reproducir 1");
        playMusic2 = new JButton("Reproducir 2");
        loopMusic1 = new JButton("Reprodir 1 continuo");
        loopMusic2 = new JButton("Reproducir 2 continua");
        stopButton = new JButton("Stop");

        // añadimos los botones al panelde botones
        buttonsPanel.add(playMusic1);
        buttonsPanel.add(playMusic2);
        buttonsPanel.add(loopMusic1);
        buttonsPanel.add(loopMusic2);
        buttonsPanel.add(stopButton);

        // añadimos el panel de botones al centro de la ventana
        add(buttonsPanel, BorderLayout.CENTER);

        // en un try-catch cargamos los sonidos a usar con extension .wav
        try {
            // cargamos el sonido 1
            URL url1 = getClass().getResource("/assets/RISE-_ft.-The-Glitch-Mob_-Mako_-and-The-Word-Alive_-Worlds-2018-League-of-Legends.wav");
            music1 = loadSound(url1, "RISE-_ft.-The-Glitch-Mob_-Mako_-and-The-Word-Alive_-Worlds-2018-League-of-Legends.wav");

            // cargamos el sonido 2
            URL url2 = getClass().getResource("/assets/Tokio-Hotel-Monsoon-Sub-Español.wav");
            music2 = loadSound(url2, "Tokio-Hotel-Monsoon-Sub-Español.wav");

        } catch (Exception e) {

            // mandamos en pantalla un panel de error
            JOptionPane.showMessageDialog(this, "Musica no encontrada error fatal");
            e.printStackTrace();

            // desabilitamos todos los botones en caso de error
            playMusic1.setEnabled(false);
            playMusic2.setEnabled(false);
            loopMusic1.setEnabled(false);
            loopMusic2.setEnabled(false);
            stopButton.setEnabled(false);
        }

        // añadimos los ActionListeners a los botones
        playMusic1.addActionListener(e -> playSoundOnce(music1));
        playMusic2.addActionListener(e -> playSoundOnce(music2));
        loopMusic1.addActionListener(e -> playSoundLoop(music1));
        loopMusic2.addActionListener(e -> playSoundLoop(music2));
        stopButton.addActionListener(e -> stopAllSound());
        
        // hacemos visible la ventana
        setVisible(true);
    }
    
    // creamos un metodo para cargar los sonidos con el link
    private Clip loadSound(URL url, String fileName) throws Exception {
        if (url == null) {
            throw new IllegalArgumentException("No se encontro el sonido " + fileName);
        }
        try (InputStream audioStream = new BufferedInputStream(url.openStream())) {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioStream);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            return clip;
        }

    }
    
    
    // creamos el metodo ppara reproducir una vez la cancion
    private void playSoundOnce(Clip clip){
    
        if(clip != null){
            stopAllSound(); // usamos la funcion para detener todos los sonidos
            clip.setFramePosition(0); // reiniciamos el sonido
            clip.start(); // iniciamos a reproducir
        }
    }
    
    // creamos el metodo que repita el sonido en bucle
    private void playSoundLoop(Clip clip){
        if(clip != null){
            stopAllSound(); // usamos la funcion para detener cualquier somido que se este ejecutando
            clip.setFramePosition(0); // reiniciamos el sonido
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    
    // creamos el motodo para detener todos los sonidos
    private void stopAllSound(){
    // parar la cancion 1
        if(music1 != null && music1.isRunning()){
            music1.stop(); // paramos la cancion
        }
    // parar la cancion 2    
        if(music2 != null && music2.isRunning()){
            music2.stop(); // paramos la cancion
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // ejecutamos la ventana en el hilo de despacho de swing
        SwingUtilities.invokeLater(() -> new Music());
    }

}
