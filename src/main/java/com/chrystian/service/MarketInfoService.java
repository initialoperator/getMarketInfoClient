package com.chrystian.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chrystian.controller.repository.TradeRepository;
import com.chrystian.model.CandleStick;
import com.chrystian.model.Trade;

@Service
public class MarketInfoService {

	@Autowired
	private TradeRepository tradeRepository;
	
	public List<Trade> getTradesWithInstrumentName(String instrument){
		return tradeRepository.findByInstrument(instrument);
	}
	
	public List<CandleStick> getCandleStickWithInstrument(String instrument, int timeFrame){
		List<Trade> trades = getTradesWithInstrumentName(instrument);
		List<CandleStick> css = getCandleSticksFromTrades(trades, timeFrame);
		return css;
	}
	
	protected List<CandleStick> getCandleSticksFromTrades(List<Trade> trades, int timeFrame){
		//60000 milliseconds in a minute
		final long minute = 60000L;
		Map<Long, List<Trade>> tradesByMin = new HashMap<>();
		trades.stream().forEach(t -> {
							long timestamp = t.getTimestamp();
							long key = (timestamp/minute+timeFrame) * minute;
							if(key < System.currentTimeMillis()) { //ignoring the current (incomplete) minute
								if(tradesByMin.get(key) == null) {
									List<Trade> l = new ArrayList<>();
									l.add(t);
									tradesByMin.put(key, l);
								}else
									tradesByMin.get(key).add(t);	
							}
						});
		
		List<CandleStick> result = tradesByMin.entrySet().stream().sorted((e1, e2) -> Long.compare(e1.getKey(), e2.getKey()))
				.map(e -> {
					List<Trade> tradesSorted = e.getValue().stream()
							.sorted((t1, t2) -> Long.compare(t1.getTimestamp(), t2.getTimestamp())).collect(Collectors.toList());
					double high = tradesSorted.stream().map(Trade::getPrice).max((d1, d2) -> Double.compare(d1, d2)).get();
					double low= tradesSorted.stream().map(Trade::getPrice).min((d1, d2) -> Double.compare(d1, d2)).get();
					double open = tradesSorted.get(0).getPrice();
					double close = tradesSorted.get(tradesSorted.size()-1).getPrice();
					String instrument = tradesSorted.get(0).getInstrument();
										
					CandleStick cs = new CandleStick();
					cs.setClose(close);
					cs.setHigh(high);
					cs.setLow(low);
					cs.setOpen(open);
					cs.setTimestamp(e.getKey());
					cs.setInstrument(instrument);
					//TODO: volume is omitting for now
					
					return cs;
				}).collect(Collectors.toList());
								
		return result;
	}
}
