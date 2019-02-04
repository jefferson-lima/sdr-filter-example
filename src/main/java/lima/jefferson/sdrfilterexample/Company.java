package lima.jefferson.sdrfilterexample;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Company {

  @Id
  @GeneratedValue
  Long id;

  String name;
  String address;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  Department department;

}
