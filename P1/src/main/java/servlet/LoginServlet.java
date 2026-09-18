package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import model.Usuario;
import servicio.UsuarioService;

/**
 * @author dz
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

   
    private UsuarioService usuarioService;

    @Override
    public void init() {
        usuarioService = new UsuarioService();
    }

    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/Login/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

         
        String correo = request.getParameter("correo");
        String contraseña = request.getParameter("contraseña");

        try {

            Usuario usuario = usuarioService.autenticar(correo, contraseña);

            if (usuario == null) {

                request.setAttribute("error", "Correo o contraseña incorrectos");

                request.getRequestDispatcher("/Login/login.jsp").forward(request, response);

                return;
            }
 
            
            HttpSession session = request.getSession();

            session.setAttribute("correo", usuario.getCorreo());

            session.setAttribute("rol", usuario.getRol());

            
            response.sendRedirect(request.getContextPath() + "/menu");

        } catch (SQLException e) {

            throw new ServletException("Error al iniciar sesión",e);
        }  
    }
    
    
    
    
}
