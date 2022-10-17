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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ToggleItem" , value = "/toggleItem")
public class ToggleItem extends HttpServlet {

    private static ConnectionPool connectionPool = ApplicationStart.getConnectionPool();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int item_id = Integer.parseInt(request.getParameter("item_id"));

        ItemFacade.toggleItem(item_id, connectionPool);
        List<Item> itemList = ItemFacade.getItems(connectionPool);
        request.setAttribute("itemList", itemList);

        request.getRequestDispatcher("WEB-INF/welcome.jsp").forward(request,response);


        /*String sql = "UPDATE item SET done = (1- done) WHERE item_id = ? ";

        try (Connection connection = connectionPool.getConnection()) {

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, item_id);
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
