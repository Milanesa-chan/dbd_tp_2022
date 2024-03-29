import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CargarTurnos {

    public static void main(String[] args) throws SQLException {
        cargarTurnos();
    }

    public static void cargarTurnos() throws SQLException {
        Statement stmt = Conector.getConn().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM \"Puede_Desarrollarse_En\";");
        int act, zona;
        String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};
        String sql = "";
        int hora, peri;
        String dia;
        ArrayList<Turno> turnosUsados = new ArrayList<>();

        while (rs.next()) {
            act = rs.getInt(1);
            zona = rs.getInt(2);
            hora = 7 + (int) (Math.random() * 15);
            peri = 1 + (int) Math.round(Math.random());
            dia = dias[(int) (Math.random() * 6)];
            Turno t = new Turno(hora, zona, peri, dia);

            if (!turnosUsados.contains(t)) {
                sql = sql.concat("INSERT INTO \"Turno\" (periodo, id_actividad, id_zona, hora, dia)" +
                        " VALUES ("+peri+","+act+","+zona+","+hora+ ",'" +dia+ "');\n");
                turnosUsados.add(t);
            }
        }

        System.out.println(sql);
        Statement stmtFinal = Conector.getConn().createStatement();
        stmtFinal.execute(sql);
    }

    public static boolean existeTurno(Turno t) throws SQLException {
        PreparedStatement stmt = Conector.getConn().prepareStatement("SELECT * FROM \"Turno\" WHERE periodo=? AND id_zona=? AND " +
                "hora=? AND dia=?;");
        stmt.setInt(1, t.peri);
        stmt.setInt(2, t.zona);

        stmt.setInt(3, t.hora);
        stmt.setString(4, t.dia);

        return stmt.execute();
    }

}

class Turno {
    public int hora, zona, peri;
    public String dia;

    public Turno(int hora, int zona, int peri, String dia) {
        this.hora = hora;
        this.zona = zona;
        this.peri = peri;
        this.dia = dia;
    }
}
