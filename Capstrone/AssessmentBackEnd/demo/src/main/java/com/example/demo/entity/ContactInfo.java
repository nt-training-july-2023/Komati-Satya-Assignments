package com.example.demo.entity;

import java.util.Objects;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class ContactInfo {
    /**
     * stores the user email.
     */
    private String email;
    /**
     * stores the user phone number.
     */
    private String phoneNumber;
    /**
     * all args constructor.
     * @param useremail email
     * @param phonenumber phone number
     */
    public ContactInfo(final String useremail,
            final String phonenumber) {
        super();
        this.email = useremail;
        this.phoneNumber = phonenumber;
    }
    /**
     * hash code method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(email, phoneNumber);
    }
    /**
     * equals method.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ContactInfo other = (ContactInfo) obj;
        return Objects.equals(email, other.email)
                && Objects.equals(phoneNumber, other.phoneNumber);
    }
     /**
      * get email.
      * @return email
      */
    public String getEmail() {
        return email;
    }
     /**
      * set email.
      * @param useremail
      */
    public void setEmail(final String useremail) {
        this.email = useremail;
    }
    /**
     * get phone number.
     * @return phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
   /**
    * set phone number.
    * @param phonenumber phone number
    */
    public void setPhoneNumber(final String phonenumber) {
        this.phoneNumber = phonenumber;
    }
}
