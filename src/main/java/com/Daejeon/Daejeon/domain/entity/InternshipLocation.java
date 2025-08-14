package com.Daejeon.Daejeon.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity // JPA 엔티티임을 명시
public class InternshipLocation {

    // Getter 및 Setter 메서드
    @Id // 기본 키(Primary Key) 지정
    private String internshipId;
    private double latitude;
    private double longitude;
    private String locationName;

    // 기본 생성자 (JPA 필수)
    public InternshipLocation() {
    }

    // 생성자
    public InternshipLocation(String internshipId, double latitude, double longitude, String locationName) {
        this.internshipId = internshipId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationName = locationName;
    }

}
