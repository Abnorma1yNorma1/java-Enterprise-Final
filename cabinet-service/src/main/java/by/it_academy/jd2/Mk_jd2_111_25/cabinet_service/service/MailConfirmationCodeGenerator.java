package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.service;

import java.security.SecureRandom;

public class MailConfirmationCodeGenerator {

    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom random = new SecureRandom();

    public static String generate(int length){
        StringBuilder code = new StringBuilder();
        for (int i = 0; i<length; i++){
            int index = random.nextInt(CHARS.length());
            code.append(CHARS.charAt(index));
        }
        return code.toString();
    }
}
