package vn.whoever.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONStream {
	
	private InputStream inputStream;
	
	public JSONStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public JSONObject getJSONObject() {
		JSONObject jsonObject = null;
		
		BufferedReader reader = null;
		StringBuilder stringBuilder = new StringBuilder();
		
		String line = null;
		try {
			reader = new BufferedReader(new InputStreamReader(inputStream));
			while((line = reader.readLine()) != null) {
				stringBuilder.append(line);
			}
			
			String data = stringBuilder.toString();
			data = java.net.URLDecoder.decode(data, "UTF-8");
			
			jsonObject = new JSONObject(data);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		return jsonObject;
	}
	
}
