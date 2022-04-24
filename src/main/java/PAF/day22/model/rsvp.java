package PAF.day22.model;

import java.util.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class rsvp {

    private String name;
    private String email;
    private String phone;
    private Date confirmation_date;
    private String comments;
    private Integer count;

    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
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
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Date getConfirmation_date() {
        return confirmation_date;
    }
    public void setConfirmation_date(Date confirmation_date) {
        this.confirmation_date = confirmation_date;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }

    public static rsvp create(SqlRowSet rs){

        rsvp rsvp = new rsvp();
        rsvp.setName(rs.getString("name"));
        rsvp.setEmail(rs.getString("email"));
        rsvp.setPhone(rs.getString("phone"));
        rsvp.setConfirmation_date(rs.getDate("confirmation_date"));
        rsvp.setComments(rs.getString("comments"));

        return rsvp;
    } 

    public static rsvp createCount(SqlRowSet rs){

        rsvp rsvp = new rsvp();
        rsvp.setCount(rs.getInt("count"));
        return rsvp;
    } 



    
}
