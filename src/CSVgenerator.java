import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class CSVgenerator {

    private File name_to_grade;
    private File outBinary;

    public CSVgenerator() {

        //Generate needed file container
        try {
            File outDirectory = new File(Constants.outCSV);
            name_to_grade = new File(Constants.outCSV + File.separator + "scores.csv");
            outBinary = new File(Constants.outCSV + File.separator + "BinaryScores.csv");

            if (!outDirectory.exists()) {outDirectory.mkdir();}
        } catch (Exception e) {
            System.out.println("Could not create CSV folder");
        }
    }

    public void organizeGradeData() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(name_to_grade));

            //the body of the file
            StringBuilder builder = new StringBuilder();

            //the first row of the file
            StringBuilder title = new StringBuilder();

            title.append("name, ");

            for (int i = 1; i <= UserInteractiveGrading.numOfProblems; i++) {
                title.append(i + ", ");
            }

            for (String student : UserInteractiveGrading.scores.keySet()) {
                builder.append(student + ", ");


                System.out.print("scores.get(student): ");
                System.out.println(UserInteractiveGrading.scores.get(student));
                for (int i = 1; i <= UserInteractiveGrading.numOfProblems; i++) {
                    builder.append(UserInteractiveGrading.scores.get(student).get(i).getPercent() + ", ");
                }

                builder.append(UserInteractiveGrading.totals.get(student).getPercent() + ", ");
                builder.append(UserInteractiveGrading.grades.get(student));
                builder.append("\n");
            }

            title.append("total (%), ");
            title.append("grade");

            out.write(title.toString() + "\n");
            out.write(builder.toString());

            out.close();

        } catch (Exception e) {
            System.out.println("Could not populate scores.csv");
        }
    }

    public void organizeBinaryData() {

        try {

            if (!outBinary.exists()) outBinary.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(outBinary));

            //first row of file
            StringBuilder title = new StringBuilder();

            //body of file
            StringBuilder body = new StringBuilder();

            System.out.println("answered correctly: " + UserInteractiveGrading.answeredCorrectly);

            title.append("name, ");
            for (int i = 1; i <= UserInteractiveGrading.numOfProblems; i++) {
                title.append(i + ", ");
            }
            for (String student : UserInteractiveGrading.answeredCorrectly.keySet()) {
                body.append(student + ", ");
                for (int i = 1; i <= UserInteractiveGrading.numOfProblems; i++) {
                    body.append(UserInteractiveGrading.answeredCorrectly.get(student).get(i) + ", ");
                }
                body.append("\n");
            }

            out.write(title + "\n");
            out.write(body.toString());
            out.close();

        } catch (IOException exception) {
            System.out.println("could not generate or populate binary reports file");
        }

    }


//
//    File outDirectory = new File(Constants.outCSV);
//                if (!outDirectory.exists()) outDirectory.mkdir();
//
//    File csv_name_to_grade = new File(Constants.outCSV + File.separator + "scores.csv");
//
//                try {
//
//        if (!csv_name_to_grade.exists()) csv_name_to_grade.createNewFile();
//        BufferedWriter out = new BufferedWriter(new FileWriter(csv_name_to_grade));
//
//        StringBuilder builder = new StringBuilder();
//        StringBuilder title = new StringBuilder();
//
//        title.append("name, ");
//
//        for (int i = 1; i <= UserInteractiveGrading.numOfProblems; i++) {
//            title.append(i + ", ");
//        }
//
//        for (String student : scores.keySet()) {
//            builder.append(student + ", ");
//
//
//            System.out.print("scores.get(student): ");
//            System.out.println(scores.get(student));
//            for (int i = 1; i <= UserInteractiveGrading.numOfProblems; i++) {
//                builder.append(scores.get(student).get(i).getPercent() + ", ");
//            }
//
//            builder.append(UserInteractiveGrading.totals.get(student).getPercent() + ", ");
//            builder.append(UserInteractiveGrading.grades.get(student));
//            builder.append("\n");
//        }
//
//        title.append("total (%), ");
//        title.append("grade");
//
//        out.write(title.toString() + "\n");
//        out.write(builder.toString());
//
//        out.close();
//
//    } catch (
//    IOException exception) {
//        System.out.println("Caught unknown IOException, could not generate File");
//    }
//
//    File outBinary = new File(Constants.outCSV + File.separator + "BinaryScores.csv");
//
//                try {
//
//        if (!outBinary.exists()) outBinary.createNewFile();
//        BufferedWriter out = new BufferedWriter(new FileWriter(outBinary));
//
//        StringBuilder title = new StringBuilder();
//        StringBuilder body = new StringBuilder();
//
//        System.out.println("answered correctly: " + UserInteractiveGrading.answeredCorrectly);
//
//        title.append("name, ");
//        for (int i = 1; i <= UserInteractiveGrading.numOfProblems; i++) {
//            title.append(i + ", ");
//        }
//        for (String student : UserInteractiveGrading.answeredCorrectly.keySet()) {
//            body.append(student + ", ");
//            for (int i = 1; i <= UserInteractiveGrading.numOfProblems; i++) {
//                body.append(UserInteractiveGrading.answeredCorrectly.get(student).get(i) + ", ");
//            }
//            body.append("\n");
//        }
//
//        out.write(title + "\n");
//        out.write(body.toString());
//        out.close();
//
//    } catch (IOException exception) {
//        System.out.println("could not generate binary reports file");
//    }

}