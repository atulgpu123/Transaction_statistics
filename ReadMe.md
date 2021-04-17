# Getting Started

### Reference Documentation

We are using two type as city and coordinates  as equalignorecase manner .

if we use type as city then city and country will  be mandatory rest all field will be ignored 
if we use type as city then lon and lat will be mandatory rest all field will be ignored 

for basic validation of country code to be use ISO 3166 country codes we are using Neo Visionaries Internationalization Package under apache license 
 
it will call accordingly call below thirdpary APIs

api.openweathermap.org/data/2.5/weather?q={city name,countrycode}&appkey=ffa6f13ea40a22452bba3be726315d3f
api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appkey=ffa6f13ea40a22452bba3be726315d3f







curl --location --request GET 'http://localhost:8080/weather?city=asf&country=IN&type=CITY' \


curl --location --request GET 'http://localhost:8080/weather?lat=12&lon=12&type=COORDINATES' \




