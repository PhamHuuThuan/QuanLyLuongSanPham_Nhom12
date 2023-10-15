package Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LuuTru {
	public String readFile(String filePath) {
		String pathFileData = "";
		try {
			FileReader reader = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(reader);
			pathFileData = bufferedReader.readLine();
			bufferedReader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return pathFileData;
	}
}
