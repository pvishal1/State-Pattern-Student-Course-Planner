package studentCoursePlanner.state;

import java.util.ArrayList;

import studentCoursePlanner.context.Student;

public class GraduateCheck implements CoursePlannerStateI {
	Student student;

	public GraduateCheck(Student student) {
		super();
		this.student = student;
	}

	@Override
	public CoursePlannerStateI checkCanGraduate(ArrayList<String> waitListIn, ArrayList<String> courseTakenIn,
			ArrayList<String> notValidCourseIn) {
		ArrayList<String> temp = new ArrayList<>();
		for (String c : waitListIn) {
			if (!notValidCourseIn.contains(c)) {
				courseTakenIn.add(c);
				student.setIsCourseAdded(true);
				if (c.matches("[A-D]")) {
					student.setLongProgramming(student.getLongProgramming() + 1);
				} else if (c.matches("[E-H]")) {
					student.setDataStructures(student.getDataStructures() + 1);
				} else if (c.matches("[I-L]")) {
					student.setHardwareSequence(student.getHardwareSequence() + 1);
				} else if (c.matches("[M-P]")) {
					student.setDataAnalytics(student.getDataAnalytics() + 1);
				}
				temp.add(c);
			}
		}
		for (String c : temp) {
			waitListIn.remove(c);
		}

		int totalNoCourseObserved = waitListIn.size() + student.getLongProgramming() + student.getDataStructures()
				+ student.getHardwareSequence() + student.getDataAnalytics() + student.getElectives();
		if ((totalNoCourseObserved % 3 == 0) && waitListIn.size() != 0) {
			for (String c : waitListIn) {
				courseTakenIn.add(c);
			}
			student.setCourseTaken(courseTakenIn);
			return new CantGraduate(student);
		}
		totalNoCourseObserved -= waitListIn.size();
		if (totalNoCourseObserved % 3 == 0 && student.isCourseAdded()) {
			student.setSemTaken(student.getSemTaken() + 1);
		}
		if ((student.getLongProgramming() >= 2) && (student.getDataStructures() >= 2)
				&& (student.getHardwareSequence() >= 2) && (student.getDataAnalytics() >= 2)
				&& (student.getElectives() >= 2)) {
			student.setIsGraduated(true);
		}
		student.setCourseTaken(courseTakenIn);
		return new AcceptCourse(student);
	}

	@Override
	public CoursePlannerStateI checkElectiveAndAddCourse(String courseIn, ArrayList<String> waitListIn,
			ArrayList<String> courseTakenIn, ArrayList<String> notValidCourseIn) {
		System.err.println("This function is not Valid for GraduateCheck state");
		System.exit(0);
		return null;
	}

	@Override
	public CoursePlannerStateI hasValidCourseThenAdd(String courseIn, ArrayList<String> waitListIn,
			ArrayList<String> courseTakenIn, ArrayList<String> notValidCourseIn) {
		System.err.println("This function is not Valid for GraduateCheck state");
		System.exit(0);
		return null;
	}
}
