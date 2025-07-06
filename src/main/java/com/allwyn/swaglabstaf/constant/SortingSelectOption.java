package com.allwyn.swaglabstaf.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortingSelectOption {

    A_TO_Z("Name (A to Z)"),
    Z_TO_A("Name (Z to A)"),
    LOW_TO_HIGH("Price (low to high)"),
    HIGH_TO_LOW("Price (high to low)");

    private final String name;
}
