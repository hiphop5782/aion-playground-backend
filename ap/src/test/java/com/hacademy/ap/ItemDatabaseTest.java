package com.hacademy.ap;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hacademy.ap.util.AionRequestSender;

import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Slf4j
@SpringBootTest
public class ItemDatabaseTest {
	
	@Autowired
	private AionRequestSender sender;
	
	private StringBuffer buffer = new StringBuffer();
	
	@BeforeEach
	public void prepare() throws FileNotFoundException {
		File target = new File("param");
		Scanner sc = new Scanner(target);
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			if(line == null) break;
			buffer.append(line);
			if(sc.hasNextLine()) {
				buffer.append("&");
			}
		}
		sc.close();
	}
	
	@Test
	public void test() throws IOException {
		String bodyString = URLEncoder.encode(buffer.toString(), "UTF-8");
		String url = "https://aionpowerbook.com/powerbook/extensions/a_item_tables/item_ajax.php?lowerid=100200001&higherid=100299999&getquality=%&lang=ko&type=&classic=";
		Response response = sender.postByFormData(url, bodyString);
		System.out.println(response);
		assertTrue(response.isSuccessful());
		
		ResponseBody responseBody = response.body();
		System.out.println(responseBody.string());
	}
	
}
