package com.yunjialu;

import com.yunjialu.store.*;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.*;

import com.google.gson.*;

@SuppressWarnings("serial")
public class UberSFFoodGAEServerServlet extends HttpServlet {

	private static final String SF_FOODTRUCK_URL = "https://data.sfgov.org/resource/6a9r-agq8.json?";
	private static final String SF_DATA_KEY = "F0PqXvLEdTVlizTKlCcDH5Xuw";
	private static final String REQUIRED_INFO = "latitude,longitude,fooditems,applicant,address,facilitytype";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String res = "";

		String lat = req.getParameter("lat");
		String lng = req.getParameter("lng");
		String ran = req.getParameter("range");

		// check if range input is valid first
		if (ran != null) {
			double latitude = Double.parseDouble(lat);
			double longitude = Double.parseDouble(lng);
			double range = Double.parseDouble(ran);
			res = getFoodTrucks(latitude, longitude, range);
		}

		resp.setContentType("text/json");
		resp.getWriter().println(res);
	}
	

	/**
	 * get food truck applicant's info that meet the user's requirement
	 * 
	 * @param lat
	 *            latitude of user's location
	 * @param lng
	 *            longitude of user's location
	 * @param range
	 *            longest distance from user's location to a food truck
	 *            applicant
	 * @return JSON Object containing all the valid food truck applicants
	 */
	private String getFoodTrucks(double lat, double lng, double range) {

		String jsonRes = "";

		// Query SF Data using SoQL Clauses, REQUIRED_INFO can be changed upon
		// demand
		String json = getRemoteJSON(SF_FOODTRUCK_URL + "$select="
				+ REQUIRED_INFO);

		if (json.length() != 0) {

			JsonElement jelement = new JsonParser().parse(json);
			JsonArray jarray = jelement.getAsJsonArray();
			double dstlat = Double.MAX_VALUE;
			double dstlng = Double.MAX_VALUE;
			List<FoodTruck> foodTrucks = new ArrayList<>();

			for (int i = 0; i < jarray.size(); i++) {

				JsonObject jobject = jarray.get(i).getAsJsonObject();
				JsonElement latobj = jobject.get("latitude");
				JsonElement lngobj = jobject.get("longitude");

				// filter the data which has no latitude and longitude value
				if (latobj != null && lngobj != null) {
					dstlat = Double.parseDouble(latobj.toString().replace("\"",
							" "));
					dstlng = Double.parseDouble(lngobj.toString().replace("\"",
							" "));
				}

				// check if the food truck applicant is valid, if true, build a
				// FoodTruck object containing required object
				if (dstlat != Double.MAX_VALUE && dstlng != Double.MAX_VALUE
						&& checkRange(lat, lng, dstlat, dstlng, range)) {

					String fooditems = jobject.get("fooditems").toString()
							.replace("\"", " ");
					String applicant = jobject.get("applicant").toString()
							.replace("\"", " ");
					String address = jobject.get("address").toString()
							.replace("\"", " ");
					String facilitytype = jobject.get("facilitytype")
							.toString().replace("\"", " ");
					FoodTruck foodTruck = new FoodTruck(dstlat, dstlng,
							fooditems, applicant, address, facilitytype);

					foodTrucks.add(foodTruck);
				}
			}

			// build a JSON object
			Gson gson = new Gson();
			jsonRes = gson.toJson(foodTrucks);
			// System.out.println(jsonRes);
		}

		return jsonRes;
	}

	/**
	 * check if the distance of a food truck applicant to the current location
	 * is within user specified range
	 * 
	 * @param lat
	 *            latitude of user's selected location
	 * @param lng
	 *            longitude of user's selected location
	 * @param dstlat
	 *            latitude of food truck applicant
	 * @param dstlng
	 *            longitude of food truck applicant
	 * @param range
	 *            range in meter specified by user
	 * @return true if the food truck applicant is within the range false if the
	 *         food truck applicant is not within the range
	 */
	private boolean checkRange(double lat, double lng, double dstlat,
			double dstlng, double range) {
		boolean valid = false;

		double distance = distFrom(lat, lng, dstlat, dstlng);

		if (distance < range)
			valid = true;

		return valid;
	}

	/**
	 * calculate the distance between two latitude and longitude according to
	 * reference material
	 * 
	 * 
	 * @param lat1
	 *            latitude of point1
	 * @param lng1
	 *            longitude of point1
	 * @param lat2
	 *            latitude of point2
	 * @param lng2
	 *            longitude of point2
	 * @return distance of two points
	 */
	public double distFrom(double lat1, double lng1, double lat2, double lng2) {
		double earthRadius = 6371000; // meters
		double dLat = Math.toRadians(lat2 - lat1);
		double dLng = Math.toRadians(lng2 - lng1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLng / 2)
				* Math.sin(dLng / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double dist = earthRadius * c;

		return dist;
	}

	/**
	 * get JSON object from SF Data API
	 * 
	 * @param urlstr
	 *            SF Food Trunck Data endpoint URL
	 * @return
	 */
	private String getRemoteJSON(String urlstr) {
		StringBuffer res = new StringBuffer();
		try {
			URL url = new URL(urlstr);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestProperty("X-Custom-Header", "xxx");
			connection.setRequestProperty("X-App-Token", SF_DATA_KEY);
			connection.setReadTimeout(30000);
			connection.setRequestProperty("Content-Type", "application/json");

			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				// if http status OK
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(url.openStream()));
				String line;
				while ((line = reader.readLine()) != null) {
					res.append(line);
				}
				reader.close();

			}
		} catch (MalformedURLException e) {
			System.out.println("Hit the malformedURLerror: " + e.toString());
		} catch (IOException ioe) {
			System.out.println("Hit the IO error: " + ioe.toString());
		}

		return res.toString();
	}

}
