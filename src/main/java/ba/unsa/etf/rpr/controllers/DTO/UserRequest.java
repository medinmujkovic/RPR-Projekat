package ba.unsa.etf.rpr.controllers.DTO;

import ba.unsa.etf.rpr.domain.Idable;

import java.io.Serializable;
import java.util.Date;

public record UserRequest (int id, String username, String password, String email, String fullName, Date dateOfBirth, String roles) implements Serializable {

    //Record used for all scenarios with user
}
