package com.allwyn.swaglabstaf.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * An enumeration representing the available options for sorting products or items in a list.
 * Each enum constant holds the display name of the sorting option, which typically
 * corresponds to the text displayed in a dropdown or select element on a web page.
 */
@Getter
@AllArgsConstructor
public enum SortingSelectOption {

    A_TO_Z("Name (A to Z)"),
    Z_TO_A("Name (Z to A)"),
    LOW_TO_HIGH("Price (low to high)"),
    HIGH_TO_LOW("Price (high to low)");

    private final String name;
}
