package me.jae57.woodywoody.repository;

import me.jae57.woodywoody.exception.FamilyNotFoundException;
import me.jae57.woodywoody.exception.ScentNotFoundException;
import me.jae57.woodywoody.model.Family;
import me.jae57.woodywoody.model.Scent;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FamilyRepository {

    private final JdbcTemplate jdbcTemplate;

    public FamilyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getFamilyId(String familyName) {
        String query = "SELECT family_id FROM family WHERE family_name=?";
        try {
            return jdbcTemplate.queryForObject(query, int.class, familyName);
        } catch (EmptyResultDataAccessException e) {
            throw new FamilyNotFoundException("family not found");
        }
    }

    public Family getFamilyById(int familyId) {
        String query = "SELECT * FROM family WHERE family_id=?";
        return jdbcTemplate.queryForObject(query, (result, rowNum) -> Family
                        .builder()
                        .familyId(result.getInt("family_id"))
                        .familyName(result.getString("family_name"))
                        .familyKorName(result.getString("family_kor_name"))
                        .explanation(result.getString("explanation"))
                        .build()
                , familyId);
    }

    public int getCountByFamilyId(int familyId) {
        String query = "SELECT count(*) FROM family WHERE family_id=?";
        return jdbcTemplate.queryForObject(query, int.class, familyId);
    }

}
