package com.chrystian.model;

import com.fasterxml.jackson.annotation.JsonProperty;

//this is not a db model
public class CandleStick {
	private long timestamp;
	private double open;
	private double high;
	private double low;
	private double close;
	private double volume;
	private String instrument;
	
	@JsonProperty("t")
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	@JsonProperty("o")
	public double getOpen() {
		return open;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	@JsonProperty("h")
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	@JsonProperty("l")
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	@JsonProperty("c")
	public double getClose() {
		return close;
	}
	public void setClose(double close) {
		this.close = close;
	}
	@JsonProperty("v")
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public String getInstrument() {
		return instrument;
	}
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}
	
}
