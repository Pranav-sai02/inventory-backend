package com.neoteric.avoota_inventory.add_rateplans.repository;

import com.neoteric.avoota_inventory.add_rateplans.entity.RatePlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatePlanRepository extends JpaRepository<RatePlan, Long> {
    List<RatePlan> findByRoomRoomId(Long roomId);
}
