package com.fpmislata.banco_back.controller.webModel.response;

import com.fpmislata.banco_back.domain.model.enums.MovementType;
import com.fpmislata.banco_back.domain.model.enums.OriginMovement;
import java.util.Date;

public record AccountMovementDetailResponse(
        Integer idAccountMovement,
        String creditCardOrigin,
        OriginMovement originMovement,
        Date date,
        Double amount,
        MovementType movementType,
        String concept) {
}
