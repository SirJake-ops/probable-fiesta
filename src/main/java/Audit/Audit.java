package Audit;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import shared.common.entities.BaseEntity;

@Entity
@Table(name = "audit")
public class Audit extends BaseEntity {

}
