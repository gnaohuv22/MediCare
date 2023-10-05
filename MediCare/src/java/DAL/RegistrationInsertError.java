/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.io.Serializable;

/**
 *
 * @author Teacher
 */
public class RegistrationInsertError implements Serializable {
    private String usernameLengthErr;
    private String passwordLengthErr;
    private String confirmNotMatch;
    private String usernameIsExited;

    public String getUsernameLengthErr() {
        return usernameLengthErr;
    }

    public void setUsernameLengthErr(String usernameLengthErr) {
        this.usernameLengthErr = usernameLengthErr;
    }

    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    public String getConfirmNotMatch() {
        return confirmNotMatch;
    }

    public void setConfirmNotMatch(String confirmNotMatch) {
        this.confirmNotMatch = confirmNotMatch;
    }

    public String getUsernameIsExited() {
        return usernameIsExited;
    }

    public void setUsernameIsExited(String usernameIsExited) {
        this.usernameIsExited = usernameIsExited;
    }
    
}
