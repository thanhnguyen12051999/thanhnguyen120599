package bean;

public class Ear {

	private Integer id;
	private String preview;
	private String produce;
	private String connect;
	private String type;
	private String price;
	private String name;

	public Ear() {
		
	}
	
	public Ear(Integer id, String preview, String produce, String connect, String type, String price,
			String name) {

		this.id=id;
		this.preview=preview;
		this.produce=produce;
		this.connect=connect;
		this.type=type;
		this.price=price;
		this.name=name;
	
	}

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	public String getProduce() {
		return produce;
	}
	public void setProduce(String produce) {
		this.produce = produce;
	}
	public String getConnect() {
		return connect;
	}
	public void setConnect(String connect) {
		this.connect = connect;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
