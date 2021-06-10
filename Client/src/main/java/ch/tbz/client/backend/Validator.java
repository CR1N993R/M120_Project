package ch.tbz.client.backend;

public class Validator {
    public String validateRegister(String username, String email, String pwd) {
        String msg = "";
        validateUsername(msg, username);
        validateEmail(msg, email);
        validatePwd(msg, pwd);
        return msg;
    }

    private void validatePwd(String msg, String pwd) {
        if(pwd.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")){

        }else{
            msg = "Password does not comply with the password rules";
        }
    }

    private void validateEmail(String msg, String email) {
        if (email.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$") || email.equals("")) {
            if (/*Bereits ein Account mit dieser Email existiert*/true) {

            } else {
                msg = "There's already an Account with this email";
            }
        } else {
            msg = "Invalid Email";
        }
    }

    private void validateUsername(String msg, String username) {
        if (username.matches("^[0-9a-zA-Z_]*$")) {
            if (username.length() >= 8) {
                if (/*Username bereits vergeben ist*/true) {

                } else {
                    msg = "Username is already taken";
                }
            } else {
                msg = "Username must be longer than 7 characters";
            }
        } else {
            msg = "Username can only contain a-Z, 0-9 and _";
        }
    }
}
