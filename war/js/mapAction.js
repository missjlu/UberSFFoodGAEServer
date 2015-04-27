var map;
var markers = [];
var lat;
var lng;
function init_map() {
	var mapCanvas = document.getElementById('map-canvas');
	var mapOptions = {
		center : new google.maps.LatLng(37.7757182, -122.4180767),
		zoom : 12,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	}
	map = new google.maps.Map(mapCanvas, mapOptions);

	// add marker when user click on the map
	google.maps.event.addListener(map, 'click', function(event) {
		deleteMarkers();
		placeMarker(event.latLng);
		lat = event.latLng.lat();
		lng = event.latLng.lng();
		document.getElementById('marker_info').innerHTML = '<h5>Current Lat: '
				+ lat + '</h5>' + '<h5>Current Lng: ' + lng + '</h5>'
				+ 'Find food truck within:<br/>'
				+ '<input type="text" id="range"> meters<br/>'
				+ '<button onclick="searchTruck()">Submit</button>';
	});

}

// send request to server upon click the submit button
function searchTruck() {

	var req = newXMLHttpRequest();
	var range = document.getElementById("range").value;
	req.onreadystatechange = getReadyStateHandler(req, updateMap);

	req.open("POST", "ubersffoodgaeserver", true);
	req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	req.send("lat=" + lat + "&lng=" + lng + "&range=" + range);
}

// update map with food truck locations upon click
function updateMap(jsonRes) {
	var json = JSON.parse(jsonRes);
	var res = [];
	for (i = 0; i < json.length; i++) {

		var foodlat = json[i].latitude;
		var foodlng = json[i].longitude;
		var fooditems = json[i].fooditems;
		var applicant = json[i].applicant;
		var address = json[i].address;
		var facilitytype = json[i].facilitytype;
		res.push(facilitytype);

		var latlng = new google.maps.LatLng(foodlat, foodlng);

		var foodmarker = new google.maps.Marker({
			position : latlng,
			map : map,
			icon : 'http://maps.google.com/mapfiles/ms/icons/green-dot.png',
			title : applicant + '\n' + fooditems + '\n' + address
		});
		markers.push(foodmarker);
	}

	// find unique food truck values
	var uniqueRes = [];
	for (i = 0; i < res.length; i++) {
		var current = res[i];
		if (uniqueRes.indexOf(current) < 0)
			uniqueRes.push(current);
	}

	var resStr = "";
	for (i = 0; i < uniqueRes.length; i++) {
		resStr += uniqueRes[i] + '/';
	}

	document.getElementById('result').innerHTML = "fooditem types include:"
			+ resStr
			+ "<br/>"
			+ "Move mouse on the green marker to see the food truck details within your specified location";
}

// Place marker and push into an array
function placeMarker(location) {
	var marker = new google.maps.Marker({
		position : location,
		map : map
	});
	markers.push(marker);
}

// Sets the map on all markers in the array.
function setAllMap(map) {
	for (var i = 0; i < markers.length; i++) {
		markers[i].setMap(map);
	}
}

// Removes the markers from the map, but keeps them in the array.
function clearMarkers() {
	setAllMap(null);
}

// Shows any markers currently in the array.
function showMarkers() {
	setAllMap(map);
}

// Deletes all markers in the array by removing references to them.
function deleteMarkers() {
	clearMarkers();
	markers = [];
}

google.maps.event.addDomListener(window, 'load', init_map);
