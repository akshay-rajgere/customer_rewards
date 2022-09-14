package com.customerrewards.rewardcalculator.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CustomerReward {

	
	private Integer customerId;
	private List<MonthlyPoints> monthlyPoints;
	private Long totalPoints;
	
	
}
