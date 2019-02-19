import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class UserInteractiveGrading {

    private HashMap<String, ArrayList<AnswerField>> ANSWER_FIELDS;
    private int numOfProblems;

    public void run() throws InterruptedException, IOException {

        ANSWER_FIELDS = loadAllAnswerFields(); //HashMap mapping page name to list of answer fields on that page

        Thread.sleep(100000);
        System.exit(0);
    }

    /**
     * @return
     * @throws InterruptedException
     * @throws IOException
     */
    private HashMap<String, ArrayList<AnswerField>> loadAllAnswerFields() throws InterruptedException, IOException {

        HashMap<String, ArrayList<AnswerField>> answers = new HashMap<>();
        File[] blankTest = new File(Constants.imagePath + "AllPagesOfBlankTest" + Constants.separator).listFiles();

        int num = 0;
        for (File page : blankTest) {

            answers.put(page.getName(), new ArrayList<>());

            QGImage pageImage = new QGImage(Constants.imagePath + "AllPagesOfBlankTest" + Constants.separator + page.getName());
            pageImage.resize(750, 500);
            pageImage.display();

            int numOfAnswerFields = Integer.parseInt(JOptionPane.showInputDialog("How many answer fields on this page?"));

            for (int i = 0; i < numOfAnswerFields; i++) {
                num++;
                answers.get(page.getName()).add(recordAnswerField(pageImage, num));
            }
        }

        Thread.sleep(1000);
        numOfProblems = num;
        return answers;
    }

    /**
     * @param page
     * @param problemNum
     * @return
     * @throws InterruptedException
     */
    private AnswerField recordAnswerField(QGImage page, int problemNum) throws InterruptedException {

        //AnswerField where we append mouse locations
        AnswerField field = new AnswerField();

        //makes sure mouse is clicked
        while (!page.mouseIsPressed()) {
            Thread.sleep(10);
            continue;
        }

        field.setTopX(getLocationOfMouse()[0]);
        field.setTopY(getLocationOfMouse()[1]);

        //allows time for the user to drag the mouse
        while (page.mouseIsPressed()) {
            Thread.sleep(10);
            continue;
        }

        field.setBottomX(getLocationOfMouse()[0]);
        field.setBottomY(getLocationOfMouse()[1]);

        field.setHeight(Math.abs(field.getTopY() - field.getBottomY()));
        field.setWidth(Math.abs(field.getTopX() - field.getBottomX()));

        field.setProblemNum(problemNum);

        page.drawRectangleAt(field.getTopX(), field.getTopY(), field.getBottomX(), field.getBottomY());

        return field;
    }

    /**
     * @return
     */
    private int[] getLocationOfMouse() {
        int mouseX = MouseInfo.getPointerInfo().getLocation().x;
        int mouseY = MouseInfo.getPointerInfo().getLocation().y;
        return new int[]{mouseX, mouseY};
    }

    private String getPageForNum(int num) {
        for (String page : ANSWER_FIELDS.keySet()) {
            for (AnswerField ans : ANSWER_FIELDS.get(page)) {
                if (ans.getProblemNum() == num) {
                    return page;
                }
            }
        }
        return null;
    }

    private AnswerField getAnswerFieldForNum(int num) {
        for (String page : ANSWER_FIELDS.keySet()) {
            for (AnswerField ans : ANSWER_FIELDS.get(page)) {
                if (ans.getProblemNum() == num) {
                    return ans;
                }
            }
        }
        return null;
    }

}
