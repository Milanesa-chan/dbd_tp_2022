import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class CargarArancel {

    public static void main(String[] args) throws SQLException {
        CargarArancel.cargar();
    }

    public static void cargar() throws SQLException {
        /*LocalDate i = Date.valueOf("2022-02-02").toLocalDate();
        LocalDate n = siguienteFecha(i, 45);
        System.out.println(n);*/

        Connection c = Conector.getConn();
        ResultSet inscs = inscripcionesAranceladas(c);
        while(inscs.next()){
            String desc = "Arancel "+inscs.getString("descripcion");
            int periodo = inscs.getInt("periodo_pago");
            int cod_base = inscs.getInt("cod_base");
            int id_socio = inscs.getInt("id_socio");
            int id_turno = inscs.getInt("id_turno");
            LocalDate fecha_inscr = inscs.getDate("fecha_inscr").toLocalDate();
            LocalDate renov = siguienteFecha(fecha_inscr, periodo);

            PreparedStatement st = c.prepareStatement("INSERT INTO \"Arancel\" " +
                    "(descripcion, fecha_renovacion, cod_base, id_socio, id_turno, fecha_inscr) VALUES " +
                    "(?, ?, ?, ?, ?, ?);");
            st.setString(1, desc);
            st.setDate(2, Date.valueOf(renov));
            st.setInt(3, cod_base);
            st.setInt(4, id_socio);
            st.setInt(5, id_turno);
            st.setDate(6, Date.valueOf(fecha_inscr));
            st.execute();
        }
    }

    public static ResultSet inscripcionesAranceladas(Connection c) throws SQLException {
        String sql = "SELECT * FROM \"Se_Inscribe_En\" SIE INNER JOIN\n" +
                "\"Turno\" T ON SIE.id_turno = T.id_turno INNER JOIN\n" +
                "\"Actividad_Arancelada\" A ON T.id_actividad = A.id_actividad INNER JOIN\n" +
                "\"Actividad\" C ON T.id_actividad = C.id_actividad;";
        Statement st = c.createStatement();
        return st.executeQuery(sql);
    }

    public static LocalDate siguienteFecha(LocalDate fechaInscr, int periodo){
        LocalDate fechaRen = fechaInscr;
        while((fechaRen = fechaRen.plus(periodo, ChronoUnit.DAYS)).isBefore(LocalDate.now()));
        System.out.println("Inscr: "+fechaInscr+" Periodo: "+periodo+" Nueva: "+fechaRen);
        return fechaRen;
    }
}
