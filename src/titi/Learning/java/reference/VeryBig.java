package titi.Learning.java.reference;

class VeryBig {
	public String id;
	// ռ�ÿռ�,���߳̽��л���
	byte[] b = new byte[2 * 1024];
 
	public VeryBig(String id) {
		this.id = id;
	}
 
	protected void finalize() {
		System.out.println("Finalizing VeryBig " + id);
	}
}