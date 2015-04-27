package com.yunjialu.store;

public class FoodTruck {

	private double latitude;
	private double longitude;
	private String fooditems;
	private String applicant;
	private String address;
	private String facilitytype;

	/**
	 * Constructor of all the useful info of a certain food truck applicant
	 * 
	 * @param latitude
	 *            latitude of the food truck applicant
	 * @param longitude
	 *            longitude of the food truck applicant
	 * @param fooditems
	 *            fooditems provided by the food truck applicant
	 * @param applicant
	 *            food truck applicant name
	 * @param address
	 *            food truck applicant address
	 * @param address
	 *            food truck type
	 */
	public FoodTruck(double latitude, double longitude, String fooditems,
			String applicant, String address, String facilitytype) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.fooditems = fooditems;
		this.applicant = applicant;
		this.address = address;
		this.facilitytype = facilitytype;
	}

	/**
	 * Latitude getter
	 * 
	 * @return latitude of the food truck applicant
	 */
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * Longitude getter
	 * 
	 * @return longitude of the food truck applicant
	 */
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Food truck type getter
	 * 
	 * @return Food truck type of the food truck applicant
	 */
	public String getFooditems() {
		return fooditems;
	}

	/**
	 * Food truck applicant address setter
	 * 
	 * @param fooditems
	 *            food truck applicant address
	 */
	public void setFooditems(String fooditems) {
		this.fooditems = fooditems;
	}

	/**
	 * Food truck applicant name getter
	 * 
	 * @return food truck applicant name
	 */
	public String getApplicant() {
		return applicant;
	}

	/**
	 * Food truck applicant name setter
	 * 
	 * @param applicant
	 *            food truck applicant name
	 */
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	/**
	 * Food truck applicant address getter
	 * 
	 * @return food truck applicant address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Food truck applicant address setter
	 * 
	 * @param address
	 *            food truck applicant address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Food truck type getter
	 * 
	 * @return food truck type
	 */
	public String getFacilitytype() {
		return facilitytype;
	}

	/**
	 * Food truck type setter
	 * 
	 * @param address
	 *            food truck type
	 */
	public void setFacilitytype(String facilitytype) {
		this.facilitytype = facilitytype;
	}

}
