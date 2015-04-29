/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global L */

function initmap()
{
    var map = L.map('map');

    L.tileLayer('https://{s}.tiles.mapbox.com/v3/{id}/{z}/{x}/{y}.png', {
        maxZoom: 18,
        attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, ' +
                '<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
                'Imagery © <a href="http://mapbox.com">Mapbox</a>',
        id: 'examples.map-i875mjb7'
    }).addTo(map);

    function onLocationFound(e) 
    {
        var radius = e.accuracy / 2;

        L.marker(e.latlng).addTo(map)
                .bindPopup("You are within " + radius + " meters from this point");

        L.circle(e.latlng, radius).addTo(map);
    }

    function onLocationError(e) 
    {
        //alert(e.message);
    }

    map.on('locationfound', onLocationFound);
    map.on('locationerror', onLocationError);

//    map.locate({setView: true, maxZoom: 16});

    L.marker([-1.2833, 36.81667], {}).addTo(map).bindPopup("Head Office, Riverside Drive, National, Nairobi");
    L.marker([-4.0500, 39.6667], {}).addTo(map).bindPopup("Coast Office, Nkurumah Road, Regional, Mombasa");
    L.marker([3.9167, 41.8333], {}).addTo(map).bindPopup("Mandera Office, Harambee Avenue, Regional, Mandera");
    L.marker([3.1500, 35.3500], {}).addTo(map).bindPopup("Turkana Office, Tom Mboya St., District, Turkana");
    L.marker([0.5167, 35.2833], {}).addTo(map).bindPopup("North Rift Office, Oloo St., County, Eldoret");
    L.marker([0.1000, 36.81667], {}).addTo(map).bindPopup("Kisumu Office, Odinga Oginga St., Regional, Kisumu");

    // start the map in Nairobi
    map.setView(new L.LatLng(-1.2833, 36.81667), 6);
    map.addLayer(osm);
}
