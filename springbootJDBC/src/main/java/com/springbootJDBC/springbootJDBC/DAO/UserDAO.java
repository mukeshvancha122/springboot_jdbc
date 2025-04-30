package com.springbootJDBC.springbootJDBC.DAO;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.springbootJDBC.springbootJDBC.entity.User;
import org.springframework.dao.DataAccessException;
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
}
