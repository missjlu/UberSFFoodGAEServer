uber-coding-challenge
=====================

This project presents a solution to the SF Food Truck coding challenge. I chose
full stack.

Topic: Create a service that tells the user what types of food trucks might be found near a specific location on a map.
The data is available on [DataSF:Food Trucks](https://data.sfgov.org/Economy-and-Community/Mobile-Food-Facility-Permit/rqzj-sfat?)


- The project is hosted on [Google App Engine](http://1-dot-ubersffood.appspot.com/)
- The source code is located on
  [github](https://github.com/missjlu/UberSFFoodGAEServer/).
- Documentation of how to use the web application is located at [User Manual](https://github.com/missjlu/UberSFFoodGAEServer/blob/master/user-manual.txt).


# Technology stack

- Language: Java (medium experience)
- Backend: Servlet. The servlet served as an endpoint API to provide the predefined info.
- Database Integration: SF Data API
- Frontend Framework: No framework. Only javascript and css (minimum experience)


# Design choices

Belo is my design logic, issues and future optimization

## Back-end

- Implemented the back-end as a web service:
    - Client-server: The servlet framework separates clients from the servers. The servlet has its own endpoint upon deployment on google app engine. The endpoint of servlet is http://1-dot-ubersffood.appspot.com//ubersffoodgaeserver.
    Any clients can do a post or get to this endpoint from their end to access the web service.
    - Stateless: The server stores no client and third-party(SODA API) context.

- Future optimizations and Issues
- The back-end could have been done more professionally to provide more flexible API web service that requires certain parameters instead of using Servlet to parse predefined info. Currently, the information provided to the front-end is hardcoded on the server side.
It can be easily changed to provide a more extensive API. Correspondingly, the object
models can also be changed.
- The back-end should provide some cache function so that there could be logs on the
server side to store important information.
- Currently, the error handling information will not be shown to the front-end. It should
be improved so that it could be more user friendly.

## Front-end

- Provided user with an interface to interact with google map, specify the location and search range and get the food truck info, name, address and food item details.
- Used the Google Maps API 3 to show the map and add the markers for search location and every food truck
  location.