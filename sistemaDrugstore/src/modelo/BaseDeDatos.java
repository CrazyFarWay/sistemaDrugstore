package modelo;

import java.sql.*;
import java.util.ArrayList;

public class BaseDeDatos {

    Connection conexion;
    String url = "jdbc:mysql://localhost/drugstore";
    String usuario = "root";
    String contraseña = "";

    public BaseDeDatos() {

        try {

            conexion = DriverManager.getConnection(url, usuario, contraseña);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void agregarProducto(Producto producto) {

        try {
            PreparedStatement statement = conexion.prepareStatement("insert into productos (nombre, marca, rubro, precio, cantidad) values (?, ?, ?, ?, ?)");

            statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getMarca());
            statement.setString(3, producto.getRubro());
            statement.setDouble(4, producto.getPrecio());
            statement.setInt(5, producto.getCantidad());

            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void eliminarProducto(int id) {
        Statement statement;

        try {
            statement = conexion.createStatement();
            statement.executeUpdate("delete from productos where id=" + id);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void modificarProducto(Producto producto) {

        try {
            PreparedStatement statement = conexion.prepareStatement("update productos set nombre=?, marca=?, rubro=?, precio=?, cantidad=? where id=?");

            statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getMarca());
            statement.setString(3, producto.getRubro());
            statement.setDouble(4, producto.getPrecio());
            statement.setInt(5, producto.getCantidad());
            statement.setInt(6, producto.getId());

            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> productos = new ArrayList<>();

        try {
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery("select * from productos order by id");

            while (resultado.next()) {
                Producto producto = new Producto(resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getString("marca"),
                        resultado.getString("rubro"),
                        resultado.getDouble("precio"),
                        resultado.getInt("cantidad"));

                productos.add(producto);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return productos;

    }

    public void agregarProveedor(Proveedor proveedor) {

        try {
            PreparedStatement statement = conexion.prepareStatement("insert into proveedores (nombre, rubro, telefono, correoElectronico, direccion) values (?, ?, ?, ?, ?)");

            statement.setString(1, proveedor.getNombre());
            statement.setString(2, proveedor.getRubro());
            statement.setString(3, proveedor.getTelefono());
            statement.setString(4, proveedor.getCorreoElectronico());
            statement.setString(5, proveedor.getDireccion());

            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void eliminarProveedor(int codigo) {
        PreparedStatement statement;

        try {
            statement = conexion.prepareStatement("DELETE FROM PROVEEDORES WHERE codigo = ?");
            statement.setInt(1, codigo);
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void modificarProveedor(Proveedor proveedor) {

        try {
            PreparedStatement statement = conexion.prepareStatement("update proveedores set nombre=?, rubro=?, telefono=?, correoElectronico=?, direccion=? where codigo=?");

            statement.setString(1, proveedor.getNombre());
            statement.setString(2, proveedor.getRubro());
            statement.setString(3, proveedor.getTelefono());
            statement.setString(4, proveedor.getCorreoElectronico());
            statement.setString(5, proveedor.getDireccion());
            statement.setInt(6, proveedor.getCodigo());

            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Proveedor> obtenerProveedores() {

        ArrayList<Proveedor> proveedores = new ArrayList<>();

        try {
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery("select * from proveedores order by codigo");

            while (resultado.next()) {
                Proveedor proveedor = new Proveedor(resultado.getInt("codigo"),
                        resultado.getString("nombre"),
                        resultado.getString("rubro"),
                        resultado.getString("telefono"),
                        resultado.getString("correoElectronico"),
                        resultado.getString("direccion"));

                proveedores.add(proveedor);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return proveedores;
    }

    public ArrayList<Usuario> obtenerUsuarios() {

        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery("select * from usuarios order by usuario");

            while (resultado.next()) {
                Usuario usuario = new Usuario(resultado.getString("usuario"),
                        resultado.getString("contraseña"));

                usuarios.add(usuario);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return usuarios;

    }

    public ArrayList<Venta> obtenerVenta() {
        ArrayList<Venta> ventas = new ArrayList<>();

        try {
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery("select * from venta order by id");

            while (resultado.next()) {
                Venta venta = new Venta(resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getString("marca"),
                        resultado.getInt("cantidad"),
                        resultado.getDouble("precioUnidad"),
                        resultado.getInt("descuento"),
                        resultado.getInt("subtotal")
                        );

                ventas.add(venta);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ventas;

    }

    public void agregarVenta(Venta venta) {

        try {
            PreparedStatement statement = conexion.prepareStatement("insert into venta (nombre, marca, cantidad, precioUnidad, descuento, subtotal) values (?, ?, ?, ?, ?, ?)");

            statement.setString(1, venta.getNombre());
            statement.setString(2, venta.getMarca());
            statement.setInt(3, venta.getCantidad());
            statement.setDouble(4, venta.getPrecioUnidad());
            statement.setInt(5, venta.getDescuento());
            statement.setDouble(6, venta.getSubtotal());

            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void eliminarVenta(int id) {
        PreparedStatement statement;

        try {
            statement = conexion.prepareStatement("delete from venta where id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void eliminarVentas(){
        PreparedStatement statement;

        try {
            statement = conexion.prepareStatement("delete from venta");
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    
    public ArrayList<Producto> obtenerProductosFiltrados(String precio, String rubro) {
        ArrayList<Producto> productos = new ArrayList<>();
        String orderBy = "";
        
        if (rubro.equals("Todos")) {
            rubro = "";
        }
        else {
            rubro = "WHERE rubro = '"+rubro+"'";
        }
        
        if (precio.equals("Todos")) {
            precio = "";
        }
        else if (precio.equals("Mayor Precio")) {
            precio = " precio DESC";
            orderBy = "ORDER BY";
        }
        else if (precio.equals("Menor Precio")) {
            precio = " precio ASC";
            orderBy = "ORDER BY";
        }
        
        //System.out.println("select * from productos "+ rubro + orderBy + precio + cantidad);
        
        try {
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery(
                    "select * from productos "+ rubro + orderBy + precio);

            while (resultado.next()) {
                Producto producto = new Producto(resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getString("marca"),
                        resultado.getString("rubro"),
                        resultado.getDouble("precio"),
                        resultado.getInt("cantidad"));

                productos.add(producto);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return productos;
    }
     public ArrayList<Proveedor> obtenerProveedoresFiltrados(String rubro) {
        ArrayList<Proveedor> proveedores = new ArrayList<>();
        
        if (rubro.equals("Todos")) {
            rubro = "";
        }
        else {
            rubro = "WHERE rubro = '"+rubro+"'";
        }
        
        //System.out.println("select * from productos "+ rubro + orderBy + precio + cantidad);
        
        try {
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery(
                    "select * from proveedores "+ rubro);

            while (resultado.next()) {
                Proveedor proveedor = new Proveedor(resultado.getInt("codigo"),
                        resultado.getString("nombre"),
                        resultado.getString("rubro"),
                        resultado.getString("telefono"),
                        resultado.getString("correoElectronico"),
                        resultado.getString("direccion"));

                proveedores.add(proveedor);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return proveedores;
    }

    public ArrayList<String> obtenerFiltrosProveedores() {
        ArrayList<String> filtros = new ArrayList<>();
        
        try {
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery(
                    "select distinct rubro from proveedores");

            while (resultado.next()) {
                String filtro;
                
                filtro = resultado.getString("rubro");

                filtros.add(filtro);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return filtros;
    }
}
