[![Build Status](https://travis-ci.org/pvanassen/ns-api.png?branch=master)](https://travis-ci.org/pvanassen/ns-api)
[![Coverage Status](https://coveralls.io/repos/pvanassen/ns-api/badge.png?branch=master)](https://coveralls.io/r/pvanassen/ns-api?branch=master)

NS API
======
An API for talking to the NS (Dutch railways) API interface written in Java

Usage
=====
To use the NS api you need to register an account with the NS. You can apply for an account at http://www.ns.nl/api/api. 

You will receive a username and password. To use the Java api, you initialize the NsApi object: 

```Java
NsApi nsApi = new NsApi(username, password);
```
Next step is to create a request, eg a 'actuele vertrektijden' request to get the current departures: 

```Java
ActueleVertrekTijdenRequest request = new ActueleVertrekTijdenRequest(stationName);
```

To do the actual request, you call getApiResponse with your request: 

```Java
ActueleVertrekTijden vertrekTijden = nsApi.getApiResponse(request); 
```

A new immutable object ActueleVertrekTijden is created containing the result of the API call. 

All classes are immutable and thread safe.
