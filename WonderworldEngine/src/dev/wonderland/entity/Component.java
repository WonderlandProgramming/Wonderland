package dev.wonderland.entity;

public class Component {
	
	protected int id;
	
	protected Component(int id){
		this.id = id;
	}
	
	public int getID(){
		return id;
	}
}
