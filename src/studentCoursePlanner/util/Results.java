package studentCoursePlanner.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Result class to store the graduation result of every student
 * @author pragyavishalakshi
 *
 */
public class Results implements FileDisplayInterface, StdoutDisplayInterface {

	/**
	 * arrayList to store the course alloted and graduation status of each student
	 */
	private ArrayList<String> resultList = null;

	/**
	 * default Constructor of Results
	 */
	public Results() {
		resultList = new ArrayList<>();
	}

	/**
	 * add the string in the resultList
	 * @param dataIn
	 */
	public void addResult(String dataIn) {
		if(dataIn != null) {
			resultList.add(dataIn);
		}
	}

	@Override
	public void printConsole() {
		for (String data : resultList) {
			System.out.println(data);
		}
	}

	@Override
	public void writeToFile(String fileIn) {
		BufferedWriter br = null;
		try {
			br = new BufferedWriter(new FileWriter(fileIn));
			for (String data : resultList) {
				br.write(data+"\n");
			}
			resultList.clear();
		} catch (IOException e) {
			System.out.println("IOException for Write file");
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
