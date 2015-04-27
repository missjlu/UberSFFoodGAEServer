uber-coding-challenge
=====================

This project presents a solution to the SF Food Truck coding challenge.

SF Movies: Create a service that shows on a map where movies have been filmed in
San Francisco. The user should be able to filter the view using autocompletion
search.

- The project is hosted on [Google App Engine](http://1-dot-ubersffood.appspot.com/)
- The source code is located on
  [github](https://github.com/missjlu/UberSFFoodGAEServer/).
- Documentation of how to use the web service is located at [User Manual](https://github.com/missjlu/UberSFFoodGAEServer/blob/master/user-manual.txt).


# Technology stack

- Language: Java (medium experience)
- Backend framework: Servlet (medium experience)
- Database Integration: SF Data API (no experience)
- Frontend framework: No

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
    - Client-server: A uniform interface separates clients from the servers.
    - Stateless: The server stores no client and third-party(SODA API) context.

- Future optimizations
, which would also be the next issues to address if given more time.
- The back-end could have been done more professionally to provide more flexible API.
Currently, the information provided to the front-end is hardcoded on the server side.
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