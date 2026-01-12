package com.fpmislata.banco_back.domain.service.dto;

import java.util.Date;
import com.fpmislata.banco_back.domain.model.enums.MovementType;
import com.fpmislata.banco_back.domain.model.enums.OriginMovement;
import jakarta.validation.constraints.NotNull;

public record AccountMovementDto(
        @NotNull Integer idAccountMovement,
        @NotNull String creditCardOrigin,
        @NotNull OriginMovement originMovement,
        @NotNull Date date,
        @NotNull Double amount,
        @NotNull MovementType movementType,
         String concept) {
}
