package ch.tbz.client.backend.util;

public abstract class Validator {
    public static String validateRegister(String username, String pwd) {
        String msg = "";
        validateUsername(msg, username);
        validatePwd(msg, pwd);
        return msg;
    }

    private static void validatePwd(String msg, String pwd) {
        if(pwd.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")){

        }else{
            msg = "Password does not comply with the password rules";
        }
    }

    private static void validateUsername(String msg, String username) {
        if (username.matches("^[0-9a-zA-Z_]*$")) {
            if (username.length() >= 8) {

            } else {
                msg = "Username must be longer than 7 characters";
            }
        } else {
            msg = "Username can only contain a-Z, 0-9 and _";
        }
    }
}
