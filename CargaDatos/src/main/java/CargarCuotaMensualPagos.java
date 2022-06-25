import java.sql.*;
import java.time.LocalDate;

public class CargarCuotaMensualPagos {

    public static void main(String[] args) throws SQLException {
        CargarCuotaMensualPagos.cargar();
    }

    public static void cargar() throws SQLException {
        Connection con = Conector.getConn();
        for(int g=103; g<108; g++) {
            LocalDate fechaBase = fechaRandom(365);
            int pago = 500*(1+(int)(Math.random()*4));
            LocalDate fechaRen = fechaBase.plusDays(30);
            boolean dejoDePagar = false;
            int deudaGrupo = 0;
            while(fechaRen.isBefore(LocalDate.now())){
                int id_cuota = crearCuotaMensual(con, g, fechaRen);
                if(!dejoDePagar){
                    crearPagoCuota(con, fechaRen, pago, 'C', g, id_cuota);
                    dejoDePagar = Math.random() > 0.95;
                }else{
                    deudaGrupo += pago;
                }
                fechaRen = fechaRen.plusDays(30);
            }
            Statement st = con.createStatement();
            st.execute("UPDATE \"Grupo_Familiar\" SET " +
                    "deuda_cuota_social = "+deudaGrupo+" WHERE " +
                    "cod_base = "+g+";");
        }
    }

    public static int crearCuotaMensual(Connection c,
                                         int cod_base,
                                         LocalDate fechaVigencia) throws SQLException {
        PreparedStatement st = c.prepareStatement("INSERT INTO \"Cuota_Mensual\" " +
                "(cod_base, fecha_vigencia) VALUES " +
                "(?, ?) RETURNING id_cuota_mensual;");
        st.setInt(1, cod_base);
        st.setDate(2, Date.valueOf(fechaVigencia));
        ResultSet rs = st.executeQuery();
        if(rs.next()){
            return rs.getInt("id_cuota_mensual");
        }else throw new SQLException();
    }

    public static LocalDate fechaRandom(int diasAtras){
        LocalDate fechaBase = LocalDate.now().minusDays(diasAtras);
        return fechaBase.plusDays((int)(Math.random()*diasAtras));
    }

    public static void crearPagoCuota(Connection c,
                                      LocalDate fecha,
                                      int monto_pago,
                                      char tipo_pago,
                                      int cod_base,
                                      int id_cuota_mensual) throws SQLException {

        PreparedStatement st = c.prepareStatement("INSERT INTO \"Pago\" " +
                "(fecha, monto_pago, tipo_pago) VALUES " +
                "(?, ?, 'C') RETURNING nro_comprobante;");
        st.setDate(1, Date.valueOf(fecha));
        st.setInt(2, monto_pago);
        ResultSet rs = st.executeQuery();

        if(rs.next()){
            int nro_comp = rs.getInt("nro_comprobante");
            PreparedStatement pc = c.prepareStatement("INSERT INTO \"Pago_Cuota\"" +
                    " (nro_comprobante, cod_base, id_cuota_mensual) VALUES " +
                    "(?, ?, ?);");
            pc.setInt(1, nro_comp);
            pc.setInt(2, cod_base);
            pc.setInt(3, id_cuota_mensual);
            pc.execute();
        }else throw new SQLException("Nose q paso xd");
    }
}
