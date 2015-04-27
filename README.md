uber-coding-challenge
=====================

This project presents a solution to the SF Food Truck coding challenge. I chose
full stack.


- The project is hosted on [Google App Engine](http://1-dot-ubersffood.appspot.com/)
- The source code is located on
  [github](https://github.com/missjlu/UberSFFoodGAEServer/).
- Documentation of how to use the web application is located at [User Manual](https://github.com/missjlu/UberSFFoodGAEServer/blob/master/user-manual.txt).


# Technology stack

- Language: Java (medium experience)
- Backend: Servlet. The servlet served as an endpoint API to provide the predefined info.
- Database Integration: SF Data API
- Frontend Framework: No framework. Only javascript and css (minimum experience)

## Reasoning:

**Language**: Java is the language I'm familiar with so I chose it.

**Backend framework**: I chose Servlet as the back-end framework which is commonly 
used.

**Database**: SF Data provide SODA API, which is easy to use to fetch data.

**Frontend**: The front-end has been implemented as a single 'index.html' with 
accompanying JavaScript file.

# Design choices

Below, I go through some of the major design choices made in the process of
creating the project.

## Back-end

- Implemented the back-end as a web service:
    - Client-server: The servlet framework separates clients from the servers. The 
    endpoint of servlet is http://1-dot-ubersffood.appspot.com//ubersffoodgaeserver.
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

- Provided user with an interface to.
- Used the Google Maps API 3 to show the map and add the markers for every food truck
  location.