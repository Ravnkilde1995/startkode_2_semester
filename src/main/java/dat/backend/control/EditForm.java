package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Item;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ItemFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "Editform", value = "/editform")
public class EditForm extends HttpServlet {

   private static ConnectionPool connectionPool = ApplicationStart.getConnectionPool();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int item_id = Integer.parseInt(request.getParameter("item_id"));
        Item item = ItemFacade.getItemById(item_id, connectionPool);
        request.setAttribute("item", item);
        request.getRequestDispatcher("WEB-INF/edititem.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
