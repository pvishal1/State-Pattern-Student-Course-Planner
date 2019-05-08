package studentCoursePlanner.state;

import java.util.ArrayList;

import studentCoursePlanner.context.Student;

public class Categories implements CoursePlannerStateI {

	Student student;

	public Categories(Student student) {
		this.student = student;
	}

	@Override
	public CoursePlannerStateI checkCanGraduate(ArrayList<String> waitListIn, ArrayList<String> courseTakenIn,
			ArrayList<String> notValidCourseIn) {
		System.err.println("This function is not Valid for Categories state");
		System.exit(0);
		return null;
	}

	@Override
	public CoursePlannerStateI hasValidCourseThenAdd(String courseIn, ArrayList<String> waitListIn,
			ArrayList<String> courseTakenIn, ArrayList<String> notValidCourseIn) {

		if (notValidCourseIn.contains(courseIn)) {
			waitListIn.add(courseIn);
		} else {
			boolean flag = true;
			if (courseTakenIn.size() > 0) {
				flag = canAddCourse(courseIn, courseTakenIn);
			}
			if (flag) {
				courseTakenIn.add(courseIn);
				student.setIsCourseAdded(true);
				if (courseIn.matches("[A-D]")) {
					student.setLongProgramming(student.getLongProgramming() + 1);
					if (notValidCourseIn.contains("D")) {
						notValidCourseIn.remove("D");
					}
				} else if (courseIn.matches("[E-H]")) {
					student.setDataStructures(student.getDataStructures() + 1);
					if (notValidCourseIn.contains("H")) {
						notValidCourseIn.remove("H");
					}
				} else if (courseIn.matches("[I-L]")) {
					student.setHardwareSequence(student.getHardwareSequence() + 1);
					if (notValidCourseIn.contains("L")) {
						notValidCourseIn.remove("L");
					}
				} else if (courseIn.matches("[M-P]")) {
					student.setDataAnalytics(student.getDataAnalytics() + 1);
					if (notValidCourseIn.contains("P")) {
						notValidCourseIn.remove("P");
					}
				}
			}
		}
		student.setWaitList(waitListIn);
		student.setCourseTaken(courseTakenIn);
		student.setNotValidCourse(notValidCourseIn);
		return new GraduateCheck(student);
	}

	public boolean canAddCourse(String courseIn, ArrayList<String> courseTakenIn) {
		ArrayList<String> temp = new ArrayList<>();
		boolean flag = false;
		for (String cTaken : courseTakenIn) {
			if(cTaken.equals(courseIn)) {
				return false;
			}
			if (courseIn.matches("[A-D]") && cTaken.matches("[A-D]") && cTaken.compareTo(courseIn) > 0) {
				flag = true;
				temp.add(cTaken);
			} else if (courseIn.matches("[E-H]") && cTaken.matches("[E-H]") && cTaken.compareTo(courseIn) > 0) {
				flag = true;
				temp.add(cTaken);
			} else if (courseIn.matches("[I-L]") && cTaken.matches("[I-L]") && cTaken.compareTo(courseIn) > 0) {
				flag = true;
				temp.add(cTaken);
			} else if (courseIn.matches("[M-P]") && cTaken.matches("[M-P]") && cTaken.compareTo(courseIn) > 0) {
				flag = true;
				temp.add(cTaken);
			}
		}
		String least="Z";
		for (String string : temp) {
			if(string.compareTo(least) < 0) {
				least = string;
			}
		}
		for (int i = (courseTakenIn.size() / 3) * 3; i < courseTakenIn.size(); i++) {
			if(courseTakenIn.get(i).equals(least)) {
				return true;
			}
		}
		if (!flag) {
			return true;
		}
		return false;
	}

	@Override
	public CoursePlannerStateI checkElectiveAndAddCourse(String courseIn, ArrayList<String> waitListIn,
			ArrayList<String> courseTakenIn, ArrayList<String> notValidCourseIn) {
		System.err.println("This function is not Valid for Categories state");
		System.exit(0);
		return null;
	}

}
