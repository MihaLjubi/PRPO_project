package jdbc;

import java.sql.*;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UporabnikDaoImpl implements BaseDao {
    private static UporabnikDaoImpl instance;
    private Logger log = Logger.getLogger(String.valueOf(instance));
    private Connection con;

    @Override
    public Connection getConnection() {
        try {
            InitialContext initCtx = new InitialContext();
            DataSource ds = (DataSource) initCtx.lookup("jdbc/SimpleJdbcDS");
            return ds.getConnection();
        } catch (Exception e) {
            log.severe("Ne morem se povezati: " + e.getMessage());
        }
        return null;
    }

    private Uporabnik getUporabnikFromRS(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String ime = rs.getString("ime");
        String priimek = rs.getString("priimek");
        String uporabniskoIme = rs.getString("uporabniskoime");
        Uporabnik user = new Uporabnik(ime, priimek, uporabniskoIme);
        user.setId(id);
        return user;
    }

    @Override
    public Entiteta vrni(int id) {

        PreparedStatement ps = null;

        try {
            if (con == null) {
                con = getConnection();
            }

            String sql = "SELECT * FROM uporabnik WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return getUporabnikFromRS(rs);
            } else {
                log.info("Uporabnik ne obstaja");
            }
        } catch (SQLException e) {
            log.severe(e.toString());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    log.severe(e.toString());
                }
            }
        }
        return null;
    }

    @Override
    public void vstavi(Entiteta ent) {
        PreparedStatement ps = null;
        Uporabnik user = (Uporabnik) ent;

        try {
            if (con == null) {
                con = getConnection();
            }

            String sql = "INSERT INTO uporabnik (ime, priimek, uporabniskoIme) VALUES (?, ?, ?)";
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getIme());
            ps.setString(2, user.getPriimek());
            ps.setString(3, user.getUporabniskoIme());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                user.setId(id);
            } else {
                System.out.println("napaka pri vstavi");
            }
        } catch (Exception e) {
            log.severe("vstavi: " + e.toString());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    log.severe(e.toString());
                }
            }
        }
    }

    @Override
    public void odstrani(int id) {
        PreparedStatement ps = null;

        try {
            if (con == null) {
                con = getConnection();
            }

            String sql = "DELETE FROM uporabnik WHERE id = ?";
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            log.severe(e.toString());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    log.severe(e.toString());
                }
            }
        }
    }

    @Override
    public void posodobi(Entiteta ent) {
        PreparedStatement ps = null;
        Uporabnik user = (Uporabnik) ent;

        try {
            if (con == null) {
                con = getConnection();
            }

            String sql = "UPDATE uporabnik SET ime = ?, priimek = ?, uporabniskoIme = ? WHERE id  = ?";
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getIme());
            ps.setString(2, user.getPriimek());
            ps.setString(3, user.getUporabniskoIme());
            ps.setInt(4, user.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            log.severe(e.toString());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    log.severe(e.toString());
                }
            }
        }
    }

    @Override
    public List<Entiteta> vrniVse() {
        List<Entiteta> uporabniki = new ArrayList<Entiteta>();
        PreparedStatement ps = null;

        try {
            if (con == null) {
                con = getConnection();
            }

            String sql = "SELECT * FROM uporabnik";
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                uporabniki.add(getUporabnikFromRS(rs));
            }

        } catch (Exception e) {
            log.severe(e.toString());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    log.severe(e.toString());
                }
            }
        }
        return uporabniki;
    }

}
