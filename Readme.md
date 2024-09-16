# Getting Started

### Reference Documentation

### Guides
### Components:
Riders: Information about riders.
Races: Information about races.
Race Results: The results per race, including time, whether the rider finished, etc.
Weather Conditions: A public weather API can be integrated to fetch weather conditions.


### Application Layers:
Database: To store riders, races, and race results.
REST Controllers: To handle HTTP requests.
Services: Business logic layer.
Repository: Interfaces to manage database operations.
External API Integration: To fetch weather data.
Database Entities: H2



### Riders:

Rider One: Finished in 1 hour (3600 seconds).
Rider Two: Finished in 1 hour 10 minutes (4200 seconds).
Rider Three: Finished in 50 minutes (3000 seconds).
Rider Four: Did not finish.




### Race:

Location: Chicago
Date: Current DateTime




### Race Results:

Rider One, Rider Two, and Rider Three finished with times recorded.
Rider Four did not finish.
Rider Five did not participate.


### HOW TO TEST:

### 1. create 5 riders with url respectively 
   http://localhost:8080/api/race/create-riders
   {"name" : "Rider one",
   "email" : "r1@gmail.com"
}

{"name" : "Rider two",
"email" : "r2@gmail.com"
}


{"name" : "Rider three",
"email" : "r3@gmail.com"
}

{"name" : "Rider four",
"email" : "r4@gmail.com"
}

{"name" : "Rider five",
"email" : "r5@gmail.com"
}


### 2. create location with url 

http://localhost:8080/api/race/create-race
{"location" : "Chicago"

}



### 3. create race-result with this url below respectively


http://localhost:8080/api/race/create-race-result

{
"rider":{
"id" : 1
},
"finished" : true,
"race":{
"id" : 1
},
"timeTaken" : 3600

}


{
"rider":{
"id" : 2
},
"finished" : true,
"race":{
"id" : 1
},
"timeTaken" : 4200

}


{
"rider":{
"id" : 3
},
"finished" : true,
"race":{
"id" : 1
},
"timeTaken" : 3000

}



{
"rider":{
"id" : 4
},
"finished" : false,
"race":{
"id" : 1
}


}



SOLUTION TO PROBLEM
1.The fastest 3 riders per race.
GET http://localhost:8080/api/race/1/fastest -H



2.Riders That Did Not Finish
GET http://localhost:8080/api/race/1/not-finished -H




3.Riders That Did Not Participate
GET http://localhost:8080/api/race/1/not-participated -H


4. Weather Conditions for the Race Location

GET http://localhost:8080/api/weather/chicago -H


5. Get All Races

GET http://localhost:8080/api/race/all-races

