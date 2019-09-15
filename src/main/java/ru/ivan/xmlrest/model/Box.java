package ru.ivan.xmlrest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="BOX")
public class Box implements Serializable {

    @Id
    private Integer id;
    @Column (name = "CONTAINED_IN")
    private Integer parentId;
}
