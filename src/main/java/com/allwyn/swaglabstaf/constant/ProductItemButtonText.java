package com.allwyn.swaglabstaf.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductItemButtonText {
    ADD_TO_CART("Add to cart"),
    REMOVE("Remove");

    private final String text;
}
