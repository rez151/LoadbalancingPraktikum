package rand;

public class VMNode {
	
	private String name;
	private double cpu; 
	private double mem; 
	private double netw; 
	
	
	public VMNode(String name, double cpu, double mem, double netw) {
		super();
		this.name = name; 
		this.cpu = cpu; 
		this.mem=mem; 
		this.netw=netw;	
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
	public void print() {
		System.out.println("VM: ["+name +" ; CPU: "+ cpu + " ; MEM :" +mem + " ; NETW "+netw   +"]");
	}
}
