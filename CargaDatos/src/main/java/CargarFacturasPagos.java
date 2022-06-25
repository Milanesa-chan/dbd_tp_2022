import java.sql.*;
import java.time.LocalDate;

public class CargarFacturasPagos {

    public static void main(String[] args) throws SQLException {
        CargarFacturasPagos.cargar();
    }

    public static void cargar() throws SQLException {
        Connection con = Conector.getConn();
        ResultSet insc = inscripcionesAranceladasActivas(con);
        while(insc.next()) {
            int cod_base = insc.getInt("cod_base");
            int id_socio = insc.getInt("id_socio");
            LocalDate fechaBase = insc.getDate("fecha_inscr").toLocalDate();
            int pago = 500*(1+(int)(Math.random()*4));
            int periodo_reno = 15*(2+(int)(Math.random()*3));
            LocalDate fechaRen = fechaBase.plusDays(periodo_reno);
            boolean dejoDePagar = false;
            int deudaSocio = 0;
            while(fechaRen.isBefore(LocalDate.now())){
                int id_factura = crearFactura(con, cod_base, id_socio);
                if(!dejoDePagar){
                    crearPagoFactura(con, fechaRen, pago, 'F', id_factura);
                    dejoDePagar = Math.random() > 0.95;
                }else{
                    deudaSocio += pago;
                }
                fechaRen = fechaRen.plusDays(periodo_reno);
            }
            Statement st = con.createStatement();
            String sqlUpdate = "UPDATE \"Socio\" SET " +
                    "deuda_socio = "+deudaSocio+" WHERE " +
                    "cod_base = "+cod_base+" AND " +
                    "id_socio = "+id_socio+";";
            System.out.println(sqlUpdate);
            st.execute(sqlUpdate);
        }
    }

    public static int crearFactura(Connection c,
                                        int cod_base,
                                        int id_socio) throws SQLException {
        PreparedStatement st = c.prepareStatement("INSERT INTO \"Factura\" " +
                "(cod_base, id_socio) VALUES " +
                "(?, ?) RETURNING id_factura;");
        st.setInt(1, cod_base);
        st.setInt(2, id_socio);

        String sql = "INSERT INTO \"Factura\" " +
                "(cod_base, id_socio) VALUES " +
                "(?, ?) RETURNING id_factura;";
        System.out.println("SQL: "+sql+" <Parametros> "+cod_base+", "+id_socio);
        ResultSet rs = st.executeQuery();
        if(rs.next()){
            return rs.getInt("id_factura");
        }else throw new SQLException();
    }

    public static LocalDate fechaRandom(int diasAtras){
        LocalDate fechaBase = LocalDate.now().minusDays(diasAtras);
        return fechaBase.plusDays((int)(Math.random()*diasAtras));
    }


    public static void crearPagoFactura(Connection c,
                                      LocalDate fecha,
                                      int monto_pago,
                                      char tipo_pago,
                                      int id_factura) throws SQLException {

        PreparedStatement st = c.prepareStatement("INSERT INTO \"Pago\" " +
                "(fecha, monto_pago, tipo_pago) VALUES " +
                "(?, ?, 'F') RETURNING nro_comprobante;");
        st.setDate(1, Date.valueOf(fecha));
        st.setInt(2, monto_pago);
        String sql = "INSERT INTO \"Pago\" " +
                "(fecha, monto_pago, tipo_pago) VALUES " +
                "(?, ?, 'F') RETURNING nro_comprobante;";
        System.out.println("SQL: "+sql+" <Parametros> "+fecha+", "+monto_pago);
        ResultSet rs = st.executeQuery();

        if(rs.next()){
            int nro_comp = rs.getInt("nro_comprobante");
            PreparedStatement pc = c.prepareStatement("INSERT INTO \"Pago_Factura\"" +
                    " (nro_comprobante, id_factura) VALUES " +
                    "(?, ?);");
            pc.setInt(1, nro_comp);
            pc.setInt(2, id_factura);
            sql = "INSERT INTO \"Pago_Factura\"" +
                    " (nro_comprobante, id_factura) VALUES " +
                    "(?, ?);";
            System.out.println("SQL: "+sql+" <Parametros> "+nro_comp+", "+id_factura);
            pc.execute();
        }else throw new SQLException("Nose q paso xd");
    }

    public static ResultSet inscripcionesAranceladasActivas(Connection c) throws SQLException {
        String sql = "SELECT * FROM \"Se_Inscribe_En\" SIE INNER JOIN\n" +
                "\"Turno\" T ON SIE.id_turno = T.id_turno INNER JOIN\n" +
                "\"Actividad_Arancelada\" A ON T.id_actividad = A.id_actividad INNER JOIN\n" +
                "\"Actividad\" C ON T.id_actividad = C.id_actividad " +
                "WHERE activo=true;";
        Statement st = c.createStatement();
        System.out.println("SQL: "+sql);
        return st.executeQuery(sql);
    }
}
