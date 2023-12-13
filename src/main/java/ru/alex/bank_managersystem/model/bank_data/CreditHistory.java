package ru.alex.bank_managersystem.model.bank_data;


import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
@Data
@Entity
@Table(name = "t_credit_history")
public class CreditHistory {

    @Id
    @Column(name = "credit_id")
    private String creditId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "credit_amount")
    private Double creditAmount;

    @Column(name = "interest_rate")
    private Double interestRate;

    @Column(name = "credit_date")
    private ZonedDateTime creditDate;

    @Column(name = "repayment_date")
    private ZonedDateTime repaymentDate;

    @Column(name = "status")
    private String status;

}
