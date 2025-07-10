package com.neoteric.avoota_inventory.add_rateplans.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.neoteric.avoota_inventory.create_room.entity.RoomEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rate_plans", schema = "avoota")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatePlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "rate_plan_name")
    private String ratePlanName;
    @Column(name = "meal_plan")
    private String mealPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    @JsonBackReference
    private RoomEntity room;


}
