package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter a folder path: ");
		String strPath = sc.nextLine();
		
		File path = new File(strPath);
		
		String outputFile = "/tmp/file-scripts/output/output.txt";
		File output = new File(outputFile);
		output.delete();
		
		// /tmp/file-scripts
		File[] files = path.listFiles(File::isFile);
		System.out.println("FILES: ");

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, true))) {
			for (File file: files) {
				System.out.println(file);
				
				try (BufferedReader br = new BufferedReader(new FileReader(file))) {
					String line = br.readLine();
					
					while (line != null) {
						System.out.println(line);

						if (!line.isEmpty()) {
							bw.write(line);
							bw.newLine();
						}

						line = br.readLine();
					}
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
			
			bw.newLine();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
			
		sc.close();
	}

}
