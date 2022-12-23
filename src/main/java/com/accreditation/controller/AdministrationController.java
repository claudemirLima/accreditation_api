package com.accreditation.controller;

import com.accreditation.service.AccreditationService;
import com.accreditation.type.AccreditationStatus;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/adm")
public class AdministrationController {

    @Autowired
    private AccreditationService accreditationService;

    @ApiOperation(value = "Accreditation status finalization.", httpMethod = "PUT", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({ @ApiResponse(code = 201, message = "The request has been received and the transaction will be processed"),
            @ApiResponse(code = 204, message = "No result for this accreditation"),
            @ApiResponse(code = 400, message = "One or more attributes is invalid"),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 500, message = "Internal Server Error ")})
    @ResponseStatus(OK)
    @PutMapping("/accreditation/{accreditationId}")
    public ResponseEntity confirmed(@PathVariable(name = "accreditationId") UUID accreditationId){
        var result = accreditationService.confirmed(accreditationId);
        if(result.isPresent())
            return ResponseEntity.ok().body(result.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Content result for this ID");
    }

    @ApiOperation(value = "Accreditation status EXPIRED.", httpMethod = "PUT", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({ @ApiResponse(code = 200, message = "The request has succeeded."),
            @ApiResponse(code = 204, message = "No result for this accreditation"),
            @ApiResponse(code = 400, message = "One or more attributes is invalid"),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 500, message = "Internal Server Error ")})
    @ResponseStatus(OK)
    @PutMapping("/accreditation/expire/{accreditationId}")
    public ResponseEntity expired(@PathVariable(name = "accreditationId") UUID accreditationId){
        var result = accreditationService.expired(accreditationId);
        if(result.isPresent())
            return ResponseEntity.ok().body(result.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Content result for this ID");
    }

}
