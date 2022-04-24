package PAF.day22.repository;

public interface Queries {
    public static final String SQL_INSERT = 
    "insert into rsvp(name, phone,email,confirmation_date,comments) values (?,?,?,?,?)";

    public static final String SQL_SELECT_ALL = 
    "select * from rsvp";

    public static final String SQL_SELECT_COUNT = 
    "select count(*) as count from rsvp";

    public static final String SQL_SELECT_RSVP = 
    "select * from rsvp where name like concat('%',?,'%')";

    public static final String SQL_UPDATE=
    "update rsvp set name=?,phone=?,email=?,confirmation_date=?,comments=? where email=?";

    public static final String SQL_EXISTING_RECORD=
    "select count(*) as count from rsvp where email=?";

}
