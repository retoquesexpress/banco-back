package com.fpmislata.banco_back.domain.model;

import java.util.Date;

public class AccountMovement {
    private Integer idAccountMovement;
    private String creditCardOrigin;
    private String originMovement;
    private Date date;
    private Double amount;
    private String movementType;
    private String concept;

    public AccountMovement() {
    }

    public AccountMovement(Integer idAccountMovement, String creditCardOrigin, String originMovement, Date date, Double amount, String movementType, String concept) {
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

    public String getOriginMovement() {
        return originMovement;
    }

    public void setOriginMovement(String originMovement) {
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

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }
}
