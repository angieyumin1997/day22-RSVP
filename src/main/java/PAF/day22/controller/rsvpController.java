package PAF.day22.controller;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import PAF.day22.model.rsvp;
import PAF.day22.repository.rsvpRepository;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;

@Controller
@RequestMapping(path = "/api")
public class rsvpController {
    @Autowired
    private rsvpRepository rsvpRepository;

    @PostMapping(path=("/rsvp"), consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView add(@RequestBody MultiValueMap<String, String> form) {

        String name = form.getFirst("name");
        String phone = form.getFirst("phone");
        String email = form.getFirst("email");
        String confirmation_date = form.getFirst("confirmation_date");
        String comments = form.getFirst("comments");

        rsvp rsvp = new rsvp();
        rsvp = rsvpRepository.checkExistingEmail(email);

        if(rsvp.getCount()==0){
        rsvp.setName(name);
        rsvp.setPhone(phone);
        rsvp.setEmail(email);
        rsvp.setComments(comments);
        Date sqlDate = Date.valueOf(confirmation_date);
        rsvp.setConfirmation_date(sqlDate);
        rsvpRepository.add(rsvp);
        }else{
        rsvp.setName(name);
        rsvp.setPhone(phone);
        rsvp.setEmail(email);
        rsvp.setComments(comments);
        Date sqlDate = Date.valueOf(confirmation_date);
        rsvp.setConfirmation_date(sqlDate);
        rsvpRepository.update(rsvp);
        }

        ModelAndView mvc = new ModelAndView();
        mvc.setStatus(HttpStatus.CREATED);
        mvc.addObject("rsvp", rsvp);
        mvc.setViewName("added");

        return mvc;

    }

    @GetMapping(path="/rsvps", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllCustomers(Model model){
        
        List<rsvp> rsvps = new LinkedList<>();
        rsvps = rsvpRepository.getAllRSVPs();

        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        
        for(rsvp r: rsvps){
            builder.add("name",r.getName())
            .add("email",r.getEmail())
            .add("phone",r.getPhone())
            .add("confirmation_date",r.getConfirmation_date().toString())
            .add("comments",r.getComments());
            arrBuilder.add(builder.build());
        }

        return ResponseEntity.ok(arrBuilder.build().toString());
    }

    @GetMapping(path="/rsvps/count", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCount(Model model){
        rsvp rsvp1 = new rsvp();
        rsvp1 = rsvpRepository.getRSVPsCount();

        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("count",rsvp1.getCount());
         
        return ResponseEntity.status(HttpStatus.CREATED).body(builder.build().toString());
    }

    @GetMapping(path="/rsvp")
    public ResponseEntity<String> search(
        @RequestParam(name="q") String name,
        Model model){
            
            Optional<List<rsvp>> opt = rsvpRepository.getRSVP(name);
            JsonObjectBuilder builder = Json.createObjectBuilder();
            JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
            System.out.printf("++++++ OPTIONALRSVPSLIST: %s\n", opt);
            if (opt.isEmpty()){
            return ResponseEntity.status(404)
                .body(
                    builder.add("error","not found: %s".formatted(name))
                        .build().toString()
                );
            }

            List<rsvp> rsvps = opt.get();
            System.out.printf("++++++ RSVPSLIST: %s\n", rsvps);
            for(rsvp r: rsvps){
            builder.add("name",r.getName())
            .add("email",r.getEmail())
            .add("phone",r.getPhone())
            .add("confirmation_date",r.getConfirmation_date().toString())
            .add("comments",r.getComments());
            arrBuilder.add(builder.build());
            }
            return ResponseEntity.ok(arrBuilder.build().toString());
    }

}
