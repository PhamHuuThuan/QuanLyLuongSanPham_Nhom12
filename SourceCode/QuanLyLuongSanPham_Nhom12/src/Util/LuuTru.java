package Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

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
	public void copyFileAvatar(String sourceFilePath, String maNV) {
	    try {
	        // Tạo đường dẫn đến thư mục mới trong project
	        String destDirectory = getClass().getResource("avatar/").getPath() + maNV;
	        Path destDir = Paths.get(destDirectory);

	        // Tạo thư mục nếu nó chưa tồn tại
	        if (!Files.exists(destDir)) {
	            Files.createDirectories(destDir);
	        }

	        // Tạo đường dẫn đến file đích
	        Path sourcePath = Paths.get(sourceFilePath);
	        Path destPath = Paths.get(destDirectory, sourcePath.getFileName().toString());

	        // Sao chép file
	        Files.copy(sourcePath, destPath);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void xoaThongTinNhanVien(String maNV) {
	    try {
	        // Tạo đường dẫn đến thư mục của nhân viên trong project
	        String directoryPath = getClass().getResource("avatar/").getPath() + maNV;
	        Path dirPath = Paths.get(directoryPath);

	        // Xóa thư mục nếu nó tồn tại
	        if (Files.exists(dirPath)) {
	            Files.walkFileTree(dirPath, new SimpleFileVisitor<Path>() {
	                @Override
	                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
	                    Files.delete(file);
	                    return FileVisitResult.CONTINUE;
	                }

	                @Override
	                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
	                    Files.delete(dir);
	                    return FileVisitResult.CONTINUE;
	                }
	            });
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
