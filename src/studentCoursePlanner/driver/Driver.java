package studentCoursePlanner.driver;

import java.util.ArrayList;
import java.util.HashMap;

import studentCoursePlanner.context.Student;
import studentCoursePlanner.util.FileDisplayInterface;
import studentCoursePlanner.util.FileProcessor;
import studentCoursePlanner.util.Results;
import studentCoursePlanner.util.StdoutDisplayInterface;

public class Driver {
	public static void main(String[] args) {
		if (args.length != 2 || args[0].equals("${arg0}") || args[1].equals("${arg1}")) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 2 argumnets.");
			System.exit(0);
		}

		Results resultI = new Results();

		StdoutDisplayInterface std = resultI;
		FileDisplayInterface fdInt = resultI;

		FileProcessor inputF = new FileProcessor(args[0]);

		HashMap<Integer, String[]> studentRecord = new HashMap<Integer, String[]>();
		ArrayList<String> data = inputF.readLine();
		for (String record : data) {
			String[] st = record.split(":");
			studentRecord.put(Integer.parseInt(st[0].trim()), st[1].split(" "));
		}

		for (Integer bNum : studentRecord.keySet()) {
			Student student = new Student();
			String[] value = studentRecord.get(bNum);
			for (String course : value) {
				if (!course.equals("")) {
					int sem = student.manageCourse(course, student, bNum);
					if ((sem == -1) || (sem > 0)) {
						break;
					}
				}
			}
			student.populateResult(student, resultI, bNum);
		}

		std.printConsole();
		fdInt.writeToFile(args[1]);
	}
}
