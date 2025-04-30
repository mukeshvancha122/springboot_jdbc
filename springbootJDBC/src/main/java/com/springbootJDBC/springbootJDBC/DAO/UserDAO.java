package com.springbootJDBC.springbootJDBC.DAO;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.springbootJDBC.springbootJDBC.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
@Repository
public class UserDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Boolean insertUser(User user) {
        boolean status=false;
        try {
            String insertUserSqlQuery="INSERT INTO users(email,gender,city,name) values(?,?,?,?)";
            int count=jdbcTemplate.update(insertUserSqlQuery,user.getEmail(),user.getGender(),user.getCity(),user.getName());
            status= (count >0) ? true: false;

        } catch (DataAccessException e) {
            status=false;
            e.printStackTrace();
        }

        return status;
    }

    public Boolean updateUser(User user, String email){
        boolean status=false;
        try{
            String updateQuery="UPDATE users set email=?,name=?,gender=?,city=? where email=?";
            int count=jdbcTemplate.update(updateQuery, user.getEmail(),user.getName(),user.getGender(),user.getCity(),user.getEmail());
            status= (count >0) ? true: false;
        } catch (Exception e) {
            status=false;
        }
        return status;
    }

    public Boolean deleteUser(String email){
        boolean status= false;
        try{
            String deleteUserQuery="Delete from users where email=?";
            int count= jdbcTemplate.update(deleteUserQuery,email);
            status= (count >0) ? true: false;
        } catch (Exception e) {
            status = false;
        }
        return status;
    }

//    public Boolean getUsers(String email){
//        String selectQuery=jdbcTemplate.query("Select * from users where email=?",email);
//        return jdbcTemplate.query(selectQuery,);
//    }
//
//    public static final class UserRowMapper implements RowMapper<User>{
//
//        public User mapRow(ResultSet rs,int rowNum) throws SQLException{
//            User user = new User();
//        }
//    }
}
