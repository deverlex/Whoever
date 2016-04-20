package vn.whoever.mainserver.service.impl;

import java.io.IOException;
import java.net.URL;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;

import vn.whoever.mainserver.service.LocationIPService;
import vn.whoever.mainserver.service.utils.ClientLocation;

@Service("locationIPService")
@Transactional
public class LocationIPServiceImpl implements LocationIPService {

	public ClientLocation getLocation(String ipAddress) {
		String dataFile = "GeoIPv6.dat";
		
		ClientLocation location = new ClientLocation();
		location.setLatitude(20.133765);
		location.setLongitude(120.143567);
		location.setPostalCode("84");
		location.setCountryName("Vietname");
		
		return location;
		//return getLocation(ipAddress, dataFile);
	}
	
	public static void main(String[] args) {
		LocationIPService ipService = new LocationIPServiceImpl();
		ipService.getLocation("72.229.28.185");
	}
	
	private ClientLocation getLocation(String ipAddress, String fileData) {
		ClientLocation location = null;
		
		URL url = getClass().getClassLoader().getResource(fileData);
		
		if(url == null) {
			System.err.println("Location datafile is not found - " + fileData);
		} else {
			location = new ClientLocation();
			
			LookupService lookup;
			try {
				lookup = new LookupService(url.getPath(), LookupService.GEOIP_MEMORY_CACHE);
				Location locationService = lookup.getLocation(ipAddress);
				
				location.setCountryName(locationService.countryName);
				location.setPostalCode(locationService.postalCode);
				location.setLatitude((double)locationService.latitude);
				location.setLongitude((double)locationService.longitude);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return location;
	}
}
