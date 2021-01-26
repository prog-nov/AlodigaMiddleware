package com.alodiga.middleware.cscoreswitch;

public class ResponseBynary {

	private byte[] buffer;
	private int offset;
	
	public ResponseBynary(){
		this.offset = 0;
		this.buffer = null;
	}
	
	public ResponseBynary(byte[] buffer, int offset){
		
		this.offset = offset;
		this.buffer = buffer;
	}
	
	public byte[] getBuffer() {
		return buffer;
	}
	public void setBuffer(byte[] byffer) {
		this.buffer = byffer;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
}
