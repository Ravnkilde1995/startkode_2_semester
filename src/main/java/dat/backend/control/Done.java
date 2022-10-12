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
import java.util.List;

@WebServlet(name = "Done", value = "/done")
public class Done extends HttpServlet {

    private static  ConnectionPool connectionPool = ApplicationStart.getConnectionPool();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int item_id = Integer.parseInt(request.getParameter("item_id"));


        // todo: flip done bit

        ItemFacade.toggleDone(item_id, connectionPool);

        List<Item> itemList = ItemFacade.getItems(connectionPool);
        request.setAttribute("itemList", itemList);
        request.getRequestDispatcher("WEB-INF/welcome.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
