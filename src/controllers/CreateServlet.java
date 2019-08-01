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
 * Servlet implementation class CreateServlet
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = (String)request.getParameter("_token");
        if(token != null && token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            Message message = new Message();

            String title = request.getParameter("title");
            message.setTitle(title);

            String content = request.getParameter("content");
            message.setContent(content);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            message.setCreated_at(timestamp);
            message.setUpdated_at(timestamp);

            em.getTransaction().begin();
            em.persist(message);
            em.getTransaction().commit();
            em.close();

            response.sendRedirect(request.getContextPath() + "/index");
        }
    }

}
