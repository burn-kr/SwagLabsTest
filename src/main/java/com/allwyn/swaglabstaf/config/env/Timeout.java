package com.allwyn.swaglabstaf.config.env;

/**
 * Represents a record for defining timeout durations for implicit and explicit waits.
 * This record provides a concise way to bundle together the two common types of waiting
 * periods used in automated tests or other time-sensitive operations.
 *
 * @param implicitWait The duration, in milliseconds, for an implicit wait.
 * @param explicitWait The duration, in milliseconds, for an explicit wait.
 */
public record Timeout(long implicitWait, long explicitWait) {
}
