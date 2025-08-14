package com.Daejeon.Daejeon.controller;

import com.Daejeon.Daejeon.domain.entity.InternshipLocation;
import com.Daejeon.Daejeon.service.InternshipLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // RESTful API 컨트롤러임을 명시
@RequestMapping("/api/internships") // 기본 URL 경로
public class InternshipLocationController {

    @Autowired
    private InternshipLocationService internshipLocationService;

    // Read: 모든 인턴십 정보 조회
    @GetMapping
    public List<InternshipLocation> getAllInternships() {
        return internshipLocationService.findAll();
    }

    // Read: 특정 인턴십 상세 정보 조회
    @GetMapping("/{internshipId}")
    public ResponseEntity<InternshipLocation> getInternshipById(@PathVariable String internshipId) {
        Optional<InternshipLocation> internshipLocation = internshipLocationService.findById(internshipId);
        return internshipLocation.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create: 새로운 인턴십 정보 추가
    @PostMapping
    public ResponseEntity<InternshipLocation> createInternship(@RequestBody InternshipLocation internshipLocation) {
        InternshipLocation savedLocation = internshipLocationService.save(internshipLocation);
        return new ResponseEntity<>(savedLocation, HttpStatus.CREATED);
    }

    // Update: 인턴십 정보 수정
    @PutMapping("/{internshipId}")
    public ResponseEntity<InternshipLocation> updateInternship(@PathVariable String internshipId, @RequestBody InternshipLocation internshipLocation) {
        try {
            InternshipLocation updatedLocation = internshipLocationService.update(internshipId, internshipLocation);
            return ResponseEntity.ok(updatedLocation);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete: 인턴십 정보 삭제
    @DeleteMapping("/{internshipId}")
    public ResponseEntity<Void> deleteInternship(@PathVariable String internshipId) {
        try {
            internshipLocationService.deleteById(internshipId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
