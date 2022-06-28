package com.hacademy.ap.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hacademy.ap.entity.CharacterDetailInformation;
import com.hacademy.ap.entity.CharacterInformation;
import com.hacademy.ap.entity.SearchResult;
import com.hacademy.ap.entity.SearchUnit;
import com.hacademy.ap.entity.User;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Component
public class AionRequestSender {
	private OkHttpClient client = new OkHttpClient();
	private ObjectMapper mapper = new ObjectMapper();
	
	public Response get(String url) throws IOException {
		Request.Builder builder = new Request.Builder().url(url).get();
		return client.newCall(builder.build()).execute();
	}
	public Response post(String url, String json) throws IOException {
		RequestBody body = RequestBody.Companion.create(json, MediaType.get("application/json"));
		Request request = new Request.Builder().url(url).post(body).build();
		return client.newCall(request).execute();
	}
	public Response put(String url, String json) throws IOException {
		RequestBody body = RequestBody.Companion.create(json, MediaType.get("application/json"));
		Request request = new Request.Builder().url(url).put(body).build();
		return client.newCall(request).execute();
	}
	
	public SearchResult findByUsername(String username) {
		try {
			String url = String.format("https://api-aion.plaync.com/search/v1/characters?classId=&pageSize=20&query=%s&raceId=&serverId=0&sort=rank", username);
			Response response = get(url);
			if(!response.isSuccessful() || response.body() == null) {
				throw new Exception();
			}
			
			return mapper.readValue(response.body().string(), SearchResult.class);
		}
		catch(Exception e) {
			return null;
		}
	}
	
	public SearchUnit findByServerNameAndUsername(User user) {
		return findByServerNameAndUsername(user.getServerName(), user.getUserName());
	}
	public SearchUnit findByServerNameAndUsername(String serverName, String username) {
		SearchResult result = findByUsername(username);
		for(SearchUnit unit : result.getDocuments()) {
			if(unit.getServerName().equals(serverName)) {
				return unit;
			}
		}
		return null;
	}
	
	public CharacterInformation findByServerCodeAndUserCode(int serverCode, int userCode) {
		try {
			String url = String.format("https://api-aion.plaync.com/game/v2/classic/characters/server/%d/id/%d", serverCode, userCode);
			Response response = get(url);
			if(!response.isSuccessful() || response.body() == null) {
				throw new Exception();
			}
			
			return mapper.readValue(response.body().string(), CharacterInformation.class);
		}
		catch (Exception e) {
			return null;
		}
	}
	
	public CharacterDetailInformation findEquipmentByServerCodeAndUserCode(int serverCode, int userCode) {
		try {
			String url = String.format("https://api-aion.plaync.com/game/v2/classic/merge/server/%d/id/%d", serverCode, userCode);
			Map<String, String[]> map = new HashMap<>();
			map.put("keyList", new String[] {"character_stats", "character_equipments", "character_abyss", "character_stigma"});
			Response response = put(url, mapper.writeValueAsString(map));
			if(!response.isSuccessful() || response.body() == null) {
				throw new Exception();
			}

			return mapper.readValue(response.body().string(), CharacterDetailInformation.class);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
