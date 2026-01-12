package com.fpmislata.banco_back.domain.service.dto;

import java.util.Date;
import com.fpmislata.banco_back.domain.model.enums.MovementType;
import com.fpmislata.banco_back.domain.model.enums.OriginMovement;

public record AccountMovementDto(
        Integer idAccountMovement,
        String creditCardOrigin,
        OriginMovement originMovement,
        Date date,
        Double amount,
        MovementType movementType,
        String concept) {
}
