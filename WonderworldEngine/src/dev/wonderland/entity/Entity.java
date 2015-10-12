package dev.wonderland.entity;

import java.util.ArrayList;
import java.util.List;

public class Entity {

	private List<Component> components;

	public Entity() {
		components = new ArrayList<Component>();
	}

	public List<Component> getComponents() {
		return components;
	}

	public Entity addComponent(Component c) {
		components.add(c);
		return this;
	}

	public boolean containsComponent(Component c) {
		return components.contains(c);
	}

	public boolean containsComponent(int componentID) {
		for (Component component : components) {
			if (component.getID() == componentID)
				return true;
		}
		return false;
	}
	
	public Component getComponent(int componentID){
		for (Component component : components) {
			if (component.getID() == componentID)
				return component;
		}
		return new Component(-1);
	}
}
