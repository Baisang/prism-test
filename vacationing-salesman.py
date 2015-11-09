import requests
import fileinput
import os
import json
from math import radians, cos, sin, asin, sqrt


key = os.environ['PRISM_MAPS_KEY']
maps_url = "https://maps.googleapis.com/maps/api/distancematrix/json"
geocode_url = "https://maps.googleapis.com/maps/api/geocode/json"

def get_distances(c_list):
    # The API is limited to 2000 chars in a URL, so that's why I can't just make
    # one giant request for everything.
    # It would be best to chunk cities out into ~2000 chars to minimize the
    # amount of requests
    distances = []
    for i in range(len(c_list) - 1):
        origin = c_list[i]
        dest = c_list[i+1]
        # If we want as the crow flies, the request is a bit different. We request to
        # the Google geocoding API and then calculate the distance from the
        # latitude and longitude accordingly.
        payload_orig = {'address': origin[0] + ' ' + origin[1],
                        'key': key}
        payload_dest = {'address': dest[0] + ' ' + dest[1],
                        'key': key}
        response_orig = requests.get(geocode_url, params=payload_orig).json()
        response_dest = requests.get(geocode_url, params=payload_dest).json()
        orig_location = response_orig['results'][0]['geometry']['location']
        dest_location = response_dest['results'][0]['geometry']['location']
        dist = calculate_distance(orig_location, dest_location, True)
        distances.append(dist)
    return distances

'''Haversine formula adapted from 
http://stackoverflow.com/questions/4913349/haversine-formula-in-python-bearing-and-distance-between-two-gps-points
'''
def calculate_distance(loc1, loc2, miles):
    lon1 = loc1['lng']
    lon2 = loc2['lng']
    lat1 = loc1['lat']
    lat2 = loc2['lat']
    # convert decimal degrees to radians 
    lon1, lat1, lon2, lat2 = map(radians, [lon1, lat1, lon2, lat2])

    # haversine formula 
    dlon = lon2 - lon1 
    dlat = lat2 - lat1 
    a = sin(dlat/2)**2 + cos(lat1) * cos(lat2) * sin(dlon/2)**2
    c = 2 * asin(sqrt(a)) 
    r = 6371 # Radius of earth in kilometers. Use 3956 for miles
    if miles:
        r = 3956
    return c * r


cities = []
for line in fileinput.input():
    line = line.strip('\n')
    pair = line.split(', ')
    cities.append(tuple(pair))
distances = get_distances(cities)
print 'Success! Your vacation itinerary is:\n'
for i in range(len(cities) - 1):
    origin = cities[i]
    dest = cities[i+1]
    print origin[0] + ', ' + origin[1] + ' -> ' + dest[0] + ', ' + dest[1] + ': ' + str(distances[i]) + ' miles'
print 'Total distance covered in your trip: ' + str(sum(distances)) + ' miles'