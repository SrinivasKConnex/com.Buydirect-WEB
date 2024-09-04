
package reusableComponents;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;

public class DeleteOldData {

	public static void DeleteOldExtendedData(String directoryPath,int daysOld) {
		
		File directory = new File(directoryPath);
		if (directory.exists() && directory.isDirectory()) {
			File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".html") || name.toLowerCase().endsWith(".jpeg"));
			if (files != null) {
				for (File file : files) {
					if (isOlderThan(file, daysOld)) {
						if (file.delete()) {
							System.out.println("Deleted Older then : "+daysOld+ " Days Screenshots of Errors"+file.getName());
						} else {
							System.out.println("Failed to delete: " + file.getName());
						}
					}
				}
			}
		} else {
			//System.out.println("Directory does not exist Any .");
		}
	}

	private static boolean isOlderThan(File file, int daysOld) {
		Path filePath = Paths.get(file.getAbsolutePath());
		try {
			Instant fileTime = Files.getLastModifiedTime(filePath).toInstant();
			LocalDate fileDate = fileTime.atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate cutoffDate = LocalDate.now().minus(daysOld, ChronoUnit.DAYS);
			boolean ss = fileDate.isBefore(cutoffDate);
			return ss;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void cleanUpFolder() {
		String directoryPath = System.getProperty("user.dir") + "\\Reports\\Screenshots"; // specify your directory
		File directory = new File(directoryPath);
		long maxFolderSizeInBytes = 1024;
		// Check if the directory exists and is a directory
		if (directory.exists() && directory.isDirectory()) {
			// Calculate the current folder size
			long currentFolderSize = calculateFolderSize(directory);

			// Delete files if the folder size exceeds the limit
			if (currentFolderSize > maxFolderSizeInBytes) {
				File[] files = directory.listFiles(
						(dir, name) -> name.toLowerCase().endsWith(".html") || name.toLowerCase().endsWith(".jpeg"));

				if (files != null && files.length > 0) {
					// Sort files by last modified time (oldest first)
					Arrays.sort(files, Comparator.comparingLong(File::lastModified));

					for (File file : files) {
						if (file.delete()) {
							System.out.println("Deleted: " + file.getName());
							currentFolderSize -= file.length();
							// Stop deleting once the folder size is under the limit
							if (currentFolderSize <= maxFolderSizeInBytes) {
								break;
							}
						} else {
							System.out.println("Failed to delete: " + file.getName());
						}
					}
				} else {
					System.out.println("No files found in the directory.");
				}
			} else {
				System.out.println("Folder size is within the limit.");
			}
		} else {
			System.out.println("Directory does not exist or is not a directory.");
		}
	}

	// Helper method to calculate the total size of the folder
	private static long calculateFolderSize(File directory) {
		long length = 0;
		File[] files = directory.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isFile()) {
					length += file.length();
				} else {
					length += calculateFolderSize(file);
				}
			}
		}
		return length;
	}

}
