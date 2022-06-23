import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CargarACargoDe {

    public static void main(String[] args) throws SQLException {
        cargar();
    }

    public static void cargar() throws SQLException {
        long tiempoStart = System.currentTimeMillis();
        Connection con = Conector.getConn();
        ResultSet turnos = obtenerTurnos(con);
        int id_turno, id_act, id_prof, cont = 0, contRep = 0;
        ArrayList<Integer> profsCap;

        while(turnos.next()){
            id_turno = turnos.getInt("id_turno");
            id_act = turnos.getInt("id_actividad");
            profsCap = profesionalesCapacitadosPara(con, id_act);
            id_prof = profsCap.get((int) (Math.random()*profsCap.size()));

            if(profYaEstaACargo(con, id_prof, id_turno))
                contRep++;
            else {
                agregarCargo(con, id_prof, id_turno);
                cont++;
            }
        }

        long tiempoTrans = System.currentTimeMillis() - tiempoStart;
        System.out.println("Se cargaron "+cont+" filas, se hallaron "+
                contRep+" repetidas. La carga tomo "+tiempoTrans+" ms.");
    }

    public static ResultSet obtenerTurnos(Connection c) throws SQLException {
        Statement stmtTurnos = c.createStatement();
        return stmtTurnos.executeQuery("SELECT * FROM \"Turno\";");
    }

    public static ArrayList<Integer> profesionalesCapacitadosPara(Connection c, int id_actividad) throws SQLException {
        ArrayList<Integer> profs = new ArrayList<>();
        Statement stmtProfs = c.createStatement();
        ResultSet rsProfs = stmtProfs.executeQuery("SELECT id_profesional FROM \"Capacitado_Para\"" +
                " WHERE id_actividad="+id_actividad+";");
        while(rsProfs.next()){
            profs.add(rsProfs.getInt("id_profesional"));
        }
        return profs;
    }

    public static boolean profYaEstaACargo(Connection c, int id_prof, int id_turno) throws SQLException {
        Statement stmtCargo = c.createStatement();
        ResultSet rs = stmtCargo.executeQuery("SELECT * FROM \"A_Cargo_De\" WHERE " +
                "id_profesional="+id_prof+" AND id_turno="+id_turno+";");
        return rs.next();
    }

    public static void agregarCargo(Connection c, int id_prof, int id_turno) throws SQLException {
        Statement stmtCarga = c.createStatement();
        stmtCarga.execute("INSERT INTO \"A_Cargo_De\" (id_profesional, id_turno) " +
                "VALUES ("+id_prof+", "+id_turno+");");
        System.out.println("Se puso al profesional "+id_prof+" a cargo del turno "+id_turno);
    }
}
