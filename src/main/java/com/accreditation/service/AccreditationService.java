package com.accreditation.service;


import com.accreditation.domain.Accreditation;
import com.accreditation.domain.AccreditationStory;
import com.accreditation.dto.AccreditationDTO;
import com.accreditation.dto.AccreditationResponseDTO;
import com.accreditation.mapper.AccreditationDTOMapper;
import com.accreditation.mapper.AccreditationMapper;
import com.accreditation.repository.AccreditationRepository;
import com.accreditation.repository.AccreditationStoryRepository;
import com.accreditation.type.AccreditationStatus;
import com.accreditation.type.CommandStatus;
import com.accreditation.util.SendCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccreditationService {

    @Autowired
    private SendCommand sendCommand;
    @Autowired
    private AccreditationDTOMapper accreditationDTOMapper;

    @Autowired
    private AccreditationMapper accreditationMapper;

    @Autowired
    private AccreditationStoryRepository accreditationStoryRepository;

    @Autowired
    private AccreditationRepository accreditationRepository;

    public Accreditation registry(AccreditationDTO accreditationDTO) {

        var check = accreditationRepository.existAccreditationPending(accreditationDTO.getUserID().toString());
        if(check) {
            sendCommand.sendCommandAccreditation(accreditationDTO, CommandStatus.FAILED);
            throw new RuntimeException("Already exist request PENDING");
        }
        var accreditation = accreditationDTOMapper.map(accreditationDTO);
        if(accreditation.getAccreditationStories() == null)
            accreditation.setAccreditationStories(new ArrayList<>());

        accreditation.getAccreditationStories().add(AccreditationStory.builder()
                .id(UUID.randomUUID())
                .request(LocalDateTime.now())
                .status(AccreditationStatus.PENDING)
                .accreditation(accreditation)
                .build());
        var saved = accreditationRepository.save(accreditation);

        sendCommand.sendCommandAccreditation(accreditationDTO,CommandStatus.CONFIRMED);
        sendCommand.sendCommandAccreditationStatusChange(accreditation.getId(), CommandStatus.CONFIRMED,AccreditationStatus.PENDING);

        return saved;
    }

    public Optional<AccreditationStatus> expired(UUID accreditationId) {
        var accreditationStory =
                accreditationStoryRepository.findByAccreditationID(accreditationId.toString());

        if(accreditationStory != null && !accreditationStory.isEmpty()){
            var last = accreditationStory.get(accreditationStory.size() -1);
            if(AccreditationStatus.EXPIRED.equals(last.getStatus()) ||
                    AccreditationStatus.FAILED.equals(last.getStatus())){
                sendCommand.sendCommandAccreditationStatusChange(accreditationId,CommandStatus.FAILED,AccreditationStatus.EXPIRED);
                return Optional.of(last.getStatus());
            }else {
                accreditationStoryRepository.save(AccreditationStory.builder()
                        .id(UUID.randomUUID())
                        .accreditation(Accreditation.builder().id(accreditationId).build())
                        .request(LocalDateTime.now())
                        .status(AccreditationStatus.EXPIRED).build());
                sendCommand.sendCommandAccreditationStatusChange(accreditationId,CommandStatus.CONFIRMED,AccreditationStatus.EXPIRED);
                return Optional.of(AccreditationStatus.EXPIRED);
            }
        }
        return Optional.empty();
    }

    public Optional<AccreditationStatus> confirmed(UUID accreditationId) {
       var accreditationStory =
               accreditationStoryRepository.findByAccreditationID(accreditationId.toString());

       if(accreditationStory != null && !accreditationStory.isEmpty()){
           var last = accreditationStory.get(accreditationStory.size() -1);
                if(AccreditationStatus.FAILED.equals(last.getStatus()) ||
                        AccreditationStatus.EXPIRED.equals(last.getStatus()) ||
                        AccreditationStatus.CONFIRMED.equals(last.getStatus())
                ){
                    sendCommand.sendCommandAccreditationStatusChange(accreditationId,CommandStatus.FAILED,AccreditationStatus.CONFIRMED);
                    return Optional.of(last.getStatus());
               }else {
                    accreditationStoryRepository.save(AccreditationStory.builder()
                            .id(UUID.randomUUID())
                            .accreditation(Accreditation.builder().id(accreditationId).build())
                            .request(LocalDateTime.now())
                            .status(AccreditationStatus.CONFIRMED).build());
                    sendCommand.sendCommandAccreditationStatusChange(accreditationId,CommandStatus.CONFIRMED,AccreditationStatus.CONFIRMED);
                    return Optional.of(AccreditationStatus.CONFIRMED);
                }
       }
       return Optional.empty();
    }

    public AccreditationResponseDTO historic(UUID userId) {
       return accreditationMapper.map(accreditationRepository.findAllByUserID(userId));
    }
}
