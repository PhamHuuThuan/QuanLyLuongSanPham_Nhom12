package Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

public class LuuTru {
    public ResourceBundle getLanguageConfig(int language) {
        ResourceBundle languageBundle = null;
		switch (language) {
            case 0:
                languageBundle  = ResourceBundle.getBundle("config/languages/vietnamese");
                break;
            case 1:
                languageBundle = ResourceBundle.getBundle("config/languages/english");
                break;
            default:
                System.out.println("Invalid language setting in config file.");
                break;
        }
		return languageBundle;
    }

    public String readFile(String filePath) {
        String fileData = "";
        try {
            FileReader reader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            fileData = bufferedReader.readLine();
            bufferedReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fileData;
    }
	public String copyFileAvatar(String sourceFilePath, String maNV) {
	    try {
	        // Tạo đường dẫn đến thư mục mới trong project
	        Path path = Paths.get("res/avatar/" + maNV);
	        if (!Files.exists(path)) {
	            Files.createDirectories(path);
	        }
	        // Tạo đường dẫn đến file nguồn và thư mục đích
	        Path sourcePath = Paths.get(sourceFilePath);
	        Path destPath = Paths.get(path.toString(), sourcePath.getFileName().toString());

	        // Sao chép file và ghi đè nếu tồn tại
	        Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);
	        return destPath.toString();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	public void xoaFileAvatar(String maNV) {
	    try {
	        // Tạo đường dẫn đến thư mục của nhân viên trong project
	        Path path = Paths.get("res/avatar/" + maNV);
	        
	        // Kiểm tra xem thư mục có tồn tại không
	        if (Files.exists(path)) {
	            // Duyệt qua tất cả các file trong thư mục
	            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
	            for (Path file : directoryStream) {
	                // Xóa file
	                Files.delete(file);
	            }
	            directoryStream.close();
	            
	            // Xóa thư mục sau khi đã xóa hết các file bên trong
	            Files.delete(path);
	        } else {
	            System.out.println("Thư mục không tồn tại: " + path);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}