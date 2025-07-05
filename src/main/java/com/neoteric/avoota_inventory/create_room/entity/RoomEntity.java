package com.neoteric.avoota_inventory.create_room.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.neoteric.avoota_inventory.add_hotel.entity.HotelEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rooms", schema = "avoota")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "room_type", nullable = false)
    private String roomType;

    @Column(name = "room_view", nullable = false)
    private String roomView;

    @Column(name = "room_size", nullable = false)
    private int roomSize;

    @Enumerated(EnumType.STRING)
    @Column(name = "size_unit", nullable = false)
    private SizeUnit sizeUnit;

    @Column(name = "room_name", nullable = false)
    private String roomName;

    @Column(name = "number_of_rooms", nullable = false)
    private int numberOfRooms;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    @JsonBackReference
    private HotelEntity hotel;
}
