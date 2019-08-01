package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;
import utils.DBUtil;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/new")
public class NewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManagerFactory();
        em.getTransaction().begin();

        Message message = new Message();

        String title = "taro";
        message.setTitle(title);

        String content = "hello";
        message.setContent(content);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        message.setUpdated_at(timestamp);
        message.setCreated_at(timestamp);

        em.persist(message);
        em.getTransaction().commit();

        response.getWriter().append(Integer.valueOf(message.getId()).toString());

        em.close();

    }

}
