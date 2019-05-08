package studentCoursePlanner.state;

import java.util.ArrayList;

import studentCoursePlanner.context.Student;

public class AcceptCourse implements CoursePlannerStateI {

	Student student;

	public AcceptCourse(Student student) {
		this.student = student;
	}

	@Override
	public CoursePlannerStateI checkCanGraduate(ArrayList<String> waitListIn, ArrayList<String> courseTakenIn,
			ArrayList<String> notValidCourseIn) {
		System.err.println("This function is not Valid for AcceptCourse state");
		System.exit(0);
		return null;
	}

	@Override
	public CoursePlannerStateI checkElectiveAndAddCourse(String courseIn, ArrayList<String> waitListIn,
			ArrayList<String> courseTakenIn, ArrayList<String> notValidCourseIn) {

		student.setCurrentState(new Electives(this.student));
		return student.getCurrentState().checkElectiveAndAddCourse(courseIn, waitListIn, courseTakenIn, notValidCourseIn);
	}

	@Override
	public CoursePlannerStateI hasValidCourseThenAdd(String courseIn, ArrayList<String> waitListIn,
			ArrayList<String> courseTakenIn, ArrayList<String> notValidCourseIn) {
		System.err.println("This function is not Valid for AcceptCourse state");
		System.exit(0);
		return null;
	}

}
