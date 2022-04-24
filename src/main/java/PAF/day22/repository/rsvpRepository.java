package PAF.day22.repository;

import org.springframework.stereotype.Repository;

import PAF.day22.model.rsvp;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

@Repository
public class rsvpRepository implements Queries{
    
    @Autowired 
    private JdbcTemplate template;

    public boolean add(final rsvp rsvp){
        int added = template.update(SQL_INSERT,
        rsvp.getName(),
        rsvp.getPhone(),
        rsvp.getEmail(),
        rsvp.getConfirmation_date(),
        rsvp.getComments());

        return added > 0;
    }

    public boolean update(final rsvp rsvp){
        int added = template.update(SQL_UPDATE,
        rsvp.getName(),
        rsvp.getPhone(),
        rsvp.getEmail(),
        rsvp.getConfirmation_date(),
        rsvp.getComments(),
        rsvp.getEmail());

        return added > 0;
    }

    public rsvp checkExistingEmail(String email){
        
        final SqlRowSet result = template.queryForRowSet(SQL_EXISTING_RECORD,
        email);
        rsvp rsvp1=new rsvp();
        while (result.next()){
            rsvp1 = rsvp.createCount(result);
        }
        return rsvp1;
        
    }
    
    public List<rsvp> getAllRSVPs(){
        final List<rsvp> rsvps = new LinkedList<>();

        final SqlRowSet result = template.queryForRowSet(SQL_SELECT_ALL);

        while (result.next()){
            rsvp rsvp1 = rsvp.create(result);
            rsvps.add(rsvp1);
        }

        return rsvps;
    }

    public rsvp getRSVPsCount(){
        final SqlRowSet result= template.queryForRowSet(SQL_SELECT_COUNT);
        rsvp rsvp1=new rsvp();
        while (result.next()){
            rsvp1 = rsvp.createCount(result);
        }
        return rsvp1;
    }

    public Optional<List<rsvp>> getRSVP(String name){
        final SqlRowSet result= template.queryForRowSet(SQL_SELECT_RSVP,name);
        final List<rsvp> rsvps = new LinkedList<>();
        rsvp rsvp1=new rsvp();
        if(result.next()){
             do {
                rsvp1 = rsvp.create(result);
                rsvps.add(rsvp1);
        }while(result.next()); }else{
            return Optional.empty();
        }
       
        return Optional.of(rsvps);
    }

    


}
