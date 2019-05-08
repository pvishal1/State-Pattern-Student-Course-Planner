package studentCoursePlanner.state;

import java.util.ArrayList;

public interface CoursePlannerStateI {
	/**
	 * check if the course provided is elective or not.
	 * Add if it's elective else call hasValidCourseThenAdd from Categories state
	 * @param course
	 * @param waitList
	 * @param courseTaken
	 * @param notValidCourse
	 * @return GraduateCheck state
	 */
	CoursePlannerStateI checkElectiveAndAddCourse(String courseIn, ArrayList<String> waitListIn, ArrayList<String> courseTakenIn,
			ArrayList<String> notValidCourseIn);

	/**
	 * check if the student can graduate or not.
	 * If can't graduate because of prerequisite not satisfied, return CantGraduate state
	 * Else set the isGraduate flag of student to true, if Graduated
	 * Default returns AcceptCourse state
	 * @param waitList
	 * @param courseTaken
	 * @param notValidCourse
	 * @return
	 */
	CoursePlannerStateI checkCanGraduate(ArrayList<String> waitListIn, ArrayList<String> courseTakenIn,
			ArrayList<String> notValidCourseIn);

	/**
	 * check if the course provided is valid or not.
	 * Add if it's not valid, add to wait list
	 * @param course
	 * @param waitList
	 * @param courseTaken
	 * @param notValidCourse
	 * @return GraduateCheck state
	 */
	CoursePlannerStateI hasValidCourseThenAdd(String courseIn, ArrayList<String> waitListIn, ArrayList<String> courseTakenIn,
			ArrayList<String> notValidCourseIn);
}
