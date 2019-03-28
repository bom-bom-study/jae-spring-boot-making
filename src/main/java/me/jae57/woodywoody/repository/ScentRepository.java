package me.jae57.woodywoody.repository;

import me.jae57.woodywoody.model.Scent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScentRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Scent> getAllScents() {
        return jdbcTemplate.query("select * from scent", (result, rowNum) -> Scent
                .builder()
                .scentId(result.getLong("scent_id"))
                .scentName(result.getString("scent_name"))
                .scentKorName(result.getString("scent_kor_name"))
                .brand(result.getString("brand"))
                .fragrance(result.getString("fragrance"))
                .build());
    }

    public Scent getScent(Long scentId){
        String query = "SELECT * FROM scent WHERE scent_id=?";
        return jdbcTemplate.queryForObject(query, (result,rowNum)-> Scent
                .builder()
                .scentId(result.getLong("scent_id"))
                .scentName(result.getString("scent_name"))
                .scentKorName(result.getString("scent_kor_name"))
                .brand(result.getString("brand"))
                .fragrance(result.getString("fragrance"))
                .build()
                ,scentId);
    }

    public int getScentByName(String scentName){
        String query = "SELECT count(*) FROM scent WHERE scent_name=?";
        return jdbcTemplate.queryForObject(query, int.class, scentName);
    }

    public Long getScentId(String scentName) {
        String query = "SELECT scent_id FROM scent WHERE scent_name=?";
        return jdbcTemplate.queryForObject(query, long.class, scentName);
    }

    public int addScent(Scent scent) {
        String query = "INSERT INTO scent(scent_name, scent_kor_name, brand, fragrance ) VALUES(?,?,?,?)";
        return jdbcTemplate.update(query,
                scent.getScentName(),
                scent.getScentKorName(),
                scent.getBrand(),
                scent.getFragrance());
    }

    public int updateScent(Long scentId, Scent scent) {
        String query = "UPDATE scent SET scent_name=?,scent_kor_name=?,brand=?,fragrance=? WHERE scent_id=?";
        return jdbcTemplate.update(query,
                scent.getScentName(),
                scent.getScentKorName(),
                scent.getBrand(),
                scent.getFragrance()
                ,scentId);
    }

    public int deleteScent(Long scentId) {
        String query = "DELETE FROM scent WHERE scent_id=?";
        return jdbcTemplate.update(query, scentId);
    }

    // test용
    public int getCount() {
        String query = "SELECT COUNT(*) FROM scent";
        return jdbcTemplate.queryForObject(query, int.class);
    }

    public int deleteAll() {
        String query = "DELETE FROM scent WHERE scent_id > 0;";
        return jdbcTemplate.update(query);
    }

    public void initializeId() {
        String query = "ALTER TABLE scent AUTO_INCREMENT = 1;";
        jdbcTemplate.update(query);
    }

}
