package com.allwyn.swaglabstaf.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * An enumeration representing the expected text values for buttons associated with product items,
 * such as adding an item to a cart or removing it.
 * This enum helps in standardizing button text checks in tests or UI interactions.
 */
@Getter
@AllArgsConstructor
public enum ProductItemButtonText {
    ADD_TO_CART("Add to cart"),
    REMOVE("Remove");

    private final String text;
}
