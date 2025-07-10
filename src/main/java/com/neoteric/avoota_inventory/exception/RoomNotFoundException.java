package com.neoteric.avoota_inventory.exception;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(String message) {
        super(message);
    }
}
