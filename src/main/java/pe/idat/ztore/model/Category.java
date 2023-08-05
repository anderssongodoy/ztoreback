package pe.idat.ztore.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Builder
@SQLDelete(sql = "UPDATE comment SET soft_delete = true WHERE id=?")
@Where(clause = "soft_delete = false")
@AllArgsConstructor
@NoArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String description;
	private String name;
	
	@Column( name="soft_delete")
	private Boolean softDelete  = Boolean.FALSE;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;
	
}
