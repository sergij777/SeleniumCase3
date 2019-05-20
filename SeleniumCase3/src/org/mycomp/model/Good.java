package org.mycomp.model;

public class Good implements Cloneable {
	private String goodName;
	private int goodPrice;
	
    public Good clone() throws CloneNotSupportedException{        
        return (Good) super.clone();
    }
	
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public int getGoodPrice() {
		return goodPrice;
	}
	public void setGoodPrice(int goodPrice) {
		this.goodPrice = goodPrice;
	}
}
