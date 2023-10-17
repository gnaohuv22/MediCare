/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import java.util.regex.Pattern;
/**
 *
 * @author DELL
 */
public class AdminException {
    

     static class LengthException extends java.lang.Exception {
        String msg;
        public LengthException(int a, int b) {
//            msg = "Input must be " + a + " to " + b +" characters long";
            msg = "Giá trị phải có độ dài từ " + a + " tới " + b +" kí tự";
        }
        public String getMessage(){
            return msg;
        }
    }

    static class LackLengthException extends java.lang.Exception  {
        String msg;
        public LackLengthException(int i) {
//            msg = "Input must have "+ i +" characters long";
            msg = "Giá trị phải có độ dài "+ i +" kí tự";
        }
        public String getMessage(){
            return msg;
        }
    }

    static class EmailValidation extends java.lang.Exception {

        public EmailValidation() {
        }
        public static boolean patternMatches(String emailAddress, String regexPattern) {
            return Pattern.compile(regexPattern)
            .matcher(emailAddress)
            .matches();
        }
        public static String getMessage(String msg1, String exPattern){
            return msg1 + "Example: " + exPattern;
        }
    }

    static class DuplicateException extends java.lang.Exception {
        String msg;
        public DuplicateException() {
//            msg = "aready exist in Database";
              msg = "Đã tồn tại!";
        }
        public String getMessage(){
            return msg;
        }
    }

    static class EmptyStringException extends java.lang.Exception {

        public EmptyStringException() {
        }
        public String getMessage(){
//            return "The input must not be empty";
            return "Giá trị nhập vào trống";
        }
    }

    static class NotMatchPasswordExcepton extends java.lang.Exception {

        public NotMatchPasswordExcepton() {
        }
        public String getMessage(){
//            return "This password did't match";
            return "Mật khẩu không khớp";
        }
    }

    static class PasswordNotChange extends java.lang.Exception {

        public PasswordNotChange() {
        }
        
        public String getMessage(){
//            return "This password did't match";
            return "Mật khẩu mới phải khác mật khẩu cũ";
        }
    }

    static class BirthDateException extends java.lang.Exception {

        public BirthDateException() {
        }
        
        public String getMessage(){
//            return "The birthdate is invalid";
            return "Ngày sinh không hợp lệ";
        }
    }

    static class RedirecUrlException extends Exception {

        public RedirecUrlException() {
        }
    }

    static class NotHaveRole extends Exception {
        String msg;
        public NotHaveRole(String msg) {
            this.msg=" "+msg;
        }
        public String getMessage(){
            return "Bạn không có quyền để thực hiện hành động này"+msg;
        }
    }
}
