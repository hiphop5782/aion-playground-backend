package com.hacademy.ap.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hacademy.ap.entity.CharacterDetailInformation;
import com.hacademy.ap.entity.CharacterInformation;
import com.hacademy.ap.entity.SearchResult;
import com.hacademy.ap.util.AionRequestSender;

@CrossOrigin
@RestController
public class APController {
	
	@Autowired
	private AionRequestSender sender; 
	
	@GetMapping("/")
	public String home() {
		return "Hello A.P(Aion Proxy)";
	}
	
	@GetMapping("/usersearch/{username}")
	public SearchResult findUser(@PathVariable String username) {
		return sender.findByUsername(username).filterAndSort();
	}
	
	@GetMapping("/userinfo/{serverCode}/{userCode}")
	public CharacterInformation findByServerCodeAndUserCode(@PathVariable int serverCode, @PathVariable int userCode) {
		return sender.findByServerCodeAndUserCode(serverCode, userCode);
	}
	
	@GetMapping("/userdetail/{serverCode}/{userCode}")
	public CharacterDetailInformation findDetailByServerCodeAndUserCode(@PathVariable int serverCode, @PathVariable int userCode) {
		return sender.findEquipmentByServerCodeAndUserCode(serverCode, userCode).sort();
	}
	
}
