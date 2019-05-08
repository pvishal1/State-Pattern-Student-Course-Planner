package studentCoursePlanner.state;

import java.util.ArrayList;

import studentCoursePlanner.context.Student;

public class Electives implements CoursePlannerStateI {

	Student student;

	public Electives(Student studentIn) {
		this.student = studentIn;
	}

	@Override
	public CoursePlannerStateI checkCanGraduate(ArrayList<String> waitListIn, ArrayList<String> courseTakenIn,
			ArrayList<String> notValidCourseIn) {
		System.err.println("This function is not Valid for Electives state");
		System.exit(0);
		return null;
	}

	@Override
	public CoursePlannerStateI checkElectiveAndAddCourse(String courseIn, ArrayList<String> waitListIn,
			ArrayList<String> courseTakenIn, ArrayList<String> notValidCourseIn) {

		if (courseIn.matches("[Q-Z]")) {
			boolean flag=true;
			for (String cTaken : courseTakenIn) {
				if(cTaken.equals(courseIn)) {
					flag = false;
				}
			}
			if(flag) {
				courseTakenIn.add(courseIn);
				student.setIsCourseAdded(true);
				student.setCourseTaken(courseTakenIn);
				student.setElectives(student.getElectives() + 1);
			}
			return new GraduateCheck(student);
		} else {
			student.setCurrentState(new Categories(this.student));
			return student.getCurrentState().hasValidCourseThenAdd(courseIn, waitListIn, courseTakenIn,
					notValidCourseIn);
		}
	}

	@Override
	public CoursePlannerStateI hasValidCourseThenAdd(String courseIn, ArrayList<String> waitListIn,
			ArrayList<String> courseTakenIn, ArrayList<String> notValidCourseIn) {
		System.err.println("This function is not Valid for Electives state");
		System.exit(0);
		return null;
	}

}
