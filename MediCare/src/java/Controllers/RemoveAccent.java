/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import DAL.NewsDAO;
import Models.News;
import java.text.Normalizer;

/**
 *
 * @author hoang
 */
public class RemoveAccent {

    public static String removeAccent(String s) {
        String nonAccent = Normalizer.normalize(s, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return nonAccent.toLowerCase()
                .replaceAll("[^\\w-]+", "-")
                .replaceAll("-+", "-")
                .replaceAll("^-|-$", "");
    }

    public static void main(String[] args) {
        NewsDAO nd = new NewsDAO();
        News n = nd.getNewsById("51111");
        System.out.println(removeAccent(n.getTitle()).replaceAll(" ", "-"));
    }
}