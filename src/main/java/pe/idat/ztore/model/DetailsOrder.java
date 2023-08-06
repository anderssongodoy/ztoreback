package pe.idat.ztore.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@SQLDelete(sql = "UPDATE user_ SET soft_delete = true WHERE id=?")
@Where(clause = "soft_delete = false")
@AllArgsConstructor
@NoArgsConstructor
public class DetailsOrder {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	private int amount;
	
	@ManyToOne
    @JoinColumn(name = "id_order", insertable = false, updatable = false)
	private Order order;
	
	@ManyToOne
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
	private Product product;

	
	
}
