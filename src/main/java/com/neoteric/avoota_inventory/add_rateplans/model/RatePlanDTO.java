package com.neoteric.avoota_inventory.add_rateplans.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatePlanDTO {
    private Long id;
    private String ratePlanName;
    private String mealPlan;
    private Long roomId;
}
