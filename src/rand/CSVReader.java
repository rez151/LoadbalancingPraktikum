package rand;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {

/*	public static void main(String[] args) {

	}*/

	public ArrayList<HWNode> readFileHWNode(String fileName) {
		String csvFile = "./HWRessourcesStatic/"+fileName; 
		String line = "";
		String cvsSplitBy = ",";
		
		ArrayList<HWNode> nodes = new ArrayList<HWNode>();
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			
			br.readLine();
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] hwRec = line.split(cvsSplitBy);

//				System.out.println("HW [Name= " + hwRec[0] + " , CPU= " + hwRec[1] + " Mem= " + hwRec[2] + " Netw = "
//						+ hwRec[3] + "]");
				nodes.add(new HWNode(hwRec[0], Double.parseDouble(hwRec[1]), Double.parseDouble(hwRec[2]), Double.parseDouble(hwRec[3])));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nodes;
	}
	
	public ArrayList<VMNode> readFileVMNode(String fileName) {
		String csvFile = "./VMHistory/"+fileName; 
		String line = "";
		String cvsSplitBy = ",";
		
		ArrayList<VMNode> vmNodes = new ArrayList<VMNode>();
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			
			br.readLine();
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] hwRec = line.split(cvsSplitBy);

//				System.out.println("HW [Name= " + hwRec[0] + " , CPU= " + hwRec[1] + " Mem= " + hwRec[2] + " Netw = "
//						+ hwRec[3] + "]");
				vmNodes.add(new VMNode(hwRec[0], Double.parseDouble(hwRec[1]), Double.parseDouble(hwRec[2]), Double.parseDouble(hwRec[3])));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return vmNodes;
	}

}