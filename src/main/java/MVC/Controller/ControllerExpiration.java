package MVC.Controller;

import MVC.Dao.All_op_timer;
import MVC.Dao.operationE;
import MVC.Model.ExpirationEntity;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ControllerExpiration", value = "/ControllerExpiration")
public class ControllerExpiration extends HttpServlet {
    private String dateExpiration;
    private int id, days;
    private operationE  operation_expiration;
    private RequestDispatcher dispatcher;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        operation_expiration = new  operationE();
        days=Integer.parseInt(request.getParameter("dateExpiration"));
        dateExpiration = All_op_timer.add_some_days_to_my_datetime(days);
        ExpirationEntity expiration = new   ExpirationEntity(dateExpiration);
        expiration.setDateExpiration(dateExpiration);

        operation_expiration.add_date_expiration(expiration);
        dispatcher= request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);

    }
}
