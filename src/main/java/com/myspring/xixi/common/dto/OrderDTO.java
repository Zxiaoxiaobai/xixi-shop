package com.myspring.xixi.common.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDTO {
	private Long id;
	private Long userId;
	private Long goodId;
	private Integer num;
	private BigDecimal price;
	private BigDecimal actualPrice;
	private Long addressId;
}
