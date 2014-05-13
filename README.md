TravelReminder
==============

This is an *attempt* at a simple travel reminder app for **Android** only at the moment. This is mainly an idea to get some Android development experience.

## App Overview
The basic premise of this is an app that allows you to set a destination and set a time you would like to end up at that destination. The app would then use Google Maps to estimate the travel time to that destination and give you a time when you should start travelling.

## Nice Features
### Traffic
The app also should take traffic into account through GoogleMaps if a data connection is available to refresh the travel time. This effectively makes the reminder an alert without a fixed time. The reminder could go off earlier if the traffic is heavier.

### Travel Lead Time
The app should also allow the user to set a lead time for the alert. For example, instead of the reminder going off when the user should leave, the user should be allowed to specify that the reminder should go off 10 minutes (or some arbitrary time) before.

## Possible Issues
### Quotas
Although use of the Google Maps data, i.e the actual maps, is free, the use of the *directions* API is free but only to a certain extent. The free directions API has quotas of 2,500 per day, with public transit directions using up 4 units of quota. This information can be found [here](https://developers.google.com/maps/documentation/directions/#Limits).

### Traffic Data
Another issue with using Google's direction API is that traffic seems to only be accounted for in the Google Maps for Business API. See "departure_time" under "Optional Parameters" on [this webpage](https://developers.google.com/maps/documentation/directions/#RequestParameters).

## Disclaimer
I have no idea what I'm doing, so bear with me...
