package com.customerrewards.rewardcalculator.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "points")
@ToString
public class MonthlyPoints {
	
	
	private Integer customerId;
	private Integer month;
	private Long points;

}
