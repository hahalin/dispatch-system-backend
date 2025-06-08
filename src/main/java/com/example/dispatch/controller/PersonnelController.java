package com.example.dispatch.controller;

import com.example.dispatch.dto.MessageResponse;
import com.example.dispatch.dto.PersonnelDTO;
import com.example.dispatch.service.PersonnelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/personnel")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@Tag(name = "人員管理", description = "人員資料管理相關的 API")
@SecurityRequirement(name = "JWT")
public class PersonnelController {

    @Autowired
    private PersonnelService personnelService;
    
    public PersonnelController() {
        System.out.println("=== PersonnelController created ===");
    }

    @GetMapping("/test")
    @Operation(summary = "測試端點", description = "測試人員 API 是否正常運作")
    @ApiResponse(responseCode = "200", description = "測試成功")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("Personnel API is working!");
    }

    @GetMapping
    @Operation(summary = "取得所有人員", description = "取得系統中所有人員的資料")
    @ApiResponse(responseCode = "200", description = "成功取得人員列表", 
                content = @Content(schema = @Schema(implementation = PersonnelDTO.class)))
    public ResponseEntity<List<PersonnelDTO>> getAllPersonnel() {
        System.out.println("=== PersonnelController.getAllPersonnel() called ===");
        try {
            List<PersonnelDTO> personnel = personnelService.getAllPersonnel();
            System.out.println("Found " + personnel.size() + " personnel records");
            return ResponseEntity.ok(personnel);
        } catch (Exception e) {
            System.out.println("Error in getAllPersonnel: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "根據 ID 取得人員", description = "根據指定的 ID 取得特定人員的資料")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "成功取得人員資料", 
                    content = @Content(schema = @Schema(implementation = PersonnelDTO.class))),
        @ApiResponse(responseCode = "404", description = "找不到指定的人員")
    })
    public ResponseEntity<PersonnelDTO> getPersonnelById(
            @Parameter(description = "人員 ID", required = true) @PathVariable Integer id) {
        PersonnelDTO personnel = personnelService.getPersonnelById(id);
        return ResponseEntity.ok(personnel);
    }
    
    @GetMapping("/status/{status}")
    @Operation(summary = "根據狀態取得人員", description = "根據指定的狀態取得人員列表")
    @ApiResponse(responseCode = "200", description = "成功取得人員列表", 
                content = @Content(schema = @Schema(implementation = PersonnelDTO.class)))
    public ResponseEntity<List<PersonnelDTO>> getPersonnelByStatus(
            @Parameter(description = "人員狀態", required = true) @PathVariable String status) {
        List<PersonnelDTO> personnel = personnelService.getPersonnelByStatus(status);
        return ResponseEntity.ok(personnel);
    }
    
    @GetMapping("/search")
    @Operation(summary = "搜尋人員", description = "根據關鍵字搜尋人員")
    @ApiResponse(responseCode = "200", description = "成功取得搜尋結果", 
                content = @Content(schema = @Schema(implementation = PersonnelDTO.class)))
    public ResponseEntity<List<PersonnelDTO>> searchPersonnel(
            @Parameter(description = "搜尋關鍵字", required = true) @RequestParam String keyword) {
        List<PersonnelDTO> personnel = personnelService.searchPersonnel(keyword);
        return ResponseEntity.ok(personnel);
    }
    
    @GetMapping("/stats")
    @Operation(summary = "取得人員統計", description = "取得人員相關的統計資料")
    @ApiResponse(responseCode = "200", description = "成功取得統計資料")
    public ResponseEntity<Map<String, Object>> getPersonnelStats() {
        Map<String, Object> stats = personnelService.getPersonnelStats();
        return ResponseEntity.ok(stats);
    }

    @PostMapping
    @Operation(summary = "新增人員", description = "新增一位新的人員")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "人員新增成功", 
                    content = @Content(schema = @Schema(implementation = PersonnelDTO.class))),
        @ApiResponse(responseCode = "400", description = "輸入資料不正確")
    })
    public ResponseEntity<PersonnelDTO> createPersonnel(@Valid @RequestBody PersonnelDTO personnelDTO) {
        PersonnelDTO createdPersonnel = personnelService.createPersonnel(personnelDTO);
        return ResponseEntity.ok(createdPersonnel);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新人員資料", description = "更新指定 ID 的人員資料")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "人員資料更新成功", 
                    content = @Content(schema = @Schema(implementation = PersonnelDTO.class))),
        @ApiResponse(responseCode = "400", description = "輸入資料不正確"),
        @ApiResponse(responseCode = "404", description = "找不到指定的人員")
    })
    public ResponseEntity<PersonnelDTO> updatePersonnel(
            @Parameter(description = "人員 ID", required = true) @PathVariable Integer id, 
            @Valid @RequestBody PersonnelDTO personnelDTO) {
        PersonnelDTO updatedPersonnel = personnelService.updatePersonnel(id, personnelDTO);
        return ResponseEntity.ok(updatedPersonnel);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "刪除人員", description = "刪除指定 ID 的人員")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "人員刪除成功", 
                    content = @Content(schema = @Schema(implementation = MessageResponse.class))),
        @ApiResponse(responseCode = "404", description = "找不到指定的人員")
    })
    public ResponseEntity<MessageResponse> deletePersonnel(
            @Parameter(description = "人員 ID", required = true) @PathVariable Integer id) {
        personnelService.deletePersonnel(id);
        return ResponseEntity.ok(MessageResponse.success("Personnel deleted successfully"));
    }

    // Resume import/export functionality can be added later when ResumeService is implemented
}
