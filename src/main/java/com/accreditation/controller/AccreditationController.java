package com.accreditation.controller;

import com.accreditation.dto.AccreditationDTO;
import com.accreditation.dto.AccreditationResponseDTO;
import com.accreditation.dto.AccreditationStoryDTO;
import com.accreditation.service.AccreditationService;
import com.accreditation.type.AccreditationStatus;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/user")
public class AccreditationController {


    @Autowired
    private AccreditationService accreditationService;

    @ApiOperation(value = "Accreditation status creation.", httpMethod = "POST", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({ @ApiResponse(code = 201, message = "The request has succeeded."),
            @ApiResponse(code = 400, message = "One or more attributes is invalid"),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 500, message = "Internal Server Error ")})
    @ResponseStatus(OK)
    @PostMapping("/accreditation")
    public ResponseEntity creation(@Validated @RequestBody AccreditationDTO accreditation){
        try{
            var saved = accreditationService.registry(accreditation);
            return ResponseEntity.accepted().body(AccreditationResponseDTO.builder().userID(saved.getId()).build());
        }catch (RuntimeException ex){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(ex.getMessage());
        }
    }

    @ApiOperation(value = "Returning all accreditation statuses linked to the provided user ID.", httpMethod = "GET", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({ @ApiResponse(code = 201, message = "The request has succeeded."),
            @ApiResponse(code = 204, message = "No result for this accreditation"),
            @ApiResponse(code = 400, message = "One or more attributes is invalid"),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 500, message = "Internal Server Error ")})
    @ResponseStatus(OK)
    @GetMapping("/{userId}/accreditation")
    public ResponseEntity<AccreditationResponseDTO> accreditation(@PathVariable(name = "userId") String userId){
        return ResponseEntity.ok().body(accreditationService.historic(UUID.fromString(userId)));
    }
}
