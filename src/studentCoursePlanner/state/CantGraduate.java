package studentCoursePlanner.state;

import java.util.ArrayList;

import studentCoursePlanner.context.Student;

public class CantGraduate implements CoursePlannerStateI {

	Student student;

	public CantGraduate(Student studentIn) {
		this.student = studentIn;
	}

	@Override
	public CoursePlannerStateI checkCanGraduate(ArrayList<String> waitListIn, ArrayList<String> courseTakenIn,
			ArrayList<String> notValidCourseIn) {
		System.err.println("This function is not Valid for CantGraduate state");
		System.exit(0);
		return null;
	}

	@Override
	public CoursePlannerStateI checkElectiveAndAddCourse(String courseIn, ArrayList<String> waitListIn,
			ArrayList<String> courseTakenIn, ArrayList<String> notValidCourseIn) {
		System.err.println("This function is not Valid for CantGraduate state");
		System.exit(0);
		return null;
	}

	@Override
	public CoursePlannerStateI hasValidCourseThenAdd(String courseIn, ArrayList<String> waitListIn,
			ArrayList<String> courseTakenIn, ArrayList<String> notValidCourseIn) {
		System.err.println("This function is not Valid for CantGraduate state");
		System.exit(0);
		return null;
	}

}
