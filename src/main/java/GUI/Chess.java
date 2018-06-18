package GUI;


import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Chess {
	private static final Logger LOG =
	        Logger.getLogger("com.nullprogram.chess.Chess");
	public static void main(final String[] args) {
        try {
            String lnf = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(lnf);
        } catch (IllegalAccessException e) {
            LOG.warning("Failed to access 'Look and Feel'");
        } catch (InstantiationException e) {
            LOG.warning("Failed to instantiate 'Look and Feel'");
        } catch (ClassNotFoundException e) {
            LOG.warning("Failed to find 'Look and Feel'");
        } catch (UnsupportedLookAndFeelException e) {
            LOG.warning("Failed to set 'Look and Feel'");
        }
        new ChessFrame(false, true);
    }
}
