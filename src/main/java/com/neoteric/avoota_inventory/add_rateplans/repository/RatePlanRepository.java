package com.neoteric.avoota_inventory.add_rateplans.repository;

import com.neoteric.avoota_inventory.add_rateplans.entity.RatePlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatePlanRepository extends JpaRepository<RatePlanEntity, Long> {
    List<RatePlanEntity> findByRoomRoomId(Long roomId);
}
