package DAL;

import java.util.Date;
import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author tranh
 */
public class AdminEmailContext {

    static String from = "thunvhe176252@fpt.edu.vn";
    static String password = "egej dvml estl papk";
//    static String password = "TEST";

    public static void sendEmailForgotPassword(String mail,String newPassword) {
//        EmployeeDAO eDAO = new EmployeeDAO();
        String to = mail;
        String subject = "Xin chào " + mail;

        String mailContent = "Mật khẩu mới của bạn là: "+newPassword;

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.prot", "465");

        // Create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.addHeader("Content-type", "text/html;charset=UTF-8");
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject(subject, "text/html;charset=UTF-8");
            msg.setSentDate(new Date());
            msg.setContent(mailContent, "text/html;charset=UTF-8");
            Transport.send(msg);
        } catch (MessagingException e) {
        }
    }
    public static void sendEmailnewPassword(String mail,String newPassword,String name) {
        String to = mail;
        String subject = "Chào "+name;

        String mailContent = "Chúng tôi vừa tạo tài khoản mới cho bạn.<br>";
        mailContent += "Hãy đăng nhập vào hệ thống và đổi mật khẩu của bạn bằng tài khoản sau:<br>";
        mailContent += "Tài khoản: "+mail+"<br>";
        mailContent += "Mật khẩu: "+newPassword+"<br>";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.prot", "465");

        // Create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.addHeader("Content-type", "text/HTML; charset = UTF-8");
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setContent(mailContent, "text/html;charset=UTF-8");
            Transport.send(msg);
        } catch (MessagingException e) {
        }
    }
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String NUMBERS = "0123456789";
    public String generatePassword(int length){
        Random random = new Random();
        String newPassword = ""+CHARACTERS.charAt(random.nextInt(CHARACTERS.length()))
                +CHARACTERS.charAt(random.nextInt(CHARACTERS.length()))
                +CHARACTERS.charAt(random.nextInt(CHARACTERS.length()))
                +CHARACTERS.charAt(random.nextInt(CHARACTERS.length()))
                +CHARACTERS.charAt(random.nextInt(CHARACTERS.length()))
                +CHARACTERS.charAt(random.nextInt(CHARACTERS.length()));
        return newPassword;
    }

    public static String generateRandomVerificationCode(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(NUMBERS.length());
            char randomChar = NUMBERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
    
    public static boolean sendVerificationCodeToEmail(String email, String code) {
        String subject = "[MediCare] Verification code";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.prot", "465");

        // Create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.addHeader("Content-type", "text/HTML; charset = UTF-8");
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setContent("<table style=\"font-family:'Open Sans',sans-serif\" width=\"100%\" border=\"0\">\n"
                    + "        <tbody>\n"
                    + "            <tr>\n"
                    + "                <td style=\"word-break:break-word;padding:28px 33px 25px;font-family:'Open Sans',sans-serif\"\n"
                    + "                    align=\"left\">\n"
                    + "                    <div>\n"
                    + "                        <p style=\"font-size:14px;line-height:200%;\">\n"
                    + "                            Hi <strong>" + email + "</strong>, here is your verification code: </p>\n"
                    + "                        <p style=\"font-size:14px;line-height:200%;\">\n"
                    + "                            <strong>" + code + "</strong></p>\n"
                    + "                        <p style=\"font-size:14px;line-height:200%;\">\n"
                    + "                            Any questions please contact: <a href=\"mailto:medicare@gmail.com\"\n"
                    + "                                target=\"_blank\">medicare@gmail.com</a> to\n"
                    + "                            be answered.</p>\n"
                    + "                    </div>\n"
                    + "                </td>\n"
                    + "            </tr>\n"
                    + "        </tbody>\n"
                    + "    </table>", "text/html;charset=UTF-8");
            Transport.send(msg);
            return true;
        } catch (MessagingException e) {
        }
        return false;
    }
//    public static String generateRandomVerificationCode(int length) {
//        Random random = new Random();
//        StringBuilder sb = new StringBuilder(length);
//        for (int i = 0; i < length; i++) {
//            int randomIndex = random.nextInt(NUMBERS.length());
//            char randomChar = NUMBERS.charAt(randomIndex);
//            sb.append(randomChar);
//        }
//        return sb.toString();
//    }

//    public static boolean sendVerificationCode(String username, String code) {
//        UsersDAO usersDAO = new UsersDAO();
//        Users user = usersDAO.searchUserByUsername(username);
//        String to = user.getEmail();
//        String subject = "[ZSHOP] Verification code";
//
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.prot", "465");
//
//        // Create Authenticator
//        Authenticator auth = new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(from, password);
//            }
//        };
//
//        Session session = Session.getInstance(props, auth);
//        MimeMessage msg = new MimeMessage(session);
//        try {
//            msg.addHeader("Content-type", "text/HTML; charset = UTF-8");
//            msg.setFrom(new InternetAddress(from));
//            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
//            msg.setSubject(subject);
//            msg.setSentDate(new Date());
//            msg.setContent("<table style=\"font-family:'Open Sans',sans-serif\" width=\"100%\" border=\"0\">\n"
//                    + "        <tbody>\n"
//                    + "            <tr>\n"
//                    + "                <td style=\"word-break:break-word;padding:28px 33px 25px;font-family:'Open Sans',sans-serif\"\n"
//                    + "                    align=\"left\">\n"
//                    + "                    <div>\n"
//                    + "                        <p style=\"font-size:14px;line-height:200%;\">\n"
//                    + "                            Hi <strong>" + username + "</strong>, here is your verification code: </p>\n"
//                    + "                        <p style=\"font-size:14px;line-height:200%;\">\n"
//                    + "                            <strong>" + code + "</strong></p>\n"
//                    + "                        <p style=\"font-size:14px;line-height:200%;\">\n"
//                    + "                            Any questions please contact: <a href=\"mailto:hieptvhe173252@gmail.com\"\n"
//                    + "                                target=\"_blank\">hieptvhe173252@gmail.com</a> to\n"
//                    + "                            be answered.</p>\n"
//                    + "                    </div>\n"
//                    + "                </td>\n"
//                    + "            </tr>\n"
//                    + "        </tbody>\n"
//                    + "    </table>", "text/html;charset=UTF-8");
//            Transport.send(msg);
//            return true;
//        } catch (MessagingException e) {
//        }
//        return false;
//    }
//
//    public static boolean sendVerificationCodeToEmail(String email, String code) {
//        String subject = "[ZSHOP] Verification code";
//
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.prot", "465");
//
//        // Create Authenticator
//        Authenticator auth = new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(from, password);
//            }
//        };
//
//        Session session = Session.getInstance(props, auth);
//        MimeMessage msg = new MimeMessage(session);
//        try {
//            msg.addHeader("Content-type", "text/HTML; charset = UTF-8");
//            msg.setFrom(new InternetAddress(from));
//            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
//            msg.setSubject(subject);
//            msg.setSentDate(new Date());
//            msg.setContent("<table style=\"font-family:'Open Sans',sans-serif\" width=\"100%\" border=\"0\">\n"
//                    + "        <tbody>\n"
//                    + "            <tr>\n"
//                    + "                <td style=\"word-break:break-word;padding:28px 33px 25px;font-family:'Open Sans',sans-serif\"\n"
//                    + "                    align=\"left\">\n"
//                    + "                    <div>\n"
//                    + "                        <p style=\"font-size:14px;line-height:200%;\">\n"
//                    + "                            Hi <strong>" + email + "</strong>, here is your verification code: </p>\n"
//                    + "                        <p style=\"font-size:14px;line-height:200%;\">\n"
//                    + "                            <strong>" + code + "</strong></p>\n"
//                    + "                        <p style=\"font-size:14px;line-height:200%;\">\n"
//                    + "                            Any questions please contact: <a href=\"mailto:hieptvhe173252@gmail.com\"\n"
//                    + "                                target=\"_blank\">hieptvhe173252@gmail.com</a> to\n"
//                    + "                            be answered.</p>\n"
//                    + "                    </div>\n"
//                    + "                </td>\n"
//                    + "            </tr>\n"
//                    + "        </tbody>\n"
//                    + "    </table>", "text/html;charset=UTF-8");
//            Transport.send(msg);
//            return true;
//        } catch (MessagingException e) {
//        }
//        return false;
//    }

//    public static void main(String[] args) {
//        AdminEmailContext context = new AdminEmailContext();
//        StockDAO stockDAO = new StockDAO();
//        String captcha = generateRandomVerificationCode(6);
//        System.out.println(captcha);
//    }

}
