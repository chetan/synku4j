package com.zynku.core.model;

public class Device {

	private Long id;
	private Manufacturer manufacturer;
	private String model;

	public Device() {
	}

	public Device(final Manufacturer manufacturer, final String model) {
		this.manufacturer = manufacturer;
		this.model = model;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

}
