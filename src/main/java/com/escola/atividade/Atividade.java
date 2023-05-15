package com.escola.atividade;


import com.escola.aluno.Aluno;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "atividade")
public class Atividade extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String nome;
    public BigDecimal nota;
    @OneToOne
    public Aluno aluno;
}
