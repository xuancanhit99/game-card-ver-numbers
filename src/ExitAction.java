import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ExitAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    ExitAction() {
        putValue(NAME, "Exit");
    }
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}

