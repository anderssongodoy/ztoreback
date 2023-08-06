package pe.idat.ztore.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.idat.ztore.model.enums.Status;

@Data
@Builder
@Entity
@SQLDelete(sql = "UPDATE user_ SET soft_delete = true WHERE id=?")
@Where(clause = "soft_delete = false")
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime date;
	private Double subtotal;
	
	@Column( name="soft_delete")
	private Boolean softDelete  = Boolean.FALSE;
	
	@Enumerated(EnumType.STRING)
    private Status status;
	
	@ManyToOne
    @JoinColumn(name = "id_customer", insertable = false, updatable = false)
    @JsonIgnore
    private UserEntity customer;

	
	
}
