package exercise4;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotToTest {
	public static String takeScreenshot(WebDriver driver, String fileName) throws IOException {
		String user = System.getProperty("user.name");
		
		String PATH = "//Users//"+user+"//Documents//";
		
		String directoryName = PATH+"TestScreenshots//";
		
		File directory = new File(directoryName);
		if (!directory.exists()) {
			directory.mkdir();
		}

		fileName = fileName + getDate() + ".png";
		
		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String dir = directoryName + fileName;
		FileUtils.copyFile(sourceFile, new File(dir));
		return dir;
	}

	public static String getDate() {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return sdf1.format(timestamp);
	}
}
