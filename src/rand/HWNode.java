package rand;

import java.util.ArrayList;
import java.util.HashMap;

public class HWNode{
	
	private String name;
	public double cpu; 
	private double mem; 
	private double netw; 
	private ArrayList<VMNode> vms;
	
	public HWNode(String name, double cpu, double mem, double netw) {
		super();
		this.name = name; 
		this.cpu = cpu; 
		this.mem=mem; 
		this.netw=netw;	
		vms = new ArrayList<VMNode>();
	}
	
	public static void main(String args[]) {
		HWNode hn = new HWNode("a", 12, 12, 1100);
		System.out.println();
	}
	
	public double normLast(int vmCPU, int vmMEM, int vmNET) {
		System.out.println(vmCPU/cpu);
		System.out.println(vmMEM/mem);
		System.out.println(vmNET/netw);
		return  sumAll(vmCPU/cpu,vmMEM/mem,vmNET/netw)/3;
		
	}
	
	private double sumAll(double... nums) { //var-args to let the caller pass an arbitrary number of int
	    double sum = 0; //start with 0
	    for(double n : nums) { 
	        sum += n; 
	    }
	    return sum; 
	} 
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCpu() {
		return cpu;
	}

	public void setCpu(double cpu) {
		this.cpu = cpu;
	}

	public double getMem() {
		return mem;
	}

	public void setMem(double mem) {
		this.mem = mem;
	}

	public double getNetw() {
		return netw;
	}

	public void setNetw(double netw) {
		this.netw = netw;
	}
	
	public void addVM(VMNode vm) {
		vms.add(vm);
	}
	public void print() {
		System.out.println("HW: ["+name +" ; CPU: "+ cpu + " ; MEM :" +mem + " ; NETW "+netw   +"]");
		for (VMNode vmNode : vms) {
			System.out.print("\t" );
			vmNode.print();
		}
	}
	public void printComplete() {
		System.out.println("HW: ["+name +" ; CPU: "+ cpu + " ; MEM :" +mem + " ; NETW "+netw   +"]");
		String completeVMs ="";
		double completeCPU = 0.0;
		double completeMEM = 0.0;
		double completeNETW = 0.0;
		for (VMNode vmNode : vms) {
			completeVMs += vmNode.getName()+";";
			completeCPU += vmNode.getCpu();
			completeMEM += vmNode.getMem();
			completeNETW += vmNode.getNetw();
		}
		System.out.println("\t"+"VMs: ["+completeVMs +" \n\t  CPU: "+ completeCPU + " \n\t  MEM :" +completeMEM + " \n\t  NETW "+completeNETW +"]");
	}
	
	public double getCPUusage() {
		double cpuUsage = 0;
		for (VMNode vmNode : vms) {
			cpuUsage += vmNode.getCpu();
		}
		return cpuUsage;
	}
	
	public double getMEMusage() {
		double memUsage = 0;
		for (VMNode vmNode : vms) {
			memUsage += vmNode.getMem();
		}
		return memUsage;
	}
	
	public double getNetwusage() {
		double netwUsage = 0;
		for (VMNode vmNode : vms) {
			netwUsage += vmNode.getNetw();
		}
		return netwUsage;
	}
	
	
	public double normLast() {
//		System.out.println(vmCPU/cpu);
//		System.out.println(vmMEM/mem);
//		System.out.println(vmNET/netw);
		return  sumAll(getCPUusage()/cpu,getMEMusage()/mem,getNetwusage()/netw)/3;
		
	}
	public String getVMNames() {
		String returnString = "";
		for (VMNode vmNode : vms) {
			returnString += vmNode.getName() + " ";
		}
		return returnString;
	}
	public String toJSON() {
		return "{\n" + 
				"        title: \""+name+"\",\n" + 
				"        values: ["+normLast() +"]\n" + 
				" },"; 
	}
	public Object[] getTouple(){
//		HashMap<String, String> returnMap = new HashMap<String,String>();
//		Pair<String,Double> a = new Pair<>(name,normLast());
//		returnMap.put(name, normLast()+"");
		String[] returnValues = {name, normLast()+""};
		return returnValues;
	}
	

}
