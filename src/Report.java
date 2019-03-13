import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class Report {

    private JFrame frame;

    private JPanel mainPanel;
    private JTextField title;
    private JButton sendInformationButton;
    private JEditorPane reportPane;
    private JTextField classReport;
    private JEditorPane classReportPane;

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public Report(HashMap<String, HashMap<Integer, Score>> scores, int numOfProblems) {

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet styleSheet = kit.getStyleSheet();

        frame = new JFrame("Class Report");
        frame.setPreferredSize(new Dimension(800, 700));

        reportPane.setEditable(false);

        String writeable = "";
        for (String student : scores.keySet()) {
            int suggestedTotal = 0;
            int suggestedEarned = 0;
            writeable += "<q>";
            writeable += "<b>" + student + "</b>" + ": ";
            writeable += "</q>";
            writeable += "<p>";
            for (int i = 1; i <= numOfProblems; i++) {
                suggestedEarned += scores.get(student).get(i).getEarned();
                suggestedTotal += scores.get(student).get(i).getPossible();
                if (i != numOfProblems) {
                    writeable += i + ": " + scores.get(student).get(i).toString() + ", ";
                } else {
                    writeable += i + ": " + scores.get(student).get(i).toString() + "<br>";
                }

            }
            Score score = new Score(suggestedEarned, suggestedTotal);
            writeable += "  total: " + score.toString() + ", " + findGrade(score) + "<br>";
            writeable += "</p>";
            writeable += "<br>";
        }

        styleSheet.addRule(
                "p {" +
                        "width: 60em; " +
                        "margin: 0 auto; " +
                        "max-width: 100%; " +
                        "line-height: 1.5; " +
                        "line-height: 25pt; " +
                        "line-height: 25pt; " +
                        "font-size: 12px; " +
                        "font-family: Optima; " +
                        "background-color: #bec7d6;" +
                "}"
        );

        reportPane.setBorder(BorderFactory.createCompoundBorder(
                mainPanel.getBorder(),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        reportPane.setEditorKit(kit);
        reportPane.setText(writeable);

        HTMLEditorKit reportHtml = new HTMLEditorKit();
        StyleSheet reportStyles = reportHtml.getStyleSheet();

        String reportWriteable = "";

        //all methods return html formatted strings
        reportWriteable += "<p>";
        reportWriteable += "Most common grade: " + getMostCommonGrade() + "<br>";
        reportWriteable += "Average percentage: " + getAveragePercent() + "<br>";
        reportWriteable += "Lowest scorers: " + getLowestScorers() + "<br>";
        reportWriteable += "Highest scorers: " + getHighestScoreres() + "<br>";
        reportWriteable += "Tags (most to least common) " + getOrderedTags() + "<br>";

        classReportPane.setBorder(BorderFactory.createCompoundBorder(
                mainPanel.getBorder(),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        classReportPane.setEditorKit(kit);
        classReportPane.setText(reportWriteable);

        sendInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO Update this code
            }
        });
    }

    //need to implement before can be tested
    public String getMostCommonGrade() {
        return "to implement";
    }

    public String getAveragePercent() {
        return "to implement";
    }

    public String getLowestScorers() {
        return "to implement";
    }

    public String getHighestScoreres() {
        return "to implement";
    }

    public String getOrderedTags() {
        return "to implement";
    }

    public void display() {
        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private Character findGrade(Score score) {
        if (score.getPercent() > 90) return 'A';
        int count = 0;
        for (double i = 0; i <= 100; i += 10) {
            if (score.getPercent() <= i) {
                return Constants.grades[count];
            }
            count++;
        }
        return 'F';
    }

    private void createUIComponents() {
        reportPane = new JEditorPane("text/html", "");
    }
}
