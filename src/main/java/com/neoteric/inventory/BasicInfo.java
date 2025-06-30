package com.neoteric.inventory;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String propertyName;
    private String displayName;

    @Column(length = 2000)
    private String description;

    private String propertyType;
    private String starRating;
    private String hotelChain;
    private String yearOfConstruction;
    private String bookingsSince;

    private Integer numRestaurants;
    private Integer numRooms;
    private Integer numFloors;

    private String currency;
    private String timezone;
    private String checkInTime;
    private String checkOutTime;

    private Boolean isAuthorized;
    private Boolean is24HrCheckIn;
}
