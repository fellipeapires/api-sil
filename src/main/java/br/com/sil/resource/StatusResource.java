package br.com.sil.resource;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/status")
public class StatusResource {
	
	@GetMapping("")
	public ResponseEntity<?> getStatus() {
		Map<String, String> status = new HashMap<String, String>();
		 status.put("status", "Sucesso");
		return new ResponseEntity<Map<String, String>>(status, HttpStatus.OK);
	}

}

