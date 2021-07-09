package ch.tbz.client.backend.util;

public abstract class Validator {
    public static String msg;

    public static String validateRegister(String username, String pwd) {
        msg = "";
        validateUsername(username);
        validatePwd(pwd);
        return msg;
    }

    private static void validatePwd(String pwd) {
        if(!pwd.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")){
            msg = "Password does not comply with the password rules";
        }
    }

    private static void validateUsername(String username) {
        if (username.matches("^[0-9a-zA-Z_]*$")) {
            if (username.length() < 4) {
                msg = "Username must be longer than 3 characters";
            }
        } else {
            msg = "Username can only contain a-Z, 0-9 and _";
        }
    }
}
