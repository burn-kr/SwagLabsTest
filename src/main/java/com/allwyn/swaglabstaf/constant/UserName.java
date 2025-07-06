package com.allwyn.swaglabstaf.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * An enumeration representing different types of predefined usernames for testing purposes,
 * particularly in scenarios where different user behaviors or states need to be simulated.
 * Each enum constant holds the actual string value of the username.
 */
@Getter
@RequiredArgsConstructor
public enum UserName {
    STANDARD_USER("standard_user"),
    LOCKED_OUT_USER("locked_out_user"),
    PROBLEM_USER("problem_user"),
    PERFORMANCE_GLITCH_USER("performance_glitch_user"),
    ERROR_USER("error_user"),
    VISUAL_USER("visual_user");

    private final String name;
}
