package studentCoursePlanner.context;

import java.util.ArrayList;

import studentCoursePlanner.state.AcceptCourse;
import studentCoursePlanner.state.CantGraduate;
import studentCoursePlanner.state.CoursePlannerStateI;
import studentCoursePlanner.util.Results;

public class Student {
	int longProgramming;
	int dataStructures;
	int hardwareSequence;
	int dataAnalytics;
	int electives;
	ArrayList<String> waitList;
	ArrayList<String> courseTaken;
	int semTaken;
	boolean isGraduated;
	boolean isCourseAdded;
	CoursePlannerStateI currentState;
	ArrayList<String> notValidCourse;

	public Student() {
		super();
		longProgramming = 0;
		dataStructures = 0;
		hardwareSequence = 0;
		dataAnalytics = 0;
		electives = 0;
		waitList = new ArrayList<>();
		semTaken = 0;
		isGraduated = false;
		isCourseAdded = false;
		courseTaken = new ArrayList<>();
		currentState = new AcceptCourse(this);

		notValidCourse = new ArrayList<>();
		notValidCourse.add("D");
		notValidCourse.add("H");
		notValidCourse.add("L");
		notValidCourse.add("P");
	}
	
	@Override
	public String toString() {
		String toPrint = "Courses taken till now by this student: "+courseTaken+"\nGraduated: "+isGraduated;
		return toPrint;
	}

	/**
	 * whether course provided is added or not
	 * @return boolean
	 */
	public boolean isCourseAdded() {
		return isCourseAdded;
	}

	/**
	 * Set true if course given is added else false
	 * @param isCourseAdded
	 */
	public void setIsCourseAdded(boolean isCourseAdded) {
		this.isCourseAdded = isCourseAdded;
	}

	/**
	 * Manage the Course. Assign them if valid.
	 * Returns the sem if Graduated, -1 when not graduated else default 0
	 * @param courseIn
	 * @param studentIn
	 * @param bNumIn
	 * @return numOfSem if Graduated, -1 when not graduated else default 0
	 */
	public int manageCourse(String courseIn, Student studentIn, int bNumIn) {
		currentState = currentState.checkElectiveAndAddCourse(courseIn, waitList, courseTaken, notValidCourse);

		currentState = currentState.checkCanGraduate(waitList, courseTaken, notValidCourse);
		setIsCourseAdded(false);
		if (currentState instanceof CantGraduate) {
			studentIn.setSemTaken(-1);
			return studentIn.getSemTaken();
		} else if (studentIn.isGraduated) {
			if (courseTaken.size() % 3 != 0) {
				studentIn.setSemTaken(studentIn.getSemTaken() + 1);
			}
			return studentIn.getSemTaken();
		}
		return 0;
	}

	/**
	 * Populates the result list with the results of each BNo
	 * @param studentIn
	 * @param resultIn
	 * @param bNumIn
	 */
	public void populateResult(Student studentIn, Results resultIn, int bNumIn) {
		if (studentIn.getSemTaken() <= 0 || !studentIn.isGraduated) {
			resultIn.addResult(Integer.toString(bNumIn) + ": " + studentIn.getCourseTaken() + ": Can't Graduate");
		} else {
			resultIn.addResult(Integer.toString(bNumIn) + ": " + studentIn.getCourseTaken() + ": "
					+ Integer.toString(studentIn.getSemTaken()));
		}
	}

	/**
	 * get the number of long programming courses taken
	 * @return integer
	 */
	public int getLongProgramming() {
		return longProgramming;
	}

	/**
	 * set the counter of long programming course
	 * @param longProgramming
	 */
	public void setLongProgramming(int longProgramming) {
		this.longProgramming = longProgramming;
	}

	/**
	 * get the number of data structures courses taken
	 * @return integer
	 */
	public int getDataStructures() {
		return dataStructures;
	}

	/**
	 * set the counter of data structures course
	 * @param dataStructures
	 */
	public void setDataStructures(int dataStructures) {
		this.dataStructures = dataStructures;
	}

	/**
	 * get the number of hardware sequence courses taken
	 * @return integer
	 */
	public int getHardwareSequence() {
		return hardwareSequence;
	}

	/**
	 * set the counter of hardware sequence course
	 * @param hardwareSequence
	 */
	public void setHardwareSequence(int hardwareSequence) {
		this.hardwareSequence = hardwareSequence;
	}

	/**
	 * get the number of data analytics courses taken
	 * @return integer
	 */
	public int getDataAnalytics() {
		return dataAnalytics;
	}

	/**
	 * set the counter of data analytics course
	 * @param dataAnalytics
	 */
	public void setDataAnalytics(int dataAnalytics) {
		this.dataAnalytics = dataAnalytics;
	}

	/**
	 * get the number of electives course taken
	 * @return integer
	 */
	public int getElectives() {
		return electives;
	}

	/**
	 * set the counter of elective course
	 * @param electives
	 */
	public void setElectives(int electives) {
		this.electives = electives;
	}

	/**
	 * gets the list of all the courses taken
	 * @return list
	 */
	public ArrayList<String> getCourseTaken() {
		return courseTaken;
	}

	/**
	 * set the course taken list to the list provided
	 * @param courseTaken
	 */
	public void setCourseTaken(ArrayList<String> courseTaken) {
		this.courseTaken = courseTaken;
	}

	/**
	 * whether the student is graduated or not
	 * @return boolean
	 */
	public boolean isGraduated() {
		return isGraduated;
	}

	/**
	 * set true when student graduates
	 * @param isGraduated
	 */
	public void setIsGraduated(boolean isGraduated) {
		this.isGraduated = isGraduated;
	}

	/**
	 * gets the list of courses that are not valid and may lead to not graduation
	 * @return list
	 */
	public ArrayList<String> getNotValidCourse() {
		return notValidCourse;
	}

	/**
	 * set the not valid course list to the list provided
	 * @param notValidCourse
	 */
	public void setNotValidCourse(ArrayList<String> notValidCourse) {
		this.notValidCourse = notValidCourse;
	}

	/**
	 * gets the list of courses that are in waiting list
	 * @return list
	 */
	public ArrayList<String> getWaitList() {
		return waitList;
	}

	/**
	 * set the wait list to the list provided
	 * @param waitList
	 */
	public void setWaitList(ArrayList<String> waitList) {
		this.waitList = waitList;
	}

	/**
	 * get the number of semester completed
	 * @return integer
	 */
	public int getSemTaken() {
		return semTaken;
	}

	/**
	 * set the value of number of semesters completed
	 * @param semTaken
	 */
	public void setSemTaken(int semTaken) {
		this.semTaken = semTaken;
	}

	/**
	 * get the current state where the student is present
	 * @return
	 */
	public CoursePlannerStateI getCurrentState() {
		return currentState;
	}

	/**
	 * set the current state of the student to perform further requirements
	 * @param currentState
	 */
	public void setCurrentState(CoursePlannerStateI currentState) {
		this.currentState = currentState;
	}
}
