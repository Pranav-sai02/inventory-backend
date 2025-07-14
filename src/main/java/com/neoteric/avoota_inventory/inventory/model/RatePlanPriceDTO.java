package com.neoteric.avoota_inventory.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatePlanPriceDTO {
    private Long hotelId;
    private Long roomId;
//    private String roomName;
    private Long ratePlanId;
//    private String ratePlanName;
    private LocalDate date;
    private int availableCount;
    private Double pricePerOne;
    private Double pricePerTwo;
}
