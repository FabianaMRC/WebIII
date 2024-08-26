package br.edu.ifpr.ifprstore.controllers;

import br.edu.ifpr.ifprstore.models.Seller;
import br.edu.ifpr.ifprstore.repositories.SellerRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"", "/sellers"})
public class SellersController extends HttpServlet {

    private SellerRepository repository;

    public SellersController() {
        repository = new SellerRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        List<Seller> sellers = repository.getSellers();

        RequestDispatcher dispatcher = request.getRequestDispatcher("/sellers.jsp");
        request.setAttribute("sellers", sellers);

        dispatcher.forward(request, resp);
        }

}







