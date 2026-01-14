package com.fpmislata.banco_back.persistence.dao.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import com.fpmislata.banco_back.domain.model.enums.MovementType;
import com.fpmislata.banco_back.domain.model.enums.OriginMovement;

@Entity
@Table(name = "accountmovement")
public class AccountMovementJpaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_account_movement")
    private Integer idAccountMovement;
    @Column(name = "credit_card_origin")
    private String creditCardOrigin;
    @Enumerated(EnumType.STRING)
    @Column(name = "origin_movement")
    private OriginMovement originMovement;
    @Column(name = "date")
    private Date date;
    @Column(name = "amount")
    private Double amount;
    @Enumerated(EnumType.STRING)
    @Column(name = "movement_type")
    private MovementType movementType;
    @Column(name = "concept")
    private String concept;

    public AccountMovementJpaEntity() {
    }

    public AccountMovementJpaEntity(Integer idAccountMovement, String creditCardOrigin, OriginMovement originMovement,
            Date date, Double amount, MovementType movementType, String concept) {
        this.idAccountMovement = idAccountMovement;
        this.creditCardOrigin = creditCardOrigin;
        this.originMovement = originMovement;
        this.date = date;
        this.amount = amount;
        this.movementType = movementType;
        this.concept = concept;
    }

    public Integer getIdAccountMovement() {
        return idAccountMovement;
    }

    public void setIdAccountMovement(Integer idAccountMovement) {
        this.idAccountMovement = idAccountMovement;
    }

    public String getCreditCardOrigin() {
        return creditCardOrigin;
    }

    public void setCreditCardOrigin(String creditCardOrigin) {
        this.creditCardOrigin = creditCardOrigin;
    }

    public OriginMovement getOriginMovement() {
        return originMovement;
    }

    public void setOriginMovement(OriginMovement originMovement) {
        this.originMovement = originMovement;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }
}
