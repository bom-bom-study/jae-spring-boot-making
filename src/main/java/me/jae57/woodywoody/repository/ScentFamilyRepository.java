package me.jae57.woodywoody.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScentFamilyRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScentFamilyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addScentFamily(Long scentId, int familyId) {
        String query = "INSERT INTO scent_family(scent_id,family_id) VALUES(?,?)";
        return jdbcTemplate.update(query, scentId, familyId);
    }

    public List<Integer> getFamiliesByScentId(Long scentId) {
        String query = "SELECT family_id FROM scent_family WHERE scent_id=?";
        return jdbcTemplate.query(query, (result, rowNum) -> result.getInt("family_id"), scentId);
    }

    public int deleteAllByScentId(Long scentId) {
        String query = "DELETE FROM scent_family WHERE scent_id =?";
        return jdbcTemplate.update(query, scentId);
    }

    public int getCountByScentId(Long scentId) {
        String query = "SELECT count(*) FROM scent_family WHERE scent_id=?";
        return jdbcTemplate.queryForObject(query, int.class, scentId);
    }

    public int getCountByFamilyId(int familyId) {
        String query = "SELECT count(*) FROM scent_family WHERE family_id=?";
        return jdbcTemplate.queryForObject(query, int.class, familyId);
    }

    public List<Long> getScentsByFamilyId(int familyId) {
        String query = "SELECT scent_id FROM scent_family WHERE family_id = ?";
        return jdbcTemplate.query(query, (result, rowNum) -> result.getLong("scent_id"), familyId);
    }
}
