package learn.guidr.data;

import learn.guidr.data.mappers.AppUserMapper;
import learn.guidr.models.AppUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;

@Repository
public class AppUserJdbcTemplateRepository implements AppUserRepository {
    private final JdbcTemplate jdbcTemplate;
    public AppUserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    @Transactional
    public AppUser findByUsername(String username) {
        List<String> roles = getRolesByUsername(username);
        final String sql = "select user_id, username, password_hash, disabled "
                + "from User "
                + "where username = ?;";
        return jdbcTemplate.query(sql, new AppUserMapper(roles), username)
                .stream()
                .findFirst().orElse(null);
    }
    @Override
    @Transactional
    public AppUser create(AppUser user) {
        final String sql = "insert into User (username, password_hash) values (?, ?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            return ps;
        }, keyHolder);
        if (rowsAffected <= 0) {
            return null;
        }
        user.setAppUserId(keyHolder.getKey().intValue());
        updateRoles(user);
        return user;
    }
    @Override
    @Transactional
    public void update(AppUser user) {
        final String sql = "update User set "
                + "username = ?, "
                + "disabled = ? "
                + "where user_id = ?";
        jdbcTemplate.update(sql,
                user.getUsername(), !user.isEnabled(), user.getAppUserId());
        updateRoles(user);
    }
    private void updateRoles(AppUser user) {
        jdbcTemplate.update("delete from user_role where user_id = ?;", user.getAppUserId());
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        if (authorities == null) {
            return;
        }
        for (String role : AppUser.convertAuthoritiesToRoles(authorities)) {
            String sql = "insert into user_role (user_id, role_id) "
                    + "select ?, role_id from Role where `name` = ?;";
            jdbcTemplate.update(sql, user.getAppUserId(), role);
        }
    }
    private List<String> getRolesByUsername(String username) {
        final String sql = "select r.name "
                + "from user_role ur "
                + "inner join Role r on ur.role_id = r.role_id "
                + "inner join User u on ur.user_id = u.user_id "
                + "where u.username = ?";
        return jdbcTemplate.query(sql, (rs, rowId) -> rs.getString("name"), username);
    }
}
