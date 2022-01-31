package org.lucasnscr.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private String type;

    private Integer column;

    private Integer row;

    private String ref;

    private String status;

    @OneToOne
    private org.lucasnscr.model.User user;

    @OneToOne
    private org.lucasnscr.model.Payment payment;
}
