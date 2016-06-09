package vn.whoever.mainserver.service;

import vn.whoever.mainserver.service.utils.ClientLocation;

public interface LocationIPService {

	public ClientLocation getLocation(String ipAddress);

}
