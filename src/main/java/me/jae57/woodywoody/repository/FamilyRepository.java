package me.jae57.woodywoody.repository;

import me.jae57.woodywoody.model.Family;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FamilyRepository {

    private final JdbcTemplate jdbcTemplate;

    public FamilyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getFamilyId(String familyName){
        String query = "SELECT family_id FROM family WHERE family_name=?";
        return jdbcTemplate.queryForObject(query, int.class, familyName);
    }

    public Family getFamilyById(int familyId){
        String query = "SELECT * FROM family WHERE family_id=?";
        return jdbcTemplate.queryForObject(query, (result,rowNum)-> Family
                    .builder()
                    .familyId(result.getInt("family_id"))
                    .familyName(result.getString("family_name"))
                    .familyKorName(result.getString("family_kor_name"))
                    .explanation(result.getString("explanation"))
                    .build()
                    ,familyId);
    }

}
