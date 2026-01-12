package com.fpmislata.banco_back.controller.webModel.request;

import com.fpmislata.banco_back.domain.model.enums.MovementType;
import com.fpmislata.banco_back.domain.model.enums.OriginMovement;
import java.sql.Date;

public record AccountMovementInsertRequest(
        String creditCardOrigin,
        OriginMovement originMovement,
        Date date,
        Double amount,
        MovementType movementType,
        String concept) {
}
