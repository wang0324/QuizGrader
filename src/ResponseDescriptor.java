import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ResponseDescriptor {

    private JFrame frame;
    private JPanel mainPanel;

    private JTextField title;
    private JEditorPane comment;
    private JButton submit;

    private StringBuilder input;

    public ResponseDescriptor(String student, Integer problemNum) {

        frame = new JFrame(student.toUpperCase() + ", #" + problemNum);
        input = new StringBuilder();

        style();

        comment.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                comment.setText("");
            }
        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frame.setVisible(false);
                    UserInteractiveGrading.comments.get(student).put(problemNum, comment.getDocument().getText(0, comment.getDocument().getLength()));
                } catch (Exception exception) {
                    UserInteractiveGrading.logger.log("Could not record comment!");
                }
            }
        });

        frame.add(mainPanel);
        frame.setPreferredSize(new Dimension(400, 300));
        frame.pack();

        frame.setLocation(new Point(
                Constants.getLocationOfMouse()[0] - frame.getWidth() / 2,
                Constants.getLocationOfMouse()[1] - frame.getHeight() / 2
        ));

    }

    public String getText() {
        return comment.getText();
    }

    public void display() {
        frame.setVisible(true);
    }

    private void style() {

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet styleSheet = kit.getStyleSheet();

        comment.setEditorKit(kit);
        comment.setDocument(kit.createDefaultDocument());
        comment.setContentType("text/html");

        styleSheet.addRule("" +
                "p {" +
                "font-size: 14px;" +
                "font-family: Optima; " +
                "}");

        comment.setBorder(BorderFactory.createCompoundBorder(
                comment.getBorder(),
                BorderFactory.createEmptyBorder(10, 15, 10, 10)));
    }
}
