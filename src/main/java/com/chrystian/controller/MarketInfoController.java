package com.chrystian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chrystian.service.MarketInfoService;

@RestController
public class MarketInfoController {

	@Autowired
	private MarketInfoService service;
	
	@RequestMapping(value = "public/get-candlestick", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getCandleSticks(@RequestParam String instrumentName, @RequestParam int timeFrame) {
		ResponseObject object = new ResponseObject();
		object.code = 0;
		object.method = "public/get-candlestick";
		object.result = service.getCandleStickWithInstrument(instrumentName, timeFrame);
		ResponseEntity<ResponseObject> res = new ResponseEntity<>(object, HttpStatus.OK);
		return res;
	}
	
	@RequestMapping(value = "public/get-trades", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getCandleSticks(@RequestParam String instrumentName) {
		ResponseObject object = new ResponseObject();
		object.code = 0;
		object.method = "public/get-trades";
		object.result = service.getTradesWithInstrumentName(instrumentName);
		ResponseEntity<ResponseObject> res = new ResponseEntity<>(object, HttpStatus.OK);
		return res;
	}
	

	@SuppressWarnings("unused")
	private static class ResponseObject {
		private int code;
		private String method;
		private List result;
		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}
		public String getMethod() {
			return method;
		}
		public void setMethod(String method) {
			this.method = method;
		}
		public List getResult() {
			return result;
		}
		public void setResult(List result) {
			this.result = result;
		}
		
	}
}
