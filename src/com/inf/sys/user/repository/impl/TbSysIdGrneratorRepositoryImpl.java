package com.inf.sys.user.repository.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.inf.sys.user.repository.TbSysIdGrneratorRepository;

//生成唯一id
@Repository
public class TbSysIdGrneratorRepositoryImpl  implements TbSysIdGrneratorRepository {

	 @Resource
	 private JdbcTemplate jdbcTemplate;
	
	public Long getTbSysIdGeneratorId() {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String sql = "REPLACE INTO tb_sys_id_generator(GENER_VALUE) values('UNIQUEID')";
        jdbcTemplate.update(new PreparedStatementCreator()
        {
            public PreparedStatement createPreparedStatement(Connection con)
                throws SQLException
            {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                return ps;
            }
        }, keyHolder);
        List<Map<String, Object>> keys = keyHolder.getKeyList();
        if (keys.size() > 1)
        {
            Object[] vs = keys.get(0).values().toArray();
            Object[] vs2 = keys.get(1).values().toArray();
            Number k1 = (Number)vs[0];
            Number k2 = (Number)vs2[0];
            
            return k1.longValue() < k2.longValue() ? k1.longValue() : k2.longValue();
        }
        else
        {
            return keyHolder.getKey().longValue();
        }
    }
}
