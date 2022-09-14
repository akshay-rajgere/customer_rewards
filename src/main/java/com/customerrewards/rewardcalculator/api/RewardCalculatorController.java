package com.customerrewards.rewardcalculator.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.catalina.core.JreMemoryLeakPreventionListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customerrewards.rewardcalculator.dto.CustomerReward;
import com.customerrewards.rewardcalculator.dto.CustomerTransaction;
import com.customerrewards.rewardcalculator.dto.MonthlyPoints;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

@RestController
public class RewardCalculatorController {
	
	
	@PostMapping("/calculateRewards")
	public Collection<CustomerReward> calculateRewards(@RequestBody List<CustomerTransaction> transactions){
		
		HashMap<MonthlyPoints, Long> pointMap= new HashMap<MonthlyPoints, Long>();
		
		transactions.forEach(t->{
			
			long points=0;
			
			long amount=t.getAmount();
			
			long over100=amount-100;
			
			if(over100<0) {
				over100=0;
			}
			
			if(amount>100) {
				amount=100;
			}
			
			long over50=amount-50;
			
			if(over50<0) {
				over50=0;
			}
			
			points=(over100*2)+over50;

			MonthlyPoints mp= new MonthlyPoints();
			mp.setCustomerId(t.getCustomerId());
			mp.setMonth(t.getDate().getMonthValue());
			if(pointMap.containsKey(mp)) {
				
				pointMap.put(mp, pointMap.get(mp)+points);
				
			}
			
			else {
				
				pointMap.put(mp,points);
				
			}
		});
		
		
		HashMap<Integer, CustomerReward> customerPoints= new HashMap<>();
		
		pointMap.entrySet().forEach(e->{
			
			
			Integer id = e.getKey().getCustomerId();
			
			if(customerPoints.containsKey(id)) {
				customerPoints.get(id).setTotalPoints(customerPoints.get(id).getTotalPoints()+e.getValue());
				MonthlyPoints custMp= e.getKey();
				custMp.setPoints(e.getValue());
				
				customerPoints.get(id).getMonthlyPoints().add(custMp);
			}
			else {
				CustomerReward rew= new CustomerReward();
				rew.setCustomerId(id);
				MonthlyPoints custMp= e.getKey();
				custMp.setPoints(e.getValue());
				ArrayList<MonthlyPoints> mpList= new ArrayList<>();
				mpList.add(custMp);
				rew.setMonthlyPoints(mpList);
				rew.setTotalPoints(e.getValue());
				customerPoints.put(id, rew);
				
			}
		});
		
		
		
		
		return customerPoints.values();
		
	}

}
