package daisonp.postgres.containers.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ColumnTransformer;

@Entity
@Table(name = "customer", schema="psql")
public class Customer  {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name="first_name", nullable=false, length=50)
    private String firstName;

    @NotBlank
    @Column(name="last_name", nullable=false, length=50)
    private String lastName;
    
    @Column(name="sort_code", nullable=false)
    @ColumnTransformer(read = "pgp_sym_decrypt(sort_code::bytea, 'secret_password')", write = "pgp_sym_encrypt(?, 'secret_password')")
    private String sortCode;
    
    @Column(name="account_number", nullable=false)
    @ColumnTransformer(read = "pgp_sym_decrypt(account_number::bytea, 'secret_password')", write = "pgp_sym_encrypt(?, 'secret_password')")
    private String account_number;

    /**
     * @return the id
     */
    public Long getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * @return the sortCode
     */
    public String getSortCode()
    {
        return sortCode;
    }

    /**
     * @param sortCode the sortCode to set
     */
    public void setSortCode(String sortCode)
    {
        this.sortCode = sortCode;
    }

    /**
     * @return the account_number
     */
    public String getAccount_number()
    {
        return account_number;
    }

    /**
     * @param account_number the account_number to set
     */
    public void setAccount_number(String account_number)
    {
        this.account_number = account_number;
    }

    
}