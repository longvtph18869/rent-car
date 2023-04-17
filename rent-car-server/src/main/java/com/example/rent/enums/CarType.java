package com.example.rent.enums;

public enum CarType {
	TWO_SEATER(2), FOUR_SEATER(4), FIVE_SEATER(5), SIX_SEATER(6), SEVEN_SEATER(7);

	private final int value;

	CarType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
