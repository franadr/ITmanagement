package models.entites.jpa;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name="customer")
public class Customer implements Serializable {

   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column (name="id", updatable=false, nullable = false)
   private Long id;

   @Column(name = "customername")
   private String name;
   
   private String email;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (id != null ? id.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      if (!(object instanceof Customer)) {
         return false;
      }
      Customer other = (Customer) object;
      return (this.id != null || other.id == null)
              && (this.id == null || this.id.equals(other.id));
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   @Override
   public String toString() {
      return "lu.uni.Customer[ id=" + id + " ]";
   }

}
