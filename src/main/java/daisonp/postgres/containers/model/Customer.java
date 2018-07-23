package daisonp.postgres.containers.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "customer", schema="test")
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
    private String sortCode;
    
    @Column(name="account_number", nullable=false)
    private String accountNumber;

    /**
     * @return the id
     */
    public Long getId()
    {
        return id;
    }

    /**
     * @return
     */
    private String getPassword()
    {
        // TODO Auto-generated method stub
        return null;
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
    public String getAccountNumber()
    {
        return accountNumber;
    }

    /**
     * @param account_number the account_number to set
     */
    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    
}