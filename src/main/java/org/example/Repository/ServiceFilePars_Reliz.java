package org.example.Repository;

import lombok.AllArgsConstructor;
import org.example.util.Measurement;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class ServiceFilePars_Reliz {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void save(Measurement measurement) {
        String sql =
                "insert into measurement.test (Id,Ia,Ib,Ic) " +
                        "SETTINGS async_insert=1, wait_for_async_insert=0 " +
                        "values (:Id, :Ia, :Ib, :Ic);";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("Id", measurement.getId());
        parameterSource.addValue("Ia", measurement.getIa());
        parameterSource.addValue("Ib", measurement.getIb());
        parameterSource.addValue("Ic", measurement.getIc());
        namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    public List<Measurement> findAll() {
        return namedParameterJdbcTemplate.query("select * from measurement.test", new MeasurementsMappaer());
    }

    public static class MeasurementsMappaer implements RowMapper<Measurement> {
        @Override
        public Measurement mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Measurement(rs.getInt("Id"), rs.getDouble("Ia"),
                    rs.getDouble("Ib"), rs.getDouble("Ic"));
        }
    }
}
