package com.ust.Expensive_application.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expensivedto {
    @NotNull
    private String name;
    @NotBlank(message = "type of transcations is mandatory")
    private String typetranscations;
    @Min(value = 1,message = "amount should be greater than 1")
    private double amount;
    @Size(min=5, max=300)
    @NotBlank(message = "payment method is mandatory")
    private String description;
    private String paymentType;

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public @NotBlank(message = "type of transcations is mandatory") String getTypetranscations() {
        return typetranscations;
    }

    public void setTypetranscations(@NotBlank(message = "type of transcations is mandatory") String typetranscations) {
        this.typetranscations = typetranscations;
    }

    @Min(value = 1, message = "amount should be greater than 1")
    public double getAmount() {
        return amount;
    }

    public void setAmount(@Min(value = 1, message = "amount should be greater than 1") double amount) {
        this.amount = amount;
    }

    public @Size(min = 5, max = 300) String getDescription() {
        return description;
    }

    public void setDescription(@Size(min = 5, max = 300) String description) {
        this.description = description;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
