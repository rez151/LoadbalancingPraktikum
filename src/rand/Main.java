package rand;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	static Workbook wb = new HSSFWorkbook();

	public static void main(String[] args) {
		// 100 - 100
		// 100 - 1000
		// 100 - 10000
		// 1000 - 1000
		// 1000 - 10000
		// 10000 - 10000
		Main loadbalancer = new Main();
		// TODO Random X
		loadbalancer.random(100, 100, "random_100_100");
		loadbalancer.random(100, 1000, "random_100_1000");
		loadbalancer.random(100, 10000, "random_100_10000");
		loadbalancer.random(1000, 1000, "random_1000_1000");
		loadbalancer.random(1000, 10000, "random_1000_10000");
		loadbalancer.random(10000, 10000, "random_10000_10000");

		// TODO Round-Robin X
		loadbalancer.roundRobin(100, 100, "round_robin_100_100");
		loadbalancer.roundRobin(100, 1000, "round_robin_100_1000");
		loadbalancer.roundRobin(100, 10000, "round_robin_100_10000");
		loadbalancer.roundRobin(1000, 1000, "round_robin_1000_1000");
		loadbalancer.roundRobin(1000, 10000, "round_robin_1000_10000");
		loadbalancer.roundRobin(10000, 10000, "round_robin_10000_10000");

		// TODO Least Loaded (CPU-Min-First, Mem-Min-First, Netz-Min-First, Normierte
		// Last-Min-First)
		// banane.cpuMin(1);
		loadbalancer.cpuMin(100, 100, "cpu_min_100_100");
		loadbalancer.cpuMin(100, 1000, "cpu_min_100_1000");
		loadbalancer.cpuMin(100, 10000, "cpu_min_100_10000");
		loadbalancer.cpuMin(1000, 1000, "cpu_min_1000_1000");
		loadbalancer.cpuMin(1000, 10000, "cpu_min_1000_10000");
		loadbalancer.cpuMin(10000, 10000, "cpu_min_10000_10000");
		// banane.memMin(1);
		loadbalancer.memMin(100, 100, "mem_min_100_100");
		loadbalancer.memMin(100, 1000, "mem_min_100_1000");
		loadbalancer.memMin(100, 10000, "mem_min_100_10000");
		loadbalancer.memMin(1000, 1000, "mem_min_1000_1000");
		loadbalancer.memMin(1000, 10000, "mem_min_1000_10000");
		loadbalancer.memMin(10000, 10000, "mem_min_10000_10000");
		// banane.netwMin(1);
		loadbalancer.netwMin(100, 100, "netw_min_100_100");
		loadbalancer.netwMin(100, 1000, "netw_min_100_1000");
		loadbalancer.netwMin(100, 10000, "netw_min_100_10000");
		loadbalancer.netwMin(1000, 1000, "netw_min_1000_1000");
		loadbalancer.netwMin(1000, 10000, "netw_min_1000_10000");
		loadbalancer.netwMin(10000, 10000, "netw_min_10000_10000");
		// banane.normLastMin(1);
		loadbalancer.normLastMin(100, 100, "normLast_min_100_100");
		loadbalancer.normLastMin(100, 1000, "normLast_min_100_1000");
		loadbalancer.normLastMin(100, 10000, "normLast_min_100_10000");
		loadbalancer.normLastMin(1000, 1000, "normLast_min_1000_1000");
		loadbalancer.normLastMin(1000, 10000, "normLast_min_1000_10000");
		loadbalancer.normLastMin(10000, 10000, "normLast_min_10000_10000");

		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("test" + ".xls");
			wb.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void random(int hw, int vms, String tabname) {
		ArrayList<HWNode> hwnodes = new CSVReader().readFileHWNode("HWRessources" + hw + "_static.csv");
		ArrayList<VMNode> vmnodes = new CSVReader().readFileVMNode("VMHistory" + vms + ".csv");
		distributeRandom(hwnodes, vmnodes, tabname);
	}

	public void distributeRandom(ArrayList<HWNode> hws, ArrayList<VMNode> vms, String tabname) {
		for (VMNode vmNode : vms) {
			hws.get(((int) (Math.random() * hws.size()) + 0)).addVM(vmNode);
		}
		toSpreadsheet(hws, tabname);
	}

	public void roundRobin(int hw, int vms, String tabname) {
		ArrayList<HWNode> hwnodes = new CSVReader().readFileHWNode("HWRessources" + hw + "_static.csv");
		ArrayList<VMNode> vmnodes = new CSVReader().readFileVMNode("VMHistory" + vms + ".csv");
		distributeRoundRobin(hwnodes, vmnodes, tabname);
	}

	public void distributeRoundRobin(ArrayList<HWNode> hws, ArrayList<VMNode> vms, String tabname) {
		int i = 0;
		for (VMNode vmNode : vms) {	
				hws.get(i).addVM(vmNode);
				i++;
				if(i==hws.size()) {
					i=0;
				}
		}
		toSpreadsheet(hws, tabname);
	}

	public void cpuMin(int hw, int vms, String tabname) {
		ArrayList<HWNode> hwnodes = new CSVReader().readFileHWNode("HWRessources" + hw + "_static.csv");
		ArrayList<VMNode> vmnodes = new CSVReader().readFileVMNode("VMHistory" + vms + ".csv");
		distributeCPUMin(hwnodes, vmnodes, tabname);
	}

	public void distributeCPUMin(ArrayList<HWNode> hws, ArrayList<VMNode> vms, String tabname) {
		int i_min = 0;
		for (VMNode vmNode : vms) {
			for (int i = 0; i < hws.size(); i++) {
				if (hws.get(i).getCPUusage() < hws.get(i_min).getCPUusage()) {
					i_min = i;
				}
			}
			hws.get(i_min).addVM(vmNode);
		}
		toSpreadsheet(hws, tabname);
	}

	public void memMin(int hw, int vms, String tabname) {
		ArrayList<HWNode> hwnodes = new CSVReader().readFileHWNode("HWRessources" + hw + "_static.csv");
		ArrayList<VMNode> vmnodes = new CSVReader().readFileVMNode("VMHistory" + vms + ".csv");
		distributeMEMMin(hwnodes, vmnodes, tabname);
	}


	public void distributeMEMMin(ArrayList<HWNode> hws, ArrayList<VMNode> vms, String tabname) {
		int i_min = 0;
		for (VMNode vmNode : vms) {
			for (int i = 0; i < hws.size(); i++) {
				if (hws.get(i).getMEMusage() < hws.get(i_min).getMEMusage()) {
					i_min = i;
				}
			}
			hws.get(i_min).addVM(vmNode);
		}
		toSpreadsheet(hws, tabname);
	}

	public void netwMin(int hw, int vms, String tabname) {
		ArrayList<HWNode> hwnodes = new CSVReader().readFileHWNode("HWRessources" + hw + "_static.csv");
		ArrayList<VMNode> vmnodes = new CSVReader().readFileVMNode("VMHistory" + vms + ".csv");
		distributeNETWMin(hwnodes, vmnodes, tabname);
	}

	public void distributeNETWMin(ArrayList<HWNode> hws, ArrayList<VMNode> vms, String tabname) {
		int i_min = 0;
		for (VMNode vmNode : vms) {
			for (int i = 0; i < hws.size(); i++) {
				if (hws.get(i).getNetwusage() < hws.get(i_min).getNetwusage()) {
					i_min = i;
				}
			}
			hws.get(i_min).addVM(vmNode);
		}
		toSpreadsheet(hws, tabname);
	}

	public void normLastMin(int hw, int vms, String tabname) {
		ArrayList<HWNode> hwnodes = new CSVReader().readFileHWNode("HWRessources" + hw + "_static.csv");
		ArrayList<VMNode> vmnodes = new CSVReader().readFileVMNode("VMHistory" + vms + ".csv");
		distributeNormLastMin(hwnodes, vmnodes, tabname);
	}


	public void distributeNormLastMin(ArrayList<HWNode> hws, ArrayList<VMNode> vms, String tabname) {
		int i_min = 0;
		for (VMNode vmNode : vms) {
			for (int i = 0; i < hws.size(); i++) {
				if (hws.get(i).normLast() < hws.get(i_min).normLast()) {
					i_min = i;
				}
			}
			hws.get(i_min).addVM(vmNode);
		}
		toSpreadsheet(hws, tabname);
	}

	public void toSpreadsheet(ArrayList<HWNode> hwN, String spreadsheetName) {
		Sheet s = wb.createSheet(spreadsheetName);
		Row r = s.createRow(0);

		Cell c0 = r.createCell(0);
		c0.setCellValue("HW");
		Cell c1 = r.createCell(1);
		c1.setCellValue("CPU");
		Cell c2 = r.createCell(2);
		c2.setCellValue("Mem");
		Cell c3 = r.createCell(3);
		c3.setCellValue("Netw");
		Cell c4 = r.createCell(4);
		c4.setCellValue("VMs");
		Cell c5 = r.createCell(5);
		c5.setCellValue("NormLast");
		Cell c6 = r.createCell(6);
		c6.setCellValue("CPU");
		Cell c7 = r.createCell(7);
		c7.setCellValue("Mem");
		Cell c8 = r.createCell(8);
		c8.setCellValue("Netw");

		CellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		// TODO check the shit for colors

		int i = 1;
		for (HWNode h : hwN) {
			Row tempR = s.createRow(i);
			Cell tempc0 = tempR.createCell(0);
			tempc0.setCellValue(h.getName());
			tempc0.setCellStyle(style);
			Cell tempc1 = tempR.createCell(1);
			tempc1.setCellValue(h.getCpu());
			Cell tempc2 = tempR.createCell(2);
			tempc2.setCellValue(h.getMem());
			Cell tempc3 = tempR.createCell(3);
			tempc3.setCellValue(h.getNetw());
			Cell tempc4 = tempR.createCell(4);
			tempc4.setCellValue(h.getVMNames());
			Cell tempc5 = tempR.createCell(5);
			tempc5.setCellValue(h.normLast());
			Cell tempc6 = tempR.createCell(6);
			tempc6.setCellValue(h.getCPUusage());
			if (h.getCPUusage() > h.getCpu()) {
				tempc6.setCellStyle(style);
			}
			Cell tempc7 = tempR.createCell(7);
			tempc7.setCellValue(h.getMEMusage());
			if (h.getMEMusage() > h.getMem()) {
				tempc7.setCellStyle(style);
			}
			Cell tempc8 = tempR.createCell(8);
			tempc8.setCellValue(h.getNetwusage());
			if (h.getNetwusage() > h.getNetw()) {
				tempc8.setCellStyle(style);
			}

			i++;
		}
	}
}
