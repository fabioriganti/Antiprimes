import antiprimes.AntiPrimesSequence;
import ui.MainWindow;
import javax.swing.*;


/**
 * Class that setup the program.
 */
public class AntiPrimesApplication {
    private AntiPrimesSequence sequence;

    /**
     * Launch the program.
     */
    public static void main(String[] args) {
        AntiPrimesSequence sequence = new AntiPrimesSequence();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainWindow window = new MainWindow(sequence);
                sequence.setObserver(window);
                window.setVisible(true);
            }
        });
    }
}
