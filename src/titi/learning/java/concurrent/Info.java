package titi.learning.java.concurrent;

class Info { // 定义信息类
	private String name = "name";// 定义name属性，为了与下面set的name属性区别开
	private String content = "content";// 定义content属性，为了与下面set的content属性区别开
	protected boolean canProduce = false; // 设置标志位,初始时先生产

	public synchronized void set(String name, String content) {
		while (!canProduce) {
			try {
				super.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.setName(name); // 设置名称
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.setContent(content); // 设置内容
		canProduce = false; // 改变标志位，表示可以取走
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
		/*try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		System.out.println(this.getName() + " --> " + this.getContent());
		canProduce = true; // 改变标志位，表示可以生产
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