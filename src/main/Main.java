
package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import fileLogic.FileMet;
import warehouse.Product;

public class Main {

	public static void main(String args[]) {

		List<Product> product = new ArrayList<Product>();

		FileMet save = new FileMet();

		String productListFileName = "ProductList.txt";

		try {
			product = (List<Product>) save.openObject(productListFileName); // lOAD FROM FILE TO LIST
		} catch (Exception e) {
			System.out.println("THE PROGRAM WAS REPAIRED");
			save.saveObject(product, productListFileName);
			product = (List<Product>) save.openObject(productListFileName); // lOAD FROM FILE TO LIST
		}

		int chose = 0;

		for (;;) {

			Scanner writingChose = new Scanner(System.in);

			/**
			 * OPTION CHOSE
			 */
			System.out.println("amount of products: " + product.size());

			System.out.println("CHOSE ACTION:" + "\n----------------------" + "\n 1.ADD POSITION       |"
					+ "\n 2.DELETE POSITION    |" + "\n 3.DELETE ALL         |" + "\n 4.SHOW ALL           |\n"
					+ " 5.WRITE TO CSV       |\n" + " 6.LOAD CSV           |\n" + " 7.CLOSE PROGRAM      |\n");

			try {
				chose = writingChose.nextInt();

			} catch (Exception e) {
				System.out.println("WRONG VALUE!!! TRY AGAIN");

			}

			int WarehouseSize = product.size();

			/**
			 * SWITCH WITH OPTION
			 */

			switch (chose) {

			case 1: {

				/**
				 * ADD PRODUCT
				 */

				int selectinput = 0;

				System.out.println("How many do you will add?:");

				try {

					selectinput = writingChose.nextInt();

					if (selectinput > 30 || WarehouseSize + selectinput > 30) {
						System.out.println("WRONG VALUE!!! the warehouse does not have that much space ");
						break;
					} else if (selectinput == 0)
						break;

				} catch (Exception e) {
					System.out.println("WRONG VALUE!!! TRY AGAIN");
					break;
				}

				int id = 0;
				double price = 0;
				String name = "";
				int number = 0;

				for (int i = 0; i < selectinput; i++) {

					if (number == 30 || WarehouseSize > 29) {
						System.out.println("WAREHOUSE IS FULL!!!!!!!");
						break;
					}

					/**
					 * PRODUCT DATA
					 */

					try {

						System.out.println("Write " + (number + 1) + ". product NAME: ");
						name = writingChose.next();

						System.out.println("Write " + (number + 1) + ". product ID: ");
						id = writingChose.nextInt();

						System.out.println("Write " + (number + 1) + ". product PRICE: ");
						price = writingChose.nextDouble();

					} catch (Exception e) {
						System.out.println("WRONG VALUE!!! TRY AGAIN");
						break;
					}

					product.add(new Product(id, name, price));

					int iterForEach = 1;

					for (Product element : product)
						System.out.println("Product Nr." + (iterForEach++) + " " + element);

					number++;
				}
				save.saveObject(product, productListFileName);

				break;
			}
			case 2: {

				System.out.println("Write number of product who you want delete: ");
				int delPosition = 0;

				try {

					delPosition = writingChose.nextInt();

					product.remove(delPosition - 1);
					save.saveObject(product, productListFileName);
				} catch (Exception e) {
					System.out.println("WRONG VALUE!!! TRY AGAIN");
					break;
				}

				break;
			}
			case 3: {

				product.clear();
				save.saveObject(product, productListFileName);

				System.out.println("Product list is empty !!!!");

				break;
			}
			case 4: {

				if (WarehouseSize == 0)
					System.out.println("Warehouse is empty");
				int number = 1;

				for (Product element : product)
					System.out.println("Product Nr." + (number++) + " " + element);

				break;
			}

			case 5: {

				String comandDelimiter = ",";
				String newLineSeparator = "\n";
				String fileHeader = "Id,Name,Price";
				String nameFileCsv = "";
				String nameFileCsvWithEnlargement = "";
				System.out.println("Write name of fille csv ");
				nameFileCsv = writingChose.next();
				nameFileCsvWithEnlargement = nameFileCsv + ".csv";
				save.saveObjectCsv(product, fileHeader, newLineSeparator, comandDelimiter, nameFileCsvWithEnlargement);

				break;
			}

			case 6: {

				String comandDelimiter = ",";
				int idToken = 0;
				int nameToken = 1;
				int priceToken = 2;
				String nameFileCsv = "";
				String nameFileCsvWithEnlargement = "";
				System.out.println("Write name of fille csv ");
				nameFileCsv = writingChose.next();
				nameFileCsvWithEnlargement = nameFileCsv + ".csv";
				product.clear();
				save.openObjectCsv(product, comandDelimiter, idToken, nameToken, priceToken,
						nameFileCsvWithEnlargement);
				save.saveObject(product, productListFileName);

				break;
			}

			case 7: {

				writingChose.close();
				System.out.println("GOOD BYE");
				break;
			}

			default:

				System.out.println("Error, wrong command");

			}

			if (chose == 7)
				break;

		}

	}

}
