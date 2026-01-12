package com.fpmislata.banco_back.domain.repository.entity;

import java.util.Date;
import com.fpmislata.banco_back.domain.model.enums.MovementType;
import com.fpmislata.banco_back.domain.model.enums.OriginMovement;

public record AccountMovementEntity(
        Integer idAccountMovent,
        String creditCardOrigin,
        OriginMovement originMovement,
        Date date,
        Double amount,
        MovementType movementType,
        String concept) {
    public AccountMovementEntity(
            Integer idAccountMovent,
            String creditCardOrigin,
            OriginMovement originMovement,
            Date date,
            Double amount,
            MovementType movementType,
            String concept) {
        this.idAccountMovent = idAccountMovent;
        this.creditCardOrigin = creditCardOrigin;
        this.originMovement = originMovement;
        this.date = date;
        this.amount = amount;
        this.movementType = movementType;
        this.concept = concept;
    }
}
