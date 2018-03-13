package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final String DEPARTMENT_ID = "departmentId";
    public static final String DEPARTMENT_NAME = "departmentName";
    public static final String DESCRIPTION = "description";

    @Value("${department.select}")
    private String GET_DEPARTMENT_SQL;
    @Value("${department.selectById}")
    private String GET_DEPARTMENT_BY_ID_SQL;
    @Value("${department.selectByName}")
    private String GET_DEPARTMENT_BY_NAME_SQL;
    @Value("${department.checkDepartment}")
    private String CHECK_DEPARTMENT_SQL;
    @Value("${department.insert}")
    private String INSERT_DEPARTMENT_SQL;
    @Value("${department.update}")
    private String UPDATE_DEPARTMENT_SQL;
    @Value("${department.delete}")
    private String DELETE_DEPARTMENT_SQL;


    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {

        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

    }


    @Override
    public List<Department> getDepartments() {

        LOGGER.debug("getDepartments()");

        List<Department> departments = namedParameterJdbcTemplate.getJdbcOperations()
                .query(GET_DEPARTMENT_SQL, new DepartmentRowMapper());

        return departments;

    }

    @Override
    public Department getDepartmentById(Integer departmentId) {

        LOGGER.debug("getDepartmentById({})", departmentId);

        SqlParameterSource namedParameters =
                new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
        Department department =
                namedParameterJdbcTemplate.queryForObject(GET_DEPARTMENT_BY_ID_SQL,
                        namedParameters,
                        new DepartmentRowMapper());

        return department;

    }

    @Override
    public Department getDepartmentByName(String departmentName) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue(DEPARTMENT_NAME, departmentName);

        Department department = namedParameterJdbcTemplate.queryForObject(GET_DEPARTMENT_BY_NAME_SQL,
                mapSqlParameterSource,
                new DepartmentRowMapper());

        return department;

    }

    @Override
    public Department addDepartment(Department department) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue(DEPARTMENT_NAME, department.getDepartmentName());

        Integer result =
                namedParameterJdbcTemplate.queryForObject(CHECK_DEPARTMENT_SQL, mapSqlParameterSource, Integer.class);

        LOGGER.debug("result({})", result);

        if (result == 0) {

            mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue(DEPARTMENT_NAME, department.getDepartmentName());
            mapSqlParameterSource.addValue(DESCRIPTION, department.getDescription());

            KeyHolder generateKeyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(INSERT_DEPARTMENT_SQL, mapSqlParameterSource, generateKeyHolder);
            department.setDepartmentId((Integer) generateKeyHolder.getKey());

        } else {

            throw new IllegalArgumentException("Department with the same values already exist.");
        }

        return department;

    }

    @Override
    public void updateDepartment(Department department) {

        SqlParameterSource named_parameter = new BeanPropertySqlParameterSource(department);
        namedParameterJdbcTemplate.update(UPDATE_DEPARTMENT_SQL, named_parameter);

    }

    @Override
    public void deleteDepartment(Integer id) {

        namedParameterJdbcTemplate.getJdbcOperations().update(DELETE_DEPARTMENT_SQL, id);
    }

    private class DepartmentRowMapper implements RowMapper<Department> {

        @Override
        public Department mapRow(ResultSet resultSet, int i) throws SQLException {

            Department department = new Department();

            department.setDepartmentId(resultSet.getInt(DEPARTMENT_ID));
            department.setDepartmentName(resultSet.getString(DEPARTMENT_NAME));
            department.setDescription(resultSet.getString(DESCRIPTION));

            return department;
        }

    }
}
