package fileLogic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.io.FileWriter;
import warehouse.Product;

public class FileMet {

	public void saveObject(Object obj, String nameFile) {
		File file = new File(nameFile);
		FileOutputStream fileOut = null;

		ObjectOutputStream out = null;

		try {

			fileOut = new FileOutputStream(file);
			out = new ObjectOutputStream(fileOut);
			out.writeObject(obj);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("END OF SAVEING");
		}

	}

	public void saveObjectCsv(List<Product> productlist, String fileHeader, String newLineSeparator, String nameFileCsv,
			String nameFile) {

		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(nameFile);
			fileWriter.append(fileHeader);
			fileWriter.append(newLineSeparator);
			for (Product product : productlist) {
				fileWriter.append(String.valueOf(product.getId()));
				fileWriter.append(nameFileCsv);
				fileWriter.append(product.getName());
				fileWriter.append(nameFileCsv);
				fileWriter.append(String.valueOf(product.getPrice()));
				fileWriter.append(newLineSeparator);
			}

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("END OF SAVEING FILE TO CSV");
		}

	}

	public Object openObject(String nameFile) {
		Object obj = new Object();
		File file = new File(nameFile);
		ObjectInputStream in = null;

		try {

			in = new ObjectInputStream(new FileInputStream(file));

			obj = in.readObject();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("END OF LOAD FILE");
		}

		return obj;
	}

	public void openObjectCsv(List<Product> productList, String commaDelimiter, int idToken, int nameToken,
			int priceToken, String fileName) {

		BufferedReader fileReader = null;

		try {

			String line = "";
			fileReader = new BufferedReader(new FileReader(fileName));
			fileReader.readLine();

			while ((line = fileReader.readLine()) != null) {

				String[] tokens = line.split(commaDelimiter);
				if (tokens.length > 0) {
					Product product = new Product(Integer.parseInt(tokens[idToken]), tokens[nameToken],
							Double.parseDouble(tokens[priceToken]));
					productList.add(product);
				}
			}

		} catch (Exception e) {
			System.out.println("Error in CsvFileReader !!!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				System.out.println("Error while closing fileReader !!!");
				e.printStackTrace();
			}
		}

	}

}
