package com.ilucky.util.rest.jersey;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RestUtil {

	public String post(String url, String jsonParam) {
		ClientResponse response = null;
		String result = null;
		try {
			Client restClient = Client.create();
			WebResource webResource = restClient.resource(url);
			response = webResource.post(ClientResponse.class, jsonParam);
			if (response.getStatus() == 200) {
				result = response.getEntity(String.class);
			} else {
				result = "error";
			}
		} catch (Exception e) {
			
		} finally {
			response.close();
		}
		return result;
	}
}
