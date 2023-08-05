package pe.idat.ztore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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

@Data
@Entity
@Builder
@SQLDelete(sql = "UPDATE comment SET soft_delete = true WHERE id=?")
@Where(clause = "soft_delete = false")
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private Double precio;
	private String image;
	
	@Column( name="soft_delete")
	private Boolean softDelete  = Boolean.FALSE;
	
	@ManyToOne
    @JoinColumn(name = "id_category", insertable = false, updatable = false)
    @JsonIgnore
	private Category category;

	
	
}
