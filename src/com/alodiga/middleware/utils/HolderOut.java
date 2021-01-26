package com.alodiga.middleware.utils;

public class HolderOut<T> {
	private T valueObject;
	private T valuePrimitives;
	public HolderOut(T valueO, T valueP) {
        this.valueObject = valueO;
        this.valuePrimitives = valueP;
    }
	public T getValueObject() {
		return valueObject;
	}
	public void setValueObject(T valueObject) {
		this.valueObject = valueObject;
	}
	public T getValuePrimitives() {
		return valuePrimitives;
	}
	public void setValuePrimitives(T valuePrimitives) {
		this.valuePrimitives = valuePrimitives;
	}
	 
}
