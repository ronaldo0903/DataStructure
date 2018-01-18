package titi.Learning.java.concurrent;

class Info { // ������Ϣ��
	private String name = "name";// ����name���ԣ�Ϊ��������set��name��������
	private String content = "content";// ����content���ԣ�Ϊ��������set��content��������
	protected boolean canProduce = true; // ���ñ�־λ,��ʼʱ������

	public synchronized void set(String name, String content) {
		while (!canProduce) {
			try {
				super.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.setName(name); // ��������
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.setContent(content); // ��������
		canProduce = false; // �ı��־λ����ʾ����ȡ��
		super.notify();
	}

	public synchronized void get() {
		while (canProduce) {
			try {
				super.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(this.getName() + " --> " + this.getContent());
		canProduce = true; // �ı��־λ����ʾ��������
		super.notify();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return this.name;
	}

	public String getContent() {
		return this.content;
	}
}